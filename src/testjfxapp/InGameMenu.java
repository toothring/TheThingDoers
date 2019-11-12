package testjfxapp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class InGameMenu {

    Stage window;
    Scene inGameMenu, inGameAudioSettings, inGameVisualSettings;
    Button resumeGame, AudioSettingsBTN, inGameVisualSettingsBTN, closeProgram;
    Slider musicVolume, sfxVolume, fps, brightness;
    Button btm1, btm2, btm3;
    Label InGameMenuLabel, AudioSettingsLabel, visualSettingsLabel;

    MainMenu mainMenu;
    TestJFXApp tetris;

    public InGameMenu(MainMenu m, TestJFXApp t) {
        tetris = t;
        mainMenu = m;
            }

    //@Override
    public void start(Stage primaryStage) throws Exception {

        // 'filler' is just a placeholder label used in the GUI scenes
        InGameMenuLabel = new Label("Needed a break?");
        InGameMenuLabel.setTextAlignment(TextAlignment.CENTER);
        InGameMenuLabel.setTextFill(Color.web("#2712c4", 1.0));
        AudioSettingsLabel = new Label("I aspire to be audio settings one day.");
        visualSettingsLabel = new Label("I wanna be visual settings when I grow up.");
        window = primaryStage;


        resumeGame = new Button("Resume Game");
        resumeGame.setOnAction(e -> {
            // Something else needs to go in here to get back to the game...
            try {
                tetris.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            tetris.resume();
        });

        AudioSettingsBTN = new Button("Audio Settings");
        AudioSettingsBTN.setOnAction(e -> window.setScene(inGameAudioSettings));

        inGameVisualSettingsBTN = new Button("Visual Settings");
        inGameVisualSettingsBTN.setOnAction(e -> window.setScene(inGameVisualSettings));

        musicVolume = new Slider(0, 100, 50);
        //unsure how to handle volume change

        sfxVolume = new Slider(0, 100, 50);
        //unsure how to handle volume change

        fps = new Slider(30, 90, 60);
        //unsure how to handle

        brightness = new Slider(0, 100, 50);
        //unsure how to handle

        btm1 = new Button("Back to In-Game Menu");
        btm1.setOnAction(e -> window.setScene(inGameMenu));

        btm2 = new Button("Back to In-Game Menu");
        btm2.setOnAction(e -> window.setScene(inGameMenu));

        // Returning to the main menu doesn't seem to play nice
        btm3 = new Button("Main Menu");
        btm3.setOnAction(e -> tetris.returnToMenu()); // I've tried mainMenu.showMenu() and tetris.returnToMenu and neither work

        // in-game menu layout:
        VBox inGameMenuLayout = new VBox(40);
        inGameMenuLayout.getChildren().addAll(InGameMenuLabel, resumeGame, AudioSettingsBTN, inGameVisualSettingsBTN, btm3);
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

    }
}