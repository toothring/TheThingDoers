public class TetsawScore {

    public static void main(String[] args) {

        // Below variables are to be collected from game actions. Example numbers entered not actual numbers.

        // Tetsaw score variables
        boolean isTetsaw = true; // is the completed game Tetsaw?
        boolean isTetris = true; // is game being played Tetris?
        boolean gameOverTetsaw = true; // is game over
        int puzzleCompleteScore = 3; // 1 = easy 1000 points, 2 = medium 1500 points, 3 = hard 2000 points.
        int correctPiecePlacement = 23; // Amount of pieces that have been placed in the correct position.
        int livesLeft = 3; // Amount of lives left at end of game
        int timeLeft = 20; // Amount if time left in countdown after game ends

        // Tetris score variables
        boolean rowDestruction = true; // One tick has passed and score has happened.
        int destructionAmount = 3; // 1 = 130, 2 = 320 points, 3 = 524 points, 4 = 828 points, 5 = 1204 points



        if(isTetsaw) {
            int finalScore = calculateTetsawScore(gameOverTetsaw, puzzleCompleteScore, correctPiecePlacement, livesLeft, timeLeft);
            System.out.println("Final score = " + finalScore);
        }
        else if(isTetris) {
            int currentScore = calculateTetrisScore(rowDestruction);
        }

    }



    public static int calculateTetsawScore(boolean gameOver, int puzzleCompleteScore, int correctPiecePlacement, int livesLeft, int timeLeft) {

        int levelScore = 0;
        int blockPlacePoints = 0;


        // Converting set level to point values
        if(puzzleCompleteScore == 1) {
            levelScore = 1000;
            blockPlacePoints = 10;
        }
        else if(puzzleCompleteScore == 2) {
            levelScore = 1500;
            blockPlacePoints = 15;
        }
        else if(puzzleCompleteScore == 3) {
            levelScore = 2000;
            blockPlacePoints = 20;
        }

        // Final Score Calculation
        if(gameOver) {
            int finalScore = (levelScore * livesLeft) + (blockPlacePoints * correctPiecePlacement);
            return finalScore;
        }
        return -1;
    }

    public static int calculateTetrisScore(boolean scoreHappen) {

        int rows1 = 130;
        int rows2 = 320;
        int rows3 = 524;
        int rows4 = 828;
        int tFlip = 1204;




        return -1;
    }

}
