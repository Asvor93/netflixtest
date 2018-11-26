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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import movierecsys.be.User;
import movierecsys.bll.exception.MovieRecSysException;
import movierecsys.dal.UserDbDAO;
import movierecsys.gui.model.MovieModel;



/**
 * FXML Controller class
 *
 * @author Philip
 */
public class LoginController implements Initializable
{

    @FXML
    private ListView<User> listView;
    @FXML
    private Button login;
    
    UserDbDAO userData;
    @FXML
    private AnchorPane rootPane2;
    
    private MovieModel movieModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
      
        try
        {
            movieModel=new MovieModel();
            ObservableList<User> userList = FXCollections.observableArrayList(movieModel.getUsers());
            listView.setItems(userList);
        } catch (MovieRecSysException ex)
        {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
      
    }    

    @FXML
    private void userLogin(ActionEvent event) throws IOException
    {
        User user = listView.getSelectionModel().getSelectedItem();
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movierecsys/gui/view/MovieRecView.fxml"));
        Parent root = (Parent)loader.load();
        
        MovieRecController mController = loader.getController();
        mController.setUser(user);
        Stage stage = (Stage) rootPane2.getScene().getWindow();   // skriv new stage hvis det skal være i et nyt vindue
        stage.setScene(new Scene(root));
        stage.show();
        
        
    }
 
}
