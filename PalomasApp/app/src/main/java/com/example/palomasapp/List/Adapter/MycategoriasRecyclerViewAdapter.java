package com.example.palomasapp.List.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.palomasapp.Funcionalidades.Response.CategoriaResponse;
import com.example.palomasapp.Funcionalidades.Response.ProductoResponse;
import com.example.palomasapp.Interfaz.OnListCategoriaInteractionListener;
import com.example.palomasapp.List.fragment_list.CategoriasFragment;
import com.example.palomasapp.Models.Categoria;
import com.example.palomasapp.R;
import com.example.palomasapp.dummy.DummyContent;

import java.util.List;


public class MycategoriasRecyclerViewAdapter extends RecyclerView.Adapter<MycategoriasRecyclerViewAdapter.ViewHolder> {

    private final List<CategoriaResponse> mValues;
    private final OnListCategoriaInteractionListener mListener;
    private Context ctx;

    public MycategoriasRecyclerViewAdapter(Context ctx, int Layout, List<CategoriaResponse> items, OnListCategoriaInteractionListener listener) {
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

        // botones
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nombre;
        public final ImageView categoriaImg;
        public CategoriaResponse mItem;

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
