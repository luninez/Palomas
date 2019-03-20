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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.palomasapp.Funcionalidades.ServiceGenerator;
import com.example.palomasapp.Funcionalidades.Services.ProductoService;
import com.example.palomasapp.Interfaz.OnListProductoInteractionListener;
import com.example.palomasapp.List.Adapter.MybuscarpastelesRecyclerViewAdapter;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.Models.ResponseContainer;
import com.example.palomasapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarpastelesFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListProductoInteractionListener mListener;
    private MybuscarpastelesRecyclerViewAdapter adapter;
    private Context ctx;
    private RecyclerView recyclerView;
    private EditText buscarProductoPorNombre;
    private ImageView btnBuscar;
    private SwipeRefreshLayout swipe;

    public BuscarpastelesFragment() { }

    public static BuscarpastelesFragment newInstance(int columnCount) {
        BuscarpastelesFragment fragment = new BuscarpastelesFragment();
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
        View view = inflater.inflate(R.layout.fragment_buscarpasteles_list, container, false);

        buscarProductoPorNombre = view.findViewById(R.id.findProducto);
        btnBuscar = view.findViewById(R.id.btnBuscarProducto);

        swipe = view.findViewById(R.id.swipeBuscarProducto);

        if (view instanceof SwipeRefreshLayout) {
            Context context = view.getContext();
            recyclerView = view.findViewById(R.id.listBuscar);
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            buscarProducto();

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

        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListProductoInteractionListener) {
            mListener = (OnListProductoInteractionListener) context;
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

    public void buscarProducto() {

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String findProducto = buscarProductoPorNombre.getText().toString().trim();

                final ProgressDialog progressDialog = new ProgressDialog(getContext(), R.style.Theme_AppCompat_DayNight_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Buscando pasteles...");
                progressDialog.show();

                //*Para ocultar automáticamente el teclado del móvil cuando se le da al botón de Buscar*//
                // InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
                // imm.hideSoftInputFromWindow(buscarProductoPorNombre.getWindowToken(), 0);


                //*Petición al API*//
                ProductoService service = ServiceGenerator.createService(ProductoService.class);
                Call<ResponseContainer<Producto>> call = service.getProductos();

                call.enqueue(new Callback<ResponseContainer<Producto>>() {
                    @Override
                    public void onResponse(Call<ResponseContainer<Producto>> call, Response<ResponseContainer<Producto>> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            adapter = new MybuscarpastelesRecyclerViewAdapter(ctx, R.layout.fragment_buscarpasteles, response.body().getRows(), mListener);
                            recyclerView.setAdapter(adapter);
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseContainer<Producto>> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Error de conexión.", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

    }

    public void actualizarDatos() {

        String findProducto = buscarProductoPorNombre.getText().toString().trim();

        //*Para ocultar automáticamente el teclado del móvil cuando se le da al botón de Buscar*//
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(buscarProductoPorNombre.getWindowToken(), 0);

        //*Petición a nuestra API*//
        ProductoService service = ServiceGenerator.createService(ProductoService.class);
        Call<ResponseContainer<Producto>> call = service.getProductos();

        call.enqueue(new Callback<ResponseContainer<Producto>>() {
            @Override
            public void onResponse(Call<ResponseContainer<Producto>> call, Response<ResponseContainer<Producto>> response) {
                if (response.isSuccessful()) {
                    adapter = new MybuscarpastelesRecyclerViewAdapter(ctx, R.layout.fragment_buscarpasteles, response.body().getRows(), mListener);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Error. Intenta de nuevo.", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<Producto>> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_LONG).show();

            }
        });


    }

}
