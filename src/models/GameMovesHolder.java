/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Mona
 */
public class GameMovesHolder {

    private static GameMoves gameMoves = new GameMoves();

    private GameMovesHolder() {

    }

    public static GameMoves getGameMoves() {
        return gameMoves;
    }

    public static void setGameMoves(GameMoves game) {
        gameMoves = game;
    }
}
