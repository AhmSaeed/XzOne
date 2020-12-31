/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author khattab
 */
public class GameMovesHolder {
    
    private final static GameMoves GAME_MOVES = new GameMoves();
    
    
    private GameMovesHolder(){
        
    }

    public static GameMoves getGAME_MOVES() {
        return GAME_MOVES;
    }
    
    
    
}
