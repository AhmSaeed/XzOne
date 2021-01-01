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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import xzone.Client;
import xzone.Constants;

/**
 * FXML Controller class
 *
 * @author Rahma Ayman
 */
public class SignUpController implements Initializable {

    @FXML
    private ImageView registerImgVw;
    @FXML
    private Label registerNowLbl;
    @FXML
    private Label userNameLbl;
    @FXML
    private Label passwordLbl;
    @FXML
    private Label confirmPasswordLbl;
    @FXML
    private TextField userNameTf;
    @FXML
    private PasswordField passwordTf;
    @FXML
    private PasswordField confirmedPasswordTf;
    @FXML
    private Button submitBtn;
    ArrayList<String> information;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void submit(ActionEvent event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                register();
                if (Client.isRegistered) {
                    final Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/views/AvailablePlayersView.fxml"));
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ((BorderPane) submitBtn.getScene().getRoot()).setCenter(root);
                            }
                        });
                    } catch (IOException ex) {
                        Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                    }

               }
//                else {
//                    Platform.runLater(() -> {
//                        userNameLbl.setText("user name is used");
//                    });

//                }
            }
        }).start();

    }

    private void register() {
        ArrayList<String> message = new ArrayList<String>();
        information = new ArrayList<String>();
        System.out.println("B4" + information);
        if (userNameTf.getText().equals("") && passwordTf.getText().equals("") && confirmedPasswordTf.getText().equals("")) {
            Platform.runLater(() -> {
                userNameLbl.setText("enter your name");
                passwordLbl.setText("enter your password");
                confirmPasswordLbl.setText("enter your confirmPassword");
            });

        } else if (!confirmedPasswordTf.getText().equals(passwordTf.getText())) {
            Platform.runLater(() -> {
                userNameLbl.setText("");
                passwordLbl.setText("");
                confirmPasswordLbl.setText("confirmPassword must be as password");
            });

        } else {
            Platform.runLater(() -> {
                userNameLbl.setText("");
                passwordLbl.setText("");
                confirmPasswordLbl.setText("");
            });

            information.add(Constants.WANT_TO_REGISTER);
            information.add(userNameTf.getText());
            information.add(passwordTf.getText());
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
