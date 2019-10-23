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
	private double t = 0;
	private Block player = new Block(200, 0, 40, 40, "player", Color.GREEN);
	
	private Parent createContent() {
		root.setPrefSize(440, 840);
		root.getChildren().add(player);
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
		t += 0.016;
		if (t > 1) {
			player.moveDown();
			t = 0;
		}
	}
	
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(createContent());
		
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
		boolean dead = false;
		final String type;
		
		Block(int x, int y, int w, int h, String type, Color color) {
			super(w, h, color);
			
			this.type = type;
			setTranslateX(x);
			setTranslateY(y);
		}
		
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
