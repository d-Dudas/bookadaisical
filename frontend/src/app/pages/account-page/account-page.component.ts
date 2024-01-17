import { Component, ViewChild } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated, selectUser } from 'src/app/account-management/auth.state';
import { Book } from 'src/app/elements/classes/book';
import { User } from 'src/app/elements/classes/user';
import { UserSlim } from 'src/app/elements/classes/userSlim';
import { ExistingNegotiationOfferDto } from 'src/app/elements/interfaces/existing-negotiation-offer-dto';
import { UserChats } from 'src/app/elements/interfaces/user-chats';
import { AccountService } from 'src/app/services/account.service';
import { BookService } from 'src/app/services/book.service';
import { ChatService } from 'src/app/services/chat.service';
import { NegotiationService } from 'src/app/services/negotiation.service';

@Component({
  selector: 'app-account-page',
  templateUrl: './account-page.component.html',
  styleUrls: ['./account-page.component.css'],
})
export class AccountPageComponent {
  public user: User | null = null;
  public visitorUsername: string | null = null;
  public isVisitorTheOwner: boolean = false;
  public isAccountSettingsPopupVisible = false;
  public books: Book[] = [];
  public chats: UserChats | null = null;
  public negotiations: ExistingNegotiationOfferDto[] = [];
  @ViewChild('drawer') drawer!: MatDrawer;
  public chatMateUsername: string | null = null;

  private isChatOpened: boolean = false;

  constructor(private accountService: AccountService,
              private route: ActivatedRoute,
              private router: Router,
              private store: Store,
              private chatService: ChatService,
              private negotiationService: NegotiationService,
              private bookService: BookService)
  {
    const username: string = this.getUserIdFromUrl();
    this.accountService.getUserDetails(username).subscribe({
      next: user => {
        this.user = user;
        this.verifyIfVisitorIsTheOwner();
        this.getUserBooks();
        this.getUserChats();
        this.getUserNegotiations();
      },
      error: () => {
        this.router.navigate(["home"]);
      }
    });
  }

  private getUserChats()
  {
    this.chatService.getUserChats(this.user?.username!).subscribe({
      next: chats => {
        this.chats = chats;
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  private getUserNegotiations()
  {
    this.negotiationService.getUserOngoingNegotiations(this.user?.username!).subscribe({
      next: negotiations => {
        this.negotiations = negotiations;
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  getUserIdFromUrl(): string
  {
    let username: string = "";
    this.route.paramMap.subscribe({
      next: params => {
        const tempUsername: string | null = params.get('userId');
        if(tempUsername !== null)
        {
          username = tempUsername;
        } else {
          this.router.navigate(["/home"]);
        }
      }
    });

    return username;
  }

  verifyIfVisitorIsTheOwner()
  {
    this.store.select(selectIsAuthenticated).subscribe({
      next: (isAuthenticated) => {
        if(isAuthenticated)
        {
          this.store.select(selectUser).subscribe({
            next: (username) => {
              if(username !== null)
              {
                this.isVisitorTheOwner = username == this.user?.username;
                this.visitorUsername = username;
              }
            }
          });
        }
      }
    });
  }

  openAccountSettingsPopup()
  {
    this.isAccountSettingsPopupVisible = true;
  }

  closeAccountSettingsPopup()
  {
    this.isAccountSettingsPopupVisible = false;
  }

  getUserUsername(): string {
    if(this.user !== null)
    {
      return this.user.username;
    }
    return "";
  }

  getVisitorUsername(): string {
    if(this.visitorUsername !== null)
    {
      return this.visitorUsername;
    }
    return "";
  }

  private getUserBooks(): void
  {
    this.bookService.getUserBooks(this.user?.username!).subscribe({
      next: userBooks => {
        this.books = userBooks;
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  public openChatWith(username: string)
  {
    if(this.drawer.opened)
    {
      if(this.chatMateUsername === username)
        this.drawer.toggle();
    } else {
      this.drawer.toggle();
    }
    this.chatMateUsername = username;
  }

  public printNegotiationStatus(status: any): string
  {
    return status.charAt(0) + status.slice(1).toLowerCase();
  }

  public openNegotiationWith(username: string): void
  {
    this.negotiationService.setResponderUsername(username);
    this.router.navigate(['/negotiate']);
  }
}
