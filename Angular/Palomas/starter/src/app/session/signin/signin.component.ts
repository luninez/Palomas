import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { NgxSpinnerService } from 'ngx-spinner';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl
} from "@angular/forms";
import { Title } from "@angular/platform-browser";
import { SessionService } from "../services/session.service";
import { MatSnackBar } from "@angular/material";
import { LoginDto } from "../dto/login.dto";
import { delay } from "q";

@Component({
  selector: "app-signin",
  templateUrl: "./signin.component.html",
  styleUrls: ["./signin.component.scss"]
})
export class SigninComponent implements OnInit {
  public form: FormGroup;
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private title: Title,
    private sessionService: SessionService,
    private snackBar: MatSnackBar,
    private spinner: NgxSpinnerService
  ) {
    this.form = this.fb.group({
      hideRequired: false,
      floatLabel: "auto"
    });
  }

  ngOnInit() {
    if (localStorage.getItem("token") == null) {
      this.router.navigate(["session/signin"]);
    } else {
      this.router.navigate(["home"]);
    }
    this.title.setTitle("WeGo - Iniciar sesión");

    this.form = this.fb.group({
      email: [null, Validators.compose([Validators.required])],
      password: [null, Validators.compose([Validators.required])]
    });
  }

  onSubmit() {
    this.spinner.show();
    const loginDto = new LoginDto(
      this.form.get("email").value,
      this.form.get("password").value
    );
    this.sessionService.dologin(loginDto).subscribe(
      resp => {
        (async () => {
          console.log(resp);
          this.sessionService.setLoginData(resp);

          await delay(4000);

          this.spinner.hide();
          this.router.navigate(["home"]);
          console.log('after delay')
        })();
      },
      error => {
        console.log(error);
        this.snackBar.open("Email o contraseña incorrectos", "x", {
          duration: 3000,
          verticalPosition: "top",
          horizontalPosition: "right"
        });
      }
    );
  }
}
