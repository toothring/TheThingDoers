/*
 * Base code was modified from:
 * Almas Baimagambetov, JavaFX Game: Space Invaders (for Beginners),
 * viewed 23 October 2019, <https://www.youtube.com/watch?v=FVo1fm52hz0&t=>
 */

package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BlockMovementTest extends Application {

	private Pane root = new Pane();
	//double t is the timer (starts at 0)
	private double t = 0;
	//you are the block
	private Block player = new Block(200, 0, 40, 40, "player", Color.BLACK); 
	
	private Parent createContent() {
		//board size. 10 accross and 10 down based on 40px block
		root.setPrefSize(440, 840);
		root.getChildren().add(player);
		//creates the timer
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				tickDown();
			}
		};
		timer.start();
		return root;
	}
	
	private void tickDown() {
		// every tick == 0.016s
		t += 0.016;
		// when tick reaches 1 second, the block moves down my 40px
		if (t > 1) {
			player.moveDown();
			// tick is reset ready for next round
			t = 0;
		}
	}
	
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(createContent());
		
		//This was taken from a YouTube video. I don't know anything about Lambda
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
				case LEFT:
					player.moveLeft();
					break;
				case RIGHT:
					player.moveRight();
					break;
			}
		});
		stage.setScene(scene);
		stage.show();
	}
	
	private static class Block extends Rectangle {
		// how the block will move
		Block(int x, int y, int w, int h, String type, Color color) {
			super(w, h, color);
			setTranslateX(x);
			setTranslateY(y);
		}
		// key events are each a 40px movement
		void moveLeft() {
			setTranslateX(getTranslateX() - 40);
		}
		void moveRight() {
			setTranslateX(getTranslateX() + 40);
		}
		void moveDown() {
			setTranslateY(getTranslateY() + 40);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
