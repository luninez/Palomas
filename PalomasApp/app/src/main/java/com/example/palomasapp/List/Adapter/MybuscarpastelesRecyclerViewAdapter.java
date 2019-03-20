package com.example.palomasapp.List.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.palomasapp.Interfaz.OnListProductoInteractionListener;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.R;

import java.util.List;

public class MybuscarpastelesRecyclerViewAdapter extends RecyclerView.Adapter<MybuscarpastelesRecyclerViewAdapter.ViewHolder> {

    private final List<Producto> mValues;
    private final OnListProductoInteractionListener mListener;
    private Context ctx;

    public MybuscarpastelesRecyclerViewAdapter(Context ctx, int Loyout, List<Producto> items, OnListProductoInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_buscarpasteles, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nombre.setText(holder.mItem.getNombre());
        holder.precio.setText(Double.toString(holder.mItem.getPrecio()));

        // activar fotos

        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //añadir al carrito
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nombre;
        public final TextView precio;
        public final ImageView imagen;
        public final ImageView btn_add;
        public Producto mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nombre = view.findViewById(R.id.buscar_item_nombre);
            precio = view.findViewById(R.id.buscar_item_precio);
            imagen = view.findViewById(R.id.buscar_item_image);
            btn_add = view.findViewById(R.id.buscar_btn_add_carrito);
        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "mView=" + mView +
                    ", nombre=" + nombre +
                    ", precio=" + precio +
                    ", mItem=" + mItem +
                    '}';
        }
    }


}
