import { Client } from 'src/app/models/client';
import { ClientsdataService } from './../../services/clientsdata.service';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { TokenService } from 'src/app/services/token.service';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-list-client',
  templateUrl: './list-client.component.html',
  styleUrls: ['./list-client.component.css']
})
export class ListClientComponent {
 
  client: Client = new Client();
  clients: Client[] = [];

  constructor(private clientsdataService: ClientsdataService,
     private router:Router,
      private formBuilder: FormBuilder,
       private tokenService:TokenService,
       private _snackBar:MatSnackBar,){}

  ngOnInit(): void {
    this.getClientsDetails();
    
  }
  getClientsDetails()
  {
    this.clientsdataService.getClient().subscribe(res=> this.clients=res );
    
  }
  
  addnew()
  {
    this.router.navigateByUrl("add-user");
  }
 
  update(userId:String)
  {
     return this.router.navigate(['update-user',userId]);
    
  }
 
  delete(userId:String)
  {
    return this.clientsdataService.deleteClient(userId).subscribe(data=>
      {
        this.getClientsDetails();
        this._snackBar.open('client was deleted ', 'ok',{duration: 3000})
      })
  }
 
  
 
  

}

