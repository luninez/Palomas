package com.example.palomasapp.List.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.palomasapp.Funcionalidades.Response.ProductoResponse;
import com.example.palomasapp.Interfaz.OnListProductoInteractionListener;
import com.example.palomasapp.List.fragment_list.BuscarpastelesFragment;
import com.example.palomasapp.List.fragment_list.CategoriasFragment;
import com.example.palomasapp.R;
import com.example.palomasapp.dummy.DummyContent.DummyItem;

import java.util.List;

public class MycarritoRecyclerViewAdapter extends RecyclerView.Adapter<MycarritoRecyclerViewAdapter.ViewHolder> {

    private final List<ProductoResponse> mValues;
    private final OnListProductoInteractionListener mListener;

    public MycarritoRecyclerViewAdapter(List<ProductoResponse> items, OnListProductoInteractionListener listener) {
        mValues = items;
        mListener = listener;
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
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
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
        public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
