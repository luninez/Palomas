import { Component, OnInit } from '@angular/core';

// SERVICIO
import { AuthService } from '../services/auth.service';

// dto
import { LoginDto } from '../dto/login.dto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email: string;
  password: string;

  constructor(private authService: AuthService) { }

  ngOnInit() { }

  doLogin() {
    const loginDto = new LoginDto(this.email, this.password);
    this.authService.login(loginDto).subscribe(loginResponse =>{
      console.log(loginResponse);
      this.authService.setLoginData(loginResponse);
    }, error => {
      console.log('Error en la petición de login');
    });
  }

}
