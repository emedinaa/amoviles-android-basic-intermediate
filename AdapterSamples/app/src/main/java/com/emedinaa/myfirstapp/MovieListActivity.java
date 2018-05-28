package com.emedinaa.myfirstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.emedinaa.myfirstapp.adapter.MovieListAdapter;
import com.emedinaa.myfirstapp.data.Movies;
import com.emedinaa.myfirstapp.model.Movie;

import java.util.List;

public class MovieListActivity extends BaseActivity {

    private ListView listViewMovies;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        enabledBack();

        listViewMovies= findViewById(R.id.listViewMovies);

        //data provider
        movies= Movies.getMovies();

        //Adapter
        MovieListAdapter adapter= new MovieListAdapter(this,movies);

        //set Adapter to UI
        listViewMovies.setAdapter(adapter);

        //events
        listViewMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Movie movie =(Movie) adapterView.getAdapter().getItem(position);
                String message= movie.getTitle()+ " "+ movie.isCartelera();
                //String.format("title %s cartela %s ",movie.getTitle(),String.valueOf(movie.isCartelera()) );
                showItem(message);
            }
        });
    }

    private void showItem(String value) {
        Toast.makeText(this,"item "+value,Toast.LENGTH_SHORT).show();
    }
}
