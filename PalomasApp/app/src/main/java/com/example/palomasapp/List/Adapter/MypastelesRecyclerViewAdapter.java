package com.example.palomasapp.List.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.palomasapp.Interfaz.OnListLineaPedidoInteractionListener;
import com.example.palomasapp.Models.GestoraPedido;
import com.example.palomasapp.Models.LineaPedido;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.R;

import java.util.ArrayList;
import java.util.List;

public class MypastelesRecyclerViewAdapter extends RecyclerView.Adapter<MypastelesRecyclerViewAdapter.ViewHolder> {

    private final List<Producto> mValues;
    private final OnListLineaPedidoInteractionListener mListener;
    private Context ctx;
    private List<LineaPedido> lineaPedidoList = new ArrayList<>();

    public MypastelesRecyclerViewAdapter(Context ctx, int Layout, List<Producto> items, OnListLineaPedidoInteractionListener listener) {
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

        holder.imagen.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if(holder.mItem.getImagenes() == null){
           Glide.with(ctx).load(R.drawable.noimg).into(holder.imagen);
        }else{
            Glide.with(ctx).load(holder.mItem.getImagenes().get(0)).into(holder.imagen);
        }

        holder.btn_add_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            LineaPedido lineaPedido = new LineaPedido(1, holder.mItem.getPrecio(), null, holder.mItem);

            if(GestoraPedido.Instance().getPedido() == null){

                GestoraPedido.Instance().crearPedido(ctx, lineaPedido);
            }else{

                lineaPedido.setPedidoId(GestoraPedido.Instance().getPedido());

              //  if(GestoraPedido.Instance().getLineaPedidos().size()>0){
                    /*COMPROBAMOS SI HAY LIENAS DE PEDIDOS REPETIDAS EN EL PEDIDO*/
                    GestoraPedido.Instance().aniadirLineaPedido(ctx,lineaPedido);

                    Toast.makeText(ctx, "AÑADIR AL CARRITO", Toast.LENGTH_SHORT).show();
               // }//else{

                //    GestoraPedido.Instance().crearLineaPedido(ctx, lineaPedido);
                //    Toast.makeText(ctx, "ABRIENDO UN CARRITO NUEVO", Toast.LENGTH_SHORT).show();

              //  }
                //GestoraPedido.Instance().setLineaPedidos(lineaPedidoList);
            }

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
        public final ImageView btn_add_carrito;
        public Producto mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nombre = view.findViewById(R.id.producto_item_nombre);
            precio = view.findViewById(R.id.producto_item_precio);
            imagen = view.findViewById(R.id.producto_item_image);
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
