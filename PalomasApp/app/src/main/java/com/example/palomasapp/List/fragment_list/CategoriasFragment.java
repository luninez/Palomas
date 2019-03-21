package com.example.palomasapp.List.fragment_list;

import android.app.ProgressDialog;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Switch;
import android.widget.Toast;

import com.example.palomasapp.Funcionalidades.ServiceGenerator;
import com.example.palomasapp.Funcionalidades.Services.CategoriaConImagenService;
import com.example.palomasapp.Funcionalidades.Services.ProductoService;
import com.example.palomasapp.Interfaz.OnListCategoriaConImagenInteractionListener;
import com.example.palomasapp.List.Adapter.MybuscarpastelesRecyclerViewAdapter;
import com.example.palomasapp.List.Adapter.MycategoriasRecyclerViewAdapter;
import com.example.palomasapp.Models.CategoriaConImagen;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.Models.ResponseContainer;
import com.example.palomasapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriasFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListCategoriaConImagenInteractionListener mListener;
    private MycategoriasRecyclerViewAdapter adapter;
    private Context ctx;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe;

    public CategoriasFragment() { }

    public static CategoriasFragment newInstance(int columnCount) {
        CategoriasFragment fragment = new CategoriasFragment();
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
        View view = inflater.inflate(R.layout.fragment_categorias_list, container, false);

        swipe = view.findViewById(R.id.swipeCategoria);

        if (view instanceof SwipeRefreshLayout) {
            Context context = view.getContext();

            recyclerView = view.findViewById(R.id.listCategorias);
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //LLAMADA A LA API
            cargarDatos(recyclerView);
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
        CategoriaConImagenService categoriaService = ServiceGenerator.createService(CategoriaConImagenService.class);
        Call<ResponseContainer<CategoriaConImagen>> call = categoriaService.getCategorias();

        call.enqueue(new Callback<ResponseContainer<CategoriaConImagen>>() {
            @Override
            public void onResponse(Call<ResponseContainer<CategoriaConImagen>> call, Response<ResponseContainer<CategoriaConImagen>> response) {
                if (response.isSuccessful()) {
                    adapter = new MycategoriasRecyclerViewAdapter(ctx, R.layout.fragment_categorias, response.body().getRows(), mListener, CategoriasFragment.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Error al obtener datos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<CategoriaConImagen>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void actualizarDatos(){
        cargarDatos(recyclerView);
    }

    public void pasarFiltro(String id) {
        PastelesFragment fr = new PastelesFragment();
        fr.filtrarPorCategoria = true;
        fr.categoriaId = id;
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerFragmentMain, fr)
                .commit();
    }

    @Override
    public void onAttach(Context context) {
        ctx = context;
        super.onAttach(context);
        if (context instanceof OnListCategoriaConImagenInteractionListener) {
            mListener = (OnListCategoriaConImagenInteractionListener) context;
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
