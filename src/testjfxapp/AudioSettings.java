package testjfxapp;
// Work in progress

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AudioSettings extends ReversableMenu {

    Stage window;
    Scene audioSettings;
    Button enterSettings, enterScoreboard, sp, mp;
    Button btm1, btm2, btm3;
    Label mainMenuLabel;

    MainMenu mainMenu;

    public AudioSettings(ReversableMenu m, MainMenu menu){
        this.m = m;
        mainMenu = menu;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        btm1 = new Button("Back to Main Menu");
        btm1.setOnAction(e -> mainMenu.showMenu());

        btm2 = new Button("Back to Menu");
        btm2.setOnAction(e -> setPreviousScene());
//
//        btm3 = new Button("Back to Menu");
//        btm3.setOnAction(e -> window.setScene(settings));

        // Graphics Options layout:
        VBox graphicsOptionsLayout = new VBox(40);
        graphicsOptionsLayout.getChildren().addAll(btm1, btm2);
        graphicsOptionsLayout.setAlignment(Pos.CENTER);
        audioSettings = new Scene(graphicsOptionsLayout, 300, 500);
        audioSettings.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        

    }

    @Override
    public void setCurrentScene() {
        window.setScene(audioSettings);
    }
}

