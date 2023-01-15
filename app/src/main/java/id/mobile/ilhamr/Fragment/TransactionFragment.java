package id.mobile.ilhamr.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
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
import id.mobile.ilhamr.DBManager;
import id.mobile.ilhamr.Model.TransactionModel;
import id.mobile.ilhamr.R;


public class TransactionFragment extends Fragment {

    RecyclerView rvTransaction;
    TransactionAdapter transactionAdapter;
    LinearLayoutManager lnrLayoutManager;
    TransactionModel transactionModel;
//    String moviesName, moviesPrice, moviesRating, moviesCountry;
    ArrayList<TransactionModel> transactionModelArrayList;
    int movieImg;
    Boolean conditions = false;
    String movieTickets;
    DBManager dbManager;
    SharedPreferences sharedPreferences;
    int userID;
    int movieID;
//    public TransactionFragment(String moviesName, String moviesPrice,  String moviesCountry, String moviesRating, int movieImg, String movieTickets){
//        this.moviesName = moviesName;
//        this.moviesPrice = moviesPrice;
//        this.moviesCountry = moviesCountry;
//        this.moviesRating = moviesRating;
//        this.movieImg = movieImg;
//        this.movieTickets = movieTickets;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        rvTransaction = view.findViewById(R.id.rv_transactions);
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.savedKey), MODE_PRIVATE);
        userID = sharedPreferences.getInt("userID", 0);
        dbManager = new DBManager(getActivity());
//        if(!conditions) {
                transactionModelArrayList = new ArrayList<>();
                transactionModelArrayList = dbManager.retrieveTransactionDatabyID(userID);
//        }else{
//            transactionModelArrayList.clear();
//            transactionAdapter.notifyDataSetChanged();
//        }
        lnrLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTransaction.setLayoutManager(lnrLayoutManager);
        transactionAdapter = new TransactionAdapter(getActivity(),  transactionModelArrayList);
        rvTransaction.setAdapter(transactionAdapter);
        return view;
    }




}