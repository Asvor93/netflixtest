/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;


import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import movierecsys.be.Movie;
import movierecsys.be.User;
import movierecsys.bll.MRSManager;
import movierecsys.bll.exception.MovieRecSysException;
import movierecsys.dal.MovieDAO;
import movierecsys.gui.model.MovieModel;

/**
 *
 * @author pgn
 */
public class MovieRecController implements Initializable
{


    /**
     * The TextField containing the query word.
     */
    @FXML
    private ListView<Movie> lstMovies;

    private MovieModel movieModel;
    @FXML
    private Button søgbtn;
    @FXML
    private TextField txtMovieSearch;
    @FXML
    private Button addMovie;
    @FXML
    private TextField name;
    @FXML
    private TextField year;
    
    MRSManager manager = new MRSManager();
    private ComboBox<String> comboBox;
    
    
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private Button remove;
    
    private User userLogin;
    @FXML
    private Label navn;
    @FXML
    private Button Ratings;
    @FXML
    private BorderPane anchorPane2;
    @FXML
    private TextField ratingInt;
    @FXML
    private Button rateMovie;


    public MovieRecController()
    {
        try
        {
            movieModel = new MovieModel();
        } catch (MovieRecSysException ex)
        {
            displayError(ex);
            System.exit(0);
        } 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       
        lstMovies.setItems(movieModel.getMovies());   

    }

    /**
     * Displays errormessages to the user.
     * @param ex The Exception
     */
    private void displayError(Exception ex)
    {
        //TODO Display error properly
        System.out.println(ex.getMessage());
        ex.printStackTrace();
    }

    @FXML
    private void FindMovie(ActionEvent event)
         
    {
      String input = txtMovieSearch.getText();
      
      
      ObservableList<Movie> movieList = FXCollections.observableArrayList(manager.searchMovies(input));
      lstMovies.setItems(movieList);
      
      
      
      
      
    }

    @FXML
    private void addMovie(ActionEvent event) throws IOException
    {
    String navn = name.getText();
    String aar = year.getText();
    int aartal = Integer.parseInt(aar);
    
    movieModel.createMovie(aartal, navn);
    
    name.clear();
    year.clear();
    
//    
    }

    @FXML
    private void removeButton(ActionEvent event)
    {
    Movie toDelete = lstMovies.getSelectionModel().getSelectedItem();
    movieModel.deleteMovie(toDelete);
    
    }
    
    public void setUser(User user)
    {
    userLogin=user;
    navn.setText(user.getName());
    
    
    }

    @FXML
    private void Ratings(ActionEvent event) throws IOException
    {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movierecsys/gui/view/Rating.fxml"));
        Parent root = (Parent)loader.load();
        
        RatingController rController = loader.getController();
        rController.setUser(userLogin);
        rController.setListView();
        Stage stage = (Stage) anchorPane2.getScene().getWindow();   // skriv new stage hvis det skal være i et nyt vindue
        stage.setScene(new Scene(root));
        stage.show();
        
    
    }

    @FXML
    private void rateMovieButton(ActionEvent event)
    {
    Movie toRate = lstMovies.getSelectionModel().getSelectedItem();
    int rat = Integer.parseInt(ratingInt.getText());
    movieModel.rateMovie(toRate, userLogin, rat);
    ratingInt.clear();
    }

    @FXML
    private void recMovies(ActionEvent event) throws IOException
    {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/movierecsys/gui/view/Rec.fxml"));
        Parent root = (Parent)loader.load();
        
        RecController recController = loader.getController();
        recController.setUser(userLogin);
     
        Stage stage = (Stage) anchorPane2.getScene().getWindow();   // skriv new stage hvis det skal være i et nyt vindue
        stage.setScene(new Scene(root));
        stage.show();
        
    
    }

    @FXML
    private void topRated(ActionEvent event)
    {
    lstMovies.setItems(movieModel.getAllTimeBestMovies());
    }


    
    


}
