/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xzone.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author khattab
 */
public class PlayerVsMachineFormViewController implements Initializable {

    @FXML
    private RadioButton xCB;
    @FXML
    private RadioButton yCB;
    @FXML
    private TextField playerNameTF;
    @FXML
    private Button playBtn;
    @FXML
    private Button backToHome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goingBackToHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsMachineFormViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
