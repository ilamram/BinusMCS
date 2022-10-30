package id.mobile.ilhamr.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.mobile.ilhamr.Activity.MovieDetailActivity;
import id.mobile.ilhamr.Adapter.TransactionAdapter;
import id.mobile.ilhamr.Model.TransactionModel;
import id.mobile.ilhamr.MovieListener;
import id.mobile.ilhamr.R;


public class TransactionFragment extends Fragment implements MovieListener{

    RecyclerView rvTransaction;
    TransactionAdapter transactionAdapter;
    LinearLayoutManager lnrLayoutManager;
    TransactionModel transactionModel;
    String moviesName, moviesPrice, moviesRating, moviesCountry;
    ArrayList<TransactionModel> transactionModelArrayList;
    MovieListener movieListener;
    int movieImg;
    Boolean conditions = false;
    String movieTickets;

    public TransactionFragment(String moviesName, String moviesPrice,  String moviesCountry, String moviesRating, int movieImg, String movieTickets){
        this.moviesName = moviesName;
        this.moviesPrice = moviesPrice;
        this.moviesCountry = moviesCountry;
        this.moviesRating = moviesRating;
        this.movieImg = movieImg;
        this.movieTickets = movieTickets;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        Log.e("TAG", "onCreateView: ");
        rvTransaction = view.findViewById(R.id.rv_transactions);
        //Untuk Aslab, setelah gua cek ternyata tiap kali kita pilih salah satu menu di bottom view navigation menu dia selalu di cycle on create
        //Jadi terpaksa gw buat kondisi boolean untuk tau kalau state dia dimana sekarang. Thx.
        if(!conditions) {
            if (moviesName != null) {
                transactionModelArrayList = new ArrayList<>();
                transactionModel = new TransactionModel();
                transactionModel.setMoviePrice(moviesPrice);
                transactionModel.setMovieName(moviesName);
                transactionModel.setMoviesRating(moviesRating);
                transactionModel.setMoviesCountry(moviesCountry);
                transactionModel.setImgMovie(movieImg);
                transactionModel.setMovieQty(movieTickets);
                transactionModelArrayList.add(transactionModel);
            }
        }else{
            transactionModelArrayList.clear();
            transactionAdapter.notifyDataSetChanged();

        }
        lnrLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTransaction.setLayoutManager(lnrLayoutManager);
        transactionAdapter = new TransactionAdapter(getActivity(), TransactionFragment.this, transactionModelArrayList);
        rvTransaction.setAdapter(transactionAdapter);

        return view;
    }


    @Override
    public void sendArrayList(int transactionModelArrayLists, boolean condition) {
        conditions = condition;
//        transactionModelArrayList.remove(transactionModelArrayLists);
        transactionModelArrayList.clear();
        transactionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG", "onPause: " );
    }
}