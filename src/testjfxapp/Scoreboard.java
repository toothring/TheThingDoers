package testjfxapp;
// Work in progress

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Scoreboard extends ReversableMenu {

    Stage window;
    Scene scoreboard;
    Button btm1;

    MainMenu mainMenu;

    public Scoreboard(MainMenu m){
        mainMenu = m;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        btm1 = new Button("Back to Menu");
        btm1.setOnAction(e -> mainMenu.showMenu());
        
        // Scoreboard layout
        VBox graphicsOptionsLayout = new VBox(40);
        graphicsOptionsLayout.getChildren().addAll(btm1);
        graphicsOptionsLayout.setAlignment(Pos.CENTER);
        scoreboard = new Scene(graphicsOptionsLayout, 300, 500);
        scoreboard.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        setCurrentScene();

    }

    @Override
    public void setCurrentScene() {
        window.setScene(scoreboard);
    }
}

