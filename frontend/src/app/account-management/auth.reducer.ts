import { createReducer, on } from '@ngrx/store';
import * as AuthActions from './auth.actions';
import { initialAuthState } from './auth.state';

export const authReducer = createReducer(
  initialAuthState,
  on(AuthActions.login, (state, { user }) => ({ isAuthenticated: true, user })),
  on(AuthActions.logout, (state) => ({ isAuthenticated: false, user: null }))
);
