import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../../session/signin/Service/auth.service';
import { environment } from 'src/environments/environment';
import { PedidoDto } from '../dto/pedidoDto'
import { PedidoInterface } from '../interfaces/pedido.interface'

const perdidoUrl = `${environment.apiUrl}pedido`;

@Injectable({
  providedIn: 'root'
})

export class PedidoService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  getAllPedido(): Observable<PedidoInterface[]> {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`
      })
    };

    return this.http.get<PedidoInterface[]>(`${perdidoUrl}/all`, requestOptions);
  }

  getPedido(idPedido: string): Observable<PedidoInterface> {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`
      })
    };

    return this.http.get<PedidoInterface>(`${perdidoUrl}/${idPedido}`, requestOptions);
  }

  createPedido(pedidoCreateDto: PedidoDto): Observable<PedidoDto> {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`
      })
    };

    return this.http.post<PedidoInterface>(`${perdidoUrl}/create`, PedidoDto, requestOptions);
  }

  updatePedido(pedido: PedidoInterface): Observable<PedidoInterface> {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`
      })
    };

    return this.http.put<PedidoInterface>(`${perdidoUrl}`, pedido, requestOptions);
  }

  eliminarPedido() {
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.authService.getToken()}`
      })
    };

    return this.http.delete<PedidoInterface[]>(`${perdidoUrl}/delete`, requestOptions);
  }

}
