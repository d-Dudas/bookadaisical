import { createFeatureSelector, createSelector } from '@ngrx/store';
import { UserSlim } from '../elements/classes/userSlim';

export interface AuthState {
  isAuthenticated: boolean;
  user: UserSlim | null;
}

export interface AuthPopupState {
  isAuthPopupVisible: boolean;
  isLoginPopupVisible: boolean;
  intendedPath: string | boolean;
}

export const initialAuthState: AuthState = {
  isAuthenticated: false,
  user: null
};

export const initialAuthPopupState: AuthPopupState = {
  isAuthPopupVisible: false,
  isLoginPopupVisible: false,
  intendedPath: false
}

const getAuthFeatureState = createFeatureSelector<AuthState>('auth');
const getAuthPopupFeatureState = createFeatureSelector<AuthPopupState>('authPopup');

export const selectIsAuthenticated = createSelector(
  getAuthFeatureState,
  (state) => state.isAuthenticated
);

export const selectUser = createSelector(
  getAuthFeatureState,
  (state) => state.user
);

export const selectIsAuthPopupVisible = createSelector(
  getAuthPopupFeatureState,
  (state) => state.isAuthPopupVisible
);

export const selectIsLoginPopupVisible = createSelector(
  getAuthPopupFeatureState,
  (state) => state.isLoginPopupVisible
);

export const selectIntendedpath = createSelector(
  getAuthPopupFeatureState,
  (state) => state.intendedPath
);
