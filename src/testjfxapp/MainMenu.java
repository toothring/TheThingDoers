package testjfxapp;
// Work in progress

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import testjfxapp.subsystems.AudioSubsystem;

import java.awt.*;

public class MainMenu extends Application{

    Stage window;
    Scene mainMenu, tetsaw; //Unused: singlePlayer, multiPlayer
    Scene audioSettingsScene, graphicSettingsScene;
    Button playTetris, playTetsaw, enterSettings, closeProgram; //Unused: playTetrisMP, playTetsawMP, enterScoreboard, sp, mp
    Button btm1, btm2, btm3, igmbutton;
    Button audioSettingsBtn, graphicSettings;

    Label mainMenuLabel, titleLabel; //Unused: scoreboardMenuLabel, settingsMenuLabel, singlePlayerMenuLabel, multiPlayerMenuLabel;

    //Create an object of the InGameMenu and TestJFXApp class so we can use it
    Tetris tetrisGame = new Tetris(10,20,30, this);

    AudioSubsystem audio;
    ReversableMenu settingsMenu = new Settings(this);
    ReversableMenu sb = new Scoreboard(this, tetrisGame);
    AudioSettings audioSettings = new AudioSettings(settingsMenu,this);

    Scoreboard sboard = new Scoreboard(this, tetrisGame);
    InGameMenu igm = new InGameMenu(this, tetrisGame, sboard, audioSettings);


    public static void main(String[] args) {
        launch(args);
        }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initAudio();

        audio.playMusic("main");
        // This will determine the screen size (width and height) which you can then assign to a scene.
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double fullwidth = screenSize.getWidth();
        double fullheight = screenSize.getHeight();
        final boolean running = false;

        titleLabel = new Label("Hi!");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD,30));
        mainMenuLabel = new Label("How's things? \nPick a button below to get started.");
        mainMenuLabel.setTextFill(Color.web("#363738", 1.0));
        mainMenuLabel.setTextAlignment(TextAlignment.CENTER);

        window = primaryStage;

        closeProgram = new Button("Quit");
        closeProgram.setOnAction(e -> quitProgram());

        playTetris = new Button("Play Tetris");
        playTetris.setOnAction(e -> {
            this.resetGame(); //this method is required for when a game is already in progress (i.e. player returned to menu)
                try {
                    tetrisGame.start(window);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        );


        playTetsaw = new Button("Play Tetsaw");
        playTetsaw.setOnAction(e -> window.setScene(tetsaw));

        enterSettings = new Button("Settings");
        enterSettings.setOnAction(e -> {
            try {
                settingsMenu.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btm1 = new Button("Back to Menu");
        btm1.setOnAction(e -> window.setScene(mainMenu));

        btm2 = new Button("Back to Menu");
        btm2.setOnAction(e -> window.setScene(mainMenu));

        btm3 = new Button("Back to Menu");
        btm3.setOnAction(e -> window.setScene(mainMenu));

        audioSettingsBtn = new Button("Audio Settings");
        audioSettingsBtn.setOnAction(e -> window.setScene(audioSettingsScene));

        graphicSettings = new Button("Graphic Settings");

        igmbutton = new Button("Open In-Game Menu");
        igmbutton.setOnAction(e -> {
            try {
                igm.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Main menu layout:
        VBox mainMenuLayout = new VBox(40);
        mainMenuLayout.getChildren().addAll(titleLabel, mainMenuLabel, playTetris, playTetsaw, enterSettings, closeProgram); //Removed SP/MP buttons, scoreboard button until fully developed
        mainMenuLayout.setAlignment(Pos.CENTER);
        mainMenu = new Scene(mainMenuLayout, 300, 500);
        mainMenu.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        // Tetsaw layout:
        VBox tetsawLayout = new VBox(40);
        tetsawLayout.getChildren().addAll(btm1, igmbutton);
        tetsawLayout.setAlignment(Pos.CENTER);
        tetsaw = new Scene(tetsawLayout, 300, 500);
        tetsaw.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        window.setScene(mainMenu);
        window.setTitle("Tetsaw Main Menu");
        window.setMinWidth(300);
        window.setMinHeight(500);
       // window.getIcons().add(new Image(getClass().getClassLoader().getResource("/icon.png").toExternalForm()));
        window.setOnCloseRequest(e -> {
          e.consume();
          quitProgram();
        });
        window.show();

    }

    public void quitProgram() {
        Boolean answer = ConfirmBox.display("Are you sure you want to quit?", "That was fun. Come back soon, yeah?");
        if(answer) {
            window.close();
            tetrisGame.stop();
        }
    }

    public void showMenu() {
        window.setScene(mainMenu);
        }

    public void resetGame(){
        tetrisGame = new Tetris(10,20,30,this);
        tetrisGame.init();
    }
    
    public AudioSubsystem getAudioSystem(){
        return audio;
    }

    private void initAudio() {
        /*
            PUT NEW AUDIO TRACKS AND SOUND EFFECTS HERE
        
            Track listing guideline: Reference Name - Description - Copyright (if applicable, use N/A for tracks/effects Callum has made)
        
        Tracks:
        main - Main background track, currently a placeholder - N/A
        
        Sound Effects:
        levelend - Level ending sound effect - N/A
        */
        audio = new AudioSubsystem();
        audio.registerSound("levelend", "levelDone.mp3");
        audio.registerMusic("main", "main.mp3");
        audio.playMusic("main");
    }
}

/* Code for implementation of semi-started other features below:

 // Not required unless MP is developed:
//        singlePlayerMenuLabel = new Label("Two's a crowd. \nI'd go solo too if I were you. \n\n...What're you playing?");
//        singlePlayerMenuLabel.setTextAlignment(TextAlignment.CENTER);
//        singlePlayerMenuLabel.setTextFill(Color.web("#363738", 1.0));
//        multiPlayerMenuLabel = new Label("I'm down for some multitasking. \n\nJust tell me what you're playing.");
//        multiPlayerMenuLabel.setTextAlignment(TextAlignment.CENTER);
//        multiPlayerMenuLabel.setTextFill(Color.web("#363738", 1.0));

// Multiplayer not being implemented presently
//        playTetrisMP = new Button("Play Tetris");
//        playTetrisMP.setOnAction(e -> {
//            tetrisGame.init();
//            try {
//                tetrisGame.start(window);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });

// Single player layout: **Not required unless MP is being implemented**
//        VBox singlePlayerLayout = new VBox(40);
//        singlePlayerLayout.getChildren().addAll(singlePlayerMenuLabel, playTetsaw, playTetris, btm2);
//        singlePlayerLayout.setAlignment(Pos.CENTER);
//        singlePlayer = new Scene(singlePlayerLayout, 300, 500);
//        singlePlayer.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

//        // Multi player layout: **Not currently being implemented**
//        VBox multiPlayerLayout = new VBox(40);
//        multiPlayerLayout.getChildren().addAll(multiPlayerMenuLabel, playTetsawMP, playTetrisMP, btm3);
//        multiPlayerLayout.setAlignment(Pos.CENTER);
//        multiPlayer = new Scene(multiPlayerLayout, 300, 500);
//        multiPlayer.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

            // Multiplayer not being implemented presently
//        playTetsawMP = new Button("Play Tetsaw");
//        playTetsawMP.setOnAction(e -> window.setScene(tetsaw));

            // Scoreboard not yet ready
//        enterScoreboard = new Button("Scoreboard");
//        enterScoreboard.setOnAction(e -> {
//            try{
//                sb.start(window);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });

        // Multiplayer not currenly being implemented
//        sp = new Button("Single Player");
//        sp.setOnAction(e -> window.setScene(singlePlayer));
//
//        mp = new Button("Multi Player");
//        mp.setOnAction(e -> window.setScene(multiPlayer));

 */
