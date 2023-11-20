import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountSettingsPopupComponent } from './account-settings-popup.component';

describe('AccountSettingsPopupComponent', () => {
  let component: AccountSettingsPopupComponent;
  let fixture: ComponentFixture<AccountSettingsPopupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AccountSettingsPopupComponent]
    });
    fixture = TestBed.createComponent(AccountSettingsPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
