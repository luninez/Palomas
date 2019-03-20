package com.example.palomasapp.List.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.palomasapp.Interfaz.OnListCategoriaConImagenInteractionListener;
import com.example.palomasapp.Models.CategoriaConImagen;
import com.example.palomasapp.R;

import java.util.List;


public class MycategoriasRecyclerViewAdapter extends RecyclerView.Adapter<MycategoriasRecyclerViewAdapter.ViewHolder> {

    private final List<CategoriaConImagen> mValues;
    private final OnListCategoriaConImagenInteractionListener mListener;
    private Context ctx;

    public MycategoriasRecyclerViewAdapter(Context ctx, int Layout, List<CategoriaConImagen> items, OnListCategoriaConImagenInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_categorias, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nombre.setText(holder.mItem.getNombre());

        Glide.with(ctx)
                .load(holder.mItem.getPicture())
                .into(holder.categoriaImg);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



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
        public final ImageView categoriaImg;
        public CategoriaConImagen mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nombre = view.findViewById(R.id.categoria_item_nombre);
            categoriaImg = view.findViewById(R.id.categoria_item_image);
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
