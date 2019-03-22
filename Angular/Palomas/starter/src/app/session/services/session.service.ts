import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { HttpHeaders, HttpClient } from "@angular/common/http";
import { LoginDto } from "../dto/login.dto";
import { Observable } from "rxjs";
import { LoginResponse } from "../interfaces/login-resp.interface";
import { UserDto } from "../dto/user.dto";

const authUrl = `${environment.apiUrl}`;
const MASTER_KEY = 'superMasterKeyDeLaLusi';
const requestOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'access_token': MASTER_KEY
  })
};

@Injectable({
  providedIn: "root"
})
export class SessionService {
  constructor(private http: HttpClient) {}

  dologin(loginDto: LoginDto): Observable<LoginResponse> {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ` + btoa(`${loginDto.email}:${loginDto.password}`),
        'Access-Control-Allow-Origin': '*'
      })
    };
    class Metakey {
      access_token: String;

      constructor(access_token: String) {
        this.access_token = access_token;
      }
    }
    const metaKey = new Metakey('superMasterKeyDeLaLusi');
    return this.http.post<LoginResponse>(`${environment.apiUrl}/auth`, metaKey, requestOptions);
  }

  register(userDto:UserDto): Observable<LoginResponse> {
    const data = {'email': userDto.email, 'password': userDto.password,'name': userDto.name, 'access_token': MASTER_KEY};
    return this.http.post<LoginResponse>(`${authUrl}/users`,data,requestOptions);
  }

  setLoginData(loginResponse: LoginResponse) {
    localStorage.setItem('token', loginResponse.token);
    localStorage.setItem('id', loginResponse.user.id);
    localStorage.setItem('name', loginResponse.user.name);
    localStorage.setItem('email', loginResponse.user.email);
    localStorage.setItem('picture', loginResponse.user.picture);
  }

  getMasterKey(): string {
    return 'superMasterKeyDeLaLusi';
  }

  getToken(): string {
    return localStorage.getItem('token');
  }

  getIdUser(): string {
    return localStorage.getItem('id');
  }

  getNameUser(): string {
    return localStorage.getItem('name');
  }

  getEmailUser(): string {
    return localStorage.getItem('email');
  }

  getPictureUser(): string {
    return localStorage.getItem('picture');
  }
}
