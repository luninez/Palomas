import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CategoriesService } from '../../services/categories.service';
import { AddCategory } from '../../dto/categories/add-category.dto';
import { MatDialogRef, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-categoria-add',
  templateUrl: './categoria-add.component.html',
  styleUrls: ['./categoria-add.component.scss']
})
export class CategoriaAddComponent implements OnInit {

  createCategory: FormGroup;

  constructor(private categoryService: CategoriesService, public dialogRef: MatDialogRef<CategoriaAddComponent>, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.createCategory = new FormGroup({
      name: new FormControl("", [Validators.required])
    });
  }

  addCategory() {
    const categoryDto = <AddCategory>this.createCategory.value;
    this.categoryService.addCategory(categoryDto).subscribe(category => {
      console.log(category);
      this.dialogRef.close();
      this.snackBar.open(`Establecimiento '${this.createCategory.controls["name"].value}' creado correctamente`, "x", {
        duration: 2000,
        verticalPosition: "top"
      }
      )}, error => {
      console.log(error);
    })
  }

}
