package id.mobile.ilhamr.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.mobile.ilhamr.Activity.MovieDetailActivity;
import id.mobile.ilhamr.Model.MovieModel;
import id.mobile.ilhamr.MovieListener;
import id.mobile.ilhamr.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    Context context;
    ArrayList<MovieModel> movieModelsArrayList;
    MovieListener mCallback;

    public MovieAdapter(Context context, ArrayList<MovieModel> movieModelsArrayList, MovieListener movieListener){
        this.context = context;
        this.movieModelsArrayList = movieModelsArrayList;
        this.mCallback = movieListener;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_cover, parent, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieHolder holder, int position) {
        Picasso.Builder builder = new Picasso.Builder(context);
        Picasso picasso = builder.build();
        picasso.load(movieModelsArrayList.get(position).getImgDrawable())
                .into(holder.ivMovie, new Callback() {
                    @Override
                    public void onSuccess() {
                        //dont need
                    }

                    @Override
                    public void onError(Exception e) {
                        //unnecessary and its ok to be empty
                    }
                });

        holder.cvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("Movie Title", movieModelsArrayList.get(position).getMovieName());
                intent.putExtra("Movie Rating", movieModelsArrayList.get(position).getMovieRating());
                intent.putExtra("Movie Description", movieModelsArrayList.get(position).getMovieDescription());
                intent.putExtra("Movie Country", movieModelsArrayList.get(position).getMovieCountry());
                intent.putExtra("Movie Price", movieModelsArrayList.get(position).getMoviePrice());
                intent.putExtra("Movie Image", movieModelsArrayList.get(position).getImgDrawable());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieModelsArrayList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        ImageView ivMovie;
        CardView cvMovie;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            ivMovie = itemView.findViewById(R.id.iv_movies);
            cvMovie = itemView.findViewById(R.id.cv_movie);
        }
    }



}
