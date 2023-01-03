import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'PizzaApp-front';

  goToLink(url: string) {
    window.open(url,"_blank");
  }
}
