/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import xzone.Client;
import xzone.Constants;

/**
 * FXML Controller class
 *
 * @author Rahma Ayman
 */
public class AvailablePlayersController implements Initializable {

    @FXML
    private Label availablePlayersLbl;
    @FXML
    private ListView<?> availablePlayersListView;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button playBtn;
    
    ArrayList<String> playingRequestPacket;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        playingRequestPacket = new ArrayList<String>();
    }    

    @FXML
    private void sendPlayRequrest(ActionEvent event) {
        
        playingRequestPacket.add(Constants.WANT_TO_PLAY);
        playingRequestPacket.add("asser");
        playingRequestPacket.add("khattab");
        try {
            System.out.println("Hello from send play request");
            Client.objectOutputStream.writeObject(playingRequestPacket);
        } catch (IOException ex) {
            Logger.getLogger(AvailablePlayersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(Client.isReadyToPlay)
            navigatePages(event);
            
        
        
    }
    
    private void navigatePages(ActionEvent e){
        
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/PlayerVsPlayerOnlineView.fxml"));
            ((BorderPane) ((Button)e.getSource()).getScene().getRoot()).setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(AvailablePlayersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
