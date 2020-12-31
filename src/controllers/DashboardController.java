/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author khattab
 */
public class DashboardController implements Initializable {

    @FXML
    private Button homeBtn;
    @FXML
    private Button recordsBtn;
    @FXML
    private Button settingsBtn;
    @FXML
    private Button helpBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private BorderPane dashboard;
    @FXML
    private VBox body;

    @FXML
    private RadioButton playerVsMachine;
    @FXML
    private RadioButton local;
    @FXML
    private RadioButton online;
    @FXML
    private ToggleGroup playingMode;
    @FXML
    private Button letsPlayBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void home(ActionEvent event) {
        homeBtn.setStyle("-fx-background-color:white");
        changeBtnsBackground(settingsBtn, recordsBtn, helpBtn, logoutBtn);
        dashboard.setCenter(body);
    }

    @FXML
    private void records(ActionEvent event) {
        recordsBtn.setStyle("-fx-background-color:white");
        changeBtnsBackground(homeBtn, settingsBtn, helpBtn, logoutBtn);
        navigatePages("/views/RecordsView.fxml");

    }

    @FXML
    private void settings(ActionEvent event) {
        settingsBtn.setStyle("-fx-background-color:white");
        changeBtnsBackground(homeBtn, recordsBtn, helpBtn, logoutBtn);
        navigatePages("/views/SettingView.fxml");
    }

    @FXML
    private void help(ActionEvent event) {
        helpBtn.setStyle("-fx-background-color:white");
        changeBtnsBackground(homeBtn, settingsBtn, recordsBtn, logoutBtn);
        navigatePages("/views/HelpView.fxml");
    }

    @FXML
    private void logout(ActionEvent event) {
        Platform.exit();
    }

    private void navigatePages(String page) {

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(page));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        dashboard.setCenter(root);

    }

//    private void gotToPlayingModes(ActionEvent event){
//        
//        dashboard.setCenter(body);
//    }
    @FXML
    private void loadGamePlay(ActionEvent event) {
        if (playerVsMachine.isSelected()) {
            navigatePages("/views/PlayerVsMachineFormView.fxml");
        } else if (local.isSelected()) {
            navigatePages("/views/PlayerVsPlayerLocalView.fxml");
        }
        else if (online.isSelected()) {
            navigatePages("/views/ServerConnectionView.fxml");
        }

    }

    private void changeBtnsBackground(Button... btns) {

        for (int i = 0; i < btns.length; i++) {
            btns[i].setStyle("-fx-background-color: none");
        }

    }

}
