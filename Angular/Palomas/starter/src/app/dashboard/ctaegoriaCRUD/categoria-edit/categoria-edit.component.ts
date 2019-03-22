import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CategoriesService } from '../../services/categories.service';
import { EditCategory } from '../../dto/categories/edit-category.dto';
import { MatDialogRef, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-categoria-edit',
  templateUrl: './categoria-edit.component.html',
  styleUrls: ['./categoria-edit.component.scss']
})
export class CategoriaEditComponent implements OnInit {

  editCategoryForm: FormGroup;
  name: string;
  id: string;

  constructor(private categoryService: CategoriesService, public snackBar: MatSnackBar, @Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<CategoriaEditComponent>) { }

  ngOnInit() {
    this.name = this.data.element.name;

    this.editCategoryForm = new FormGroup({
      name: new FormControl(this.name, [Validators.required])
    });

  }

  editCategory() {
    const editCategoryDto = <EditCategory>this.editCategoryForm.value;
    this.categoryService.editCategory(this.data.element.id, editCategoryDto).subscribe(categoryModified => {
      this.dialogRef.close();
      this.snackBar.open(`Producto '${this.editCategoryForm.controls["name"].value}' creado correctamente`, "x", {
        duration: 2000,
        verticalPosition: "top"
      }
      )
    }, error => {
      console.log(error);
    })
  }

}

