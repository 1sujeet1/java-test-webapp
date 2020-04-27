package com.example.javatest.dto.responsedto;

import java.util.ArrayList;
import java.util.List;

import com.example.javatest.dto.requestdto.UpdateMovieRequestDto;

/**
 * MovieResponseDto
 */
public class MovieResponseDto {

    List<Movie> movies = new ArrayList<Movie>() ;
    private int totalData;
    private int totalPage;
    
    public static class Movie extends UpdateMovieRequestDto{        
    }

    

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
          
    
}