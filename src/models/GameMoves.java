/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import java.io.Serializable;


/**
 *
 * @author khattab
 */
public class GameMoves implements Serializable{
    
    private Player playerOne;
    private Player playerTwo;
    private int [] moves;
    private int counter;
    
    
    public GameMoves(){
        playerOne = playerTwo = null;
        moves = new int[9];
        counter = 0;
        initMoves();
    }
    public GameMoves(Player playerOne, Player playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        moves = new int[9];
        counter = 0;
        initMoves();
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
    
    public void setMoves(int [] moves){
        
        System.arraycopy(moves, 0, this.moves, 0, 9);
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
    
    private int convert2DIndexesTo1D(int row, int col){
        
        return row * 3 + col;
    }
    
    private Cell convert1DindexTo2D(int index){
    
        int row = index/3;
        int col = index%3;
        
        return new Cell(row, col);
    }
    
}
