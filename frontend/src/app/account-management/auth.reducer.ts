import { createReducer, on } from '@ngrx/store';
import * as AuthActions from './auth.actions';
import { initialAuthState, initialAuthPopupState, initialTokenVerificationStatus } from './auth.state';

export const authReducer = createReducer(
  initialAuthState,
  on(AuthActions.login, (state, { username }) => ({ isAuthenticated: true, username, isAuthPopupVisible: false, isLoginPopupVisible: false })),
  on(AuthActions.logout, (state) => ({ isAuthenticated: false, username: null, isAuthPopupVisible: false, isLoginPopupVisible: false }))
);

export const authPopupReducer = createReducer(
  initialAuthPopupState,
  on(AuthActions.showAuthPopup, (state) => ({isAuthPopupVisible: true, isLoginPopupVisible: true, intendedPath: false})),
  on(AuthActions.hideAuthPopup, (state) => ({isAuthPopupVisible: false, isLoginPopupVisible: false, intendedPath: state.intendedPath})),
  on(AuthActions.hideLoginPopup, (state) => ({isAuthPopupVisible: true, isLoginPopupVisible: false, intendedPath: state.intendedPath})),
  on(AuthActions.setIntendedPath, (state, {path}) => ({isAuthPopupVisible: state.isAuthPopupVisible, isLoginPopupVisible: state.isLoginPopupVisible, intendedPath: path}))
);

export const tokenVerificationStatusReducer = createReducer(
  initialTokenVerificationStatus,
  on(AuthActions.setTokenVerificationStatus, (state, {status}) => ({verified: status}))
);
