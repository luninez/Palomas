import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PedidoService } from '../pedido.service';
import { PedidoDto } from '../../dto/pedidoDto';

@Component({
  selector: 'app-dialog-add-pedido',
  templateUrl: './dialog-add-pedido.component.html',
  styleUrls: ['./dialog-add-pedido.component.scss']
})
export class DialogAddPedidoComponent implements OnInit {
  estado: string;
  fecha: string;
  usuarioId: string;

  public form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private pedidoService: PedidoService
  ) { }

  ngOnInit() {
    this.form = this.fb.group ({
      estado: [null, Validators.compose ([ Validators.required ])],
      fecha: [null, Validators.compose ([ Validators.required ])],
      usuarioId: [null, Validators.compose ([ Validators.required ])],
    })
  }

  addPedido() {
    const pedidoDto = new PedidoDto(this.estado, this.fecha, this.usuarioId);
    this.pedidoService.createPedido(pedidoDto).subcribe(
      pedido => {
        console.log(pedido);
      }
      )
  }

}
