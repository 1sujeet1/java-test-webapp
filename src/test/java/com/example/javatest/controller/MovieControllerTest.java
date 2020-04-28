package com.example.javatest.controller;

import com.example.javatest.service.impl.MovieServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MovieControllerTest {
 
    private MovieController movieController;

    @Mock
    private MovieServiceImpl movieService;

    @BeforeEach
    public void before(){
        MockitoAnnotations.initMocks(this);
        movieController=new MovieController(movieService);

    }


}