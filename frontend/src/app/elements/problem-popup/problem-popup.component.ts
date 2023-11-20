import { Component, EventEmitter, Input, Output } from '@angular/core';

export interface Problem {
  exists: boolean,
  message: string
}

@Component({
  selector: 'app-problem-popup',
  templateUrl: './problem-popup.component.html',
  styleUrls: ['./problem-popup.component.css']
})
export class ProblemPopupComponent {
  @Input() message: string;
  @Input() isVisible: boolean = false;
  @Output() closePopupEvenet = new EventEmitter<void>();

  constructor() {
    this.message = "An error occured.";
  }

  closePopup() {
    // this.isVisible = false;
    this.closePopupEvenet.emit();
  }
}
