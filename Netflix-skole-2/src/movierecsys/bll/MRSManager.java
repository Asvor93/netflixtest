/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package movierecsys.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movierecsys.be.Movie;
import movierecsys.be.Rating;

import movierecsys.be.User;
import movierecsys.bll.exception.MovieRecSysException;
import movierecsys.dal.MovieDAO;
import movierecsys.dal.MovieDbDAO;
import movierecsys.dal.RatingDbDAO;
import movierecsys.dal.UserDbDAO;


/**
 *
 * @author pgn
 */
public class MRSManager implements MRSLogicFacade {

    private final MovieDbDAO movieDbDAO;
    private final UserDbDAO userDbDAO;
    private final RatingDbDAO ratingDbDAO;
    
    public MRSManager()
    {
        movieDbDAO = new MovieDbDAO();
        userDbDAO = new UserDbDAO();
        ratingDbDAO = new RatingDbDAO();
    }
    
    @Override
    public List<Rating> getRecommendedMovies(User user)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getAllTimeTopRatedMovies()
    {
       List<Movie> topRated = new ArrayList<>();
       

        try
        {
            List<Rating> allRatings = ratingDbDAO.getAllRatings();
            
            for (Rating x : allRatings)
            {
             boolean found = false;
                for(Movie y : topRated){
                  if (y.getId()==x.getMovie()){
                     y.addCounter();
                     y.addRating(x.getRating());
                     found=true;}
                }
             if (found==false){
                 Movie toAdd = new Movie(x.getMovie(), 0, "");
                 toAdd.addRating(x.getRating());
                 toAdd.addCounter();
                 topRated.add(toAdd);
             }    
                
            }
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
   
 topRated.sort( Comparator.comparing( Movie::getAverage ) );
 Collections.reverse(topRated);
 
        try
        {
            List<Movie>allMovies=movieDbDAO.getAllMovies();
            for (Movie l : topRated)
            {
                for (Movie k : allMovies){
                    if (k.getId()==l.getId()){
                       l.setTitle(k.getTitle());
                       l.setYear(k.getYear());
                    }
                }
                    
            }
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
  

  return topRated;
    }

    @Override
    public List<Movie> getMovieReccomendations(User user)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> searchMovies(String query)
    {
    ArrayList<Movie> searchMovies = new ArrayList<>();
    List<Movie> allMovies= new ArrayList<>();
        try
        {
            allMovies = movieDbDAO.getAllMovies();
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Movie x : allMovies){
         
            if (x.getTitle().toLowerCase().contains(query.toLowerCase())){
                searchMovies.add(x);
                
            }
                
        }
  
    return searchMovies;
    }

    @Override
    public Movie createMovie(int year, String title)
    {
    Movie newMovie = null;
        try
        {
            newMovie = movieDbDAO.createMovie(year, title);
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    return newMovie;
     
    }

    @Override
    public void updateMovie(Movie movie)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMovie(Movie movie)
    {
        try
        {
            movieDbDAO.deleteMovie(movie);
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void rateMovie(Movie movie, User user, int rating)
    {
    Rating ratingObject = new Rating(movie.getId(),user.getId(),rating);
        try
        {
            ratingDbDAO.createRating(ratingObject);
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User createNewUser(String name)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserById(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllUsers()
    {
       List<User> allUsers = null;
        try
        {
            allUsers=userDbDAO.getAllUsers();
            return allUsers;
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       return allUsers;
    }

    /**
     * Gets all movies.
     * @return List of movies.
     * @throws MovieRecSysException
     */
    @Override
    public List<Movie> getAllMovies() throws MovieRecSysException
    {
        try
        {
            return movieDbDAO.getAllMovies();
        } catch (IOException ex)
        {
//            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex); You could log an exception
            throw new MovieRecSysException("Could not read all movies. Cause: " + ex.getMessage());
        }
    }

}
