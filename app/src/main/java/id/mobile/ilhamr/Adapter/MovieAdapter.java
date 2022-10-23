package id.mobile.ilhamr.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.mobile.ilhamr.Model.MovieModel;
import id.mobile.ilhamr.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    Context context;
    ArrayList<MovieModel> movieModelsArrayList;


    public MovieAdapter(Context context, ArrayList<MovieModel> movieModelsArrayList){
        this.context = context;
        this.movieModelsArrayList = movieModelsArrayList;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);
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
        holder.tvMovieTitle.setText(movieModelsArrayList.get(position).getMovieName());
        holder.tvDescriptionMovie.setText(movieModelsArrayList.get(position).getMovieDescription());
        holder.tvMoney.setText(movieModelsArrayList.get(position).getMoviePrice());
    }

    @Override
    public int getItemCount() {
        return movieModelsArrayList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        TextView tvMovieTitle, tvDescriptionMovie, tvMoney;
        ImageView ivMovie;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieTitle = itemView.findViewById(R.id.txt_movie_title);
            tvDescriptionMovie = itemView.findViewById(R.id.tv_description_movie);
            tvMoney = itemView.findViewById(R.id.tv_money);
            ivMovie = itemView.findViewById(R.id.iv_movies);
        }
    }
}
