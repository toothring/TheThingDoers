package tetristestground;
// Work in progress

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {

    Stage window;
    Scene mainMenu, tetris, tetsaw, settings, scoreboard;
    Button playTetris, playTetsaw, enterSettings, enterScoreboard, backToMenu;
    Label filler, filler2, filler3, filler4;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // 'filler' is just a placeholder label used in the other GUI scenes
        filler = new Label("I am a placeholder");
        filler2 = new Label("I am also a placeholder");
        filler3 = new Label("I aspire to be a scoreboard one day.");
        filler4 = new Label("I wanna be a settings menu when I grow up.");
        window = primaryStage;

        playTetris = new Button("Play Tetris");
        playTetris.setOnAction(e -> window.setScene(tetris));

        playTetsaw = new Button("Play Tetsaw");
        playTetsaw.setOnAction(e -> window.setScene(tetsaw));

        enterScoreboard = new Button("Scoreboard");
        enterScoreboard.setOnAction(e -> window.setScene(scoreboard));

        enterSettings = new Button("Settings");
        enterSettings.setOnAction(e -> window.setScene(settings));

        backToMenu = new Button("Back to Menu");
        backToMenu.setOnAction(e -> window.setScene(mainMenu));

        // Main menu layout:
        VBox mainMenuLayout = new VBox(40);
        mainMenuLayout.getChildren().addAll(filler, playTetris, playTetsaw, enterSettings, enterScoreboard);
        mainMenu = new Scene(mainMenuLayout, 300, 300);

        // Tetsaw layout:
        VBox tetsawLayout = new VBox(40);
        tetsawLayout.getChildren().addAll(filler, backToMenu);
        tetsaw = new Scene(tetsawLayout, 300, 300);

        // Tetris layout:
        VBox tetrisLayout = new VBox(40);
        tetrisLayout.getChildren().addAll(filler2, backToMenu);
        tetris = new Scene(tetrisLayout, 300, 300);

        // Scoreboard layout:
        VBox scoreboardLayout = new VBox(40);
        scoreboardLayout.getChildren().addAll(filler3, backToMenu);
        scoreboard = new Scene(scoreboardLayout, 300, 300);

        // Settings layout:
        VBox settingsLayout = new VBox(40);
        settingsLayout.getChildren().addAll(filler4, backToMenu);
        settings = new Scene(settingsLayout, 300, 300);

        window.setScene(mainMenu);
        window.setTitle("Tetsaw Main Menu");
        window.show();

    }
}
