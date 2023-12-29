import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { BookPageComponent } from './pages/book-page/book-page.component';
import { NegotiatePageComponent } from './pages/negotiate-page/negotiate-page.component';
import { AccountPageComponent } from './pages/account-page/account-page.component';
import { AddBookPageComponent } from './pages/add-book-page/add-book-page.component';

const routes: Routes = [
  {path: 'home', component: LandingPageComponent},
  {path: 'search', component: SearchPageComponent},
  {path: 'books/:uniqueId', component: BookPageComponent},
  {path: 'negotiate/:bookId', component: NegotiatePageComponent},
  {path: 'account/:userId', component: AccountPageComponent},
  {path: 'upload-new-book', component: AddBookPageComponent},

  {path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
