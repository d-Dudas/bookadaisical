import { Component, ViewChild } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { finalize } from 'rxjs/operators';
import { selectIsAuthenticated, selectTokenVerificationStatus, selectUser } from 'src/app/account-management/auth.state';
import { NegotiatingUsersDto } from 'src/app/elements/interfaces/find-existing-negotiation-dto';
import { NegotiateItem } from 'src/app/elements/interfaces/negotiation-item';
import { NegotiationOfferDto } from 'src/app/elements/interfaces/negotiation-offer-dto';
import { Problem } from 'src/app/elements/problem-popup/problem-popup.component';
import { BookService } from 'src/app/services/book.service';
import { NegotiationService } from 'src/app/services/negotiation.service';

@Component({
  selector: 'app-negotiate-page',
  templateUrl: './negotiate-page.component.html',
  styleUrls: ['./negotiate-page.component.css']
})
export class NegotiatePageComponent {
  public initiatorUsername: string = "";
  public responderUsername: string = "";
  public initiatorItems: NegotiateItem[] = [];
  public responderItems: NegotiateItem[] = [];
  public problem: Problem = {
    exists: false,
    message: "An error occured",
  };
  public isAcceptable: boolean = false;
  public anOfferWasMade: boolean = false;
  public isAcceptableByUser: boolean = true;
  public negotiationStatus: string = 'NOT STARTED';
  @ViewChild('drawer') drawer!: MatDrawer;

  private initialInitiatorItems: NegotiateItem[] = [];
  private initialResponderItems: NegotiateItem[] = [];
  private preselectedBookId: string = "";
  private redirectTo: string = '';

  constructor(private store: Store,
              private bookService: BookService,
              private negotiationService: NegotiationService,
              private router: Router)
  {
  }

  async ngOnInit()
  {
    await this.setupInitiatorData();
    await this.setupResponderData();
  }

  private async setupInitiatorData() {
    this.store.select(selectTokenVerificationStatus).subscribe((isVerified) => {
      if(isVerified)
      {
        this.store.select(selectIsAuthenticated).subscribe((isAuthenticated) =>
        {
          if(!isAuthenticated)
          {
            this.router.navigate(['/home']);
          } else {
            this.store.select(selectUser).subscribe((username) => {
              this.initiatorUsername = username!;
              if(this.initiatorUsername === this.responderUsername) this.router.navigate(['/home']);
              this.getInitiatorBooks();
            });
          }
        });
      }
    });
  }
  
  private async getInitiatorBooks() {
    if(this.initiatorUsername === undefined) return;
    this.bookService.getUserBooks(this.initiatorUsername).subscribe((initiatorBooks) => {
      for (const book of initiatorBooks) {
        this.initiatorItems.push({book, selected: false});
      }
      this.findExistingNegotiation();
    });
  }

  private async setupResponderData() {
    this.responderUsername = this.negotiationService.getResponderUsername();
    if(this.initiatorUsername === this.responderUsername) this.router.navigate(['/home']);
    this.getResponderBooks();
  }


  private async getResponderBooks() {
    if(this.responderUsername === undefined) return;
    this.bookService.getUserBooks(this.responderUsername).subscribe((responderBooks) => {
      for (const book of responderBooks) {
        this.responderItems.push({book, selected: book.id === this.preselectedBookId});
      }
      this.findExistingNegotiation();
    });
  }

  private async findExistingNegotiation()
  {
    let users: NegotiatingUsersDto = {
      initiatorUsername: this.initiatorUsername,
      responderUsername: this.responderUsername
    };

    this.negotiationService.getExistingNegotiation(users).pipe(
      finalize(() => {
        for(let item of this.responderItems)
        {
          if(item.book.id === this.preselectedBookId)
          {
            item.selected = true;
          }
        }
      })
    ).subscribe({
      next: negotiationOfferDto => {
        this.negotiationStatus = negotiationOfferDto.status.toString();
        this.extractSelectedBooks(
          negotiationOfferDto.initiatorUsername === this.initiatorUsername ?
          negotiationOfferDto.initiatorSelectedBooks :
          negotiationOfferDto.responderSelectedBooks,
          this.initiatorItems);
        this.initialInitiatorItems = JSON.parse(JSON.stringify(this.initiatorItems));
        this.extractSelectedBooks(
          negotiationOfferDto.responderUsername === this.responderUsername ?
          negotiationOfferDto.responderSelectedBooks :
          negotiationOfferDto.initiatorSelectedBooks,
          this.responderItems);
        this.initialResponderItems = JSON.parse(JSON.stringify(this.responderItems));
        if(negotiationOfferDto.initiatorSelectedBooks.length > 0 &&
           negotiationOfferDto.responderSelectedBooks.length > 0 &&
           (negotiationOfferDto.responderSelectedBooks.includes(this.preselectedBookId) ||
           negotiationOfferDto.initiatorSelectedBooks.includes(this.preselectedBookId) ||
           this.preselectedBookId === ""))
        {
          this.isAcceptable = true;
        }
        if(this.initiatorUsername === negotiationOfferDto.initiatorUsername) this.isAcceptableByUser = false;
        this.anOfferWasMade = true;
      },
      error: () => {
        this.preselectedBookId = this.negotiationService.getStoredBookId();
      }
    });
  }

  private extractSelectedBooks(selectedBooks: string[], items: NegotiateItem[])
  {
    for(let item of items) item.selected = false;
    for (let bookId of selectedBooks)
    {
      for(let item of items)
      {
        if(bookId === item.book.id)
        {
          item.selected = true;
        }
      }
    }
  }

  selectBook(item: NegotiateItem): void {
    item.selected = !item.selected;
    this.isAcceptable = !this.areItemsChanged(this.initiatorItems, this.initialInitiatorItems) &&
                      !this.areItemsChanged(this.responderItems, this.initialResponderItems);
  }

  private areItemsChanged(currentItems: NegotiateItem[], initialItems: NegotiateItem[]): boolean {
    if (currentItems.length !== initialItems.length) return true;
  
    for (let i = 0; i < currentItems.length; i++) {
      if (currentItems[i].book.id !== initialItems[i].book.id || currentItems[i].selected !== initialItems[i].selected) {
        return true;
      }
    }
  
    return false;
  }

  openBookInNewTab(bookId: any) {
    const url = "/books/" + bookId;
    window.open(url, '_blank');
  }

  sendOffer(): void {
    let initiatorSelectedBookIds: string[] | boolean = this.extractSelectedItems(this.initiatorItems);
    if(!Array.isArray(initiatorSelectedBookIds)) return;

    if (initiatorSelectedBookIds.length < 1)
    {
      this.setProblem("You can't make an offer without selecting at least one book of yours.");
      return;
    }

    let responderSelectedBookIds: string[] | boolean = this.extractSelectedItems(this.responderItems);
    if(!Array.isArray(responderSelectedBookIds)) return;

    if (responderSelectedBookIds.length < 1)
    {
      this.setProblem("You can't make an offer without selecting at least one book the other part.");
      return;
    }

    let negotiationOffer: NegotiationOfferDto = {
      initiatorUsername: this.initiatorUsername,
      responderUsername: this.responderUsername,
      initiatorSelectedBooks: initiatorSelectedBookIds,
      responderSelectedBooks: responderSelectedBookIds
    }

    this.negotiationService.sendOffer(negotiationOffer).subscribe({
      next: () => {
        this.setProblem("The offer has been succesffuly sent.");
        this.redirectTo = '/home';
      },
      error: () => {
        this.setProblem("Something went wrong. Please try again later.");
      }
    });
  }

  extractSelectedItems(list: NegotiateItem[]): string[] | boolean {
    let returnList: string[] = [];
    for(let item of list)
    {
      if(item.selected)
      {
        if(item.book.id != undefined)
        {
          returnList.push(item.book.id);
        } else {
          this.setProblem("A problem occured. Please try again later.");
          return false;
        }
      }
    }

    return returnList;
  }

  setProblem(message: string): void {
    this.problem = {
      exists: true,
      message: message
    };
  }

  closeProblemPopup(): void {
    this.problem = {
      exists: false,
      message: "A problem occured."
    }
    if(this.redirectTo != '')
    {
      this.router.navigate([this.redirectTo]);
    }
  }

  public acceptOffer(): void {
    if(!this.isAcceptable) return;
    let users: NegotiatingUsersDto = {
      initiatorUsername: this.initiatorUsername,
      responderUsername: this.responderUsername
    };

    this.negotiationService.acceptOffer(users).subscribe({
      next: () => {
        this.drawer.toggle();
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  public cancelNegotiation(): void {
    let users: NegotiatingUsersDto = {
      initiatorUsername: this.initiatorUsername,
      responderUsername: this.responderUsername
    };

    this.negotiationService.cancelNegotiation(users).subscribe({
      next: () => {
        this.setProblem("The negotiation has been canceled. You will be redirected to the home page");
        this.redirectTo = '/home';
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  public getDefaultMessage(): string {
    return "Hi! I just accepted your negotiation offer.";
  }

  public closeChat() {
    this.drawer.toggle();
  }

  public isAlreadyAccepted() {
    return this.negotiationStatus === 'ACCEPTED';
  }
}
