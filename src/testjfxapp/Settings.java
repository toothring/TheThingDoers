package testjfxapp;
// Work in progress

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Settings extends ReversableMenu {

    Stage window;
    Scene settings;
    Button btm1, graphicsOpts, accessSet, audioSet;

    MainMenu mainMenu;
    ReversableMenu graphicsOptions, accessibilSettings, audioSettings;

    public Settings(MainMenu m){
        mainMenu = m;
        graphicsOptions = new GraphicsOptions(this, mainMenu);
        accessibilSettings = new AccessibilSettings(this, mainMenu);
        audioSettings = new AudioSettings(this, mainMenu);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        btm1 = new Button("Back to Menu");
        btm1.setOnAction(e -> mainMenu.showMenu());
        
        graphicsOptions.start(window);
        graphicsOpts = new Button("Graphics Options");
        graphicsOpts.setOnAction(e -> graphicsOptions.setCurrentScene());

        accessibilSettings.start(window);
        accessSet = new Button("Accessibility Settings");
        accessSet.setOnAction(e -> accessibilSettings.setCurrentScene());

        audioSettings.start(window);
        audioSet = new Button("Audio Settings");
        audioSet.setOnAction(e -> audioSettings.setCurrentScene());

        // Graphics Options layout:
        VBox graphicsOptionsLayout = new VBox(40);
        graphicsOptionsLayout.getChildren().addAll(graphicsOpts, accessSet, audioSet, btm1);
        graphicsOptionsLayout.setAlignment(Pos.CENTER);
        settings = new Scene(graphicsOptionsLayout, 300, 500);
        settings.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        setCurrentScene();

    }

    @Override
    public void setCurrentScene() {
        window.setScene(settings);
    }
}

