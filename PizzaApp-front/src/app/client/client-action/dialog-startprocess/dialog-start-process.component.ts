import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {ClientService} from "../../client.service";

@Component({
  selector: 'app-bottom-sheet-start-process',
  templateUrl: './dialog-start-process.component.html',
  styleUrls: ['./dialog-start-process.component.scss']
})
export class DialogStartProcessComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DialogStartProcessComponent>,
              private clientService: ClientService) {}

  ngOnInit(): void {
  }

  startProcess(): void {
    this.clientService.startProcess();
  }

}
