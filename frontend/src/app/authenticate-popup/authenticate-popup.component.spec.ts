import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthenticatePopupComponent } from './authenticate-popup.component';

describe('LoginPopupComponent', () => {
  let component: AuthenticatePopupComponent;
  let fixture: ComponentFixture<AuthenticatePopupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AuthenticatePopupComponent]
    });
    fixture = TestBed.createComponent(AuthenticatePopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
