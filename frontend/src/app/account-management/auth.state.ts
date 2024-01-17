import { createFeatureSelector, createSelector } from '@ngrx/store';

export interface AuthState {
  isAuthenticated: boolean;
  username: string | null;
}

export interface AuthPopupState {
  isAuthPopupVisible: boolean;
  isLoginPopupVisible: boolean;
  intendedPath: string | boolean;
}

export interface TokenVerificationStatus {
  verified: boolean;
}

export const initialAuthState: AuthState = {
  isAuthenticated: false,
  username: null
};

export const initialAuthPopupState: AuthPopupState = {
  isAuthPopupVisible: false,
  isLoginPopupVisible: false,
  intendedPath: false
};

export const initialTokenVerificationStatus: TokenVerificationStatus = {
  verified: false
};

const getAuthFeatureState = createFeatureSelector<AuthState>('auth');
const getAuthPopupFeatureState = createFeatureSelector<AuthPopupState>('authPopup');
const getTokenVerificationStatus = createFeatureSelector<TokenVerificationStatus>('tokenVerificationStatus');

export const selectIsAuthenticated = createSelector(
  getAuthFeatureState,
  (state) => state.isAuthenticated
);

export const selectUser = createSelector(
  getAuthFeatureState,
  (state) => state.username
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

export const selectTokenVerificationStatus = createSelector(
  getTokenVerificationStatus,
  (state) => state.verified
);
