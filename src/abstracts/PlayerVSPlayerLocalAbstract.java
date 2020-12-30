/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstracts;

import interfaces.GamePlayInterface;
import java.util.Random;

/**
 *
 * @author Windows
 */
public abstract class PlayerVSPlayerLocalAbstract implements GamePlayInterface {
    public int chooseRandomPlayer(int noOfPlayers){
        Random rand = new Random(); 
        int random = rand.nextInt(noOfPlayers);
        return random;
    };
    
    public boolean checkRow(int[][] board, int y){
        if(board[0][y] == board[1][y] && board[1][y] == board[2][y]){
            return true;
        }
        return false;
    };
    
    public boolean checkColoumn(int[][] board, int x){
        if(board[x][0] == board[x][1] && board[x][1] == board[x][2]){
            return true;
        }
        return false;
    };
    
    public boolean checkDiagonal(int[][] board){
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            return true;
        }
        return false;
    };
    
    public boolean checkAntiDiagonal(int[][] board){
        if(board[2][0] == board[1][1] && board[1][1] == board[0][2]){
            return true;
        }
        return false;
    };
}
