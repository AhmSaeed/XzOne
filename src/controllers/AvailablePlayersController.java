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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private ListView<String> availablePlayersListView;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button logoutBtn;
    Thread th;
    ArrayList<String> information;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //getAvailablePlayers();
        //updateAvailablePlayersList();
    }

    @FXML
    private void refeshAvialablePlayers(ActionEvent event) {
        getAvailablePlayers();
        updateAvailablePlayersList();
    }

    private void getAvailablePlayers() {
        new Thread (){
            @Override
            public void run() {
                information = new ArrayList<>();
                information.add(Constants.GET_AVAILABLE_PLAYERS);
                try {
                    Client.objectOutputStream.writeObject(information);
                    Client.objectOutputStream.flush();
                } catch (IOException ex) {
                    Logger.getLogger(AvailablePlayersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
                
          

    }

    private void updateAvailablePlayersList() {
        if (Client.getAvailablePlayers) {
            
                availablePlayersListView.getItems().clear();
                availablePlayersListView.getItems().addAll(Client.availablePlayersList);
        }
        //Client.availablePlayersList=new ArrayList<>();
    }

}
