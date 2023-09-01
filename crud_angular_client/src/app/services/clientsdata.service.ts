import { Injectable } from '@angular/core';
import{HttpClient}from'@angular/common/http'
import { Client } from '../models/client';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class ClientsdataService {
  readonly API_URL = "http://localhost:8080"
 
  
  constructor(private httpClient : HttpClient, private _snackBar:MatSnackBar) { }

  getClient() : Observable<Client[]>
  {
    return this.httpClient.get<Client[]>(this.API_URL + '/users?page=0&limit=15&search=');
  }
  addClient(client: Client):Observable<Object>
  {
    return this.httpClient.post(this.API_URL + '/users' ,client)
    
  }
  getUserById(userId:String):Observable<Client>
  {
    return this.httpClient.get<Client>(this.API_URL + '/users/'+userId);
  }
  editClient(userId:String, client:Client):Observable<Client>
  {
    return this.httpClient.put<Client>(this.API_URL+'/users/'+userId, client);
  }
  deleteClient(userId:String):Observable<Object>
  {
    return this.httpClient.delete(this.API_URL + '/users/'+userId);
    
  }

  
  



}
