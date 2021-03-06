package com.example.palomasapp.Dialog.fragment_dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.palomasapp.Dialog.ViewModel.ProductoDeleteViewModel;
import com.example.palomasapp.R;

public class ProductoDelete extends DialogFragment {

    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_ID_PRODUCTO = "id_producto";
    private ProductoDeleteViewModel mViewModel;

    private DialogInterface.OnDismissListener onDismissListener;

    private View view;
    private TextView nombre;
    private String argNombre, argId;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    public static ProductoDelete newInstance() {
        return new ProductoDelete();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProductoDeleteViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            argNombre = getArguments().getString(ARG_NOMBRE);
            argId = getArguments().getString(ARG_ID_PRODUCTO);
        }
    }

    public static ProductoDelete newInstance(String id, String nombre) {
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, nombre);
        args.putString(ARG_ID_PRODUCTO, id);

        ProductoDelete fragment = new ProductoDelete();
        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.producto_delete_fragment, null);

        nombre = view.findViewById(R.id.productoDeleteName);

        nombre.setText(argNombre);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Eliminar a: ")

                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {

                    public void onClick(final DialogInterface dialog, int id) {
                        String nombreEditado = nombre.getText().toString();

                        mViewModel.deleteProducto(nombreEditado,argId, dialog);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });


        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

}
