/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Windows
 */
public class GameMoves implements Serializable {
    
    private Player playerOne;
    private Player playerTwo;
    private int [] moves;
    private int startedBy;
    private int counter;
    
    public GameMoves(){
        playerOne = playerTwo = null;
        moves = new int[9];
        counter = 0;
    }
    
    public GameMoves(Player playerOne, Player playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }
    
    public void setTwoPlayers(Player playerOne, Player playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }
    
    
    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Player PlayerTwo) {
        this.playerTwo = PlayerTwo;
    }
    
    public void setStartedBy(int startedBy) {
        this.startedBy = startedBy;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
    
    public int[] getMoves() {
        return moves;
    }
    
    public int getStartedBy() {
        return startedBy;
    }
    
    public void setMoves(int [] moves){
        System.arraycopy(moves, 0, this.moves, 0, 9);
    }
    
    public void addMove(int move){
        if(counter < 9){
            moves[counter] = move;
            counter++;
        }
    }
    
    private void initMoves(){
        
        for(int i = 0;i < 9;i++)
            moves[i] = -1;
    }
    
    public int convert2DIndexesTo1D(int col, int row){
        return row * 3 + col;
    }
    
    public Cell convert1DIndexesTo2D(int index){
        int row = index / 3;
        int col = index % 3;
        return new Cell(row, col);
    }
}
