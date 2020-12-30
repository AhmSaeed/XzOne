/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.PlayersLocal;

/**
 *
 * @author Windows
 */
public final class NavigationHolder {
    private PlayersLocal players;
    
    private final static NavigationHolder INSTANCE = new NavigationHolder();
    
    private NavigationHolder(){}
    
    public static NavigationHolder getInstance(){
        return INSTANCE;
    }
    
    public void setPlayers(PlayersLocal players){
        this.players = players;
    }
    
    public PlayersLocal getPlayers(){
        return this.players;
    }
}
