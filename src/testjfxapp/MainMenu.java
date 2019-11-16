package testjfxapp;
// Work in progress

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import testjfxapp.subsystems.AudioSubsystem;

import java.awt.*;

public class MainMenu extends Application{

    Stage window;
    Scene mainMenu, tetris, tetsaw, settings, scoreboard, singlePlayer, multiPlayer, gameEndScene;
    Button playTetris, playTetrisMP, playTetsaw, playTetsawMP, enterSettings, enterScoreboard, closeProgram, sp, mp;
    Button btm1, btm2, btm3, btm4, btm5, btm6, igmbutton;
    Label tetrisMenuLabel, mainMenuLabel, tetsawMenuLabel, scoreboardMenuLabel, settingsMenuLabel, singlePlayerMenuLabel, multiPlayerMenuLabel;

    //Create an object of the InGameMenu and TestJFXApp class so we can use it
    Tetris tetrisGame = new Tetris(10,20,30, this);

    AudioSubsystem audio;
    ReversableMenu settingsMenu = new Settings(this);
    ReversableMenu sb = new Scoreboard(this, tetrisGame);

    Scoreboard sboard = new Scoreboard(this, tetrisGame); //Added because I couldn't pass sb into the below
    InGameMenu igm = new InGameMenu(this, tetrisGame, sboard); //Added sboard so the InGameMenu can access it
    //AudioSettings audioSettings = new AudioSettings(this);
    //AccessibilSettings accessibilSettings = new AccessibilSettings(this);


    public static void main(String[] args) {
        launch(args);
        }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initAudio();


        // This will determine the screen size (width and height) which you can then assign to a scene.
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double fullwidth = screenSize.getWidth();
        double fullheight = screenSize.getHeight();
        final boolean running = false;

        mainMenuLabel = new Label("How's things? \nPick a button below to get started.");
        mainMenuLabel.setTextFill(Color.web("#363738", 1.0));
        mainMenuLabel.setTextAlignment(TextAlignment.CENTER);
        singlePlayerMenuLabel = new Label("Two's a crowd. \nI'd go solo too if I were you. \n\n...What're you playing?");
        singlePlayerMenuLabel.setTextAlignment(TextAlignment.CENTER);
        singlePlayerMenuLabel.setTextFill(Color.web("#363738", 1.0));
        multiPlayerMenuLabel = new Label("I'm down for some multitasking. \n\nJust tell me what you're playing.");
        multiPlayerMenuLabel.setTextAlignment(TextAlignment.CENTER);
        multiPlayerMenuLabel.setTextFill(Color.web("#363738", 1.0));
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

        playTetrisMP = new Button("Play Tetris");
        playTetrisMP.setOnAction(e -> {
            tetrisGame.init();
            try {
                tetrisGame.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        playTetsaw = new Button("Play Tetsaw");
        playTetsaw.setOnAction(e -> window.setScene(tetsaw));

        playTetsawMP = new Button("Play Tetsaw");
        playTetsawMP.setOnAction(e -> window.setScene(tetsaw));

        enterScoreboard = new Button("Scoreboard");
        enterScoreboard.setOnAction(e -> {
            try{
                sb.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        enterSettings = new Button("Settings");
        enterSettings.setOnAction(e -> {
            try {
                settingsMenu.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        sp = new Button("Single Player");
        sp.setOnAction(e -> window.setScene(singlePlayer));

        mp = new Button("Multi Player");
        mp.setOnAction(e -> window.setScene(multiPlayer));

        btm1 = new Button("Back to Menu");
        btm1.setOnAction(e -> window.setScene(mainMenu));

        btm2 = new Button("Back to Menu");
        btm2.setOnAction(e -> window.setScene(mainMenu));

        btm3 = new Button("Back to Menu");
        btm3.setOnAction(e -> window.setScene(mainMenu));

        btm4 = new Button("Back to Menu");
        btm4.setOnAction(e -> window.setScene(mainMenu));

        btm5 = new Button("Back to Menu");
        btm5.setOnAction(e -> window.setScene(mainMenu));

        btm6 = new Button("Back to Menu");
        btm6.setOnAction(e -> window.setScene(mainMenu));

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
        mainMenuLayout.getChildren().addAll(mainMenuLabel, sp, mp, enterSettings, enterScoreboard, closeProgram);
        mainMenuLayout.setAlignment(Pos.CENTER);
        mainMenu = new Scene(mainMenuLayout, 300, 500);
        mainMenu.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        // Single player layout:
        VBox singlePlayerLayout = new VBox(40);
        singlePlayerLayout.getChildren().addAll(singlePlayerMenuLabel, playTetsaw, playTetris, btm5);
        singlePlayerLayout.setAlignment(Pos.CENTER);
        singlePlayer = new Scene(singlePlayerLayout, 300, 500);
        singlePlayer.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        // Multi player layout:
        VBox multiPlayerLayout = new VBox(40);
        multiPlayerLayout.getChildren().addAll(multiPlayerMenuLabel, playTetsawMP, playTetrisMP, btm6);
        multiPlayerLayout.setAlignment(Pos.CENTER);
        multiPlayer = new Scene(multiPlayerLayout, 300, 500);
        multiPlayer.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        // Tetsaw layout:
        VBox tetsawLayout = new VBox(40);
        tetsawLayout.getChildren().addAll(btm1, igmbutton);
        tetsawLayout.setAlignment(Pos.CENTER);
        tetsaw = new Scene(tetsawLayout, 300, 500);
        tetsaw.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        // End-of-game score screen layout:
        Label tetrisScore = new Label("Your last score in Tetris was "+ Scoreboard.round(Scoreboard.calculateTetrisScore(), 2));
        VBox gameEndLayout = new VBox(40);
        gameEndLayout.getChildren().addAll(tetrisScore, btm2);
        gameEndLayout.setAlignment(Pos.CENTER);
        gameEndScene = new Scene(gameEndLayout, 300, 500);
        gameEndScene.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());


        window.setScene(mainMenu);
        window.setTitle("Tetsaw Main Menu");
        window.setMinWidth(300);
        window.setMinHeight(500);
        window.getIcons().add(new Image("/icon.png"));
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

    public void showGameEndScene(){
        window.setScene(gameEndScene);
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

