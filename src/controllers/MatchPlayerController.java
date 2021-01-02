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

/**
 * FXML Controller class
 *
 * @author Mona
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
    private Button runBtn;
    @FXML
    private Button startBtn;
    private GameMovesHolder movesHolder;
    private GameMoves gameMoves;
    Button[] boardBtns = new Button[9];

    /**
     * Initializes the controller class.
     */
    int playPieceCode1;
    int playPieceCode2;
    String playPiece = "";
    Thread thread;
    int i = 0;
    int[] moves;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        playPieceCode1 = 0;
        playPieceCode2 = 0;
        playPiece = "";
        gameMoves = GameMovesHolder.getGameMoves();
        moves = gameMoves.getMoves();
// TODO
        boardBtns[0] = cell1Btn;
        boardBtns[1] = cell2Btn;
        boardBtns[2] = cell3Btn;
        boardBtns[3] = cell4Btn;
        boardBtns[4] = cell5Btn;
        boardBtns[5] = cell6Btn;
        boardBtns[6] = cell7Btn;
        boardBtns[7] = cell8Btn;
        boardBtns[8] = cell9Btn;

        machineLbl.setText(gameMoves.getPlayerOne().getName());
        playerLbl.setText(gameMoves.getPlayerTwo().getName());
        gameScoreRightLbl.setText(gameMoves.getPlayerTwo().getScore() + "");
        gameScoreLeftLbl.setText(gameMoves.getPlayerOne().getScore() + "");
        totalScoreRightLBL.setText(gameMoves.getPlayerTwo().getPlayPiece() == 1? "X" : "O");
        totalScoreLeftLBL.setText(gameMoves.getPlayerOne().getPlayPiece() == 2? "O" : "X");
        machineTurnRect.setVisible(gameMoves.getStartedBy() == 2);
        playerTurnRect.setVisible(gameMoves.getStartedBy() == 1);
        for (int move : gameMoves.getMoves()) {
            System.out.println("move from initialize " + move);
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
        moves = flattenMoves(gameMoves.getMoves());
        playPieceCode1 = gameMoves.getPlayerOne().getPlayPiece();
        playPieceCode2 = gameMoves.getPlayerTwo().getPlayPiece();
        System.out.println("First Player: " + playPieceCode1);
        System.out.println("Second Player: " + playPieceCode2);
        System.out.println("Player whom started: " + gameMoves.getStartedBy());
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (i = 0; i < 9; i++) {
                        if (moves[i] == -1) {
                            break;
                        }
                        if (i % 2 == 0) {
                            if(playPieceCode1 == gameMoves.getStartedBy()){
                                playPiece = "X";
                                toggleFlag(false, true);
                            }
                            else{
                                playPiece = "O";
                                toggleFlag(true, false);
                            }
                        } else {
                            if(playPieceCode2 == gameMoves.getStartedBy()){
                                playPiece = "X";
                                toggleFlag(false, true);
                            }
                            else{
                                playPiece = "O";
                                toggleFlag(true, false);
                            }
                        }
                        
                        Platform.runLater(() -> boardBtns[moves[i]].setText(playPiece));
                        System.out.println(playPiece);
                        try {
                            Thread.sleep(1500);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        t.start();
    }
    
    public void toggleFlag(boolean isPlayer1Round, boolean isPlayer2Round){
        Platform.runLater(() -> machineTurnRect.setVisible(isPlayer1Round));
        Platform.runLater(() -> playerTurnRect.setVisible(isPlayer2Round));
    }
    
    public int[] flattenMoves(int[] mov){
        int zCounter = 0;
        for(int i = 0;i < 9;i++)
            if(mov[i] == 0){
                if(zCounter == 0) zCounter++;
                else  mov[i] = -1;
            }
        return mov;
    }
}
