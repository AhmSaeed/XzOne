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
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import xzone.Client;

/**
 * FXML Controller class
 *
 * @author khattab
 */
public class ServerConnectionController implements Initializable {

    @FXML
    private Button connectBtn;
    @FXML
    private TextField ipTf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        ipTf.setText("127.0.0.1");
        System.out.println(",,");
        //new Client();
        System.out.println(",");
    }

    @FXML
    private void connectToServer(ActionEvent event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (ipTf.getText().equals("")) {
                    Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Connection");
                            alert.setHeaderText("Connetion Failure");
                            alert.setContentText("enter ip!!");
                            alert.showAndWait();
                        });
                } else {

                    if (Client.connect(ipTf.getText())) {
                        final Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/views/SignInView.fxml"));
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    ((BorderPane) connectBtn.getScene().getRoot()).setCenter(root);
                                }
                            });

                        } catch (IOException ex) {
                            Logger.getLogger(ServerConnectionController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Client.recieving();
                    } else {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Connection");
                            alert.setHeaderText("Connetion Failure");
                            alert.setContentText("The Server offline or Wrong IP!!");
                            alert.showAndWait();
                        });

                    }
                }
            }

        }).start();

    }
    /*Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };*/
 /*sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                if (Client.connect(ipTf.getText())) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/views/SignInView.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(ServerConnectionController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ((BorderPane) connectBtn.getScene().getRoot()).setCenter(root);
                    Client.recieving();
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Connection");
                        alert.setHeaderText("Connetion Failure");
                        alert.setContentText("The Server offline or Wrong IP!!");
                        alert.showAndWait();
                }
            }
        });*/
}
