package id.mobile.ilhamr.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import id.mobile.ilhamr.DBManager;
import id.mobile.ilhamr.Model.MovieVolleyModel.FilmsItem;
import id.mobile.ilhamr.Model.TransactionModel;
import id.mobile.ilhamr.R;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {

    Context context;
    ArrayList<TransactionModel> transactionModelArrayList;

    String movieId;
    DBManager dbManager;
    FilmsItem filmsItem;

    public TransactionAdapter(Context context,  ArrayList<TransactionModel> transactionModelArrayList){
        this.context = context;

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
        dbManager = new DBManager(context);
        holder.tvQuantity.setText(String.valueOf(transactionModelArrayList.get(position).getMovieQty()));
        movieId = transactionModelArrayList.get(position).getMovieID();
        filmsItem = dbManager.getFilmsDataById(movieId);
        holder.tvMovieTitle.setText(filmsItem.getTitle());
        holder.tvMoviesRating.setText(filmsItem.getRating());
        holder.tvMoviesCountry.setText(filmsItem.getCountry());
        holder.tvHargaMovie.setText("$ " + filmsItem.getPrice());
        Picasso.Builder builder = new Picasso.Builder(context);
        Picasso picasso = builder.build();
        picasso.load(filmsItem.getImage())
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
                holder.btnUpdate.setVisibility(View.VISIBLE);
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    holder.btnUpdate.setVisibility(View.VISIBLE);
                String stringQuantity = holder.tvQuantity.getText().toString();
                Integer quantity = Integer.parseInt(stringQuantity);
                if(quantity <= 1){
                    holder.btnUpdate.setVisibility(View.GONE);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setMessage("Are you sure, you want to set your purchase quantity to 0 ?");
                    alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            holder.tvQuantity.setText((transactionModelArrayList.get(position).getMovieQty()));
                            holder.btnUpdate.setVisibility(View.GONE);
                        }
                    });
                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dbManager.deleteQtyTicketByID(transactionModelArrayList.get(position).getTransactionId());
                                    transactionModelArrayList.remove(position);
                                    notifyDataSetChanged();
                                }
                            })
                            .show();
                }else{
                    quantity--;
                }
                holder.tvQuantity.setText(quantity.toString());
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.updateQtyTicket(holder.tvQuantity.getText().toString(), transactionModelArrayList.get(position).getTransactionId());
                holder.btnUpdate.setVisibility(View.GONE);
                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(transactionModelArrayList == null){
            return 0;
        }else {
            return transactionModelArrayList.size();
        }
    }

    public class TransactionHolder extends RecyclerView.ViewHolder{

        TextView tvMovieTitle, tvHargaMovie, tvMoviesRating, tvMoviesCountry, tvQuantity;
        ImageView ivCover, remove, add;
        Button btnUpdate;
        RelativeLayout rlTransaction;

        public TransactionHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieTitle = itemView.findViewById(R.id.tv_movies_title);
            tvHargaMovie = itemView.findViewById(R.id.tv_movies_price);
            tvMoviesRating = itemView.findViewById(R.id.tv_movie_rating);
            tvMoviesCountry = itemView.findViewById(R.id.tv_movie_country);
            ivCover = itemView.findViewById(R.id.iv_image);
            remove = itemView.findViewById(R.id.remove);
            add = itemView.findViewById(R.id.add);
            rlTransaction = itemView.findViewById(R.id.rl_transaction);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            btnUpdate = itemView.findViewById(R.id.btn_update_qty);

        }
    }
}
