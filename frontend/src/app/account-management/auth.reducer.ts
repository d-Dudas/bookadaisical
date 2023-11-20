import { createReducer, on } from '@ngrx/store';
import * as AuthActions from './auth.actions';
import { initialAuthState, initialAuthPopupState } from './auth.state';

export const authReducer = createReducer(
  initialAuthState,
  on(AuthActions.login, (state, { user }) => ({ isAuthenticated: true, user, isAuthPopupVisible: false, isLoginPopupVisible: false })),
  on(AuthActions.logout, (state) => ({ isAuthenticated: false, user: null, isAuthPopupVisible: false, isLoginPopupVisible: false }))
);

export const authPopupReducer = createReducer(
  initialAuthPopupState,
  on(AuthActions.showAuthPopup, (state) => ({isAuthPopupVisible: true, isLoginPopupVisible: true, intendedPath: false})),
  on(AuthActions.hideAuthPopup, (state) => ({isAuthPopupVisible: false, isLoginPopupVisible: false, intendedPath: state.intendedPath})),
  on(AuthActions.hideLoginPopup, (state) => ({isAuthPopupVisible: true, isLoginPopupVisible: false, intendedPath: state.intendedPath})),
  on(AuthActions.setIntendedPath, (state, {path}) => ({isAuthPopupVisible: state.isAuthPopupVisible, isLoginPopupVisible: state.isLoginPopupVisible, intendedPath: path}))
)
