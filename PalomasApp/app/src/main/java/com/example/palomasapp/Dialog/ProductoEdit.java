package com.example.palomasapp.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palomasapp.Funcionalidades.ServiceGenerator;
import com.example.palomasapp.Funcionalidades.Services.CategoriaConImagenService;
import com.example.palomasapp.Funcionalidades.Util;
import com.example.palomasapp.Models.CategoriaConImagen;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.Models.ResponseContainer;
import com.example.palomasapp.Models.TipoAutenticacion;
import com.example.palomasapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoEdit extends DialogFragment {

    private static final String ARG_ID = "id";
    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_PRECIO = "precio";
    private static final String ARG_DESCRIPCION = "descripcion";
    private static final String ARG_CATEGORIA = "categoria";
    private ProductoEditViewModel mViewModel;

    private DialogInterface.OnDismissListener onDismissListener;

    private View view;
    private TextView nombre, precio, descripcion;
    private String argId, argNombre, argPrecio, argDescripcion, argCategoriaId;

    private Spinner spinnerCategoriasId;
    private ArrayAdapter<CategoriaConImagen> categorias;
    private List<CategoriaConImagen> listCategorias;
    private String categoriaId;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    public static ProductoEdit newInstance() {
        return new ProductoEdit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductoEditViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            argNombre = getArguments().getString(ARG_NOMBRE);
            argPrecio = getArguments().getString(ARG_PRECIO);
            argDescripcion = getArguments().getString(ARG_DESCRIPCION);
            argCategoriaId = getArguments().getString(ARG_CATEGORIA);

            argId = getArguments().getString(ARG_ID);

        }
    }

    public static ProductoEdit newInstance(Producto p){
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, p.getNombre());
        args.putString(ARG_PRECIO, Double.toString(p.getPrecio()));
        args.putString(ARG_DESCRIPCION, p.getDescripcion());
        args.putString(ARG_CATEGORIA, p.getCategoriaId());

        args.putString(ARG_ID, p.getId());

        ProductoEdit fragment = new ProductoEdit();
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.producto_edit_fragment, null);

        nombre = view.findViewById(R.id.producto_edit_name);
        precio = view.findViewById(R.id.producto_edit_price);
        descripcion = view.findViewById(R.id.producto_edit_descripcion);
        spinnerCategoriasId = view.findViewById(R.id.producto_edit_spinner_categoria);

        nombre.setText(argNombre);
        precio.setText(argPrecio);
        descripcion.setText(argDescripcion);

        nombre.setEnabled(true);
        precio.setEnabled(true);
        descripcion.setEnabled(true);

        cargarSpinner();

        //*Se crea el DialogFragment*//
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, int id) {
                String nombreProducto = nombre.getText().toString();
                String precioProducto = precio.getText().toString();
                String descripcionProducto = descripcion.getText().toString();

                Producto p = (Producto) spinnerCategoriasId.getSelectedItem();
                categoriaId = p.getCategoriaId();

                Producto producto = new Producto(argNombre, argDescripcion, Double.parseDouble(argPrecio), argCategoriaId);
                mViewModel.editProducto(producto, argId, dialog);


            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        builder.setView(view);

        return builder.create();

    }

    public void cargarSpinner() {

        CategoriaConImagenService service = ServiceGenerator.createService(CategoriaConImagenService.class, Util.getToken(this.getActivity()), TipoAutenticacion.JWT);
        Call<ResponseContainer<CategoriaConImagen>> call = service.getCategorias();

        call.enqueue(new Callback<ResponseContainer<CategoriaConImagen>>() {
            @Override
            public void onResponse(Call<ResponseContainer<CategoriaConImagen>> call, Response<ResponseContainer<CategoriaConImagen>> response) {
                if (response.isSuccessful()) {
                    listCategorias = new ArrayList<>(response.body().getRows());
                    categorias = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listCategorias);
                    spinnerCategoriasId.setAdapter(categorias);
                } else {
                    Toast.makeText(getContext(), "Error al obtener datos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<CategoriaConImagen>> call, Throwable t) {

                Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });


    }

}
