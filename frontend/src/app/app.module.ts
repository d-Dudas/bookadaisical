import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AuthenticatePopupComponent } from './elements/navbar/authenticate-popup/authenticate-popup.component';
import { StoreModule } from '@ngrx/store';
import { tokenVerificationStatusReducer, authPopupReducer, authReducer } from './account-management/auth.reducer';
import { RegisterPopupComponent } from './elements/navbar/authenticate-popup/register-popup/register-popup.component';
import { LoginPopupComponent } from './elements/navbar/authenticate-popup/login-popup/login-popup.component';
import { NavbarComponent } from './elements/navbar/navbar.component';
import { BookPreviewComponent } from './elements/book-preview/book-preview.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { RootComponent } from './root/root.component';
import { BookPageComponent } from './pages/book-page/book-page.component';
import { NegotiatePageComponent } from './pages/negotiate-page/negotiate-page.component';
import { ProblemPopupComponent } from './elements/problem-popup/problem-popup.component';
import { AccountPageComponent } from './pages/account-page/account-page.component';
import { AccountSettingsPopupComponent } from './pages/account-page/account-settings-popup/account-settings-popup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { ChatComponent } from './elements/chat/chat.component';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { MatSliderModule } from '@angular/material/slider';
import { MatChipsModule } from '@angular/material/chips';
import { MatToolbarModule } from '@angular/material/toolbar';
import { CarouselModule } from 'primeng/carousel';
import { AddBookPageComponent } from './pages/add-book-page/add-book-page.component';
import { MatStepperModule } from '@angular/material/stepper';
import { MatSelectModule } from '@angular/material/select';
import {
  MatSlideToggleModule,
  _MatSlideToggleRequiredValidatorModule,
} from '@angular/material/slide-toggle';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTabsModule } from '@angular/material/tabs';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { DatePipe } from '@angular/common';
import { MultiSelectModule } from 'primeng/multiselect';

@NgModule({
  declarations: [
    LandingPageComponent,
    AuthenticatePopupComponent,
    LoginPopupComponent,
    RegisterPopupComponent,
    NavbarComponent,
    BookPreviewComponent,
    SearchPageComponent,
    RootComponent,
    BookPageComponent,
    NegotiatePageComponent,
    ProblemPopupComponent,
    AccountPageComponent,
    AccountSettingsPopupComponent,
    ChatComponent,
    AddBookPageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    StoreModule.forRoot(authReducer),
    StoreModule.forFeature('auth', authReducer),
    StoreModule.forFeature('authPopup', authPopupReducer),
    StoreModule.forFeature('tokenVerificationStatus', tokenVerificationStatusReducer),
    ReactiveFormsModule,
    MatSliderModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatChipsModule,
    MatToolbarModule,
    CarouselModule,
    MatStepperModule,
    MatSelectModule,
    MatSlideToggleModule,
    _MatSlideToggleRequiredValidatorModule,
    MatDividerModule,
    MatExpansionModule,
    MatTabsModule,
    MatExpansionModule,
    MatSnackBarModule,
    MultiSelectModule
  ],
  providers: [DatePipe],
  bootstrap: [RootComponent]
})
export class AppModule { }
