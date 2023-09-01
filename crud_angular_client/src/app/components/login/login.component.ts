import { AccountService } from './../../services/account.service';
import { TokenService } from './../../services/token.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(12)])
  })
form: any;
  
  constructor(private authService: AuthService,
      private tokenService:TokenService,
      private router:Router,
      private accountService:AccountService
      ){}

  ngOnInit(): void {
      
  }

  login(){
    const formValue = this.loginForm.value;
  
  if (formValue.email && formValue.password) {
    this.authService.login({
      email: formValue.email,
      password: formValue.password
    }).subscribe(res => this.handelResponse(res));
  } 
  }

  handelResponse(res: Object){
    this.tokenService.handle(res);
    this.accountService.changeStatus(true);
    this.router.navigateByUrl("/client");
    

  }

}
