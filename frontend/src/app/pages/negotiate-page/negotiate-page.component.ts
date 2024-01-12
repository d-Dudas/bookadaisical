import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
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
          console.log(isAuthenticated);
          if(!isAuthenticated)
          {
            this.router.navigate(['/home']);
          } else {
            this.store.select(selectUser).subscribe((username) => {
              this.initiatorUsername = username!;
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
    });
  }

  private async setupResponderData() {
    this.responderUsername = this.negotiationService.getResponderUsername();
    this.preselectedBookId = this.negotiationService.getStoredBookId();
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

    this.negotiationService.getExistingNegotiation(users).subscribe({
      next: negotiationOfferDto => {
        console.log(negotiationOfferDto)
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
        if(negotiationOfferDto.initiatorSelectedBooks.length > 0 && negotiationOfferDto.responderSelectedBooks.length > 0) this.isAcceptable = true;
        this.anOfferWasMade = true;
      },
      error: () => {}
    });
  }

  private extractSelectedBooks(selectedBooks: string[], items: NegotiateItem[])
  {
    for (let bookId of selectedBooks)
    {
      for(let item of items)
      {
        if(bookId == item.book.id)
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
        console.log("accepted");
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
        console.log("canceled");
      },
      error: (error) => {
        console.log(error);
      }
    })
  }
}
