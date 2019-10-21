package tetristestground;
// Work in progress

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {

    Stage window;
    Scene mainMenu, tetris, tetsaw, settings, scoreboard;
    Button playTetris, playTetsaw, enterSettings, enterScoreboard, closeProgram;
    Button btm1, btm2, btm3, btm4;
    Label filler, filler1, filler2, filler3, filler4;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // 'filler' is just a placeholder label used in the GUI scenes
        filler = new Label("I am a placeholder");
        filler1 = new Label("I am a main menu.");
        filler2 = new Label("I am also a placeholder");
        filler3 = new Label("I aspire to be a scoreboard one day.");
        filler4 = new Label("I wanna be a settings menu when I grow up.");
        window = primaryStage;

        closeProgram = new Button("Quit");
        closeProgram.setOnAction(e -> quitProgram());

        playTetris = new Button("Play Tetris");
        playTetris.setOnAction(e -> window.setScene(tetris));

        playTetsaw = new Button("Play Tetsaw");
        playTetsaw.setOnAction(e -> window.setScene(tetsaw));

        enterScoreboard = new Button("Scoreboard");
        enterScoreboard.setOnAction(e -> window.setScene(scoreboard));

        enterSettings = new Button("Settings");
        enterSettings.setOnAction(e -> window.setScene(settings));

        btm1 = new Button("Back to Menu");
        btm1.setOnAction(e -> window.setScene(mainMenu));

        btm2 = new Button("Back to Menu");
        btm2.setOnAction(e -> window.setScene(mainMenu));

        btm3 = new Button("Back to Menu");
        btm3.setOnAction(e -> window.setScene(mainMenu));

        btm4 = new Button("Back to Menu");
        btm4.setOnAction(e -> window.setScene(mainMenu));

        // Main menu layout:
        VBox mainMenuLayout = new VBox(40);
        mainMenuLayout.getChildren().addAll(filler1, playTetris, playTetsaw, enterSettings, enterScoreboard, closeProgram);
        mainMenuLayout.setAlignment(Pos.CENTER);
        mainMenu = new Scene(mainMenuLayout, 300, 400);

        // Tetsaw layout:
        VBox tetsawLayout = new VBox(40);
        tetsawLayout.getChildren().addAll(filler2, btm1);
        tetsaw = new Scene(tetsawLayout, 300, 300);

        // Tetris layout:
        VBox tetrisLayout = new VBox(40);
        tetrisLayout.getChildren().addAll(filler, btm2);
        tetris = new Scene(tetrisLayout, 300, 300);

        // Scoreboard layout:
        VBox scoreboardLayout = new VBox(40);
        scoreboardLayout.getChildren().addAll(filler3, btm3);
        scoreboard = new Scene(scoreboardLayout, 300, 300);

        // Settings layout:
        VBox settingsLayout = new VBox(40);
        settingsLayout.getChildren().addAll(filler4, btm4);
        settings = new Scene(settingsLayout, 300, 300);

        window.setScene(mainMenu);
        window.setTitle("Tetsaw Main Menu");
        window.setOnCloseRequest(e -> {
          e.consume();
          quitProgram();
        });
        window.show();

    }

    private void quitProgram() {
        Boolean answer = ConfirmBox.display("Are you sure you want to quit?", "Are you sure you want to quit?");
        if(answer)
            window.close();
        }
}
