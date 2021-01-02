/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.NavigationHolder;
import abstracts.PlayerVSPlayerLocalAbstract;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javafx.geometry.Insets;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Player;
import models.GameMoves;

/**
 * FXML Controller class
 *
 * @author Windows
 */
public class PlayerVsPlayerLocalController extends PlayerVSPlayerLocalAbstract implements Initializable {

    @FXML
    private Pane mainPe;
    @FXML
    private Button xo00BTN;
    @FXML
    private Button xo10BTN;
    @FXML
    private Button xo20BTN;
    @FXML
    private Button xo01BTN;
    @FXML
    private Button xo11BTN;
    @FXML
    private Button xo21BTN;
    @FXML
    private Button xo02BTN;
    @FXML
    private Button xo12BTN;
    @FXML
    private Button xo22BTN;
    @FXML
    private ImageView avatarLeftIV;
    @FXML
    private Rectangle iterationLeftSQ;
    @FXML
    private Label playerLeftLBL;
    @FXML
    private Label totalScoreLeftLBL;
    @FXML
    private Label gameScoreLeftLBL;
    @FXML
    private Label gameScoreRightLBL;
    @FXML
    private Rectangle iterationRightSQ;
    @FXML
    private Label playerRightLBL;
    @FXML
    private Label totalScoreRightLBL;
    @FXML
    private ImageView avatarRightIV;
    @FXML
    private Button backBTN;
    private BorderPane dashboard;
    private int[][] board = { {0, 0, 0}, {0, 0, 0}, {0, 0, 0} };
    private Player player1;
    private Player player2;
    private int randomPlayerNo;
    private int gameCounter = 0;
    private GameMoves gameMoves;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        NavigationHolder navigationHolder = NavigationHolder.getInstance();
        gameMoves = new GameMoves();
        player1 = new Player(navigationHolder.getGameMoves().getPlayerOne().getName());
        player2 = new Player(navigationHolder.getGameMoves().getPlayerTwo().getName());
        player1.setPlayPiece(1);
        player2.setPlayPiece(2);
        playerLeftLBL.setText(navigationHolder.getGameMoves().getPlayerOne().getName());
        playerRightLBL.setText(navigationHolder.getGameMoves().getPlayerTwo().getName());
        totalScoreLeftLBL.setText("X");
        totalScoreRightLBL.setText("O");
        randomPlayerNo = chooseRandomPlayer(2);
        gameMoves.setStartedBy(randomPlayerNo + 1);
        iterationRightSQ.setVisible(randomPlayerNo == 1);
        iterationLeftSQ.setVisible(randomPlayerNo == 0);
    }    

    @FXML
    private void onPress00BTN(ActionEvent event) {
        onPress(0, 0, xo00BTN, event);
        System.out.println(checkRow(board, 0));
        if(checkColoumn(board, 0) || checkRow(board, 0) || checkDiagonal(board)) winPopUp();
        else if(gameCounter >= 9) tiePopUp();
    }

    @FXML
    private void onPress10BTN(ActionEvent event) {
        onPress(1, 0, xo10BTN, event);
        if(checkColoumn(board, 1) || checkRow(board, 0)) winPopUp();
        else if(gameCounter >= 9) tiePopUp();
    }

    @FXML
    private void onPress20BTN(ActionEvent event) {
        onPress(2, 0, xo20BTN, event);
        if(checkColoumn(board, 2) || checkRow(board, 0) || checkAntiDiagonal(board)) winPopUp();
        else if(gameCounter >= 9) tiePopUp();
    }

    @FXML
    private void onPress01BTN(ActionEvent event) {
        onPress(0, 1, xo01BTN, event);
        if(checkColoumn(board, 0) || checkRow(board, 1)) winPopUp();
        else if(gameCounter >= 9) tiePopUp();
    }

    @FXML
    private void onPress11BTN(ActionEvent event) {
        onPress(1, 1, xo11BTN, event);
        if(checkColoumn(board, 1) || checkRow(board, 1) || checkDiagonal(board) || checkAntiDiagonal(board)) winPopUp();
        else if(gameCounter >= 9) tiePopUp();
    }

    @FXML
    private void onPress21BTN(ActionEvent event) {
        onPress(2, 1, xo21BTN, event);
        if(checkColoumn(board, 2) || checkRow(board, 1)) winPopUp();
        else if(gameCounter >= 9) tiePopUp();
    }

    @FXML
    private void onPress02BTN(ActionEvent event) {
        onPress(0, 2, xo02BTN, event);
        if(checkColoumn(board, 0) || checkRow(board, 2) || checkAntiDiagonal(board)) winPopUp();
        else if(gameCounter >= 9) tiePopUp();
    }

    @FXML
    private void onPress12BTN(ActionEvent event) {
        onPress(1, 2, xo12BTN, event);
        if(checkColoumn(board, 1) || checkRow(board, 2)) winPopUp();
        else if(gameCounter >= 9) tiePopUp();
    }

    @FXML
    private void onPress22BTN(ActionEvent event) {
        onPress(2, 2, xo22BTN, event);
        if(checkColoumn(board, 2) || checkRow(board, 2) || checkDiagonal(board)) winPopUp();
        else if(gameCounter >= 9) tiePopUp();
    }
    
    private void flipRound(){
        if(randomPlayerNo == 0){
            iterationRightSQ.setVisible(true);
            iterationLeftSQ.setVisible(false);
            randomPlayerNo = 1;
        }
        else{
            iterationRightSQ.setVisible(false);
            iterationLeftSQ.setVisible(true);
            randomPlayerNo = 0;
        }
    }
    
    private void onPress(int x, int y, Button button, ActionEvent event){
        dashboard = ((BorderPane)(((Button)event.getSource()).getScene().getRoot()));
        if(board[x][y] != 0) return;
        if(randomPlayerNo == 0){
            board[x][y] = player1.getPlayPiece();
            button.setText("X");
        }
        else{
            board[x][y] = player2.getPlayPiece();
            button.setText("O");
        }
        gameMoves.addMove(gameMoves.convert2DIndexesTo1D(x, y));
        gameMoves.setTwoPlayers(player1, player2);
        gameCounter++;
        flipRound();
    }
    
    private void countScore(){
        if(randomPlayerNo == 0){
            player2.setScore(player2.getScore() + 1);
            gameScoreRightLBL.setText(String.valueOf(player2.getScore()));
        }
        else{
            player1.setScore(player1.getScore() + 1);
            gameScoreLeftLBL.setText(String.valueOf(player1.getScore()));
        }
    }
    
    private void resetGame(){
        xo00BTN.setText("");
        xo01BTN.setText("");
        xo02BTN.setText("");
        xo10BTN.setText("");
        xo11BTN.setText("");
        xo12BTN.setText("");
        xo20BTN.setText("");
        xo21BTN.setText("");
        xo22BTN.setText("");
        for(int m = 0; m < 3; m++){
            for(int n = 0; n < 3; n++){
                board[m][n] = 0;
            }
        }
    }
    
    private void winPopUp(){
        countScore();
        recordMatch();
        Stage primaryStage = (Stage) mainPe.getScene().getWindow();
    
        VBox winnerRoot = new VBox(5);
        winnerRoot.setMinHeight(5);
        winnerRoot.setMinWidth(5);
        winnerRoot.getChildren().add(new Label());
        winnerRoot.setStyle("-fx-background-color: #ffffffcc; -fx-border-radius: 50px; -fx-border-width: 2px; -fx-border-color: #34A3F3");
        winnerRoot.setAlignment(Pos.CENTER);
        winnerRoot.setPadding(new Insets(10, 10, 10, 10));
        
        ImageView imageView = new ImageView("/Assets/trophies.png");
        imageView.setFitHeight(170);
        imageView.setFitWidth(170);
        Label youWinLbl = new Label();
        youWinLbl.setText(randomPlayerNo == 0? player2.getName() + " Win" : player1.getName() + " Win");
        Button playAgainButton = new Button("Play Again");
        playAgainButton.setStyle("-fx-pref-width: 150px; -fx-pref-height: 30px; -fx-border-color: #34A3F3; -fx-background-color: transparent; -fx-border-radius: 5px; -fx-border-width: 2px");
        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-pref-width: 150px; -fx-pref-height: 30px; -fx-border-color: #34A3F3; -fx-background-color: transparent; -fx-border-radius: 5px; -fx-border-width: 2px");
        winnerRoot.getChildren().add(imageView);
        winnerRoot.getChildren().add(youWinLbl);
        winnerRoot.getChildren().add(playAgainButton);
        winnerRoot.getChildren().add(closeButton);
        
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(primaryStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(winnerRoot, 300, 300, Color.TRANSPARENT));
        
        popupStage.show();
        
        playAgainButton.setOnAction(event -> {
            gameCounter = 0;
            popupStage.hide();
            resetGame();
        });
        
        closeButton.setOnAction(event -> {
            popupStage.hide();
            navigatePages("/views/PlayerVsPlayerLocalFormView.fxml");
        });
        
    }
    
    private void tiePopUp(){
        recordMatch();
        Stage primaryStage = (Stage) mainPe.getScene().getWindow();
    
        VBox winnerRoot = new VBox(5);
        winnerRoot.setMinHeight(5);
        winnerRoot.setMinWidth(5);
        winnerRoot.getChildren().add(new Label());
        winnerRoot.setStyle("-fx-background-color: #ffffffcc; -fx-border-radius: 50px; -fx-border-width: 2px; -fx-border-color: #34A3F3");
        winnerRoot.setAlignment(Pos.CENTER);
        winnerRoot.setPadding(new Insets(10, 10, 10, 10));
        
        ImageView imageView = new ImageView("/Assets/tie.png");
        imageView.setFitHeight(170);
        imageView.setFitWidth(170);
        Label youWinLbl = new Label();
        youWinLbl.setText("Tie");
        Button playAgainButton = new Button("Play Again");
        playAgainButton.setStyle("-fx-pref-width: 150px; -fx-pref-height: 30px; -fx-border-color: #34A3F3; -fx-background-color: transparent; -fx-border-radius: 5px; -fx-border-width: 2px");
        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-pref-width: 150px; -fx-pref-height: 30px; -fx-border-color: #34A3F3; -fx-background-color: transparent; -fx-border-radius: 5px; -fx-border-width: 2px");
        winnerRoot.getChildren().add(imageView);
        winnerRoot.getChildren().add(youWinLbl);
        winnerRoot.getChildren().add(playAgainButton);
        winnerRoot.getChildren().add(closeButton);
        
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(primaryStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(winnerRoot, 300, 300, Color.TRANSPARENT));
        
        popupStage.show();
        
        playAgainButton.setOnAction(event -> {
            gameCounter = 0;
            popupStage.hide();
            resetGame();
        });
        
        closeButton.setOnAction(event -> {
            popupStage.hide();
            navigatePages("/views/PlayerVsPlayerLocalFormView.fxml");
        });
        
    }
    
    private void recordMatch(){
	
	int [] moves = gameMoves.getMoves();
	int i = 1;
	for(int move: moves){
            System.out.println("move from record match: " + i + " ->" +move);
            i++;
	}
	ObjectOutputStream os = null;
        String fileName = "src\\Records\\" + player1.getName() + "Vs" + player2.getName() + ".txt";
        FileOutputStream fos;
        
        try {
            fos = new FileOutputStream(fileName);
            os = new ObjectOutputStream(fos);
            os.writeObject(gameMoves);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlayerVsPlayerLocalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsPlayerLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
}
