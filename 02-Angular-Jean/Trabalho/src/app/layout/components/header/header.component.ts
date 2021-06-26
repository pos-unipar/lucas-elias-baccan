import { Component, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  constructor() { }

  onNavigate() {
    window.open("https://github.com/pos-unipar/lucas-elias-baccan/tree/master/02-Angular-Jean/Trabalho", "_blank");
  }

}
