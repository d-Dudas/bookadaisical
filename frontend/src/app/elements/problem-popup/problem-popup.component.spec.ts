import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemPopupComponent } from './problem-popup.component';

describe('ProblemPopupComponent', () => {
  let component: ProblemPopupComponent;
  let fixture: ComponentFixture<ProblemPopupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProblemPopupComponent]
    });
    fixture = TestBed.createComponent(ProblemPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
