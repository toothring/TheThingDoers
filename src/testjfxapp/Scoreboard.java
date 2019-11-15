package testjfxapp;
/* Work in progress:
- Add scoreboard scene
- Add scores to scoreboard
- Save the scores and display them next time (File IO)?
**Parked as not MVF**
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Scoreboard extends ReversableMenu {

    Stage window;
    Scene scoreboard;
    Button btm1;

    MainMenu mainMenu;
    Tetris tetris = new Tetris(10, 20, 30, mainMenu);
    // Add same for tetsaw class

    public Scoreboard(MainMenu m){
        mainMenu = m;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        btm1 = new Button("Back to Menu");
        btm1.setOnAction(e -> mainMenu.showMenu());
        Label tetrisScore = new Label("Your last score in Tetris was "+this.calculateTetrisScore());
        Label tetsawScore = new Label("Your last score in Tetsaw was "+this.calculateTetrisScore()); // Change to tetsaw when implemented
        
        VBox graphicsOptionsLayout = new VBox(40);
        graphicsOptionsLayout.getChildren().addAll(tetrisScore, btm1);
        graphicsOptionsLayout.setAlignment(Pos.CENTER);
        scoreboard = new Scene(graphicsOptionsLayout, 300, 500);
        //scoreboard.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString()); *Using different theme for scoreboard

        setCurrentScene();

    }

    @Override
    public void setCurrentScene() {
        window.setScene(scoreboard);
    }

    public double calculateTetrisScore(){ // Calculate final score using these (just multiplying at the moment)
        double finalScore;
        finalScore = (tetris.getTickScore()*tetris.getBlockScore());
        return finalScore;
    }
//    ***Add when tetsaw class exists***
//    public double calculateTetsawScore(){
//        double finalScore;
//        finalScore = (tetsaw.getScore);
//        return finalScore;
//    }


}



