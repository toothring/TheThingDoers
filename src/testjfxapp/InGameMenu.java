package testjfxapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;

public class InGameMenu extends Application {

    Stage window;
    Scene inGameMenu, inGameAudioSettings, inGameVisualSettings;
    Button resumeGame, AudioSettingsBTN, inGameVisualSettingsBTN, closeProgram;
    Slider musicVolume, sfxVolume, fps, brightness;
    Button btm1, btm2;
    Label InGameMenuLabel, AudioSettingsLabel, visualSettingsLabel;

    TestJFXApp tetrisGame = new TestJFXApp(10,20,30, this);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        // 'filler' is just a placeholder label used in the GUI scenes
        InGameMenuLabel = new Label("Need a break? \nHave a KitKat.");
        InGameMenuLabel.setTextAlignment(TextAlignment.CENTER);
        InGameMenuLabel.setTextFill(Color.web("#2712c4", 1.0));
        AudioSettingsLabel = new Label("I aspire to be audio settings one day.");
        visualSettingsLabel = new Label("I wanna be visual settings when I grow up.");
        window = primaryStage;

        closeProgram = new Button("Quit Game");
        closeProgram.setOnAction(e -> quitProgram());

        resumeGame = new Button("Resume Game");
        //resumeGame.setOnAction(e -> window.setScene(tetris));


        AudioSettingsBTN = new Button("Audio Settings");
        AudioSettingsBTN.setOnAction(e -> window.setScene(inGameAudioSettings));

        inGameVisualSettingsBTN = new Button("Visual Settings");
        inGameVisualSettingsBTN.setOnAction(e -> window.setScene(inGameVisualSettings));

        musicVolume = new Slider(0,100,50);
        //unsure how to listen/handle volume change

        sfxVolume = new Slider (0,100,50);
        //unsure how to listen/handle volume change

        fps = new Slider (30,90,60);
        //unsure how to listen/handle

        brightness = new Slider (0,100,50);
        //unsure how to listen/handle


        btm1 = new Button("Back to Menu");
        btm1.setOnAction(e -> window.setScene(inGameMenu));

        btm2 = new Button("Back to Menu");
        btm2.setOnAction(e -> window.setScene(inGameMenu));

        // in-game menu layout:
        VBox inGameMenuLayout = new VBox(40);
        inGameMenuLayout.getChildren().addAll(InGameMenuLabel, resumeGame, AudioSettingsBTN, inGameVisualSettingsBTN, closeProgram);
        inGameMenuLayout.setAlignment(Pos.CENTER);
        inGameMenu = new Scene(inGameMenuLayout, 300, 500);
       inGameMenu.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        // Audio Settings layout:
        VBox inGameAudioSettingsLayout = new VBox(40);
        inGameAudioSettingsLayout.getChildren().addAll(musicVolume, sfxVolume, btm1);
        inGameAudioSettingsLayout.setAlignment(Pos.CENTER);
        inGameAudioSettings = new Scene(inGameAudioSettingsLayout, 300, 500);
        inGameAudioSettings.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());


        // Visual Settings layout:
        VBox visualSettingsLayout = new VBox(40);
        visualSettingsLayout.getChildren().addAll(fps, brightness, btm2);
        visualSettingsLayout.setAlignment(Pos.CENTER);
        inGameVisualSettings = new Scene(visualSettingsLayout, 300, 500);
        inGameVisualSettings.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        window.setScene(inGameMenu);
        window.setTitle("Game Paused");
        window.setMinWidth(300);
        window.setMinHeight(500);
        window.getIcons().add(new Image("/icon.png"));
        window.setOnCloseRequest(e -> {
            e.consume();
            quitProgram();
        });
        window.show();

    }

    private void quitProgram() {
        Boolean answer = ConfirmBox.display("Are you sure you want to quit?", "That was fun. Come back soon, yeah?");
        if(answer) {
            tetrisGame.stop();
            window.close();
        }
    }

//    public void showMenu() {
//        window.setScene(mainMenu);
//    }
}

