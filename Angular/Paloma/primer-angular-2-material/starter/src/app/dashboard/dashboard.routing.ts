import { Routes } from '@angular/router';

import { DashboardComponent } from './dashboard.component';
import { PedidoComponent } from './pedido/pedido.component';

export const DashboardRoutes: Routes = [
  { path: '', 
    children: [
      {path: 'dashboard', component: DashboardComponent},
      {path: 'pedido', component: PedidoComponent}
    ]
}];
