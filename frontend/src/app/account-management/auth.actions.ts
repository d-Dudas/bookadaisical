import { createAction, props } from '@ngrx/store';
import { User } from './user';

export const login = createAction('[Auth] Login', props<{ user: User }>());
export const logout = createAction('[Auth] Logout');
export const showAuthPopup = createAction('[Auth] ShowAuthPopup');
export const hideAuthPopup = createAction('[Auth] HideAuthPopup');
export const hideLoginPopup = createAction('[Auth] HideLoginPopup');
export const setIntendedPath = createAction('[Auth] SetIntendedPath', props<{ path: string }>());
