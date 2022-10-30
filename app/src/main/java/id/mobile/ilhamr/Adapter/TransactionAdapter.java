package id.mobile.ilhamr.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.mobile.ilhamr.Activity.LoginActivity;
import id.mobile.ilhamr.Model.TransactionModel;
import id.mobile.ilhamr.MovieListener;
import id.mobile.ilhamr.R;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {

    Context context;
    ArrayList<TransactionModel> transactionModelArrayList;
    MovieListener movieListenerLister;

    public TransactionAdapter(Context context, MovieListener movieListener, ArrayList<TransactionModel> transactionModelArrayList){
        this.context = context;
        this.movieListenerLister = movieListener;
        this.transactionModelArrayList = transactionModelArrayList;
    }

    @NonNull
    @Override
    public TransactionAdapter.TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_transaction, parent, false);
        return new TransactionAdapter.TransactionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.TransactionHolder holder, int position) {
        holder.tvMovieTitle.setText(transactionModelArrayList.get(position).getMovieName());
        holder.tvHargaMovie.setText(transactionModelArrayList.get(position).getMoviePrice());
        holder.tvMoviesRating.setText(transactionModelArrayList.get(position).getMoviesRating());
        holder.tvMoviesCountry.setText(transactionModelArrayList.get(position).getMoviesCountry());
        holder.tvQuantity.setText(String.valueOf(transactionModelArrayList.get(position).getMovieQty()));
        Picasso.Builder builder = new Picasso.Builder(context);
        Picasso picasso = builder.build();
        picasso.load(transactionModelArrayList.get(position).getImgMovie())
                .into(holder.ivCover, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                    }
                });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringQuantity = holder.tvQuantity.getText().toString();
                Integer quantity = Integer.parseInt(stringQuantity);
                quantity++;
                holder.tvQuantity.setText(quantity.toString());
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringQuantity = holder.tvQuantity.getText().toString();
                Integer quantity = Integer.parseInt(stringQuantity);
                if(quantity <= 1){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setMessage("Are you sure, you want to set your purchase quantity to 0 ?");
                    alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    movieListenerLister.sendArrayList(position, true);
                                }
                            })
                            .show();
                }else{
                    quantity--;
                }
                holder.tvQuantity.setText(quantity.toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        if(transactionModelArrayList == null){
            Log.e("TAG", "getItemCount: here" );
            return 0;
        }else {
            Log.e("TAG", "getItemCount:" );
            return transactionModelArrayList.size();
        }
    }

    public class TransactionHolder extends RecyclerView.ViewHolder{

        TextView tvMovieTitle, tvHargaMovie, tvMoviesRating, tvMoviesCountry, tvQuantity;
        ImageView ivCover, remove, add;

        public TransactionHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieTitle = itemView.findViewById(R.id.tv_movies_title);
            tvHargaMovie = itemView.findViewById(R.id.tv_movies_price);
            tvMoviesRating = itemView.findViewById(R.id.tv_movie_rating);
            tvMoviesCountry = itemView.findViewById(R.id.tv_movie_country);
            ivCover = itemView.findViewById(R.id.iv_image);
            remove = itemView.findViewById(R.id.remove);
            add = itemView.findViewById(R.id.add);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
        }
    }
}
