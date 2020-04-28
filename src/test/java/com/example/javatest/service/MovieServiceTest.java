package com.example.javatest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.javatest.dto.requestdto.SaveMovieRequestDto;
import com.example.javatest.exceptions.NotExistsException;
import com.example.javatest.model.Category;
import com.example.javatest.model.Movie;
import com.example.javatest.repository.MovieRepository;
import com.example.javatest.service.impl.MovieServiceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

public class MovieServiceTest {

    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void initialze() {
        MockitoAnnotations.initMocks(this);
        movieService = new MovieServiceImpl(movieRepository, new ModelMapper());

    }

    @Test
    public void create_movie_test() throws Exception {
        SaveMovieRequestDto requestDto = new SaveMovieRequestDto();
        requestDto.setCategoryId(1);
        requestDto.setTitle("test title");
        requestDto.setStarRating(3.2f);

        Movie expected = new Movie();
        expected.setCategory(buildCategory(requestDto.getCategoryId()));
        expected.setStarRating(requestDto.getStarRating());
        expected.setTitle(requestDto.getTitle());
        when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(expected);
        Movie movieReq = new Movie();
        modelMapper.map(requestDto, movieReq);
        movieReq.setCategory(buildCategory(1));
        Movie actual = movieService.save(movieReq);
        
        assertEquals(expected, actual, "unecpected result");
    }


    @Test()
    public void test_find_movie_by_Id_if_not_present() {
        when(movieRepository.getOne(Mockito.anyInt())).thenReturn(null);

        assertThrows(NotExistsException.class, () -> {
            movieService.findMovie(2);
        });

    }

    @Test
    public void test_get_all_movies() {
        List<Movie> expected = new ArrayList<>();
        expected.add(buildMovie(1));
        expected.add(buildMovie(2));
        expected.add(buildMovie(3));
        expected.add(buildMovie(4));
        when(movieRepository.findAll()).thenReturn(expected);
        List<Movie> actual = movieService.getAllMovies();
        assertEquals(expected, actual, "data is not same");

    }

    @AfterEach
    public void deinitialize() {
        movieService = null;
        movieRepository = null;
    }

    private Movie buildMovie(Integer id) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setStarRating(2.4f);
        movie.setTitle("test title");
        movie.setCategory(buildCategory(1));
        return movie;
    }

    private Category buildCategory(Integer id) {
        Category Category = new Category();
        Category.setId(id);
        Category.setCategory("test category");
        return Category;
    }
}