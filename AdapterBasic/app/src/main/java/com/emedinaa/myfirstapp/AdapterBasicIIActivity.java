package com.emedinaa.myfirstapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.emedinaa.myfirstapp.adapters.ProductAdapter;
import com.emedinaa.myfirstapp.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Listar productos
 *
 * Paso 1 : Crear la entidad Producto /model/Product
 * Paso 2 : Crear la celda , row_product.xml
 * Paso 3 : Crear nuestro origen de datos List<Product> products
 * Paso 4: Contenedor , recyclerView
 * Paso 5 : Adapter, ProductAdapter
 * Paso 6 : Setear adapter
 */
public class AdapterBasicIIActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_basic_02);
        enabledBack();
        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                //LinearLayoutManager.VERTICAL,false));
        loadProducts();
        //inicializar adapter
        ProductAdapter adapter= new ProductAdapter(this,products);
        recyclerView.setAdapter(adapter);
    }

    private void loadProducts(){
        Product product1= new Product(100,"Mesa de Ping pong",
                "Esta es un mesa de ping pong \nde 200x100 cm",90);

        Product product2= new Product(101,"Pizarra Acrílica",
                "Esta es un pizarra acrílica \nde 800x400 cm",120);

        Product product3= new Product(102,"Silla giratoria",
                "Esta es un silla giratoria \nde de color Negro",70);

        Product product4= new Product(103,"Silla básica",
                "Esta es un silla básica \nde de color blanco",50);

        Product product5= new Product(104,"Monitor Led",
                "Esta es un monitor led \nde 40'",180);

        products= new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
    }
}
