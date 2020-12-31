/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Player;

/**
 * FXML Controller class
 *
 * @author khattab
 */
public class PlayerVsMachineFormController implements Initializable {

    @FXML
    private Button playBtn;
    @FXML
    private RadioButton xRb;
    @FXML
    private RadioButton oRb;
    @FXML
    private ToggleGroup gamePiece;
    @FXML
    private TextField playerNameTf;
    @FXML
    private Label informLbl;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void goingBackToHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsMachineFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    @FXML
    private void goToGamePlay(ActionEvent event) {
        
        String name = playerNameTf.getText();
        
        if(checkWhiteSpaces()){
            informLbl.setVisible(true);
            return;
        }
        
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource("/views/PlayerVsPlayerOnlineView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsMachineFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sendPlayerData();
        
        ((BorderPane)playBtn.getScene().getRoot()).setCenter(root);
    }
    
    
    private void sendPlayerData(){
        
        String name = playerNameTf.getText();
        int playPiece = 1;
        
        if(xRb.isSelected())
            playPiece = 1;
        else if(oRb.isSelected())
            playPiece = 2;
        
        Player player = new Player(name, playPiece);
        Stage root = ((Stage)playBtn.getScene().getWindow());
        
        root.setUserData(player);
        
    }
    
    private boolean checkWhiteSpaces(){
        
        String name = playerNameTf.getText();
        boolean isAllWhiteSpaces = false;
        
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+$");
        Matcher match = pattern.matcher(name);
        
        isAllWhiteSpaces = !match.matches();
        
        return isAllWhiteSpaces;
    }
    
}
