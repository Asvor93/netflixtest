/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import movierecsys.be.User;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class RecController implements Initializable
{

    @FXML
    private ListView<?> listView;
    @FXML
    private AnchorPane anchorPane2;
    
    private User userLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
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
}
