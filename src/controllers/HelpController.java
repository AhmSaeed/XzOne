/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Rahma Ayman
 */
public class HelpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private Media media;
    int count=1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String path = new File("src/videos/xo.mp4").getAbsolutePath();
        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }
    @FXML
    private void playVideo(MouseEvent event){
        if(count==1){
            mediaPlayer.play();
            count=2;
        }else{
            mediaPlayer.pause();
            count=1;
        }
        
        
    }

}
