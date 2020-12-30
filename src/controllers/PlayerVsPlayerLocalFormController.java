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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.PlayersLocal;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class PlayerVsPlayerLocalFormController implements Initializable {

    @FXML
    private Label playerXLbl;
    @FXML
    private TextField player1Nametf;
    @FXML
    private Label playerOLbl;
    @FXML
    private TextField plater2NameTf;
    @FXML
    private Button letsPlayBtn;
    @FXML
    private Label playerXErrorMessageLbl;
    @FXML
    private Label playerOErrorMessageLbl;
    private BorderPane dashboard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void onLetsPlayPress(ActionEvent event){
        String player1Name = player1Nametf.getText();
        String player2Name = plater2NameTf.getText();
        
        if(notValidPlayerName(player1Name)){
            playerXErrorMessageLbl.setText("Player X name is empty!");
            return;
        }
        else playerXErrorMessageLbl.setText("");
        if(notValidPlayerName(player2Name)){
            playerOErrorMessageLbl.setText("Player O name is empty!");
            return;
        }
        else playerOErrorMessageLbl.setText("");
        
        NavigationHolder navigationHolder = NavigationHolder.getInstance();
        navigationHolder.setPlayers(new PlayersLocal(player1Name, player2Name));
        
        dashboard = ((BorderPane)(((Button)event.getSource()).getScene().getRoot()));
        navigatePages("/views/XOGameplayView.fxml");
    }
    
    private void navigatePages(String page) {

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(page));
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsPlayerLocalFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        dashboard.setCenter(root);
    }
    
    private boolean notValidPlayerName(String name){
        if(name == null || name.trim().length() == 0){
            return true;
        }
        return false;
    }
}
