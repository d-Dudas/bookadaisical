import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NegotiatePageComponent } from './negotiate-page.component';

describe('NegotiatePageComponent', () => {
  let component: NegotiatePageComponent;
  let fixture: ComponentFixture<NegotiatePageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NegotiatePageComponent]
    });
    fixture = TestBed.createComponent(NegotiatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
