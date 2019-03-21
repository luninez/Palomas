package com.example.palomasapp.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.palomasapp.Funcionalidades.Response.AuthAndRegisterResponse;
import com.example.palomasapp.Funcionalidades.ServiceGenerator;
import com.example.palomasapp.Funcionalidades.Services.AuthAndRegisterService;
import com.example.palomasapp.Funcionalidades.Util;
import com.example.palomasapp.R;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btnLogin, btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegistro = findViewById(R.id.btnLoginRegistro);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
            }
        });

        Util.setData(LoginActivity.this,
                ""
                ,""
                ,""
                ,""
                ,""
                ,"");

        doLogin();
    }

    public void onLoginSuccess(Call<AuthAndRegisterResponse> call, Response<AuthAndRegisterResponse> response) {


        Util.setData(LoginActivity.this, response.body().getToken(), response.body().getUser().getId(),
                response.body().getUser().getEmail(),response.body().getUser().getName()
                ,response.body().getUser().getPicture()
                ,response.body().getUser().getRole());

        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        finish();

    }

    public void onLoginFail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);


        builder.setIcon(R.drawable.ic_cancelar);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();

            }
        });

        builder.setMessage(R.string.login_error)
                .setTitle(R.string.error);


        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void doLogin() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Autentificando...");
                progressDialog.show();

                String emailTxt = email.getText().toString();
                String passwordTxt = password.getText().toString();

                String credentials = Credentials.basic(emailTxt, passwordTxt);

                AuthAndRegisterService loginService = ServiceGenerator.createService(AuthAndRegisterService.class);

                Call<AuthAndRegisterResponse> call = loginService.login(credentials);

                call.enqueue(new Callback<AuthAndRegisterResponse>() {
                    @Override
                    public void onResponse(final Call<AuthAndRegisterResponse> call, final Response<AuthAndRegisterResponse> response) {

                        if (response.isSuccessful()) {

                            Runnable progressRunnable = new Runnable() {

                                @Override
                                public void run() {
                                    progressDialog.cancel();
                                    onLoginSuccess(call, response);
                                }
                            };

                            Handler pdCanceller = new Handler();
                            pdCanceller.postDelayed(progressRunnable, 2000);


                        } else {
                            progressDialog.cancel();
                            onLoginFail();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthAndRegisterResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Error de conexión", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

    }
}
