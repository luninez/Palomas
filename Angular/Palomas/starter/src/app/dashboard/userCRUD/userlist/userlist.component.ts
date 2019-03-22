import { Component, OnInit } from '@angular/core';
import { UserResponse } from '../../interfaces/user.interface';
import { Title } from '@angular/platform-browser';
import { UsuariosService } from '../../services/usuarios.service';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.scss']
})
export class UserlistComponent implements OnInit {

  arrayUsers: UserResponse[];
  constructor(private title: Title, private usersService: UsuariosService) { }

  ngOnInit() {
    this.title.setTitle("WeGo - Usuarios");
    this.getUsers();

  }

  getUsers(){
    this.usersService.getAllUsers().subscribe(users => {

      this.arrayUsers = users;
    }, error =>{
      console.log(error);
    })
  }

}
