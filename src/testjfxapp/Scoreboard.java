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

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Scoreboard extends ReversableMenu {

    Stage window;
    Scene scoreboard;
    Button btm1;

    MainMenu mainMenu;
    Tetris tetris;

    public Scoreboard(MainMenu m, Tetris t){
        mainMenu = m;
        tetris = t;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        btm1 = new Button("Back to Menu");
        btm1.setOnAction(e -> mainMenu.showMenu());
        Label tetrisScore = new Label("Your last score in Tetris was "+this.round(this.calculateTetrisScore(), 2));
        Label tetsawScore = new Label("Your last score in Tetsaw was "+this.round(this.calculateTetrisScore(), 2)); // Change to tetsaw when implemented
        
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

    //Round the number calculated above
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
//    ***Add when tetsaw class exists***
//    public double calculateTetsawScore(){
//        double finalScore;
//        finalScore = (tetsaw.getScore);
//        return finalScore;
//    }


}



