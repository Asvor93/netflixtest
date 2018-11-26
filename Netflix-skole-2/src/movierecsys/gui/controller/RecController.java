/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;
import movierecsys.bll.MRSManager;
import movierecsys.bll.exception.MovieRecSysException;
import movierecsys.gui.model.MovieModel;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class RecController implements Initializable
{

    @FXML
    private ListView<Movie> listView;
    @FXML
    private AnchorPane anchorPane2;
    
    private User userLogin;
    
    private MovieModel movieModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            movieModel= new MovieModel();
        } catch (MovieRecSysException ex)
        {
            Logger.getLogger(RecController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void back(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movierecsys/gui/view/MovieRecView.fxml"));
        Parent root = (Parent)loader.load();
//        
        MovieRecController mController = loader.getController();
        mController.setUser(userLogin);
        Stage stage = (Stage) anchorPane2.getScene().getWindow();   // skriv new stage hvis det skal være i et nyt vindue
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void setUser(User user)
    {
    userLogin=user;
    }
    
    public void setListView(){
            ObservableList<Movie> recMovies = FXCollections.observableArrayList(movieModel.getUserRec(userLogin));
            listView.setItems(recMovies);
    }
}
