package com.example.javatest.service;

import java.util.List;

import com.example.javatest.model.Movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {

	Movie save(Movie movie);

	List<Movie> getAllMovies();

	
	Page<Movie> getAllMovies(Pageable pageable);
	
	Movie findMovie(int movieId); 

	void delete(Movie movie);
	
	

}
