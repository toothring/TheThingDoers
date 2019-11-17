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
import javafx.application.Platform;

public class MainMenu extends Application {

    Stage window;
    Scene mainMenu, tetsaw, tetris; //Unused: singlePlayer, multiPlayer
    Scene audioSettingsScene, graphicSettingsScene;
    Button playTetris, tetrisSplashScreen, playTetsawE, playTetsawN, playTetsawH, enterSettings, closeProgram, playTetsaw; //Unused: playTetrisMP, playTetsawMP, enterScoreboard, sp, mp
    Button btm1, btm2, btm3, igmbutton;
    Button audioSettingsBtn, graphicSettings;

    Label mainMenuLabel, titleLabel, playInstructions, playInstructions2, tetsawWelcomeLabel, tetrisWelcomeLabel; //Unused: scoreboardMenuLabel, settingsMenuLabel, singlePlayerMenuLabel, multiPlayerMenuLabel;

    //Create an object of the InGameMenu and TestJFXApp class so we can use it
    Tetris tetrisGame = new Tetris(0, 0, 0, this);
    Tetsaw tetsawGame;

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
        Platform.setImplicitExit(false);
        AudioSubsystem.playMusic("main");
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
        playInstructions = new Label("Choose a difficulty below.\n\nGame controls:\n A: Left\n D: Right\n W: Drop\n S: Speed up descent\n O: Rotate block\n ESC: Pause / open menu");
        playInstructions.setTextAlignment(TextAlignment.CENTER);
        playInstructions2 = new Label("Game controls:\n A: Left\n D: Right\n W: Drop\n S: Speed up descent\n O: Rotate block\n ESC: Pause / open menu");
        playInstructions2.setTextAlignment(TextAlignment.CENTER);
        tetsawWelcomeLabel = new Label("Puzzle time!");
        tetsawWelcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD,20));
        tetsawWelcomeLabel.setTextAlignment(TextAlignment.CENTER);
        tetrisWelcomeLabel = new Label("Firing up the classic, are we?");
        tetrisWelcomeLabel.setTextAlignment(TextAlignment.CENTER);
        tetrisWelcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD,20));

        window = primaryStage;

        closeProgram = new Button("Quit");
        closeProgram.setOnAction(e -> quitProgram());

        playTetris = new Button("Play Tetris");
        playTetris.setOnAction(e -> {
            tetrisGame = new Tetris(10, 20, 30, this);
            tetrisGame.init();
            //this.resetGame(); //this method is required for when a game is already in progress (i.e. player returned to menu)
            try {
                tetrisGame.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        );


        playTetsawE = new Button("Play Tetsaw (Easy)");
        playTetsawE.setOnAction(e -> {
            tetsawGame = new Tetsaw(30, 24, 30, this, Data.easyMode);
            tetsawGame.init();
            //this.resetGame(); //this method is required for when a game is already in progress (i.e. player returned to menu)
            try {
                tetsawGame.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        playTetsawN = new Button("Play Tetsaw (Normal)");
        playTetsawN.setOnAction(e -> {
            tetsawGame = new Tetsaw(30, 24, 30, this, Data.normalMode);
            tetsawGame.init();
            //this.resetGame(); //this method is required for when a game is already in progress (i.e. player returned to menu)
            try {
                tetsawGame.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        playTetsawH = new Button("Play Tetsaw (Hard)");
        playTetsawH.setOnAction(e -> {
            tetsawGame = new Tetsaw(30, 24, 30, this, Data.hardMode);
            tetsawGame.init();
            //this.resetGame(); //this method is required for when a game is already in progress (i.e. player returned to menu)
            try {
                tetsawGame.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        playTetsaw = new Button("Play Tetsaw");
        playTetsaw.setOnAction(e -> {
            window.setScene(tetsaw);
        });

        tetrisSplashScreen = new Button("Play Tetris");
        tetrisSplashScreen.setOnAction(e -> {
            window.setScene(tetris);
        });

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
        mainMenuLayout.getChildren().addAll(titleLabel, mainMenuLabel, tetrisSplashScreen, playTetsaw, enterSettings, closeProgram); //Removed SP/MP buttons, scoreboard button until fully developed
        mainMenuLayout.setAlignment(Pos.CENTER);
        mainMenu = new Scene(mainMenuLayout, 300, 500);
        mainMenu.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        // Tetsaw splash screen layout:
        VBox tetsawLayout = new VBox(40);
        tetsawLayout.getChildren().addAll(tetsawWelcomeLabel, playInstructions, playTetsawE, playTetsawN, playTetsawH, btm1);
        tetsawLayout.setAlignment(Pos.CENTER);
        tetsaw = new Scene(tetsawLayout, 300, 500);
        tetsaw.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        // Tetris splash screen layout
        VBox tetrisLayout = new VBox(40);
        tetrisLayout.getChildren().addAll(tetrisWelcomeLabel, playInstructions2, playTetris, btm2);
        tetrisLayout.setAlignment(Pos.CENTER);
        tetris = new Scene(tetrisLayout, 300, 500);
        tetris.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());


        window.setScene(mainMenu);
        window.setTitle("Tetsaw Main Menu");
        window.setMinWidth(300);
        window.setMinHeight(500);
     //   window.getIcons().add(new Image(getClass().getClassLoader().getResource("/icon.png").toExternalForm()));
        window.setOnCloseRequest(e -> {
            e.consume();
            quitProgram();
        });
        window.show();

    }

    public void quitProgram() {
        Boolean answer = ConfirmBox.display("Are you sure you want to quit?", "That was fun. Come back soon, yeah?");
        if (answer) {
            window.close();
            if (tetrisGame != null) {
                tetrisGame.stop();
            }
            if (tetsawGame != null) {
                tetsawGame.stop();
            }
        }
    }

    public void showMenu() {
        AudioSubsystem.playMusic("main");
        window.setScene(mainMenu);
        }

    /*public void resetGame() {
        tetrisGame = new Tetris(10, 20, 30, this);
        tetrisGame.init();
    }*/

    public AudioSubsystem getAudioSystem() {
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
        audio.registerMusic("hard", "hard.mp3");
        audio.registerMusic("easy", "easy.mp3");
        AudioSubsystem.playMusic("main");
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
