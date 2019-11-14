package testjfxapp;
/* Work in progress:
- Add scoreboard scene
- Add scores to scoreboard

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

    public Scoreboard(MainMenu m){
        mainMenu = m;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        btm1 = new Button("Back to Menu");
        btm1.setOnAction(e -> mainMenu.showMenu());
        Label tetrisScore = new Label("Score is "+this.calculateScore());
        
        // Scoreboard layout
        VBox graphicsOptionsLayout = new VBox(40);
        graphicsOptionsLayout.getChildren().addAll(tetrisScore, btm1);
        graphicsOptionsLayout.setAlignment(Pos.CENTER);
        scoreboard = new Scene(graphicsOptionsLayout, 300, 500);
        scoreboard.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        setCurrentScene();

    }

    @Override
    public void setCurrentScene() {
        window.setScene(scoreboard);
    }

    // *Don't need these because I can just call the methods in calculateScore()*
//    public int setTickScore(){ // Collect tick score from Tetris class
//        int tickScore = tetris.getTickScore();
//        return tickScore;
//    }
//
//    public int setBlockScore(){ // Collect block score from Tetris class
//        int blockScore = tetris.getTickScore();
//        return blockScore;
//    }

    public double calculateScore(){ // Calculate final score using these (just multiplying at the moment)
        double finalScore;
        finalScore = (tetris.getTickScore()*tetris.getBlockScore());
        return finalScore;
    }
}



