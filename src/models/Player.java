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
public class Player implements Serializable{
    private String name;
    private int score;
    private int playPiece;
    
    public Player(String name){this.name = name;}
    
    public Player(String name, int playPiece){
        this.name = name;
        this.playPiece = playPiece;
        this.score = 0;
        
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public void setPlayPiece(int score){
        this.playPiece = score;
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
    
    @Override
    public String toString(){
        return "player name is " + this.name + ", with play piece " + 
                this.playPiece + " and the score is " + this.score;
    }
}
