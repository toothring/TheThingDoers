public class TetsawScore {

    public static void main(String[] args) {
        boolean gameOver = true; // is game over
        int puzzleCompleteScore = 3; // 1 = easy 1000 points, 2 = medium 1500 points, 3 = hard 2000 points.
        int correctPiecePlacement = 23; // Amount of pieces that have been placed in the correct position.
        int livesLeft = 3; // Amount of lives left at end of game
        int timeLeft = 20; // Amount if time left in countdown after game ends

        int finalScore = calculateScore(gameOver, puzzleCompleteScore, correctPiecePlacement, livesLeft, timeLeft);
        System.out.println("Final score = " + finalScore);
    }

    public static int calculateScore(boolean gameOver, int puzzleCompleteScore, int correctPiecePlacement, int livesLeft, int timeLeft) {

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
            System.out.println(finalScore);
        }




        return -1;
    }
}
