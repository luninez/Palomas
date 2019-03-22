import { Component } from '@angular/core';
import { Title } from "@angular/platform-browser";
import { Router } from "@angular/router";
import { CategoriesService } from "./services/categories.service";
import { ContainerResp } from './interfaces/container.interface';
import { CategoriaResponse } from './interfaces/category.interface';
import { MatDialog, MatSnackBar } from "@angular/material";
import { CategoriaAddComponent } from './ctaegoriaCRUD/categoria-add/categoria-add.component';
import { CategoriaDeleteComponent } from './ctaegoriaCRUD/categoria-delete/categoria-delete.component';
import { CategoriaEditComponent } from './ctaegoriaCRUD/categoria-edit/categoria-edit.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  arrayCategoria: ContainerResp<CategoriaResponse[]>;

  constructor(
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private title: Title,
    private router: Router,
    private categoriaService: CategoriesService
  ) {}

  ngOnInit(): void {
    if (localStorage.getItem("token") == null) {
      this.router.navigate([""]);
    }
    this.title.setTitle("Inicio");
    this.getCategoria();
  }

  getCategoria() {
    this.categoriaService.getAllCategories().subscribe(
      categoria => {
        console.log(categoria);
        this.arrayCategoria = categoria;
      },
      error => {
        this.snackBar.open("Error al obtener establecimientos", "x", {
          duration: 3000,
          verticalPosition: "top",
          horizontalPosition: "right"
        });
      }
    );
  }

  openDialogAdd() {
    const dialogAdd = this.dialog.open(
      CategoriaAddComponent,
      {
        height: "82%"
      }
    );
    dialogAdd.afterClosed().subscribe(
      response => {
        this.getCategoria();
      },
      error => {
        console.log(error);
      }
    );
  }

  openDialogDelete(categoria: CategoriaResponse) {
    const dialogDelete = this.dialog.open(
      CategoriaDeleteComponent,
      {
        height: "33%",
        data: {
          element: categoria
        }
      }
    );
    dialogDelete.afterClosed().subscribe(
      response => {
        this.getCategoria();
      },
      error => {
        console.log(error);
      }
    );
  }

  openDialogEdit(categoria: CategoriaResponse) {
    const dialogEdit = this.dialog.open(
      CategoriaEditComponent,
      {
        width: '50%',
        height: "88%",
        data: {
          element: categoria
        }
      }
    );
    dialogEdit.afterClosed().subscribe(
      response => {
        this.getCategoria();
      },
      error => {
        console.log(error);
      }
    );
  }
}
