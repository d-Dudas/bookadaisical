import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { selectIsAuthenticated, selectUser } from 'src/app/account-management/auth.state';
import { ElementRef, HostListener, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  isAuthenticated: boolean = false;
  username: string = "";
  isNavHidden = false;
  private prevScrollPos = window.scrollY;

  constructor(private store: Store, private el: ElementRef, private renderer: Renderer2){}

  ngOnInit()
  {
    this.store.select(selectIsAuthenticated).subscribe((is) => {this.isAuthenticated = is;});
    this.store.select(selectUser).subscribe((username) => {this.username = username!});
  }

  @HostListener('window:scroll', ['$event'])
  onScroll(event: Event): void {
    const currentScrollPos = window.scrollY;

    if (this.prevScrollPos > currentScrollPos) {
      this.isNavHidden = false;
      this.renderer.removeClass(this.el.nativeElement.querySelector('.navbar-container'), 'hidden');
    } else {
      this.isNavHidden = true;
      this.renderer.addClass(this.el.nativeElement.querySelector('.navbar-container'), 'hidden');
    }

    this.prevScrollPos = currentScrollPos;
  }
  
}
