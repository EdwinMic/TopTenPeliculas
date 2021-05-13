package com.example.toptenpeliculas.ui.listpeliculas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toptenpeliculas.R;
import com.example.toptenpeliculas.model.ListModelPelicula;
import com.example.toptenpeliculas.model.PeliculaModel;
import com.example.toptenpeliculas.ui.detallepelicula.DetallePeliculaActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PeliculaListActivity extends AppCompatActivity {


    private List<ListModelPelicula> movieModelList;
    private PeliculaListAdapter adapter;
    private PeliculaListViewModel viewModel;

    private List<ListModelPelicula> seendListDataPeliculas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Listado de Peliculas");
        }


        RecyclerView recyclerView = findViewById(R.id.rvTopTenPeliculas);
        final TextView noresult = findViewById(R.id.tvDataNull);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new PeliculaListAdapter(this, movieModelList);
        recyclerView.setAdapter(adapter);


        viewModel = ViewModelProviders.of(this).get(PeliculaListViewModel.class);
        viewModel.getPerliculasListObserver().observe(this, new Observer<PeliculaModel>() {
            @Override
            public void onChanged(PeliculaModel movieModels) {
                if(movieModels != null) {
                    Log.e("MainActivity:;","Data: " + movieModels.getResults().get(0).getTitle());
                    movieModelList = movieModels.getResults();
                    adapter.setMovieList(movieModelList);
                    noresult.setVisibility(View.GONE);
                    adapter.setClickListener(new PeliculaListAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Log.e("MainActivity:;","Data: " + movieModels.getResults().get(position).getTitle());
                            seendListDataPeliculas.clear();
                            seendListDataPeliculas.add(movieModels.getResults().get(position));
                            Intent intent = new Intent(PeliculaListActivity.this, DetallePeliculaActivity.class);
                            Bundle args = new Bundle();
                            args.putSerializable("ListPelicula", (Serializable) seendListDataPeliculas);
                            intent.putExtra("BUNDLE", args);
                            startActivity(intent);
                        }
                    });
                } else {
                    noresult.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();

    }
}