package testjfxapp;
// Work in progress

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Settings {

    Stage window;
    Scene settings;
    Button btm1, graphicsOpts, accessSet, audioSet;

    MainMenu mainMenu;
    GraphicsOptions graphicsOptions;
    AccesSettings accesSettings;
    AudioSettings audioSettings;

    public Settings(MainMenu m){
        mainMenu = m;
    }

   // @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        btm1 = new Button("Back to Menu");
        btm1.setOnAction(e -> window.setScene(mainMenu.mainMenu));

        graphicsOpts = new Button("Graphics Options");
        graphicsOpts.setOnAction(e -> window.setScene(graphicsOptions.graphicsOptions));

        accessSet = new Button("Accessibility Settings");
        accessSet.setOnAction(e -> window.setScene(accesSettings.accesSettings));

        audioSet = new Button("Audio Settings");
        audioSet.setOnAction(e -> window.setScene(audioSettings.audioSettings));

        // Graphics Options layout:
        VBox graphicsOptionsLayout = new VBox(40);
        graphicsOptionsLayout.getChildren().addAll(graphicsOpts, accessSet, audioSet, btm1);
        graphicsOptionsLayout.setAlignment(Pos.CENTER);
        settings = new Scene(graphicsOptionsLayout, 300, 500);
        settings.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        window.setScene(settings);

    }
}

