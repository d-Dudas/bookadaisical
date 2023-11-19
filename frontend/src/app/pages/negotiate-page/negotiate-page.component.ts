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
  bookId: number | undefined;
  initiatorId: number = -1;
  responderId: number = -1;
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
        this.store.select(selectUser).subscribe((user) => {
          if(user?.id !== undefined)
          {
            this.initiatorId = user?.id;
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
        this.bookId = parseInt(bookIdString, 10);
        this.bookService.getBookOwnerId(this.bookId).subscribe((ownerId) => {
          this.responderId = ownerId;
          this.getResponderBooks();
        });
      }
    });
  }

  getInitiatorBooks(): void {
    if(this.initiatorId === undefined) return;
    this.bookService.getUserBooks(this.initiatorId).subscribe((initiatorBooks) => {
      for (const book of initiatorBooks) {
        this.initiatorItems.push({book, selected: false});
      }
    })
  }

  getResponderBooks(): void {
    if(this.responderId === undefined) return;
    this.bookService.getUserBooks(this.responderId).subscribe((responderBooks) => {
      for (const book of responderBooks) {
        this.responderItems.push({book, selected: book.uniqueId === this.bookId});
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
    let initiatorSelectedBookIds: number[] | boolean = this.getSelectedItemsFrom(this.initiatorItems);
    if(!Array.isArray(initiatorSelectedBookIds)) return;

    if (initiatorSelectedBookIds.length < 1)
    {
      this.setProblem("You can't make an offer without selecting at least one book of yours.");
      return;
    }

    let responderSelectedBookIds: number[] | boolean = this.getSelectedItemsFrom(this.responderItems);
    if(!Array.isArray(responderSelectedBookIds)) return;

    if (responderSelectedBookIds.length < 1)
    {
      this.setProblem("You can't make an offer without selecting at least one book the other part.");
      return;
    }

    let negotiationOffer: NegotiationOfferDto = {
      initiatorId: this.initiatorId,
      responderId: this.responderId,
      initiatorSelectedBooks: initiatorSelectedBookIds,
      responderSelectedBooks: responderSelectedBookIds
    }

    this.negotiationService.sendOffer(negotiationOffer);
  }

  getSelectedItemsFrom(list: NegotiateItem[]): number[] | boolean {
    let returnList: number[] = [];
    for(let item of list)
    {
      if(item.selected)
      {
        if(item.book.uniqueId != undefined)
        {
          returnList.push(item.book.uniqueId);
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
