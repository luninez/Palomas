package com.example.palomasapp.List.fragment_list;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.palomasapp.Interfaz.OnListLineaPedidoInteractionListener;
import com.example.palomasapp.List.Adapter.MypastelesRecyclerViewAdapter;
import com.example.palomasapp.Funcionalidades.ServiceGenerator;
import com.example.palomasapp.Funcionalidades.Services.ProductoService;
import com.example.palomasapp.Interfaz.OnListProductoInteractionListener;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.Models.ResponseContainer;
import com.example.palomasapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PastelesFragment extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListLineaPedidoInteractionListener mListener;
    private MypastelesRecyclerViewAdapter adapter;
    private Context ctx;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe;
    public String categoriaId;
    public boolean filtrarPorCategoria;

    public PastelesFragment() {
        filtrarPorCategoria = false;
    }

    public static PastelesFragment newInstance(int columnCount) {
        PastelesFragment fragment = new PastelesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pasteles_list, container, false);

        swipe = view.findViewById(R.id.swipePasteles);

        if (view instanceof SwipeRefreshLayout) {
            Context context = view.getContext();

            recyclerView = view.findViewById(R.id.listPasteles);
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //LLAMADA A LA API
            if(!filtrarPorCategoria) {
                cargarDatos(recyclerView);
            }else{
                cargarDatosPorCategoria(recyclerView, categoriaId);
            }
        }

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        actualizarDatos();
                        swipe.setRefreshing(false);
                    }
                }, 3000);
            }


        });

        return view;
    }

    public void cargarDatos(final RecyclerView recyclerView) {
        ProductoService productoService = ServiceGenerator.createService(ProductoService.class);
        Call<ResponseContainer<Producto>> call = productoService.getProductos();

        call.enqueue(new Callback<ResponseContainer<Producto>>() {
            @Override
            public void onResponse(Call<ResponseContainer<Producto>> call, Response<ResponseContainer<Producto>> response) {
                if (response.isSuccessful()) {
                    adapter = new MypastelesRecyclerViewAdapter(ctx, R.layout.fragment_pasteles, response.body().getRows(), mListener);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Error al obtener datos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<Producto>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cargarDatosPorCategoria(final RecyclerView recyclerView, String categoriaId) {
        ProductoService productoService = ServiceGenerator.createService(ProductoService.class);
        Call<ResponseContainer<Producto>> call = productoService.getFiltrarCategoria(categoriaId);

        call.enqueue(new Callback<ResponseContainer<Producto>>() {
            @Override
            public void onResponse(Call<ResponseContainer<Producto>> call, Response<ResponseContainer<Producto>> response) {
                if (response.isSuccessful()) {
                    adapter = new MypastelesRecyclerViewAdapter(ctx, R.layout.fragment_pasteles, response.body().getRows(), mListener);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Error al obtener datos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<Producto>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void actualizarDatos(){
        cargarDatos(recyclerView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ctx = context;
        if (context instanceof OnListLineaPedidoInteractionListener) {
            mListener = (OnListLineaPedidoInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
