
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/models/client';
import { ClientsdataService } from 'src/app/services/clientsdata.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit{
  open!: boolean;
  client : Client = new Client();
  userId !: String;
  @Output() modalClient = new EventEmitter();
  constructor(private activeRouter:ActivatedRoute, private clientsdataService:ClientsdataService, private router:Router, private _snackBar:MatSnackBar){}
  ngOnInit(): void {
    this.userId = this.activeRouter.snapshot.params['userId'];
    this.clientsdataService.getUserById(this.userId).subscribe(data => {
      this.client=data});


  }
  updateClient()
  {
    return this.clientsdataService.editClient(this.userId,this.client).subscribe(data=>{
      this.router.navigate(['client']);
      this._snackBar.open('client was updated ', 'ok',{duration: 3000})
      
    })
  }

  
 
}
