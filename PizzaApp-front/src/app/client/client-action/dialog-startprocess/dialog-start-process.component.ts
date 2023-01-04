import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {ClientService} from "../../client.service";
import {interval} from "rxjs";

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
    let milliseconds = 950;
    let sub = interval(milliseconds).subscribe(() => {
        this.clientService.startProcess();
        sub.unsubscribe();
    })
    // this.clientService.startProcess();
  }
}
