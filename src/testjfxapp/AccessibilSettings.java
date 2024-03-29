package testjfxapp;
// Work in progress

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccessibilSettings extends ReversableMenu {

    Stage window;
    Scene accessibilSettings;
    Button enterSettings, enterScoreboard, sp, mp;
    Button btm1, btm2, btm3;
    Label mainMenuLabel;

    MainMenu mainMenu;

    public AccessibilSettings(ReversableMenu m, MainMenu menu){
        this.m = m;
        mainMenu = menu;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        btm1 = new Button("Back to Main Menu");
        btm1.setOnAction(e -> mainMenu.showMenu());

        btm2 = new Button("Back");
        btm2.setOnAction(e -> setPreviousScene());

        // Graphics Options layout:
        VBox accessibilSettings = new VBox(40);
        accessibilSettings.getChildren().addAll(btm1, btm2);
        accessibilSettings.setAlignment(Pos.CENTER);
        this.accessibilSettings = new Scene(accessibilSettings, 300, 500);
        this.accessibilSettings.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

    }

    @Override
    public void setCurrentScene() {
        window.setScene(accessibilSettings);
    }
}

