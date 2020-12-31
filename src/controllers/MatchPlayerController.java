/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import models.GameMoves;
import models.GameMovesHolder;
import models.Player;

/**
 * FXML Controller class
 *
 * @author khattab
 */
public class MatchPlayerController implements Initializable {

    @FXML
    private Button cell1Btn;
    @FXML
    private Button cell2Btn;
    @FXML
    private Button cell3Btn;
    @FXML
    private Button cell4Btn;
    @FXML
    private Button cell5Btn;
    @FXML
    private Button cell6Btn;
    @FXML
    private Button cell7Btn;
    @FXML
    private Button cell8Btn;
    @FXML
    private Button cell9Btn;
    @FXML
    private ImageView avatarLeftIV;
    @FXML
    private Rectangle playerTurnRect;
    @FXML
    private Label playerLbl;
    @FXML
    private Label totalScoreLeftLBL;
    @FXML
    private Label gameScoreLeftLbl;
    @FXML
    private Label gameScoreRightLbl;
    @FXML
    private Rectangle machineTurnRect;
    @FXML
    private Label machineLbl;
    @FXML
    private Label totalScoreRightLBL;
    @FXML
    private ImageView avatarRightIV;
    @FXML
    private Button backBtn;
    @FXML
    private Button playRecordedMatchBtn;

    GameMoves gameMoves = null;
    int[] moves = null;

    int i = 0;
    Player playerOne;
    Player playerTwo;
    int playPieceCode = 0;
    String playPiece = "";

    Button[] boardOfBtn = new Button[9];

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameMoves = GameMovesHolder.getGAME_MOVES();
        moves = gameMoves.getMoves();
        playerOne = gameMoves.getPlayerOne();
        playerTwo = gameMoves.getPlayerTwo();

        boardOfBtn[0] = cell1Btn;
        boardOfBtn[1] = cell2Btn;
        boardOfBtn[2] = cell3Btn;
        boardOfBtn[3] = cell4Btn;
        boardOfBtn[4] = cell5Btn;
        boardOfBtn[5] = cell6Btn;
        boardOfBtn[6] = cell7Btn;
        boardOfBtn[7] = cell8Btn;
        boardOfBtn[8] = cell9Btn;

        int count = 1;
        for (int move : moves) {
            System.out.println("move " + count + "--> " + move);
            count++;
        }
    }

    @FXML
    private void handlePlaying(ActionEvent event) {
    }

    @FXML
    private void backToUserConfig(ActionEvent event) {
    }

    @FXML
    private void playMatch(ActionEvent event) {

        while (moves[i] != -1) {

            playPieceCode = (i % 2 == 0) ? playerOne.getPlayPiece() : playerTwo.getPlayPiece();

            playPiece = (playPieceCode == 1) ? "X" : "O";

            boardOfBtn[moves[i]].setText(playPiece);

            i++;

        }
    }

}
