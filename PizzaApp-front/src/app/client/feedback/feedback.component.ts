import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ClientService} from "../client.service";

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.scss']
})
export class FeedbackComponent implements OnInit, AfterViewInit {

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
  }

  onSubmit() { //todo: implement this in html

  }

  ngAfterViewInit(): void {
  }

}
