import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import {
  MatIconModule,
  MatCardModule,
  MatButtonModule,
  MatListModule,
  MatProgressBarModule,
  MatMenuModule,
  MatSnackBarModule,
  MatDialogModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule
} from "@angular/material";
import { FlexLayoutModule } from "@angular/flex-layout";

import { DashboardComponent } from "./dashboard.component";
import { DashboardRoutes } from "./dashboard.routing";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CategoriaListComponent } from './ctaegoriaCRUD/categoria-list/categoria-list.component'
import { CategoriaDeleteComponent } from './ctaegoriaCRUD//categoria-delete/categoria-delete.component';
import { CategoriaAddComponent } from './ctaegoriaCRUD/categoria-add/categoria-add.component';
import { CategoriaEditComponent } from './ctaegoriaCRUD/categoria-edit/categoria-edit.component';
import { UserlistComponent } from './userCRUD/userlist/userlist.component';


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
    FlexLayoutModule,
    MatSnackBarModule,
    MatDialogModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatSelectModule
  ],
  declarations: [DashboardComponent,
    CategoriaAddComponent, CategoriaDeleteComponent, CategoriaEditComponent, CategoriaListComponent,
    UserlistComponent],
  entryComponents: [CategoriaAddComponent, CategoriaDeleteComponent, CategoriaEditComponent, CategoriaListComponent, UserlistComponent]
})
export class DashboardModule { }
