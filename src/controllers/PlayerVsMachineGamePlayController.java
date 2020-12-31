/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Cell;
import models.GameMoves;
import models.Player;

/**
 * FXML Controller class
 *
 * @author khattab
 */
public class PlayerVsMachineGamePlayController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button cell1Btn, cell2Btn, cell3Btn, cell4Btn, cell5Btn, cell6Btn, cell7Btn, cell8Btn, cell9Btn;

    @FXML
    Button startBtn;

    @FXML
    Button backBtn;

    @FXML
    Label playerLbl, machineLbl, gameScoreLeftLbl, gameScoreRightLbl;

    @FXML
    Rectangle playerTurnRect, machineTurnRect;

    private static int movesCounter = 0;
    private int[][] ticTacToeBoard;
    private Button[][] boardOfBtns;
    private List<Cell> availableCells;
    private GameMoves gameMoves;

    private Player player, machine;

    private Cell cell;

    private RotateTransition rotate = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        machine = new Player("machine");
        ticTacToeBoard = new int[3][3];
        boardOfBtns = new Button[][]{{cell1Btn, cell2Btn, cell3Btn},
        {cell4Btn, cell5Btn, cell6Btn},
        {cell7Btn, cell8Btn, cell9Btn}};
        gameMoves = new GameMoves();
        cell = null;
        rotate = new RotateTransition();
        toggleCells(true);
        initBoard();

        availableCells = new ArrayList<Cell>();

    }

    private void newGame() {
        movesCounter = 0;
        gameScoreLeftLbl.setText(Integer.toString(player.getScore()));
        gameScoreRightLbl.setText(Integer.toString(machine.getScore()));
        resetGame();

    }

    @FXML
    private void backToUserConfig(ActionEvent e) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/PlayerVsMachineFormView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsMachineGamePlayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        BorderPane dashBoard = ((BorderPane) ((Button) e.getSource()).getScene().getRoot());
        dashBoard.setCenter(root);

    }

    @FXML
    private void startGame(ActionEvent e) {

        int machinePlayPiece = 0;
        Button btn = ((Button) e.getSource());
        this.player = ReceivePlayerData(btn);

        machinePlayPiece = (this.player.getPlayPiece() == Player.X_PLAY_PIECE)
                ? Player.O_PLAY_PIECE : Player.X_PLAY_PIECE;

        this.machine.setPlayPiece(machinePlayPiece);

        toggleCells(false);

        playerLbl.setText(player.getName());
        machineLbl.setText(machine.getName());
        machineTurnRect.setVisible(false);

        gameMoves.setTwoPlayers(player, machine);
        
        ((Button) e.getSource()).setDisable(false);

        animateRect(playerTurnRect);

    }

    private void animateRect(Rectangle rect) {

        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(500);
        rotate.setAutoReverse(true);
        rotate.setNode(rect);
        rotate.play();

    }

    private void stopAnimateRect() {
        rotate.stop();
    }

    @FXML
    private void handlePlaying(ActionEvent e) {
        int row = 0, col = 0;
        char playerPlayPiece = (player.getPlayPiece() == Player.X_PLAY_PIECE) ? 'X' : 'O';
        char machinePlayerPiece = (playerPlayPiece == 'X') ? 'O' : 'X';
        String knotImage = "src\\Assets\\knot.png";
        String trophyImage = "src\\Assets\\trophy.png";
        String buttonContent = ((Button) e.getSource()).getText();
        if (!buttonContent.isEmpty()) {
            return;
        }

        if (((Button) e.getSource()) == cell1Btn) {
            row = 0;
            col = 0;
        } else if (((Button) e.getSource()) == cell2Btn) {
            row = 0;
            col = 1;
        } else if (((Button) e.getSource()) == cell3Btn) {
            row = 0;
            col = 2;
        } else if (((Button) e.getSource()) == cell4Btn) {
            row = 1;
            col = 0;
        } else if (((Button) e.getSource()) == cell5Btn) {
            row = 1;
            col = 1;
        } else if (((Button) e.getSource()) == cell6Btn) {
            row = 1;
            col = 2;
        } else if (((Button) e.getSource()) == cell7Btn) {
            row = 2;
            col = 0;
        } else if (((Button) e.getSource()) == cell8Btn) {
            row = 2;
            col = 1;
        } else if (((Button) e.getSource()) == cell9Btn) {
            row = 2;
            col = 2;
        }

        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        Stage primaryStage = ((Stage) ((Node) e.getSource()).getScene().getWindow());
        popupStage.initOwner(primaryStage);
        popupStage.initModality(Modality.APPLICATION_MODAL);

        VBox winnerRoot = createPopupMenu(popupStage, trophyImage);
        popupStage.setScene(new Scene(winnerRoot, 300, 300, Color.TRANSPARENT));
        VBox tieRoot = createPopupMenu(popupStage, knotImage);

        ((Button) e.getSource()).setText(playerPlayPiece + "");
        //((Button) e.getSource()).setDisable(true);

        ticTacToeBoard[row][col] = player.getPlayPiece();
      
        movesCounter++;
        
        int move = convert2DIndexesTo1D(row, col);
        System.out.println("Player move--> " + move);
        gameMoves.addMove(move);

        if (movesCounter == 9) {

            stopAnimateRect();

            ((Label) winnerRoot.getChildren().get(1)).setText("Game Tie");
            popupStage.setScene(new Scene(tieRoot, 300, 300, Color.TRANSPARENT));
            popupStage.show();
            return;

        }

        if (checkState()) {

            stopAnimateRect();

            player.increaseScore();

            ((Label) winnerRoot.getChildren().get(1)).setTextFill(Color.web("#e1b12c"));
            ((Label) winnerRoot.getChildren().get(1)).setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 21));
            ((Label) winnerRoot.getChildren().get(1)).setText("The winner is "
                    + player.getName());
            popupStage.show();

            return;
        }

        playerTurnRect.setVisible(false);
        machineTurnRect.setVisible(true);

        animateRect(machineTurnRect);

        toggleCells(true);

        Thread machineThread = new Thread() {

            public void run() {

                try {

                    cell = calculateMinHeuristic();
                    System.out.println(cell.getRow() + ", " + cell.getCol());
                    ticTacToeBoard[cell.getRow()][cell.getCol()] = machine.getPlayPiece();
                    
                    int move = convert2DIndexesTo1D(cell.getRow(), cell.getCol());
                    System.out.println("Machine move--> " + move);
                    gameMoves.addMove(move);

                    //((Button)getNodeFromGridPane(gridPane, cell.getCol(), cell.getRow())).setText("O");
                    boardOfBtns[cell.getRow()][cell.getCol()].setText(machinePlayerPiece + "");
                    //boardOfBtns[cell.getRow()][cell.getCol()].setDisable(true);
                    movesCounter++;
                    if (movesCounter == 9) {

                        stopAnimateRect();

                        ((Label) winnerRoot.getChildren().get(1)).setText("Game Tie");

                        popupStage.setScene(new Scene(tieRoot, 300, 300, Color.TRANSPARENT));
                        popupStage.show();

                        return;

                    }

                    if (checkState()) {

                        stopAnimateRect();

                        machine.increaseScore();

                        ((Label) winnerRoot.getChildren().get(1)).setTextFill(Color.web("#e1b12c"));
                        ((Label) winnerRoot.getChildren().get(1)).setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 21));
                        ((Label) winnerRoot.getChildren().get(1)).setText("The winner is "
                                + machine.getName());

                        popupStage.show();

                    }

                    Thread.sleep(600);

                } catch (InterruptedException ex) {
                    Logger.getLogger(PlayerVsMachineGamePlayController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        };

        Platform.runLater(machineThread);

        Thread turnThread = new Thread() {

            public void run() {

                try {

                    toggleCells(false);
                    playerTurnRect.setVisible(true);
                    machineTurnRect.setVisible(false);

                    Thread.sleep(600);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlayerVsMachineGamePlayController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        Platform.runLater(turnThread);

    }
    
    private int convert2DIndexesTo1D(int row, int col){
        
        return row * 3 + col;
    }
    
    private Cell convert1DindexTo2D(int index){
    
        int row = index/3;
        int col = index%3;
        
        return new Cell(row, col);
    }

    private VBox createPopupMenu(Stage popupStage, String imagePath) {

        VBox tieRoot = new VBox(5);
        tieRoot.setMinHeight(5);
        tieRoot.setMinWidth(5);
        InputStream stream = null;
        try {
            stream = new FileInputStream(imagePath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlayerVsMachineGamePlayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = new Image(stream);
        ImageView imageView = new ImageView(image);
        imageView.setX(10);
        imageView.setY(10);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        tieRoot.getChildren().add(imageView);
        tieRoot.getChildren().add(new Label("Game Tie"));
        tieRoot.setStyle("-fx-background-color:  #FFFFFFEE;-fx-border-radius: 20px;-fx-border-color: gray");

        tieRoot.setAlignment(Pos.CENTER);
        tieRoot.setPadding(new Insets(20));
        tieRoot.setSpacing(10);
        Button newGameBtn = new Button("New Game");
        Button closeBtn = new Button("Close");

        newGameBtn.setOnAction(event -> {

            recordMatch();
            popupStage.hide();
            newGame();
        });

        closeBtn.setOnAction(event -> {
            
            recordMatch();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/views/PlayerVsMachineFormView.fxml"));
                ((BorderPane) backBtn.getScene().getRoot()).setCenter(root);
                popupStage.hide();
            } catch (IOException ex) {
                Logger.getLogger(PlayerVsMachineGamePlayController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        tieRoot.getChildren().add(newGameBtn);
        tieRoot.getChildren().add(closeBtn);

        
        
        return tieRoot;

    }

    public boolean checkState() {
        boolean isWinner = false;

        // for 3 rows
        for (int i = 0; i < 3 && !isWinner; i++) {
            isWinner = checkRow(i);

        }
        // for 3 cols
        for (int i = 0; i < 3 && !isWinner; i++) {

            isWinner = checkColumn(i);

        }

        if (isWinner) {
            return isWinner;
        }

        if (checkDiagonal() || checkAnitDiagonal()) {
            isWinner = true;
        }

        return isWinner;

    }

    public boolean checkRow(int rowToCheck) {

        if (ticTacToeBoard[rowToCheck][0] == 1 || ticTacToeBoard[rowToCheck][0] == 2) {
            return (ticTacToeBoard[rowToCheck][0] == ticTacToeBoard[rowToCheck][1]
                    && ticTacToeBoard[rowToCheck][1] == ticTacToeBoard[rowToCheck][2]);
        }
        return false;
    }

    public boolean checkColumn(int colToCheck) {
        if (ticTacToeBoard[0][colToCheck] == 1 || ticTacToeBoard[0][colToCheck] == 2) {
            return (ticTacToeBoard[0][colToCheck] == ticTacToeBoard[1][colToCheck]
                    && ticTacToeBoard[1][colToCheck] == ticTacToeBoard[2][colToCheck]);
        }
        return false;
    }

    public boolean checkDiagonal() {
        if (ticTacToeBoard[0][0] == 1 || ticTacToeBoard[0][0] == 2) {
            return (ticTacToeBoard[0][0] == ticTacToeBoard[1][1]
                    && ticTacToeBoard[1][1] == ticTacToeBoard[2][2]);
        }
        return false;
    }

    public boolean checkAnitDiagonal() {
        if (ticTacToeBoard[0][2] == 1 || ticTacToeBoard[0][2] == 2) {
            return (ticTacToeBoard[0][2] == ticTacToeBoard[1][1]
                    && ticTacToeBoard[1][1] == ticTacToeBoard[2][0]);
        }
        return false;
    }

    public void resetGame() {

        toggleCells(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardOfBtns[i][j].setText("");
            }
        }

        startBtn.setDisable(false);

        initBoard();
    }

    public int calculateRowScore(int[][] tempBoard, int rowToCheck) {

        int score = 0;
        int numOfXs = 0;
        int numOfZeros = 0;
        int numOfOs = 0;

        for (int i = 0; i < 3; i++) {
            if (tempBoard[rowToCheck][i] == player.getPlayPiece()) {
                numOfXs++;
            } else if (tempBoard[rowToCheck][i] == 0) {
                numOfZeros++;
            } else if (tempBoard[rowToCheck][i] == machine.getPlayPiece()) {
                numOfOs++;
            }

        }

        if (numOfXs == 1 && numOfZeros == 2) {
            score = 5;
        } else if (numOfXs == 2 && numOfZeros == 1) {
            score = 20;
        }

        if (numOfOs == 3) {
            score = Integer.MIN_VALUE;
        }

        return score;

    }

    public int calculateColScore(int[][] tempBoard, int colToCheck) {

        int score = 0;
        int numOfXs = 0;
        int numOfZeros = 0;
        int numOfOs = 0;

        for (int i = 0; i < 3; i++) {
            if (tempBoard[i][colToCheck] == player.getPlayPiece()) {
                numOfXs++;
            } else if (tempBoard[i][colToCheck] == 0) {
                numOfZeros++;
            } else if (tempBoard[i][colToCheck] == machine.getPlayPiece()) {
                numOfOs++;
            }

        }

        if (numOfXs == 1 && numOfZeros == 2) {
            score = 5;
        } else if (numOfXs == 2 && numOfZeros == 1) {
            score = 20;
        }

        if (numOfOs == 3) {
            score = Integer.MIN_VALUE;
        }

        return score;

    }

    public int calculateDiagonalScore(int[][] tempBoard) {

        int score = 0;
        int numOfXs = 0;
        int numOfZeros = 0;
        int numOfOs = 0;

        for (int i = 0; i < 3; i++) {
            if (tempBoard[i][i] == player.getPlayPiece()) {
                numOfXs++;
            } else if (tempBoard[i][i] == 0) {
                numOfZeros++;
            } else if (tempBoard[i][i] == machine.getPlayPiece()) {
                numOfOs++;
            }

        }

        if (numOfXs == 1 && numOfZeros == 2) {
            score = 5;
        } else if (numOfXs == 2 && numOfZeros == 1) {
            score = 20;
        }

        if (numOfOs == 3) {
            score = Integer.MIN_VALUE;
        }

        return score;

    }

    public int calculateAnitDiagonalScore(int[][] tempBoard) {

        int score = 0;
        int numOfXs = 0;
        int numOfZeros = 0;
        int numOfOs = 0;

        for (int i = 0; i < 3; i++) {
            if (tempBoard[i][2 - i] == player.getPlayPiece()) {
                numOfXs++;
            } else if (tempBoard[i][2 - i] == 0) {
                numOfZeros++;
            } else if (tempBoard[i][2 - i] == machine.getPlayPiece()) {
                numOfOs++;
            }

        }

        if (numOfXs == 1 && numOfZeros == 2) {
            score = 5;
        } else if (numOfXs == 2 && numOfZeros == 1) {
            score = 20;
        }

        if (numOfOs == 3) {
            score = Integer.MIN_VALUE;
        }

        return score;

    }

    public int calculateNodeScore(Cell availableCell) {

        int row = availableCell.getRow();
        int col = availableCell.getCol();
        int nodeScore = 0;

        int[][] tempBoard = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tempBoard[i][j] = ticTacToeBoard[i][j];
            }
        }

        tempBoard[row][col] = machine.getPlayPiece();

        for (int i = 0; i < 3; i++) {
            nodeScore += calculateRowScore(tempBoard, i);
            nodeScore += calculateColScore(tempBoard, i);
        }

        nodeScore += calculateDiagonalScore(tempBoard);
        nodeScore += calculateAnitDiagonalScore(tempBoard);

        return nodeScore;

    }

    public Cell calculateMinHeuristic() {

        int minScore = Integer.MAX_VALUE;
        int score = 0;
        int row = 0, col = 0;
        availableCells.clear();
        findAvailableCells();

        for (Cell cell : availableCells) {

            score = calculateNodeScore(cell);

            //System.out.println("score of cell -> coordinates(" + cell.getRow() + ", " + cell.getCol() + ") is " + score);
            if (minScore > score) {

                minScore = score;
                row = cell.getRow();
                col = cell.getCol();

            }

        }

        return new Cell(row, col);

    }

    public void findAvailableCells() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard[i][j] == 0) {
                    availableCells.add(new Cell(i, j));
                }

            }
        }
    }

    public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public void initBoard() {
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                ticTacToeBoard[i][j] = 0;
            }
        }
    }

    private Player ReceivePlayerData(Button btn) {
        Stage stage = ((Stage) btn.getScene().getWindow());
        Player player = ((Player) stage.getUserData());

        return player;

    }

    private void toggleCells(boolean isDisabled) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardOfBtns[i][j].setDisable(isDisabled);
            }
        }
    }
    
    private void recordMatch(){
        
        int [] moves = gameMoves.getMoves();
        int i = 1;
        for(int move: moves){
            System.out.println("move from record match: " + i + " ->" +move);
            i++;
        }
        ObjectOutputStream os = null;
        try {
            
            String fileName = "src\\Records\\" + player.getName() + "Vs" + machine.getName() + ".txt";
            
            FileOutputStream fos = new FileOutputStream(fileName);
            os = new ObjectOutputStream(fos);
            os.writeObject(gameMoves);
            
        } catch (IOException ex) {
            Logger.getLogger(PlayerVsMachineGamePlayController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(PlayerVsMachineGamePlayController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

