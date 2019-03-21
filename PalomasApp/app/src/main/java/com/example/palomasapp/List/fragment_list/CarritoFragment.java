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
import android.widget.Button;
import android.widget.Toast;

import com.example.palomasapp.Funcionalidades.ServiceGenerator;
import com.example.palomasapp.Funcionalidades.Services.LineaPedidoService;
import com.example.palomasapp.Funcionalidades.Services.PedidoService;
import com.example.palomasapp.Funcionalidades.Util;
import com.example.palomasapp.Interfaz.OnListLineaPedidoInteractionListener;
import com.example.palomasapp.List.Adapter.MycarritoRecyclerViewAdapter;
import com.example.palomasapp.Models.EstadoPedido;
import com.example.palomasapp.Models.GestoraPedido;
import com.example.palomasapp.Models.LineaPedido;
import com.example.palomasapp.Models.Pedido;
import com.example.palomasapp.Models.ResponseContainer;
import com.example.palomasapp.Models.TipoAutenticacion;
import com.example.palomasapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarritoFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListLineaPedidoInteractionListener mListener;
    private MycarritoRecyclerViewAdapter adapter;
    private Context ctx;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe;
    private Button btn_cerrarPedido;
    private List<LineaPedido> lineaPedidoList;

    public CarritoFragment() { }

    public static CarritoFragment newInstance(int columnCount) {
        CarritoFragment fragment = new CarritoFragment();
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
        View view = inflater.inflate(R.layout.fragment_carrito_list, container, false);

        btn_cerrarPedido = view.findViewById(R.id.cerrar_pedido);

        swipe = view.findViewById(R.id.swipeCarrito);

        if (view instanceof SwipeRefreshLayout) {
            Context context = view.getContext();

            recyclerView = view.findViewById(R.id.listCarrito);
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //LLAMADA API
            //cargarDatos(recyclerView);

            cargarDatosLocales();
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
        LineaPedidoService lineaPedidoService = ServiceGenerator.createService(LineaPedidoService.class, Util.getToken(ctx), TipoAutenticacion.JWT);
        Call<ResponseContainer<LineaPedido>> call = lineaPedidoService.getLineaPedidosPedioId(GestoraPedido.Instance().getPedido().getId());

        call.enqueue(new Callback<ResponseContainer<LineaPedido>>() {
            @Override
            public void onResponse(Call<ResponseContainer<LineaPedido>> call, Response<ResponseContainer<LineaPedido>> response) {
                if (response.isSuccessful()) {
                    lineaPedidoList = response.body().getRows();
                    cargarDatosLocales();
                } else {
                    Toast.makeText(getContext(), "Error al obtener datos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<LineaPedido>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cargarDatosLocales(){
        adapter = new MycarritoRecyclerViewAdapter(ctx, R.layout.fragment_carrito, GestoraPedido.Instance().getLineaPedidos(), mListener);
        recyclerView.setAdapter(adapter);
    }

    public void actualizarDatos(){
        cargarDatos(recyclerView);
    }

    public void cerrarPedido(){
        btn_cerrarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestoraPedido.Instance().getPedido().setEstado(EstadoPedido.RECIBIDO.toString());
                Pedido p = GestoraPedido.Instance().getPedido();
                PedidoService service = ServiceGenerator.createService(PedidoService.class, Util.getToken(ctx), TipoAutenticacion.JWT);
                Call<Pedido> call = service.editPedido(p.getId(), p);

                call.enqueue(new Callback<Pedido>() {
                    @Override
                    public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                        if(response.isSuccessful()){
                            cargarDatosLocales();
                        }else{
                            Toast.makeText(ctx, "Error al cerrar el pedido", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Pedido> call, Throwable t) {
                        Toast.makeText(ctx, "No funka", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        ctx = context;
        super.onAttach(context);
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
