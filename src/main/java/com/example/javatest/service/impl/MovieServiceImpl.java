package com.example.javatest.service.impl;

import java.util.List;

import com.example.javatest.exceptions.NotExistsException;
import com.example.javatest.model.Movie;
import com.example.javatest.repository.MovieRepository;
import com.example.javatest.service.MovieService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    private ModelMapper modelMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }
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
        if(movie == null){
           throw new NotExistsException(String.format("movie not found with id {}", movieId));
        }
        return movie;

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
