package id.mobile.ilhamr.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.mobile.ilhamr.Model.TransactionModel;
import id.mobile.ilhamr.R;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {
    Context context;
    ArrayList<TransactionModel> transactionModelArrayList;

    public TransactionAdapter(Context context, ArrayList<TransactionModel> transactionModelArrayList){
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
        holder.tvMovieTitle.setText(transactionModelArrayList.get(position).getMovieName());
        holder.tvHargaMovie.setText(transactionModelArrayList.get(position).getMoviePrice());
    }

    @Override
    public int getItemCount() {
            return 0;
    }

    public class TransactionHolder extends RecyclerView.ViewHolder{

        TextView tvMovieTitle, tvHargaMovie;

        public TransactionHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            tvHargaMovie = itemView.findViewById(R.id.tv_harga_movie);
        }
    }
}
