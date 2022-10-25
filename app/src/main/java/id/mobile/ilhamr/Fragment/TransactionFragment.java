package id.mobile.ilhamr.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.mobile.ilhamr.Adapter.TransactionAdapter;
import id.mobile.ilhamr.Model.TransactionModel;
import id.mobile.ilhamr.MovieListener;
import id.mobile.ilhamr.R;


public class TransactionFragment extends Fragment {
    final static String DATA_RECEIVE = "data_receive";
    RecyclerView rvTransaction;
    TransactionAdapter transactionAdapter;
    LinearLayoutManager lnrLayoutManager;
    TransactionModel transactionModel;
    ArrayList<TransactionModel> transactionModelArrayList;
    String movieName, moviePrice;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        rvTransaction = view.findViewById(R.id.rv_transactions);
        lnrLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTransaction.setLayoutManager(lnrLayoutManager);
        transactionAdapter = new TransactionAdapter(getActivity(), transactionModelArrayList);
        rvTransaction.setAdapter(transactionAdapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            Log.e("TAG", "onStart: " );
            transactionModelArrayList = new ArrayList<>();
            transactionModel = new TransactionModel();
            transactionModel.setMovieName(args.getString("movieName"));
            transactionModel.setMoviePrice(args.getString("moviePrice"));
            transactionModelArrayList.add(transactionModel);

        }else{
            Log.e("TAG", "onStarts: " );

        }

    }

}