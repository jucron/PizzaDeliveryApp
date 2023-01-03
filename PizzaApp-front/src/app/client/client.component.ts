import {Component, OnInit} from '@angular/core';
import {ClientService} from "./client.service";

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.scss']
})
export class ClientComponent implements OnInit {
  mainUsername = localStorage.getItem('mainUsername');

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.clientService.executeLogOUT(this.mainUsername)
  }
}
