import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Client } from 'src/app/models/client';
import { ClientsdataService } from 'src/app/services/clientsdata.service';
import { TokenService } from 'src/app/services/token.service';
@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent {
  client: Client = new Client();
  
  constructor(  private router:Router,
    private formBuilder: FormBuilder,
     private _snackBar:MatSnackBar,
     private clientsdataService:ClientsdataService ){}

     create(): void {
      this.clientsdataService.addClient(this.client).subscribe(data => {
        this.router.navigateByUrl("client");
        this._snackBar.open('client was added', 'ok',{duration:3000});
      });
    }

}
