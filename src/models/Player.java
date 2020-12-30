/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Windows
 */
public class Player {
    private String playerName;
    private int totalScore;
    private int gameScore;
    private String avatarURI;
    private int lastX;
    private int lastY;
    private char choosenChar;
    private int randomNum;
    
    public Player(){
        playerName = "";
        totalScore = 0;
        gameScore = 0;
        avatarURI = "";
        lastX = 0;
        lastY = 0;
        choosenChar = 'X';
        randomNum = 0;
    }
    
    public void setPlayerName(String name){
        this.playerName = name;
    }
    
    public void setTotalScore(int score){
        this.totalScore = score;
    }
    
    public void setGameScore(int score){
        this.gameScore = score;
    }
    
    public void setAvatarURI(String uri){
        this.avatarURI = uri;
    }
    
    public void setLastX(int x){
        this.lastX = x;
    }
    
    public void setLastY(int y){
        this.lastY = y;
    }
    
    public void setChoosenChar(char ch){
        this.choosenChar = ch;
    }
    
    public void setRandomNum(int randomNum){
        this.randomNum = randomNum;
    }
    
    public String getPlayerName(){
        return playerName;
    }
    
    public int getTotalScore(){
        return totalScore;
    }
    
    public int getGameScore(){
        return gameScore;
    }
    
    public String getAvatarURI(){
        return avatarURI;
    }
    
    public int getLastX(){
        return lastX;
    }
    
    public int getLastY(){
        return lastY;
    }
    
    public char getChoosenChar(){
        return choosenChar;
    }
    
    public int getRandomNum(){
        return randomNum;
    }
}
