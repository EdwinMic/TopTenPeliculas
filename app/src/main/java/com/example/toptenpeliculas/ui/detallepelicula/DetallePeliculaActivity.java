package com.example.toptenpeliculas.ui.detallepelicula;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.toptenpeliculas.R;
import com.example.toptenpeliculas.model.ListModelPelicula;
import com.example.toptenpeliculas.model.PeliculaModel;

import java.util.ArrayList;

public class DetallePeliculaActivity extends AppCompatActivity {


    Bundle bundle, args;
    public static ArrayList<ListModelPelicula> listPelicula;
    Intent intent;

    String categoria = "Todos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);


        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView imgPelicula = findViewById(R.id.imgPeliculaDetalle);
        TextView tvTitulo = findViewById(R.id.tvTitlePeliculaDetalle);
        TextView tvCalificacion = findViewById(R.id.tvCalificacionDetalle);
        TextView tvCategoria = findViewById(R.id.tvCategoriaDetalle);
        TextView tvDescripcion = findViewById(R.id.tvDescripcionDetalle);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Detalle Pelicula");
        }


        listPelicula = new ArrayList<>();
        listPelicula.clear();

        bundle = getIntent().getExtras();
        intent = getIntent();
        args = intent.getBundleExtra("BUNDLE");
        listPelicula = (ArrayList<ListModelPelicula>) args.getSerializable("ListPelicula");
        Toast.makeText(this,"" +  listPelicula.get(0).getTitle().toString(), Toast.LENGTH_LONG ).show();


        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"+listPelicula.get(0).getPoster_path())
                .apply(RequestOptions.centerCropTransform())
                .into(imgPelicula);

        tvTitulo.setText(listPelicula.get(0).getTitle());
        tvCalificacion.setText("Calificacion: "+listPelicula.get(0).getVote_average());

        if(listPelicula.get(0).getAdult()){
            categoria = "Adulto";
        }
        tvCategoria.setText("Categoria: " + categoria);
        tvDescripcion.setText(listPelicula.get(0).getOverview());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}