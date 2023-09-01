import { ClientsdataService } from './../../services/clientsdata.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from 'src/app/models/client';
import { AccountService } from 'src/app/services/account.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{
  currentUser!:null;
  userName!: string;

  constructor(private accountService:AccountService, private tokenService:TokenService, private router:Router, private clientsdataService:ClientsdataService){}
  ngOnInit(): void {
    this.accountService.authStatus.subscribe(res=>{
      this.currentUser = this.tokenService.getInfos();
    })
    this.getUserName();
   
  }
  logout()
  {
    this.tokenService.remove();
    this.accountService.changeStatus(false);
    this.router.navigateByUrl("/login");
  }
  
  getUserName() {
      return this.clientsdataService.getUserById(String(this.tokenService.getId())).subscribe(res =>{
        this.userName = res.lastName;
      })
    
  }

}
