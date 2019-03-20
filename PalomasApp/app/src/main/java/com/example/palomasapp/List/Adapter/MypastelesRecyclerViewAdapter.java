package com.example.palomasapp.List.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palomasapp.Interfaz.OnListProductoInteractionListener;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.R;

import java.util.List;

public class MypastelesRecyclerViewAdapter extends RecyclerView.Adapter<MypastelesRecyclerViewAdapter.ViewHolder> {

    private final List<Producto> mValues;
    private final OnListProductoInteractionListener mListener;
    private Context ctx;

    public MypastelesRecyclerViewAdapter(Context ctx, int Layout, List<Producto> items, OnListProductoInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pasteles, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nombre.setText(holder.mItem.getNombre());
        holder.precio.setText(Double.toString(holder.mItem.getPrecio()));
        
        holder.btn_add_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDeleteProductoClick(holder.mItem.getId(), holder.mItem.getNombre());
                Toast.makeText(ctx, "En construccion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        // public final ImageView image;
        public final TextView nombre;
        public final TextView precio;
        public final ImageView btn_add_carrito;
        public Producto mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            // image = view.findViewById(R.id.producto_item_image);
            nombre = view.findViewById(R.id.producto_item_nombre);
            precio = view.findViewById(R.id.producto_item_precio);
            btn_add_carrito = view.findViewById(R.id.producto_btn_add_carrito);
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
