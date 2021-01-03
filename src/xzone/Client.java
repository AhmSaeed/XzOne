/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xzone;

import controllers.AvailablePlayersController;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
    public static boolean getAvailablePlayers = false;
    public static String playerName;
    public static String opponentName;
    public static ArrayList<String> availablePlayersList;
    //static DataInputStream dis;
    //static PrintStream ps;
    public static boolean isReadyToPlay;
     
    public static boolean connect(String ip) {
        try {

            System.out.println("dddd");
            socket = new Socket(ip, 5000);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
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
                while (true) {
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
                            //message = (ArrayList<String>) objectInputStream.readObject();
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
                            System.out.println("aP" + playerName);
                        } else if (message.get(0).equals(Constants.AVAILABLE_PLAYERS)) {
                            System.out.println("aP is send from server");
                            availablePlayersList = new ArrayList<>();
                            availablePlayersList.clear();
                            message.remove(0);
                            //get list of available players names
                            for (String name : message) {
//                                if (!playerName.equals(name)) {
                                    availablePlayersList.add(name);
//                                }
                            }
                            
                            getAvailablePlayers = true;
                            
                            System.out.println("aP is send from server"+availablePlayersList);
                        }else if(message.get(0).equals(Constants.WANT_TO_PLAY)){
                            opponentName=message.get(1);
                            playerName=message.get(2);
                            System.out.println(message.get(2)+"want to play with "+message.get(1));
                            Platform.runLater(()->{
                                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("confirmation Dialog");
                                alert.setHeaderText("Do you accept chaleenge with "+message.get(2));
                                alert.setContentText("Are you ok with this challenge?");
                                Optional<ButtonType> result = alert.showAndWait();
                                System.out.println(result.get().getText());
                                ArrayList<String>information=new ArrayList<>();
                                if(result.get().getText().equals("OK")){
                                    System.out.println(opponentName+" "+playerName);
                                    information.add(Constants.ACCEPT_PLAYING_REQUEST);
                                    information.add(opponentName);
                                    information.add(playerName);
                                    try {
                                        objectOutputStream.writeObject(information);
                                        objectOutputStream.flush();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else{
                                    System.out.println(opponentName+" "+playerName);
                                    information.add(Constants.REJECT_PLAYING_REQUEST);
                                    information.add(opponentName);
                                    information.add(playerName);
                                    try {
                                        objectOutputStream.writeObject(information);
                                        objectOutputStream.flush();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            
                        }else if(message.get(0).equals(Constants.ACCEPT_PLAYING_REQUEST)){
                            System.out.println("xo game is oppened");
                        }else if(message.get(0).equals(Constants.REJECT_PLAYING_REQUEST)){
                            Platform.runLater(() -> {
                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setTitle("request is rejected");
                                    alert.setHeaderText("request is rejected");
                                    alert.setContentText("your opponent "+message.get(2)+" reject your request");
                                    alert.showAndWait();
                                });
                        }

                    } catch (EOFException ex) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Dialog");
                            alert.setContentText("YOU LOGED OUT");
                            alert.showAndWait();
                        });
                        stop();

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
            }
        }.start();
    }

}
