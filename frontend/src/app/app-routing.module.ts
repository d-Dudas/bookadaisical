import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { BookPageComponent } from './pages/book-page/book-page.component';
import { NegotiatePageComponent } from './pages/negotiate-page/negotiate-page.component';

const routes: Routes = [
  {path: 'home', component: LandingPageComponent},
  {path: 'search', component: SearchPageComponent},
  {path: 'books/:uniqueId', component: BookPageComponent},
  {path: 'negotiate/:bookId', component: NegotiatePageComponent},

  {path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
