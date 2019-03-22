import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SessionService } from 'src/app/session/services/session.service';
import { CategoriaResponse } from '../interfaces/category.interface';
import { AddCategory } from '../dto/categories/add-category.dto';
import { EditCategory } from '../dto/categories/edit-category.dto';
import { ContainerResp } from '../interfaces/container.interface'
const categoriesUrl = `${environment.apiUrl}/categories`;

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor(private http: HttpClient, private sessionService: SessionService) { }

  requestOptionsMaster = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    })
  };

  requestOptionsToken = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.sessionService.getToken()}`,
      'Access-Control-Allow-Origin': '*'
    })
  };

  getAllCategories() {
    return this.http.get<ContainerResp<CategoriaResponse[]>>(`${categoriesUrl}?access_token=Oy2bM8ce8W04tkI6NglUqtvL376AShna`, this.requestOptionsMaster);
  }

  deleteOneCategory(id: string) {
    return this.http.delete(`${categoriesUrl}/${id}`, this.requestOptionsToken);
  }

  addCategory(category: AddCategory) {
    return this.http.post<CategoriaResponse>(`${categoriesUrl}`, category, this.requestOptionsToken);
  }

  editCategory(id: string, categoryModified: EditCategory) {
    return this.http.put<CategoriaResponse>(`${categoriesUrl}/${id}`, categoryModified, this.requestOptionsToken);
  }
}
