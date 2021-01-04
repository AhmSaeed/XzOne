/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xzone;

import javafx.scene.layout.BorderPane;

/**
 *
 * @author Rahma Ayman
 */
public class DashBoardHolder {
    
    private static BorderPane bp = null;
    
    private DashBoardHolder(){}
    
    public static void setDashBoard(BorderPane dashBoard){
        if(bp == null)
            bp = dashBoard;
    }
    
    public static BorderPane getDashBoard(){return bp;}
}
