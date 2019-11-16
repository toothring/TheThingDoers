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
    Button resumeGame, btm1, btm2, btm3;
    Label inGameMenuLabel, audioSettingsLabel, visualSettingsLabel, tetrisScore, tetsawScore, gameOverLabel, levelCompleteLabel;

    MainMenu mainMenu;
    Tetris tetris;
    Scoreboard scoreboard;
    AudioSettings audioSettings;

    public InGameMenu(MainMenu m, Tetris t, Scoreboard s, AudioSettings as) {
        tetris = t;
        mainMenu = m;
        scoreboard = s; // Added so its' methods can be called...
        audioSettings = as;
            }

    public void start(Stage primaryStage) throws Exception {

        tetrisScore = new Label("Your current score is " + scoreboard.round(scoreboard.calculateTetrisScore(), 2));
        tetrisScore.setTextAlignment(TextAlignment.CENTER);
        if (scoreboard.round(Scoreboard.calculateTetrisScore(), 2) < 100.0) {
            tetrisScore.setTextFill(Color.web("#bd1509", 1.0));
        } else {
            tetrisScore.setTextFill(Color.web("#0930bd", 1.0));
        }

        // tetsawScore = new Label("Your last score in Tetsaw was "+scoreboard.round(scoreboard.calculateTetrisScore(), 2));

        gameOverLabel = new Label("Game Over.");
        levelCompleteLabel = new Label("Level Complete!");

        inGameMenuLabel = new Label("Needed a break?");
        inGameMenuLabel.setTextFill(Color.web("#363738", 1.0));

        audioSettingsLabel = new Label("I aspire to be audio settings one day.");
        visualSettingsLabel = new Label("I wanna be visual settings when I grow up.");
        window = primaryStage;

        resumeGame = new Button("Resume Game"); // This will need to be adjusted to account for Tetsaw too.
        resumeGame.setOnAction(e -> {
            try {
                tetris.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btm1 = new Button("Back to In-Game Menu");
        btm1.setOnAction(e -> window.setScene(inGameMenu));

        btm2 = new Button("Back to In-Game Menu");
        btm2.setOnAction(e -> window.setScene(inGameMenu));

        btm3 = new Button("Main Menu");
        btm3.setOnAction(e -> tetris.returnToMenu());

        // in-game menu layout:
        VBox inGameMenuLayout = new VBox(40);
        if (tetris.getGameOverSwitch()){ // If the game is over, display "Game Over" before the score.
            inGameMenuLayout.getChildren().addAll(gameOverLabel, tetrisScore, inGameMenuLabel, btm3);
        }
        // **ADJUST REFERENCE TO TETSAW CLASS**
        else if (tetris.getLevelCompleteSwitch()) {// If a level is finished, display "Level completed" before the score.
            inGameMenuLayout.getChildren().addAll(levelCompleteLabel, tetrisScore, inGameMenuLabel, resumeGame, btm3);
        }
        else{ // Otherwise, don't :)
            inGameMenuLayout.getChildren().addAll(tetrisScore, inGameMenuLabel, resumeGame, btm3);
        }
        inGameMenuLayout.setAlignment(Pos.CENTER);
        inGameMenu = new Scene(inGameMenuLayout, 300, 500);
        inGameMenu.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        window.setScene(inGameMenu);

    }
}