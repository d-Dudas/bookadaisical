import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AuthenticatePopupComponent } from './authenticate-popup/authenticate-popup.component';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { UserInfoComponent } from './user-info/user-info.component';
import { authReducer } from './account-management/auth.reducer';
import { RegisterPopupComponent } from './register-popup/register-popup.component';
import { LoginPopupComponent } from './login-popup/login-popup.component';

@NgModule({
  declarations: [
    // AppComponent,
    LandingPageComponent,
    AuthenticatePopupComponent,
    LoginPopupComponent,
    UserInfoComponent,
    RegisterPopupComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    StoreModule.forRoot(authReducer),
    StoreModule.forFeature('auth', authReducer),
    EffectsModule.forRoot([])
  ],
  providers: [],
  bootstrap: [LandingPageComponent]
})
export class AppModule { }
