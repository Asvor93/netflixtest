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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;
import movierecsys.dal.RatingDbDAO;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class RatingController implements Initializable
{

    @FXML
    private ListView<Rating> listViewRatings;
    @FXML
    private Label userName;
    
    private User userLogin;
    @FXML
    private AnchorPane anchorPane2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }    
       public void setUser(User user)
    {
    userLogin=user;
    userName.setText(user.getName());

    }
       public void setListView()
       {
           //Burde køre igennem model -> MRSManager->DAO  
           RatingDbDAO ratingData = new RatingDbDAO();
        try
        {
            ObservableList<Rating> ratingList = FXCollections.observableArrayList(ratingData.getRatings(userLogin));
            listViewRatings.setItems(ratingList);
        } catch (IOException ex)
        {
            Logger.getLogger(RatingController.class.getName()).log(Level.SEVERE, null, ex);
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
    
}
