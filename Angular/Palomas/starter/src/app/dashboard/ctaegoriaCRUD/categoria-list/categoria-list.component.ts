import { Component, OnInit } from '@angular/core';
import { CategoriesService } from '../../services/categories.service';
import { Title } from '@angular/platform-browser';
import { MatDialog } from '@angular/material';
import { CategoriaAddComponent } from '../categoria-add/categoria-add.component';
import { CategoriaEditComponent } from '../categoria-edit/categoria-edit.component';
import { CategoriaDeleteComponent } from '../categoria-delete/categoria-delete.component';
import { AddCategory } from '../../dto/categories/add-category.dto';
import { CategoriaResponse } from '../../interfaces/category.interface';
import { ContainerResp } from '../../interfaces/container.interface';

@Component({
  selector: 'app-categoria-list',
  templateUrl: './categoria-list.component.html',
  styleUrls: ['./categoria-list.component.scss']
})
export class CategoriaListComponent implements OnInit {

  arrayCategories: ContainerResp<CategoriaResponse[]>;

  constructor(private dialog: MatDialog,private categoryService: CategoriesService, private title: Title) { }

  ngOnInit() {
    this.title.setTitle('WeGo - Categorias');

    this.getCategories();
  }

  getCategories() {
    this.categoryService.getAllCategories().subscribe(categories => {
      this.arrayCategories = categories;

    }, error => {
      console.log(error);
    })
  }

  openDialogAddCategory() {
    const dialogAddCategory = this.dialog.open(
      CategoriaAddComponent
    );
    dialogAddCategory.afterClosed().subscribe(
      response => {
        this.getCategories();
      },
      error => {
        console.log(error);
      }
    );
  }

  openDialogEditCategory(category: CategoriaResponse) {
    const dialogEditCategory = this.dialog.open(
      CategoriaEditComponent,
      {
        data: {
          element: category
        }
      }
    );
    dialogEditCategory.afterClosed().subscribe(
      response => {
        this.getCategories();
      },
      error => {
        console.log(error);
      }
    );
  }

  openDialogDeleteCategory(category: AddCategory) {

    const dialogDeleteCategory = this.dialog.open(
      CategoriaDeleteComponent,
      {
        data: {
          element: category
        }
      }
    );
    dialogDeleteCategory.afterClosed().subscribe(
      response => {
        this.getCategories();
      },
      error => {
        console.log(error);
      }
    );

  }

}
