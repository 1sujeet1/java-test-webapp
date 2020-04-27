package com.example.javatest.dto.requestdto;

import org.springframework.lang.NonNull;

/**
 * UpdateMovieRequestDto
 */
public class UpdateMovieRequestDto {

    @NonNull
    public int movieId;
    public String title;
    public double starRating;
    public int categoryId;

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setStarRating(double starRating) {
        this.starRating = starRating;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

  
    
}