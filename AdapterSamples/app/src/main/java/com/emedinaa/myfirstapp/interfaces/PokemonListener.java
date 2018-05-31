package com.emedinaa.myfirstapp.interfaces;

import com.emedinaa.myfirstapp.model.Pokemon;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 5/30/18
 */
public interface PokemonListener {

    //cargar listado de pokemones
    //mostrar listado de pokemones
    //ejecutar una acción sobre cada celda

    void clickItemPokemon(Pokemon pokemon);
}
