/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.GameMoves;
import models.GameMovesHolder;
import models.Player;

/**
 * FXML Controller class
 *
 * @author khattab
 */
public class RecordsController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();
    GameMoves gameMoves = null;

    private String match = "";
    private String[] files = null;

    @FXML
    private Label matchesListLbl;
    @FXML
    private ListView<String> matchesListView;
    @FXML
    private Button watchBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        getAllMatches();
        loadItems();
    }

    private void loadItems() {

        list.removeAll(list);

        populateMatchesList();

        matchesListView.getItems().addAll(list);
    }

    @FXML
    private void getSelectedMatch(MouseEvent event) {

        match = matchesListView.getSelectionModel().getSelectedItem();
        retrieveMatch(match);
        System.out.println("the selected match is " + match);
    }

    private void getAllMatches() {

        String currentMatch = "";
        File file = new File("src\\Records");
        files = file.list();

        for (int i = 0; i < files.length; i++) {

            currentMatch = files[i].replace(".txt", "");
            files[i] = currentMatch;

        }

    }

    private void populateMatchesList() {

        list.addAll(files);

    }

    private void retrieveMatch(String match) {

        ObjectInputStream is = null;

        try {

            String fileName = "src\\Records\\" + match + ".txt";
            FileInputStream fis = new FileInputStream(fileName);
            is = new ObjectInputStream(fis);
            gameMoves = ((GameMoves) is.readObject());
        } catch (IOException ex) {
            Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        int[] moves = gameMoves.getMoves();

        int i = 1;
        for (int move : moves) {
            System.out.println("move: " + i + " ->" + move);
            i++;
        }
    }

    @FXML
    private void goToMatchPlayer(ActionEvent event) {
        GameMovesHolder.getGameMoves().setMoves(gameMoves.getMoves());
        GameMovesHolder.getGameMoves().setTwoPlayers(gameMoves.getPlayerOne(), gameMoves.getPlayerTwo());
        GameMovesHolder.getGameMoves().setStartedBy(gameMoves.getStartedBy());
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/views/MatchPlayerView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        BorderPane dashBoard = ((BorderPane) ((Button) event.getSource()).getScene().getRoot());
        dashBoard.setCenter(root);
    }

}
