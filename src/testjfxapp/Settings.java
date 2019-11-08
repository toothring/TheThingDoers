package testjfxapp;
// Work in progress

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Settings {

    Stage window;
    Scene settings;
    Button enterSettings, enterScoreboard, closeProgram, sp, mp;
    Button btm1, btm2, btm3;
    Label mainMenuLabel;

    MainMenu mainMenu;

    public Settings(MainMenu m){
        mainMenu = m;
    }

   // @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        closeProgram = new Button("Quit");
        closeProgram.setOnAction(e -> mainMenu.quitProgram());

        btm1 = new Button("Back to Menu");
        btm1.setOnAction(e -> {
            try {
                mainMenu.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btm2 = new Button("Back to Menu");
        btm2.setOnAction(e -> window.setScene(settings));

        btm3 = new Button("Back to Menu");
        btm3.setOnAction(e -> window.setScene(settings));

        // Graphics Options layout:
        VBox graphicsOptionsLayout = new VBox(40);
        graphicsOptionsLayout.getChildren().addAll(btm1, closeProgram);
        graphicsOptionsLayout.setAlignment(Pos.CENTER);
        settings = new Scene(graphicsOptionsLayout, 300, 500);
        settings.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        window.setScene(settings);

    }
}

