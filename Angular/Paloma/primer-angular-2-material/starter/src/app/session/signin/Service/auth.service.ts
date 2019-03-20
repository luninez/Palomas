import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SigninDto } from '../signin-dto';
import { Observable } from 'rxjs';
import { SigninResponse } from '../signin-response';
import { environment } from 'src/environments/environment';

const authUrl = `${environment.apiUrl}auth`;

const requestOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  // signin(param1, param2): tipoQueDevuelveElMetodo
  signin(signinDto: SigninDto): Observable<SigninResponse> {
    // PARÁMETROS
    // param1: url de la petición
    // param2: body, datos que envío en el cuerpo de la petición, en este caso 'signinDto'
    // param3: headers o cabeceras que viajan en la petición

    // RESPUESTA
    // En este caso el tipo de respuesta es 'SigninResponse'
    return this.http.post<SigninResponse>(`${authUrl}/login`, signinDto, requestOptions);
  }

  setSigninData(signinResponse: SigninResponse) {
    localStorage.setItem('token', signinResponse.token);
    localStorage.setItem('username', signinResponse.name);
    localStorage.setItem('email', signinResponse.email);
    localStorage.setItem('role', signinResponse.role);
  }

  getToken(): string {
    return localStorage.getItem('token');
  }

  isAdmin() {
    return localStorage.getItem('rol') === 'admin';
  }

}