import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatIconModule, MatCardModule, MatButtonModule, MatListModule, MatProgressBarModule, MatMenuModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { DashboardComponent } from './dashboard.component';
import { DashboardRoutes } from './dashboard.routing';
import { PedidoComponent } from './pedido/pedido.component';
import { DialogEditPedidoComponent } from './pedido/dialog-edit-pedido/dialog-edit-pedido.component';
import { DialogDeletePedidoComponent } from './pedido/dialog-delete-pedido/dialog-delete-pedido.component';
import { DialogAddPedidoComponent } from './pedido/dialog-add-pedido/dialog-add-pedido.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(DashboardRoutes),
    MatIconModule,
    MatCardModule,
    MatButtonModule,
    MatListModule,
    MatProgressBarModule,
    MatMenuModule,
    FlexLayoutModule
  ],
  declarations: [ DashboardComponent, PedidoComponent, DialogEditPedidoComponent, DialogDeletePedidoComponent, DialogAddPedidoComponent ]
})

export class DashboardModule {}
