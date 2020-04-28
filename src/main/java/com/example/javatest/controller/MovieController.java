package com.example.javatest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.example.javatest.dto.requestdto.SaveMovieRequestDto;
import com.example.javatest.dto.requestdto.UpdateMovieRequestDto;
import com.example.javatest.dto.responsedto.AppResponse;
import com.example.javatest.dto.responsedto.MovieResponseDto;
import com.example.javatest.exceptions.AppErrorResponse;
import com.example.javatest.exceptions.NotExistsException;
import com.example.javatest.model.Category;
import com.example.javatest.model.Movie;
import com.example.javatest.service.CategoryService;
import com.example.javatest.service.MovieService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * MovieController
 */
@RestController
@RequestMapping("/")
public class MovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovieService movieService;
     

    @Autowired
    CategoryService categoryService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    
// save Movie

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<AppResponse> save(
        @Valid @RequestBody SaveMovieRequestDto saveMovieRequestDto,
        BindingResult bindingResult) throws Exception 
        {
            AppResponse appResponse = new AppResponse();
            if (bindingResult.hasErrors()) {
                AppErrorResponse configErrorResponse = new AppErrorResponse();
                List<AppErrorResponse.Error> errors = new ArrayList<>();
                for (FieldError fieldError : bindingResult.getFieldErrors()) {
                    AppErrorResponse.Error rError = new AppErrorResponse.Error();
                    rError.setCode(HttpStatus.BAD_REQUEST.value());
                    rError.setUserMessage(fieldError.getField());
                    rError.setInternalMessage(fieldError.getDefaultMessage());
                    errors.add(rError);
                    LOGGER.error(bindingResult.getFieldError().getDefaultMessage());
                }
                configErrorResponse.setErrors(errors);
                appResponse.setResponse(configErrorResponse);
                return new ResponseEntity<>(appResponse, HttpStatus.BAD_REQUEST);
            }
               
            Movie movie = new Movie();
            modelMapper.map(saveMovieRequestDto, movie);
            if(saveMovieRequestDto.getCategoryId()>0){
                Category category = categoryService.findCategory(saveMovieRequestDto.getCategoryId());
                if(category != null){
                    movie.setCategory(category);
                }                
            }            
            movie.setCreatedOn(new Date());
            movie.setUpdatedOn(new Date());
            movie = movieService.save(movie);
            appResponse.setDescription("movie saved successfully");
            appResponse.setResponse(movie);
            return new ResponseEntity<AppResponse>(appResponse, HttpStatus.CREATED);
    }


// update movie 

    @PatchMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<AppResponse> update(@Valid @RequestBody UpdateMovieRequestDto updateMovieRequestDto,
        BindingResult bindingResult) throws Exception 
        {
            AppResponse appResponse = new AppResponse();
            MovieResponseDto.Movie movieResponse = new MovieResponseDto.Movie();               
            Movie existingMovie  = movieService.findMovie(updateMovieRequestDto.getMovieId());
            if(existingMovie == null){
                throw new NotExistsException("movie not found");
            }        
            if(updateMovieRequestDto.getTitle() != null){
                existingMovie.setTitle(updateMovieRequestDto.getTitle());
            }
            if(updateMovieRequestDto.getStarRating()>0){
                existingMovie.setStarRating(updateMovieRequestDto.getStarRating());
            }
            if(updateMovieRequestDto.getCategoryId()>0){
                Category newCategory = categoryService.findCategory(updateMovieRequestDto.getCategoryId());
                if(newCategory != null){
                    existingMovie.setCategory(newCategory);
                }            
            }
            existingMovie = movieService.save(existingMovie); 
            modelMapper.map(existingMovie, movieResponse); 
            movieResponse.setCategoryId(existingMovie.getCategory().getId());     	
            appResponse.setDescription("movie updated successfully");
            appResponse.setResponse(movieResponse);
            return new ResponseEntity<>(appResponse, HttpStatus.CREATED);
    }

  // user retrives list of movies

    @GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<AppResponse> getMovies(@RequestParam(defaultValue = "0") Integer pageNo, 
        @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy)
        {              
        AppResponse appResponse = new AppResponse();
        MovieResponseDto moviesResponse = new MovieResponseDto();
        Pageable pageable = PageRequest.of(pageNo, pageSize, org.springframework.data.domain.Sort.by("id").ascending());
        Page<Movie> moviesPageData = movieService.getAllMovies(pageable);
        List<Movie> moviesList = new ArrayList<Movie>();
        if(!moviesPageData.isEmpty()){
            moviesList = moviesPageData.getContent();
        }
        for(Movie movie : moviesList){
            MovieResponseDto.Movie movieR = new MovieResponseDto.Movie();
            modelMapper.map(movie, movieR);
            movieR.setCategoryId(movie.getCategory() != null ? movie.getCategory().getId() : null);
            movieR.setMovieId(movie.getId());
            moviesResponse.getMovies().add(movieR);
        }
        moviesResponse.setTotalData((int)moviesPageData.getTotalElements());
        moviesResponse.setTotalPage((int)moviesPageData.getTotalPages());
		appResponse.setResponse(moviesResponse);
		appResponse.setDescription("movie list");
		return new ResponseEntity<>(appResponse, HttpStatus.OK);
	}


  // user gtrives a movie by ID

    @GetMapping(value = "/movie/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<AppResponse> getMovie(@PathVariable String movieId)
        {      
            AppResponse appResponse = new AppResponse();            
            MovieResponseDto.Movie moviesResponse = new MovieResponseDto.Movie();        
            Movie movie = movieService.findMovie(Integer.parseInt(movieId));
            modelMapper.map(movie, moviesResponse);
            moviesResponse.setMovieId(movie.getId());
            moviesResponse.setCategoryId(movie.getCategory() != null ? movie.getCategory().getId() : null);
               
            appResponse.setResponse(moviesResponse);
            appResponse.setDescription("your movie");
            return new ResponseEntity<>(appResponse, HttpStatus.OK);
	    }


  // user deleted a movie

    @DeleteMapping(value = "/movie/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<AppResponse> deleteMovie(@PathVariable String movieId) throws Exception 
        {
            AppResponse appResponse = new AppResponse();		
            Movie movie = movieService.findMovie(Integer.parseInt(movieId));
            if (movie == null) {
                throw new NotExistsException("movie not exists");
            }
            else{
                movieService.delete(movie);
                appResponse.setDescription("movie deleted successfully");
            }
            appResponse.setResponse(null);
            return new ResponseEntity<>(appResponse, HttpStatus.OK);
        }
    

// creating category

@PostMapping(value = "/save/category/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<AppResponse> saveCategory(@RequestParam String categoryName) throws Exception 
        {
            AppResponse appResponse = new AppResponse(); 
              
            Category existingCategory = categoryService.getCategory(categoryName);
            Category category = null;
            appResponse.setResponse(null);
            appResponse.setDescription("category not saved");
            if(existingCategory == null){
                category = new Category();
                category.setCategory(categoryName);
                category = categoryService.save(category) ; 
                appResponse.setResponse(category);
                appResponse.setDescription("category saved successfully");
            } 
            else{
                appResponse.setDescription("category alredy found");
            }
            return new ResponseEntity<AppResponse>(appResponse, HttpStatus.CREATED);
    }

}