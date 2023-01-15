package id.mobile.ilhamr.Model.MovieVolleyModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MovieVolleyModel{

	@SerializedName("films")
	private List<FilmsItem> films;

	public void setFilms(List<FilmsItem> films){
		this.films = films;
	}

	public List<FilmsItem> getFilms(){
		return films;
	}
}