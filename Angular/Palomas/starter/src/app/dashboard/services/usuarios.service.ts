import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SessionService } from 'src/app/session/services/session.service';
import { environment } from 'src/environments/environment.prod';
import { UserResponse } from '../interfaces/user.interface';
import { Observable } from 'rxjs';


const usersUrl = `${environment.apiUrl}/users`;
@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  constructor(private http: HttpClient, private sessionService: SessionService) { }


  requestOptionsMaster = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    })
  };
  requestOptionsToken = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.sessionService.getToken()}`,
      'Access-Control-Allow-Origin': '*'
    })
  };


  getAllUsers(): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(`${usersUrl}`, this.requestOptionsToken);
  }
}
