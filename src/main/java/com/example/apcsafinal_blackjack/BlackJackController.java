package com.example.apcsafinal_blackjack;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class BlackJackController {
    private BlackJack controller;
    private MediaPlayer mediaPlayer;
    @FXML
    private Label welcomeText;

    public BlackJackController(BlackJack controllerRef){
        controller = controllerRef;
        playMusic("1st Contact - Aberration.mp3");
    }

    @FXML
    protected void onLoginClick() {
        playHitSound("mixkit-interface-click-1126.wav");
    }

    protected void onCreateClick() {
        playHitSound("mixkit-interface-click-1126.wav");
    }

    private void playHitSound(String fileName){
        String path = getClass().getResource(fileName).getPath();
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    private void playMusic(String fileName){
        String path = getClass().getResource(fileName).getPath();
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }


}