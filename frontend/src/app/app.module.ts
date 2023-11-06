import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AuthenticatePopupComponent } from './elements/navbar/authenticate-popup/authenticate-popup.component';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { UserInfoComponent } from './user-info/user-info.component';
import { authReducer } from './account-management/auth.reducer';
import { RegisterPopupComponent } from './elements/navbar/authenticate-popup/register-popup/register-popup.component';
import { LoginPopupComponent } from './elements/navbar/authenticate-popup/login-popup/login-popup.component';
import { NavbarComponent } from './elements/navbar/navbar.component';
import { BookPreviewComponent } from './elements/book-preview/book-preview.component';
import { Book } from './elements/classes/book';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { RootComponent } from './root/root.component';
import { NgxSliderModule } from 'ngx-slider-v2';
import { BookPageComponent } from './pages/book-page/book-page.component';
import { SendMessageToBookOwnerPopupComponent } from './pages/book-page/send-message-to-book-owner-popup/send-message-to-book-owner-popup.component';
import { NegotiatePageComponent } from './pages/negotiate-page/negotiate-page.component';

@NgModule({
  declarations: [
    LandingPageComponent,
    AuthenticatePopupComponent,
    LoginPopupComponent,
    UserInfoComponent,
    RegisterPopupComponent,
    NavbarComponent,
    BookPreviewComponent,
    SearchPageComponent,
    RootComponent,
    BookPageComponent,
    SendMessageToBookOwnerPopupComponent,
    NegotiatePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    StoreModule.forRoot(authReducer),
    StoreModule.forFeature('auth', authReducer),
    EffectsModule.forRoot([]),
    ReactiveFormsModule,
    NgxSliderModule
  ],
  providers: [Book],
  bootstrap: [RootComponent]
})
export class AppModule { }
