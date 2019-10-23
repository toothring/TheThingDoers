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
//import javafx.scene.input.KeyEvent;
//import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BlockMovementTest extends Application {

	private Pane root = new Pane();
	//double t is the timer (starts at 0)
	private double t = 0;
	//you are the block
	private Block player = new Block(200, 0, 40, 40, "player", Color.GREEN);
	private static int currentRotation=0;
	private static int rotate=0;
	
	private Parent createContent() {
		//board size. 10 accross and 10 down based on 40px block
		root.setPrefSize(400, 800);
		root.getChildren().add(player);
		//creates the timer
		AnimationTimer timer = new AnimationTimer() {
			
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
                case DOWN:
                    player.manualDown();
                    break;
                case UP:
                    player.dropPiece();
                    break;
				case Z:
					switch (currentRotation) {
						case 0:
							currentRotation=-90;
							break;
						case -90:
							currentRotation=180;
							break;
						case 180:
							currentRotation=90;
							break;
						case 90:
							currentRotation=0;
							break;
					}
					player.setRotate(currentRotation);
				case X:
					switch (currentRotation) {
						case 0:
							currentRotation=90;
							break;
						case 90:
							currentRotation=180;
							break;
						case 180:
							currentRotation=-90;
							break;
						case -90:
							currentRotation=0;
							break;
					}
					player.setRotate(currentRotation);
			}
		});
		
//		scene.setOnKeyPressed.eve(this);
		
		stage.setScene(scene);
		stage.show();
	}
	
//	@Override
//	public void handle(KeyEvent event) {
//		switch (event.getCode()) {
//		case LEFT:
//			player.moveLeft();
//			break;
//		case RIGHT:
//			player.moveRight();
//			break;
//		}
//	}
	
	private static class Block extends Rectangle {
		// how the block will move
		Block(int x, int y, int w, int h, String type, Color color) {
			super(w, h, color);
			setTranslateX(x);
			setTranslateY(y);
		}
		// key events are each a 40px movement
		void moveLeft() {

            if (getTranslateX() > 0) {
                setTranslateX(getTranslateX() - 40);
            }
        }

        void moveRight() {
            if (getTranslateX() < 340) {
                setTranslateX(getTranslateX() + 40);
            }
        }
        void moveDown() {

            if (getTranslateY() < 730) {
                setTranslateY(getTranslateY() + 40);
            }
        }
	    void manualDown() {
            if (getTranslateY() < 730) {
                setTranslateY(getTranslateY() + 80);
            }
        }

        void dropPiece() {
            setTranslateY(760);
        }
	}

	public static void main(String[] args) {
		launch(args);
	}
}