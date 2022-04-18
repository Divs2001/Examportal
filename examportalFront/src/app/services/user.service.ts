import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { 
    
  }

  public addUser(user:any){
      return this.http.post(`${baseUrl}/users/saveUser`, user);
  } 

  public forgotPassword(data:any){
    return this.http.post(`${baseUrl}/users/forgot-password`,data);
  }

  public resetPassword(jsonData:any){
    return this.http.put(`${baseUrl}/users/reset-password`,jsonData);
  }
}
