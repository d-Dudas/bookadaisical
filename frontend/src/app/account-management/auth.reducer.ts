import { createReducer, on } from '@ngrx/store';
import * as AuthActions from './auth.actions';
import { initialAuthState } from './auth.state';

export const authReducer = createReducer(
  initialAuthState,
  on(AuthActions.login, (state, { user }) => ({ isAuthenticated: true, user, isAuthPopupVisible: false, isLoginPopupVisible: false })),
  on(AuthActions.logout, (state) => ({ isAuthenticated: false, user: null, isAuthPopupVisible: false, isLoginPopupVisible: false })),
  on(AuthActions.hideAuthPopup, (state) => ({isAuthenticated: state.isAuthenticated, user: state.user, isAuthPopupVisible: false, isLoginPopupVisible: false})),
  on(AuthActions.showAuthPopup, (state) => ({isAuthenticated: state.isAuthenticated, user: state.user, isAuthPopupVisible: true, isLoginPopupVisible: true})),
  on(AuthActions.hideLoginPopup, (state) => ({isAuthenticated: state.isAuthenticated, user: state.user, isAuthPopupVisible: true, isLoginPopupVisible: false}))
);
