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
import javafx.stage.Stage;
import movierecsys.be.Movie;
import movierecsys.be.User;
import movierecsys.dal.UserDbDAO;



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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
      userData=new UserDbDAO();
        try
        {
            ObservableList<User> userList = FXCollections.observableArrayList(userData.getAllUsers());
            listView.setItems(userList);
        } catch (IOException ex)
        {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }    

    @FXML
    private void userLogin(ActionEvent event) throws IOException
    {
    User user = listView.getSelectionModel().getSelectedItem();
    
//    stage.setScene(scene);
//    stage.show();
//    }
//    
//    privat void setStage (Stage stage)
    }
}
