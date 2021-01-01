/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xzone;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 *
 * @author Rahma Ayman
 */
public class Client extends Thread {

    static Socket socket;
    public static ObjectInputStream objectInputStream;
    public static ObjectOutputStream objectOutputStream;
    public static boolean isRegistered;
    public static boolean isLogged;
    public static String playerName;
    //static DataInputStream dis;
    //static PrintStream ps;

    public static boolean connect(String ip) {
        try {

            System.out.println("dddd");
            socket = new Socket("127.0.0.1", 5000);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            //dis=new DataInputStream(socket.getInputStream());
            //System.out.println(dis.readLine());
            return true;
        } catch (ConnectException ex) {
            System.out.println("serverOFF");
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static void recieving() {
        new Thread() {
//            String sendData = "hello client";
            ArrayList<String> message = new ArrayList<>();

            @Override
            public void run() {
                try {
                    message = (ArrayList<String>) objectInputStream.readObject();
                    if (message.get(0).equals(Constants.REGISTER)) {
                        if (message.get(1).equals(Constants.YOU_ARA_REGISTER)) {
                            isRegistered = true;
                        } else {
                            isRegistered = false;
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Register");
                                alert.setHeaderText("Register Failure");
                                alert.setContentText("this name is already used try another one!!");
                                alert.showAndWait();
                            });
                        }
                    } else if (message.get(0).equals(Constants.LOGIN)) {
                        message = (ArrayList<String>) objectInputStream.readObject();
                        if (message.get(1).equals(Constants.YOU_LOGED_IN)) {
                            isLogged = true;
                        } else {
                            isLogged = false;
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Login");
                                alert.setHeaderText("Login Failure");
                                alert.setContentText("your userName or password is wrong or you are aleady loged in from another device!!");
                                alert.showAndWait();
                            });
                        }
                    }

                } catch (SocketException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    try {
                        socket.close();
                        objectInputStream.close();
                        objectInputStream.close();
                        stop();
                    } catch (IOException ex1) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }.start();
    }
//    public static boolean isLogged(ArrayList<String> message){
//        if(message.get(1).equals("you loged in")){
//            return true;
//        }else{
//            return false;
//        }
//    }

}
