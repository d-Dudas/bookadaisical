import { createFeatureSelector, createSelector } from '@ngrx/store';
import { User } from './user';

export interface AuthState {
  isAuthenticated: boolean;
  user: User | null;
  isAuthPopupVisible: boolean;
  isLoginPopupVisible: boolean;
} // TODO: remember intended path if login is blocking some route

export const initialAuthState: AuthState = {
  isAuthenticated: false,
  user: null,
  isAuthPopupVisible: false,
  isLoginPopupVisible: false
};

const getAuthFeatureState = createFeatureSelector<AuthState>('auth');

export const selectIsAuthenticated = createSelector(
  getAuthFeatureState,
  (state) => state.isAuthenticated
);

export const selectUser = createSelector(
  getAuthFeatureState,
  (state) => state.user
);

export const selectIsAuthPopupVisible = createSelector(
  getAuthFeatureState,
  (state) => state.isAuthPopupVisible
);

export const selectIsLoginPopupVisible = createSelector(
  getAuthFeatureState,
  (state) => state.isLoginPopupVisible
);
