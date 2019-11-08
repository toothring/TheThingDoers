package testjfxapp;
// Work in progress

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccesSettings {

    Stage window;
    Scene accesSettings;
    Button enterSettings, enterScoreboard, sp, mp;
    Button btm1, btm2, btm3;
    Label mainMenuLabel;

    MainMenu mainMenu;

    public AccesSettings(MainMenu m){
        mainMenu = m;
    }

   // @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        btm1 = new Button("Back to Menu");
        btm1.setOnAction(e -> window.setScene(mainMenu.mainMenu));

//        btm2 = new Button("Back to Menu");
//        btm2.setOnAction(e -> window.setScene(settings));
//
//        btm3 = new Button("Back to Menu");
//        btm3.setOnAction(e -> window.setScene(settings));

        // Graphics Options layout:
        VBox graphicsOptionsLayout = new VBox(40);
        graphicsOptionsLayout.getChildren().addAll(btm1);
        graphicsOptionsLayout.setAlignment(Pos.CENTER);
        accesSettings = new Scene(graphicsOptionsLayout, 300, 500);
        accesSettings.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        window.setScene(accesSettings);

    }
}

