package com.example.palomasapp.Models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.palomasapp.Funcionalidades.ServiceGenerator;
import com.example.palomasapp.Funcionalidades.Services.LineaPedidoService;
import com.example.palomasapp.Funcionalidades.Services.PedidoService;
import com.example.palomasapp.Funcionalidades.Services.UserService;
import com.example.palomasapp.Funcionalidades.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GestoraPedido {

    private Pedido pedido;
    private User user;
    private List<LineaPedido> lineaPedidos;
    private static GestoraPedido instance;

    public static GestoraPedido Instance(){
        if(instance == null)
            instance = new GestoraPedido();
        return  instance;
    }

    public void obtenerUsuario(final Context ctx){
        UserService userService = ServiceGenerator.createService(UserService.class);
        Call<User> callUser = userService.getOneUser(Util.getUserId(ctx));

        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    user = response.body();
                }else {
                    Toast.makeText(ctx, "No se obtubo usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Usuario", t.getMessage());
            }
        });
    }
    
    public void crearPedido(final Context ctx, final LineaPedido lineaPedido){

        obtenerUsuario(ctx);

        Pedido p = new Pedido(EstadoPedido.PIDIENDO.toString(), Calendar.getInstance().toString(), GestoraPedido.Instance().user);

        PedidoService service = ServiceGenerator.createService(PedidoService.class, Util.getToken(ctx), TipoAutenticacion.JWT);
        Call<Pedido> call = service.addPedidos(p);

        call.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                if(response.isSuccessful()){
                    pedido = response.body();
                    pedido.setUsuarioId(GestoraPedido.Instance().user);
                    lineaPedido.setPedidoId(pedido);
                    crearLineaPedido(ctx, lineaPedido);
                }else{
                    Toast.makeText(ctx, "No se pudo crear un pedido nuevo, intentelo más tarde", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Toast.makeText(ctx, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cargarPedidosPendientes(final Context ctx){
        final PedidoService service = ServiceGenerator.createService(PedidoService.class, Util.getToken(ctx), TipoAutenticacion.JWT);
        Call<ResponseContainer<Pedido>> call = service.getPedidosUsuarioEstado(Util.getUserId(ctx), EstadoPedido.PIDIENDO.toString());

        call.enqueue(new Callback<ResponseContainer<Pedido>>() {
            @Override
            public void onResponse(Call<ResponseContainer<Pedido>> call, Response<ResponseContainer<Pedido>> response) {
                if(response.isSuccessful()){
                    if(response.body().getCount() > 0){
                        pedido = response.body().getRows().get(0);
                        pedido.setUsuarioId(GestoraPedido.Instance().user);
                        lineaPedidos = new ArrayList<>();
                        cargarLineasPedido(ctx);

                    }
                }else{
                    Toast.makeText(ctx, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<Pedido>> call, Throwable t) {
                Log.d("Pedido", t.getMessage());
                Toast.makeText(ctx, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void crearLineaPedido(final Context ctx, LineaPedido lineaPedido){
        final LineaPedidoService service = ServiceGenerator.createService(LineaPedidoService.class,  Util.getToken(ctx), TipoAutenticacion.JWT);
        Call<LineaPedido> call = service.addLineaPedidos(lineaPedido);

        call.enqueue(new Callback<LineaPedido>() {
            @Override
            public void onResponse(Call<LineaPedido> call, Response<LineaPedido> response) {
                if(response.isSuccessful()){
                    lineaPedidos.add(response.body());
                    //Pintar

                }else{
                    Toast.makeText(ctx, "No se pudo añadir al carrito.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LineaPedido> call, Throwable t) {
                Toast.makeText(ctx, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editarLineaPedido(final Context ctx, LineaPedido lineaPedido){
        final LineaPedidoService service = ServiceGenerator.createService(LineaPedidoService.class);
        Call<LineaPedido> call = service.editLineaPedidos(lineaPedido.getId(), lineaPedido);

        call.enqueue(new Callback<LineaPedido>() {
            @Override
            public void onResponse(Call<LineaPedido> call, Response<LineaPedido> response) {
                if(response.isSuccessful()){
                    //Pintar

                }else{
                    Toast.makeText(ctx, "No se pudo añadir al carrito.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LineaPedido> call, Throwable t) {
                Toast.makeText(ctx, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void lienaRepetida(final Context ctx, final LineaPedido lineaPedido){
        final LineaPedidoService service = ServiceGenerator.createService(LineaPedidoService.class);
        Call<ResponseContainer<LineaPedido>> callListar = service.getLineaPedidosPedioId(pedido.getId());

        callListar.enqueue(new Callback<ResponseContainer<LineaPedido>>() {
            @Override
            public void onResponse(Call<ResponseContainer<LineaPedido>> call, Response<ResponseContainer<LineaPedido>> response) {
                if(response.isSuccessful()){
                    for(int i = 0; i < response.body().getRows().size(); i++){
                        if(response.body().getRows().get(i).getProductoId() == lineaPedido.getProductoId()){
                            response.body().getRows().get(i).setCantidad(response.body().getRows().get(i).getCantidad() + 1);
                            response.body().getRows().get(i).setPrecio(response.body().getRows().get(i).getPrecio() + lineaPedido.getPrecio());
                            editarLineaPedido(ctx, lineaPedido);
                            return;
                        }
                    }
                    crearLineaPedido(ctx, lineaPedido);

                }else{
                    Toast.makeText(ctx, "No se pudo borrar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<LineaPedido>> call, Throwable t) {
                Toast.makeText(ctx, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void aniadirLineaPedido(final Context ctx, final LineaPedido lineaPedido){
            for(int i = 0; i < lineaPedidos.size(); i++) {
                if(lineaPedidos.get(i).getProductoId() == lineaPedido.getProductoId()){

                    lineaPedidos.get(i).setCantidad(lineaPedidos.get(i).getCantidad() + 1);
                    lineaPedidos.get(i).setPrecio(lineaPedidos.get(i).getPrecio() + lineaPedido.getPrecio());
                    editarLineaPedido(ctx, lineaPedido);
                    return;
                }
            }
        crearLineaPedido(ctx, lineaPedido);
    }

    public void cargarLineasPedido(final Context ctx){
        final LineaPedidoService service = ServiceGenerator.createService(LineaPedidoService.class, Util.getToken(ctx), TipoAutenticacion.JWT);
        Call<ResponseContainer<LineaPedido>> callListar = service.getLineaPedidosPedioId(pedido.getId());

        callListar.enqueue(new Callback<ResponseContainer<LineaPedido>>() {
            @Override
            public void onResponse(Call<ResponseContainer<LineaPedido>> call, Response<ResponseContainer<LineaPedido>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ctx, "Tienes elementos en el carrito.", Toast.LENGTH_SHORT).show();
                    lineaPedidos = response.body().getRows();
                }else{
                    Toast.makeText(ctx, "No se pudo borrar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<LineaPedido>> call, Throwable t) {
                Toast.makeText(ctx, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<LineaPedido> getLineaPedidos() {
        return lineaPedidos;
    }

    public void setLineaPedidos(List<LineaPedido> lineaPedidos) {
        this.lineaPedidos = lineaPedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GestoraPedido)) return false;
        GestoraPedido that = (GestoraPedido) o;
        return Objects.equals(pedido, that.pedido) &&
                Objects.equals(lineaPedidos, that.lineaPedidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, lineaPedidos);
    }

    @Override
    public String toString() {
        return "GestoraPedido{" +
                "pedido=" + pedido +
                ", lineaPedidos=" + lineaPedidos +
                '}';
    }
}
