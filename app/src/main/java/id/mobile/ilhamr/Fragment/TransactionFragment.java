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


public class TransactionFragment extends Fragment{

    RecyclerView rvTransaction;
    TransactionAdapter transactionAdapter;
    LinearLayoutManager lnrLayoutManager;
    TransactionModel transactionModel;
    String moviesName, moviesPrice, moviesRating, moviesCountry;
    ArrayList<TransactionModel> transactionModelArrayList;
    MovieListener movieListener;
    int movieImg;
    public TransactionFragment(String moviesName, String moviesPrice,  String moviesCountry, String moviesRating, int movieImg){
        this.moviesName = moviesName;
        this.moviesPrice = moviesPrice;
        this.moviesCountry = moviesCountry;
        this.moviesRating = moviesRating;
        this.movieImg = movieImg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        rvTransaction = view.findViewById(R.id.rv_transactions);
        if(moviesName != null) {
            transactionModelArrayList = new ArrayList<>();
            transactionModel = new TransactionModel();
            transactionModel.setMoviePrice(moviesPrice);
            transactionModel.setMovieName(moviesName);
            transactionModel.setMoviesRating(moviesRating);
            transactionModel.setMoviesCountry(moviesCountry);
            transactionModel.setImgMovie(movieImg);
            transactionModelArrayList.add(transactionModel);
        }

        lnrLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTransaction.setLayoutManager(lnrLayoutManager);
        transactionAdapter = new TransactionAdapter(getActivity(), transactionModelArrayList);
        rvTransaction.setAdapter(transactionAdapter);

        return view;
    }


}