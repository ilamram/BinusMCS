package id.mobile.ilhamr.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

import id.mobile.ilhamr.Adapter.MovieAdapter;
import id.mobile.ilhamr.Model.MovieModel;
import id.mobile.ilhamr.Model.MovieVolleyModel.FilmsItem;
import id.mobile.ilhamr.Model.TransactionModel;
import id.mobile.ilhamr.R;


public class HomeFragment extends Fragment {

    RecyclerView rvMovies;
    MovieAdapter movieAdapter;
    LinearLayoutManager lnrLayoutManager;
    ArrayList<FilmsItem> filmsModeArrayList;

    public HomeFragment(ArrayList<FilmsItem> filmsModeArrayList){
        this.filmsModeArrayList = filmsModeArrayList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvMovies = view.findViewById(R.id.rv_movie_list);
        lnrLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvMovies.setLayoutManager(lnrLayoutManager);
        movieAdapter = new MovieAdapter(getActivity(), filmsModeArrayList);
        rvMovies.setAdapter(movieAdapter);
        return view;
    }

}