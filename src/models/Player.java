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
public class Player implements Serializable{
    
    public static final int X_PLAY_PIECE = 1;
    public static final int O_PLAY_PIECE = 2;
    private String name;
    private int playPiece;
    private int score;
    
    public Player(String name){this.name = name;}
    
    public Player(String name, int playPiece){
        this.name = name;
        this.playPiece = playPiece;
        this.score = 0;
        
    }

    public String getName() {
        return name;
    }

    public int getPlayPiece() {
        return playPiece;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayPiece(int playPiece) {
        this.playPiece = playPiece;
    }

    public void increaseScore() {
        this.score++;
    }
    
    @Override
    public String toString(){
        
        return "player name is " + this.name + ", with play piece " + 
                this.playPiece + " and the score is " + this.score;
    }
    
}
