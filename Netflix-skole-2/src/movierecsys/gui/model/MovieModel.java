/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movierecsys.be.Movie;
import movierecsys.be.User;
import movierecsys.bll.MRSLogicFacade;
import movierecsys.bll.MRSManager;
import movierecsys.bll.exception.MovieRecSysException;

/**
 *
 * @author pgn
 */
public class MovieModel
{

    private ObservableList<Movie> movies;
    private ObservableList<User> users;
    private ObservableList<Movie> topMovies;
    private ObservableList<Movie> recMovies;

    private MRSLogicFacade logiclayer;

    public MovieModel() throws MovieRecSysException
    {
        movies = FXCollections.observableArrayList();
        users = FXCollections.observableArrayList();
        topMovies = FXCollections.observableArrayList();
        recMovies = FXCollections.observableArrayList();
        logiclayer = new MRSManager();
        movies.addAll(logiclayer.getAllMovies());
        
        
        
    }

    /**
     * Gets a reference to the observable list of Movies.
     * @return List of movies.
     */
    public ObservableList<Movie> getMovies()
    {
        return movies;
    }

    public void createMovie(int year, String title)
    {
        Movie movie = logiclayer.createMovie(year, title);
        movies.add(movie);
    }
    
    public void deleteMovie(Movie movie)
    {
        logiclayer.deleteMovie(movie);
        movies.remove(movie);
    }
    
    public ObservableList<User> getUsers()
    {
        users.addAll(logiclayer.getAllUsers());
        return users;
    }
    
    public void rateMovie(Movie movie, User user, int rating)
    {
        logiclayer.rateMovie(movie, user, rating);
    }
    
    
    public ObservableList<Movie> getAllTimeBestMovies()
    {
    topMovies.addAll(logiclayer.getAllTimeTopRatedMovies());
    return topMovies;
    }
    
    public ObservableList<Movie> getUserRec(User user){
    recMovies.addAll(logiclayer.getMovieReccomendations(user));
    return recMovies;
    }
    
}
