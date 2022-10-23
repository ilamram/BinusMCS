package id.mobile.ilhamr.Fragment;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

import id.mobile.ilhamr.Adapter.MovieAdapter;
import id.mobile.ilhamr.Model.MovieModel;
import id.mobile.ilhamr.R;


public class HomeFragment extends Fragment {

    RecyclerView rvMovies;
    MovieAdapter movieAdapter;
    LinearLayoutManager lnrLayoutManager;
    MovieModel movieModel;
    MovieModel movieModel1;
    ArrayList<MovieModel> movieModelsArrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvMovies = view.findViewById(R.id.rv_movie_list);

        movieModelsArrayList = new ArrayList<>();
        movieModel = new MovieModel();
        movieModel.setImgDrawable(R.drawable.breakingbad);
        movieModel.setMovieName("Breaking Bad");
        movieModel.setMoviePrice("Rp. 500.000,00");
        movieModel.setMovieDescription("Walter White is on the hustle");
        movieModelsArrayList.add(movieModel);

        movieModel1 = new MovieModel();
        movieModel1.setImgDrawable(R.drawable.megan);
        movieModel1.setMovieName("MEGAN");
        movieModel1.setMoviePrice("Rp. 500.000,00");
        movieModel1.setMovieDescription("The doll that slays");
        movieModelsArrayList.add(movieModel1);

        lnrLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvMovies.setLayoutManager(lnrLayoutManager);
        movieAdapter = new MovieAdapter(getActivity(), movieModelsArrayList);
        rvMovies.setAdapter(movieAdapter);

        return view;
    }
}