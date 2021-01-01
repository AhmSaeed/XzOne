/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.GameMoves;

/**
 *
 * @author Windows
 */
public final class NavigationHolder {
    private GameMoves gameMoves;
    
    private final static NavigationHolder INSTANCE = new NavigationHolder();
    
    private NavigationHolder(){}
    
    public static NavigationHolder getInstance(){
        return INSTANCE;
    }
    
    public void setPlayers(GameMoves gameMoves){
        this.gameMoves = gameMoves;
    }
    
    public GameMoves getGameMoves(){
        return this.gameMoves;
    }
}
