/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Windows
 */
public interface GamePlayInterface {
    public int chooseRandomPlayer(int noOfPlayers);
    public boolean checkRow(int[][] board, int y);
    public boolean checkColoumn(int[][] board, int x);
    public boolean checkDiagonal(int[][] board);
    public boolean checkAntiDiagonal(int[][] board);
}
