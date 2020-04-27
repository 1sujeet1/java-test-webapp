package com.example.javatest.service.impl;

import java.util.List;

import com.example.javatest.model.Movie;
import com.example.javatest.repository.MovieRepository;
import com.example.javatest.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findMovie(int movieId) {
        Movie movie = movieRepository.findById(movieId);
        return (movie != null ? movie : null);
    }

    @Override
    public void delete(Movie movie) {
        movieRepository.delete(movie);

    }

  
    @Override
    public Page<Movie> getAllMovies(Pageable pageable) {
        return movieRepository.getAllMovies(pageable);
    }

}
