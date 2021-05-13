package com.example.toptenpeliculas.ui.listpeliculas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.toptenpeliculas.R;
import com.example.toptenpeliculas.model.ListModelPelicula;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PeliculaListAdapter extends RecyclerView.Adapter<PeliculaListAdapter.MyViewHolder> {
    private Context context;
    private List<ListModelPelicula> peliculasList;
    private ItemClickListener mClickListener;

    public PeliculaListAdapter(Context context, List<ListModelPelicula> peliculasList) {
        this.context = context;
        this.peliculasList = peliculasList;
    }

    public void setMovieList(List<ListModelPelicula> peliculasList) {
        this.peliculasList = peliculasList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PeliculaListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaListAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText("Titulo: "+ this.peliculasList.get(position).getTitle().toString());
        holder.tvDescription.setText("Calificacion: "+ this.peliculasList.get(position).getVote_average());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+this.peliculasList.get(position).getPoster_path())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(this.peliculasList != null) {
            return this.peliculasList.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitlePelicula);
            tvDescription = (TextView)itemView.findViewById(R.id.tvCalificacion);
            imageView = (ImageView)itemView.findViewById(R.id.imgPelicula);
            itemView.setOnClickListener(this::onClick);
        }

        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
