package testjfxapp;
// Work in progress

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class Settings {

    Stage window;
    Scene settings;
    Button enterSettings, enterScoreboard, closeProgram, sp, mp;
    Button btm1, btm2, btm3;
    Label mainMenuLabel;

    //Create an object of the MainMenu and TestJFXApp class so we can use it
    MainMenu mainMenu = new MainMenu();
    TestJFXApp tetrisGame = new TestJFXApp(10,20,30, mainMenu);

    public static void main(String[] args) {
        Application.launch(args);
    }

   // @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        closeProgram = new Button("Quit");
        closeProgram.setOnAction(e -> quitProgram());


        btm1 = new Button("Back to Menu");
        btm1.setOnAction(e -> window.setScene(settings));

        btm2 = new Button("Back to Menu");
        btm2.setOnAction(e -> window.setScene(settings));

        btm3 = new Button("Back to Menu");
        btm3.setOnAction(e -> window.setScene(settings));

        // Graphics Options layout:
        VBox graphicsOptionsLayout = new VBox(40);
        graphicsOptionsLayout.getChildren().addAll(closeProgram);
        graphicsOptionsLayout.setAlignment(Pos.CENTER);
        settings = new Scene(graphicsOptionsLayout, 300, 500);
        settings.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        window.setScene(settings);
        window.setTitle("Graphics Options");
        window.setMinWidth(300);
        window.setMinHeight(500);
        window.getIcons().add(new Image("/icon.png"));
        window.setOnCloseRequest(e -> {
            e.consume();
            quitProgram();
        });
        window.show();

    }

    public void quitProgram() {
        Boolean answer = ConfirmBox.display("Are you sure you want to quit?", "That was fun. Come back soon, yeah?");
        if(answer) {
            tetrisGame.stop();
            window.close();

        }
    }

    public void showMenu() {
        window.setScene(settings);
    }

    public void fullScreenMode(){
        // This will determine the screen size (width and height) which you can then assign to a scene.
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double fullwidth = screenSize.getWidth();
        double fullheight = screenSize.getHeight();
    }
}

