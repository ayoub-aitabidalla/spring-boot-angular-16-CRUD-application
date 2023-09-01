import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }
  login(data:{email:string, password:string}){
    return this.httpClient.post("http://localhost:8080/users/login", data);
  }
  add(data:{email:string, password:string,firstName:string,lastName:string,country:string,street:string,city:string,type:string,postal:string,mobile:string,skype:string}){
    return this.httpClient.post("http://localhost:8080/users", data);
  }
 
 
}
