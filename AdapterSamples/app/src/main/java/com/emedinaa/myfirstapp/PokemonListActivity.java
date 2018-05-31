package com.emedinaa.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.emedinaa.myfirstapp.adapter.PokemonAdapter;
import com.emedinaa.myfirstapp.data.PokemonData;
import com.emedinaa.myfirstapp.interfaces.PokemonListener;
import com.emedinaa.myfirstapp.model.Pokemon;

import java.util.List;

public class PokemonListActivity extends BaseActivity
implements PokemonListener{

    private final int DEFAULT_SPANCOUNT=3;
    private RecyclerView recyclerViewPokemon;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Pokemon> pokemonList;
    private PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);
        ui();

        loadPokemonData();
    }

    private void renderPokemons(){
        pokemonAdapter = new PokemonAdapter(this,pokemonList);
        //pokemonAdapter.setOnItemClickListener(this);

        recyclerViewPokemon.setAdapter(pokemonAdapter);
    }

    private void loadPokemonData() {
        pokemonList= new PokemonData().generate();
        renderPokemons();
    }

    private void ui() {
        enabledBack();

        recyclerViewPokemon= (RecyclerView)findViewById(R.id.recyclerViewPokemon);
        //mLayoutManager= new LinearLayoutManager(this);//,LinearLayoutManager.VERTICAL,)
        mLayoutManager = new GridLayoutManager(this,DEFAULT_SPANCOUNT);
        recyclerViewPokemon.setLayoutManager(mLayoutManager);

        //events
        /*recyclerViewPokemon.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerViewPokemon, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(pokemonList!=null){
                    Pokemon pokemon= pokemonList.get(position);
                    Log.v("CONSOLE", "pokemom "+pokemon.getName());
                    //goToDetails(pokemon);
                }
            }

            @Override
            public void onLongClick(View view, int position) {}
        }));*/
    }

    private void goToDetails(Pokemon pokemon){
        Intent intent= new Intent(this,PokemonDetailsActivity.class);
        startActivity(intent);
    }

    public void itemClickPhoto(Pokemon pokemon){
        Log.v("CONSOLE", "Activity itemClickPhoto "+pokemon.getName());
    }

    @Override
    public void clickItemPokemon(Pokemon pokemon) {
        Log.v("CONSOLE", "Interface itemClickPhoto "+pokemon.getName());
    }
}
