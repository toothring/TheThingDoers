import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;





public class Game{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

		// private Shapes[] shape = Shapes("shapeT", "shapeI", "ShapeL", "ShapeO", "ShapeS");
	
		public Game() {
			
	
			
		}
		
		public void StartGame(Stage primarystage) {
			
			Rectangle rect = new Rectangle(50,50);
			
			StackPane root = new StackPane(rect);
			
		}
				
		public void MovePiece() {
			
			int newYPos = 0; //Set to current Y position
			int newXPos = 0; //Set to current X position
			
			
			
			EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
			    @Override
			    public void handle(KeyEvent event) {
			        if(event.getCode() == KeyCode.RIGHT){
			            newXPos = newXPos + 10;
			        } 
			        else if(event.getCode() == KeyCode.LEFT) {
			        	newXPos = newXPos - 10;
			        }
			        else if(event.getCode() == KeyCode.SPACE) {
			        	//code to drop piece
			        }
			        else if(event.getCode() == KeyCode.DOWN) {
			        	newYPos = newYPos + 10; 
			        }
			        event.consume();
			    }
		}

		}
		
		public void TurnPiece() {
			
		}
		
		public void SwapPiece() {
			
		}
		
		public void DropPiece() {
			
		}
		
		public void PlayMusic() {
			
		}
		
		public void PlaySound() {
			
		}
		
		public void PauseGame() {
			
		}
		
		public void RestartGame() {
			
		}
		
		public void ResumeGame() {
			
		}
		
		public void SaveGame() {
			
		}
		
		public void LoadGame() {
			
		}
	}
	

