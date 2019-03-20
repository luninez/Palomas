package com.example.palomasapp.List.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palomasapp.Interfaz.OnListLineaPedidoInteractionListener;
import com.example.palomasapp.Models.LineaPedido;
import com.example.palomasapp.R;

import java.util.List;

public class MycarritoRecyclerViewAdapter extends RecyclerView.Adapter<MycarritoRecyclerViewAdapter.ViewHolder> {

    private final List<LineaPedido> mValues;
    private final OnListLineaPedidoInteractionListener mListener;
    private Context ctx;

    public MycarritoRecyclerViewAdapter(Context ctx, int Layout, List<LineaPedido> items, OnListLineaPedidoInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_carrito, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nombre.setText(holder.mItem.getProductoId().getNombre());
        holder.precio.setText(Double.toString(holder.mItem.getPrecio()));
        holder.cantidad.setText(Integer.toString(holder.mItem.getCantidad()));

        // Glide.with(ctx).load(holder.mItem.getProductoId().).into(holder.lineaImg);

        holder.lineaDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog delete linea de pedido
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
        public final ImageView lineaImg;
        public final ImageView lineaDelete;
        public final TextView nombre;
        public final TextView precio;
        public final TextView cantidad;
        public LineaPedido mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            lineaImg = view.findViewById(R.id.carrito_item_imagen);
            lineaDelete = view.findViewById(R.id.carrito_item_delete);
            nombre = view.findViewById(R.id.carrito_item_nombre);
            precio = view.findViewById(R.id.carrito_item_precio);
            cantidad = view.findViewById(R.id.carrito_item_cantidad);

        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "mView=" + mView +
                    ", nombre=" + nombre +
                    ", mItem=" + mItem +
                    '}';
        }
    }
}
