package com.example.toptenpeliculas.ui.listpeliculas;

import android.util.Log;

import com.example.toptenpeliculas.model.PeliculaModel;
import com.example.toptenpeliculas.network.RepoInstace;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculaListViewModel extends ViewModel {


    private MutableLiveData<PeliculaModel> peliculasList;
    public PeliculaListViewModel(){
        peliculasList = new MutableLiveData<>();
    }


    public MutableLiveData<PeliculaModel> getPerliculasListObserver() {
        return peliculasList;

    }

    String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=cf254622946c8fd22c9724a0ac47be10&language=en-Es";
    public void makeApiCall() {

        Call<PeliculaModel> call = RepoInstace.getListPeliculas().getPeliculaListInterface(url);
        call.enqueue(new Callback<PeliculaModel>() {
            @Override
            public void onResponse(Call<PeliculaModel> call, Response<PeliculaModel> response) {
                peliculasList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<PeliculaModel> call, Throwable t) {

            }
        });


    }

}
