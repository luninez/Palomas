package com.example.palomasapp.Blanck;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.palomasapp.Funcionalidades.ServiceGenerator;
import com.example.palomasapp.Funcionalidades.Services.UserService;
import com.example.palomasapp.Funcionalidades.Util;
import com.example.palomasapp.Models.ResponseContainer;
import com.example.palomasapp.Models.User;
import com.example.palomasapp.R;

import retrofit2.Call;

public class Perfil extends Fragment {
    private ProfileViewModel mViewModel;
    private ImageView perfil_imagen;
    private EditText name, email;
    private Button editPerfil;

    public static Perfil newInstance() { return new Perfil(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        perfil_imagen = v.findViewById(R.id.perfil_image);
        name = v.findViewById(R.id.perfil_name);
        email = v.findViewById(R.id.perfil_name3);

        editPerfil = v.findViewById(R.id.perfil_btn_editar);

        // Eventos
        editPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              UserService service = ServiceGenerator.createService(UserService.class);
              Call<User> call = service.getOneUser(Util.getUserId(getContext()));
            }
        });


        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
    }

}
