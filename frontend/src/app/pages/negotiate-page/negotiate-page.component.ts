import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated, selectUser } from 'src/app/account-management/auth.state';
import { Book } from 'src/app/elements/classes/book';
import { Problem } from 'src/app/elements/problem-popup/problem-popup.component';
import { BookService } from 'src/app/services/book.service';
import { NegotiationService, NegotiationOfferDto } from 'src/app/services/negotiation.service';


interface NegotiateItem {
  book: Book,
  selected: boolean
}
@Component({
  selector: 'app-negotiate-page',
  templateUrl: './negotiate-page.component.html',
  styleUrls: ['./negotiate-page.component.css']
})
export class NegotiatePageComponent {
  bookId: string = "";
  initiatorUsername: string = "";
  responderUsername: string = "";
  initiatorItems: NegotiateItem[] = [];
  responderItems: NegotiateItem[] = [];
  problemDetected: boolean = false;
  problemMessage: string = "An error occured";
  problem: Problem = {
    exists: false,
    message: "An error occured"
  };

  constructor(private store: Store,
              private bookService: BookService,
              private negotiationService: NegotiationService,
              private route: ActivatedRoute,
              private router: Router)
  {
    this.setupInitiatorData();
    this.setupResponderData();
  }

  setupInitiatorData(): void {
    this.store.select(selectIsAuthenticated).subscribe((isAuthenticated) =>
    {
      if(isAuthenticated)
      {
        this.store.select(selectUser).subscribe((username) => {
          if(username !== null)
          {
            this.initiatorUsername = username;
            this.getInitiatorBooks();
          }
        });
      }
      else
      {
        // TODO: force login
      }
    });
  }

  setupResponderData(): void {
    this.route.paramMap.subscribe(params => { // TODO: handle error case
      const bookIdString = params.get('bookId');
      if(bookIdString !== null)
      {
        this.bookId = bookIdString;
        this.bookService.getBookOwnerUsername(this.bookId).subscribe((ownerId) => {
          this.responderUsername = ownerId;
          this.getResponderBooks();
        });
      }
    });
  }

  getInitiatorBooks(): void {
    if(this.initiatorUsername === undefined) return;
    this.bookService.getUserBooks(this.initiatorUsername).subscribe((initiatorBooks) => {
      for (const book of initiatorBooks) {
        this.initiatorItems.push({book, selected: false});
      }
    })
  }

  getResponderBooks(): void {
    if(this.responderUsername === undefined) return;
    this.bookService.getUserBooks(this.responderUsername).subscribe((responderBooks) => {
      for (const book of responderBooks) {
        this.responderItems.push({book, selected: book.id === this.bookId});
      }
    })
  }

  selectBook(item: NegotiateItem): void {
    item.selected = !item.selected;
  }

  openBookInNewTab(bookId: any) {
    const url = "/books/" + bookId;
    window.open(url, '_blank');
  }

  sendOffer(): void {
    let initiatorSelectedBookIds: string[] | boolean = this.getSelectedItemsFrom(this.initiatorItems);
    if(!Array.isArray(initiatorSelectedBookIds)) return;

    if (initiatorSelectedBookIds.length < 1)
    {
      this.setProblem("You can't make an offer without selecting at least one book of yours.");
      return;
    }

    let responderSelectedBookIds: string[] | boolean = this.getSelectedItemsFrom(this.responderItems);
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

    this.negotiationService.sendOffer(negotiationOffer);
  }

  getSelectedItemsFrom(list: NegotiateItem[]): string[] | boolean {
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
    }
  }

  closeProblemPopup(): void {
    this.problem = {
      exists: false,
      message: "A problem occured."
    }
  }
}
