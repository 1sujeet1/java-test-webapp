package com.example.javatest.dto.requestdto;

import org.springframework.lang.NonNull;

/**
 * SaveMovieRequestDto
 */
public class SaveMovieRequestDto {

    @NonNull
    public String title;
    @NonNull
    public double starRating;
    @NonNull
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

  
    
}