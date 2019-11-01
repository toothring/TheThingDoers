package testjfxapp;
// Work in progress
    // Write a fx to show main menu, call this within TestJFXApp w/ button.

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

public class MainMenu extends Application {

    Stage window;
    Scene mainMenu, tetris, tetsaw, settings, scoreboard, singlePlayer, multiPlayer;
    Button playTetris, playTetrisMP, playTetsaw, playTetsawMP, enterSettings, enterScoreboard, closeProgram, sp, mp;
    Button btm1, btm2, btm3, btm4, btm5, btm6;
    Label tetrisMenuLabel, mainMenuLabel, tetsawMenuLabel, scoreboardMenuLabel, settingsMenuLabel, singlePlayerMenuLabel, multiPlayerMenuLabel;

    TestJFXApp tetrisGame = new TestJFXApp(10,20,30, this);
    AudioSubsystem audio;

    public static void main(String[] args) {
        launch(args);
        }

    @Override
    public void start(Stage primaryStage) throws Exception {
        audio = new AudioSubsystem();
        audio.playTest();


        // 'filler' is just a placeholder label used in the GUI scenes
        mainMenuLabel = new Label("How's things? \nPick a button below to get started.");
        mainMenuLabel.setTextAlignment(TextAlignment.CENTER);
        mainMenuLabel.setTextFill(Color.web("#2712c4", 1.0));
        scoreboardMenuLabel = new Label("I aspire to be a scoreboard one day.");
        settingsMenuLabel = new Label("I wanna be a settings menu when I grow up.");
        singlePlayerMenuLabel = new Label("Two's a crowd. \nI'd go solo too if I were you. \n\n...What're you playing?");
        singlePlayerMenuLabel.setTextAlignment(TextAlignment.CENTER);
        multiPlayerMenuLabel = new Label("I'm down for some multitasking. \n\nJust tell me what you're playing.");
        multiPlayerMenuLabel.setTextAlignment(TextAlignment.CENTER);
        window = primaryStage;

        closeProgram = new Button("Quit");
        closeProgram.setOnAction(e -> quitProgram());

        playTetris = new Button("Play Tetris");
        playTetris.setOnAction(e -> {
            tetrisGame.init();
            try {
                tetrisGame.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

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
        enterScoreboard.setOnAction(e -> window.setScene(scoreboard));

        enterSettings = new Button("Settings");
        enterSettings.setOnAction(e -> window.setScene(settings));

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
        tetsawLayout.getChildren().addAll(btm1);
        tetsawLayout.setAlignment(Pos.CENTER);
        tetsaw = new Scene(tetsawLayout, 300, 500);
        tetsaw.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        // Tetris layout:
        VBox tetrisLayout = new VBox(40);
        tetrisLayout.getChildren().addAll(btm2);
        tetrisLayout.setAlignment(Pos.CENTER);
        tetris = new Scene(tetrisLayout, 300, 500);
        tetris.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        // Scoreboard layout:
        VBox scoreboardLayout = new VBox(40);
        scoreboardLayout.getChildren().addAll(scoreboardMenuLabel, btm3);
        scoreboardLayout.setAlignment(Pos.CENTER);
        scoreboard = new Scene(scoreboardLayout, 300, 500);
        scoreboard.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        // Settings layout:
        VBox settingsLayout = new VBox(40);
        settingsLayout.getChildren().addAll(settingsMenuLabel, btm4);
        settingsLayout.setAlignment(Pos.CENTER);
        settings = new Scene(settingsLayout, 300, 500);
        settings.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        window.setScene(mainMenu);
        window.setTitle("Tetsaw Main Menu");
        window.setMinWidth(300);
        window.setMinHeight(500);
        window.getIcons().add(new Image("/images/icon.png"));
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

    public void showMenu() {
        window.setScene(mainMenu);
        }
}

