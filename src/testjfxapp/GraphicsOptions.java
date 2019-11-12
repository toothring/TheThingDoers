package testjfxapp;
// Work in progress

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class GraphicsOptions extends ReversableMenu {

    Stage window;
    Scene graphicsOptions;
    Button enterSettings, enterScoreboard, sp, mp;
    Button btm1, btm2, btm3;
    Label mainMenuLabel;

    //Create an object of the MainMenu and TestJFXApp class so we can use it
    MainMenu mainMenu;

    public GraphicsOptions(ReversableMenu m, MainMenu menu) {
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

        btm3 = new Button("Back to Menu");
        btm3.setOnAction(e -> window.setScene(graphicsOptions));

        // Graphics Options layout:
        VBox graphicsOptionsLayout = new VBox(40);
        graphicsOptionsLayout.getChildren().addAll(btm1, btm2);
        graphicsOptionsLayout.setAlignment(Pos.CENTER);
        graphicsOptions = new Scene(graphicsOptionsLayout, 300, 500);
        graphicsOptions.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());
    }

    public void fullScreenMode(){
        // This will determine the screen size (width and height) which you can then assign to a scene.
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double fullwidth = screenSize.getWidth();
        double fullheight = screenSize.getHeight();
    }

    @Override
    public void setCurrentScene() {
        window.setScene(graphicsOptions);
    }
}

