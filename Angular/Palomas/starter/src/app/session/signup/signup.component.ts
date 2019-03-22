import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { Title } from '@angular/platform-browser';
import { SessionService } from '../services/session.service';
import { UserDto } from '../dto/user.dto';
import { MatSnackBar } from '@angular/material';
import { NgxSpinnerService } from 'ngx-spinner';
import { delay } from 'q';

const password = new FormControl('', Validators.required);
const confirmPassword = new FormControl('', CustomValidators.equalTo(password));

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  public form: FormGroup;
  constructor(private spinner: NgxSpinnerService, private snackBar: MatSnackBar, private sessionService: SessionService, private fb: FormBuilder, private router: Router, private title: Title) { }

  ngOnInit() {
    if (localStorage.getItem("token") == null) {
      this.router.navigate([""]);
    } else {
      this.router.navigate(["home"]);
    }
    this.title.setTitle("WeGo - Crear cuenta");
    this.form = this.fb.group({
      email: [null, Validators.compose([Validators.required, CustomValidators.email])],
      name: [null, Validators.required],
      password: password,
      confirmPassword: confirmPassword
    });
  }

  onSubmit() {
    this.spinner.show();
    const userDto = new UserDto(this.form.get('email').value, this.form.get('password').value, this.form.get('name').value);
    this.sessionService.register(userDto).subscribe(resp => {
      (async () => {

        this.sessionService.setLoginData(resp);

        await delay(4000);

        this.spinner.hide();
        this.router.navigate(['home']);

      })();

    }, error => {
      console.log(error);
      this.snackBar.open("Email o contraseña incorrectos", "x", {
        duration: 3000,
        verticalPosition: "top",
        horizontalPosition: "right"
      });
    });
  }
}
