import { createFeatureSelector, createSelector } from '@ngrx/store';
import { User } from './user';

export interface AuthState {
  isAuthenticated: boolean;
  user: User | null;
}

export const initialAuthState: AuthState = {
  isAuthenticated: false,
  user: null,
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
