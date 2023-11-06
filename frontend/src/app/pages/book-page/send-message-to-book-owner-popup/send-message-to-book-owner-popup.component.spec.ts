import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendMessageToBookOwnerPopupComponent } from './send-message-to-book-owner-popup.component';

describe('SendMessageToBookOwnerPopupComponent', () => {
  let component: SendMessageToBookOwnerPopupComponent;
  let fixture: ComponentFixture<SendMessageToBookOwnerPopupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SendMessageToBookOwnerPopupComponent]
    });
    fixture = TestBed.createComponent(SendMessageToBookOwnerPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
