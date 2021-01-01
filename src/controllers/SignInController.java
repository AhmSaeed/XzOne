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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import xzone.Client;
import xzone.Constants;

/**
 * FXML Controller class
 *
 * @author khattab
 */
public class SignInController implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;
    @FXML
    private Button loginBtn;
    @FXML
    private Button signUpBtn;
    @FXML
    private Label userNameLbl;
    @FXML
    private Label passwordLbl;
    ArrayList<String> information = new ArrayList<String>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void goToSignUP(ActionEvent event) {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/views/SignUpView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ((BorderPane) signUpBtn.getScene().getRoot()).setCenter(root);

    }

    @FXML
    private void goToAvailablePlayerScreen(ActionEvent event) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                login();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (Client.isLogged) {
                    Client.playerName=userName.getText();
                    final Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/views/AvailablePlayersView.fxml"));
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ((BorderPane) loginBtn.getScene().getRoot()).setCenter(root);
                            }
                        });
                    } catch (IOException ex) {
                        Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        }).start();
    }

    private void login() {
        if (userName.getText().equals("") && passWord.getText().equals("")) {
            Platform.runLater(() -> {
                userNameLbl.setText("enter your name");
                passwordLbl.setText("enter your password");
            });
        } else {
            Platform.runLater(() -> {
                userNameLbl.setText("");
                passwordLbl.setText("");
            });
            information.add(Constants.WANT_TO_LOGIN);
            information.add(userName.getText());
            information.add(passWord.getText());
            System.out.println("After" + information);
            try {
                Client.objectOutputStream.writeObject(information);
                Client.objectOutputStream.flush();
                System.out.println("Sent player1: " + information);
            } catch (IOException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
