package com.example.palomasapp.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.palomasapp.Dialog.ProductoDelete;
import com.example.palomasapp.Dialog.ProductoEdit;
import com.example.palomasapp.Funcionalidades.Response.ProductoResponse;
import com.example.palomasapp.Funcionalidades.Util;
import com.example.palomasapp.Interfaz.OnListCategoriaInteractionListener;
import com.example.palomasapp.Interfaz.OnListProductoInteractionListener;
import com.example.palomasapp.List.fragment_list.BuscarpastelesFragment;
import com.example.palomasapp.List.fragment_list.CarritoFragment;
import com.example.palomasapp.List.fragment_list.CategoriasFragment;
import com.example.palomasapp.List.fragment_list.PastelesFragment;
import com.example.palomasapp.Models.Categoria;
import com.example.palomasapp.Models.Producto;
import com.example.palomasapp.Perfil;
import com.example.palomasapp.R;

import java.util.List;

public class DashboardActivity extends AppCompatActivity implements OnListProductoInteractionListener, OnListCategoriaInteractionListener {

    private TextView mTextMessage;
    private Fragment f;
    private FloatingActionButton fab;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            BottomNavigationView n = findViewById(R.id.navigation);
            fab = findViewById(R.id.fab);

            Menu menu = n.getMenu();
            menu.findItem(R.id.navigation_home).setIcon(R.drawable.ic_home_black_24dp);
            menu.findItem(R.id.navigation_search).setIcon(R.drawable.ic_search_black_24dp);
            menu.findItem(R.id.navigation_category).setIcon(R.drawable.ic_capcake_black_24dp);
            menu.findItem(R.id.navigation_carrito).setIcon(R.drawable.ic_carrito_black_24dp);
            menu.findItem(R.id.navigation_perfil).setIcon(R.drawable.ic_perfil_black_24dp);

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    item.setIcon(R.drawable.ic_home_black_24dp);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.containerFragmentMain, new PastelesFragment(), "home")
                            .commit();

                    if(!Util.getUserRole(getApplicationContext()).equals("admin")){
                        fab.hide();
                    }

                    return true;
                case R.id.navigation_search:
                    item.setIcon(R.drawable.ic_search_black_24dp);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.containerFragmentMain, new BuscarpastelesFragment(), "search")
                            .commit();

                    if(!Util.getUserRole(getApplicationContext()).equals("admin")){
                        fab.hide();
                    }

                    return true;
                case R.id.navigation_category:
                    item.setIcon(R.drawable.ic_capcake_black_24dp);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.containerFragmentMain, new CategoriasFragment(), "categorias")
                            .commit();

                    if(!Util.getUserRole(getApplicationContext()).equals("admin")){
                        fab.hide();
                    }

                    return true;
                case R.id.navigation_carrito:
                    item.setIcon(R.drawable.ic_carrito_black_24dp);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.containerFragmentMain, new CarritoFragment(), "carrito")
                            .commit();

                    if(!Util.getUserRole(getApplicationContext()).equals("admin")){
                        fab.hide();
                    }

                    return true;
                case R.id.navigation_perfil:
                    item.setIcon(R.drawable.ic_perfil_black_24dp);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.containerFragmentMain, new Perfil(), "perfil")
                            .commit();

                    if(!Util.getUserRole(getApplicationContext()).equals("admin")){
                        fab.hide();
                    }

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.containerFragmentMain, new PastelesFragment(), "home").commit();
    }

    @Override
    public void onDeleteProductoClick(String id, String nombre) {
        ProductoDelete f = ProductoDelete.newInstance(id, nombre);
        f.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("mainFragment");
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.detach(currentFragment);
                fragmentTransaction.attach(currentFragment);
                fragmentTransaction.commit();
            }
        });
        FragmentManager fm = getSupportFragmentManager();
        f.show(fm, "DeleteProducto");
    }

    @Override
    public void onEditProductoClick(ProductoResponse p) {
        ProductoEdit f = ProductoEdit.newInstance(p);
        f.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("mainFragment");
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.detach(currentFragment);
                fragmentTransaction.attach(currentFragment);
                fragmentTransaction.commit();
            }
        });
        FragmentManager fm = getSupportFragmentManager();
        f.show(fm, "EditarPersona");
    }

    @Override
    public void onAddProductoClick(Producto p) {

    }

    @Override
    public void onInfoClickProducto(Producto p) {

    }

    @Override
    public void onFavoriteClickProducto(ProductoResponse p) {

    }

    @Override
    public void onDeleteCategoriaClick(String id, String nombre) {

    }

    @Override
    public void onEditCategoriaClick(Categoria c) {

    }

    @Override
    public void onAddCategoriaClick(Categoria c) {

    }

    @Override
    public void onInfoClickCategoria(Categoria c) {

    }

    @Override
    public void onGoToProductoClick(List<Producto> productos) {

    }
}
