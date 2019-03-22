import { Routes } from '@angular/router';

import { DashboardComponent } from './dashboard.component';
import { CategoriaListComponent } from './ctaegoriaCRUD/categoria-list/categoria-list.component'
import { UserlistComponent } from './userCRUD/userlist/userlist.component'

export const DashboardRoutes: Routes = [{
  path: 'home',
  component: DashboardComponent
},
{
  path: 'categories',
  component: CategoriaListComponent
},
{
  path: 'users',
  component: UserlistComponent
}

];
