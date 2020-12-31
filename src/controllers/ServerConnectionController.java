/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author khattab
 */
public class ServerConnectionController implements Initializable {

    @FXML
    private Button connectBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Client();
    }    

    @FXML
    private void connectToServer(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/SignInView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ServerConnectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ((BorderPane)connectBtn.getScene().getRoot()).setCenter(root);
    }
    
}

class Client{

    Socket server;
    DataInputStream dis;
    PrintStream ps;
    
    public Client(){
        
        try {
            server = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(server.getInputStream());
            ps = new PrintStream(server.getOutputStream());
            
            System.out.println("Hello from client");
            
            System.out.println(dis.readLine());

        }
        
        catch (ConnectException cx){
            
            System.out.println("The server is down you can't connect!");
        }
        catch (SocketException sx){
            
            Logger.getLogger(ServerConnectionController.class.getName()).log(Level.SEVERE, null, sx);
            
        }
        
        catch (IOException ex) {
            Logger.getLogger(ServerConnectionController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        
    }





}
