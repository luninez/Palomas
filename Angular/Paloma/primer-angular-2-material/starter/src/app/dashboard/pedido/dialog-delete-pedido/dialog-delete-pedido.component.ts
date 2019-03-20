import { Component, OnInit, Inject } from '@angular/core';
import { PedidoDto } from '../../dto/pedidoDto';
import { MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { AuthService } from 'src/app/session/signin/Service/auth.service';

@Component({
  selector: 'app-dialog-delete-pedido',
  templateUrl: './dialog-delete-pedido.component.html',
  styleUrls: ['./dialog-delete-pedido.component.scss']
})
export class DialogDeletePedidoComponent implements OnInit {
  name: string;
  pedidoEliminar: PedidoDto;

  constructor(public snackBar: MatSnackBar, private authService: AuthService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    this.pedidoEliminar == this.data.recurso;
  }

  eliminarPedido() {
    this.snackBar.open(`Eliminando ${this.pedidoEliminar.fecha}`, 'Cerrar', {
      duration: 3000,
    });
  }

}
