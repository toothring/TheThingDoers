/*
 * Copyright (C) 2019 Orion
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package testjfxapp;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import orion.number.Vector2I;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Orion
 */
public class Tetris extends Application {

    private static final long serialVersionUID = 1L;

    private static int PLAY_AREA_WIDTH;
    private static int PLAY_AREA_HEIGHT;
    private static int TILE_SIZE;

    private final Vector2I movement = new Vector2I(0, 1);
    private final Vector2I moveLeft = new Vector2I(-1, 0);
    private final Vector2I moveRight = new Vector2I(1, 0);
    private int currentRotation;
    private int newBlock;

    private final Random r = new Random();
    private static Vector2I[] playArea;
    private static ArrayList<TetrisBlock> blocks;
    private static TetrisBlock currentBlock;
    private Boolean running = true;
    private int x = 0;
    private static int scorePerTick = 0; // To hold the score per line dropped.
    private static int scorePerLandedBlock = 0; // To hold the score per landed block

    private static Group root;
    private static Canvas CANVAS;
    private static GraphicsContext GRAPHICS;

    private MainMenu menu;
    private InGameMenu igm;
    private Scene scene;

    private static int[] rowCount = new int[20];

    private double ticks = 2.0; // The larger this number is, the faster the game
    private double ns = 1000000000 / ticks;

    public Tetris(int Width, int Height, int Scale, MainMenu menu) {
        PLAY_AREA_WIDTH = Width;
        PLAY_AREA_HEIGHT = Height;
        TILE_SIZE = Scale;
        root = new Group();
        CANVAS = new Canvas(PLAY_AREA_WIDTH * TILE_SIZE, PLAY_AREA_HEIGHT * TILE_SIZE);
        GRAPHICS = CANVAS.getGraphicsContext2D();
        System.out.println(GRAPHICS.toString());
        blocks = new ArrayList<>();
        playArea = new Vector2I[PLAY_AREA_WIDTH * PLAY_AREA_HEIGHT];
        this.menu = menu;
        igm = new InGameMenu(menu, this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void start(Stage arg0) throws Exception {
        arg0.setTitle("Tetris");
        running = true;
        if (root.getChildren().size() < 1) {
            root.getChildren().addAll(CANVAS);
            scene = new Scene(root);
        }

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    userMovement("left");
                    break;
                case D:
                    userMovement("right");
                    break;
                case S:
                    tickDown();
                    break;
                case W:
                    boolean touchdown = false;
                    int maxFall = 0;
                    touchdown = tickDown2();
                    while (touchdown == false && maxFall != 17) {
                        touchdown = tickDown2();
                        maxFall++;
                    }
                    break;
                case O: currentRotation--;
                    //0 = O, 1 = T, 2 = S, 3 = I, 4 = J
                    if (currentRotation == -4)
                        currentRotation = 0;
                    currentBlock.rotateBlock(-1);
                    if (newBlock == 0){
                        if (currentRotation == -2 || currentRotation == 2)
                            userMovement("left");
                        if (currentRotation == 0)
                            userMovement("right");
                    }
                    break;
                case P: currentRotation++;
                    if (currentRotation == 4)
                        currentRotation = 0;
                    currentBlock.rotateBlock(1);
                    // O block
                    if (newBlock == 0){
                        if (currentRotation == -1 || currentRotation == 3)
                            userMovement("left");
                        if (currentRotation == 0)
                            userMovement("right");
                    }
                    // T block
                    if (newBlock == 1) {

                    }
                    // S block
                    if (newBlock == 2) {

                    }
                    // I block
                    if (newBlock == 3) {
                        if (currentRotation == 0 || currentRotation == 4) {
                            userMovement("left");
                        }
                        if (currentRotation == 1) {
                            userMovement("right");
                        }
                        if (currentRotation == 2) {
                            userMovement("right");
                            userMovement("right");
                        }
                        if (currentRotation == 3) {
                            userMovement("left");
                            userMovement("left");
                        }
                    }
                    // J block
                    if (newBlock == 4) {
                        if (currentRotation == 1) {
                            tickDown2();
                            userMovement("right");
                        }
                        if (currentRotation == 2) {
                            userMovement("right");
                        }
                        if (currentRotation == 3) {
                            userMovement("left");
                            userMovement("left");
                        }
                    }
                    break;
                case ESCAPE: try {
                    this.pause();
//                    this.getBlockScore();
                    this.getTickScore();
                    igm.start(menu.window);
                } catch (Exception ex) {
                    ex.printStackTrace();
                };
                    break;
            }
        });

        arg0.setScene(scene);
        arg0.show();
        System.out.println("ARGH");
        //This is the magic thread that runs the game
        new Thread(() -> {
            double delta = 0;
            long timer = System.nanoTime();
            while (running) {
                long now = System.nanoTime();
                delta += (now - timer) / ns;
                timer = now;
                while (delta > 1) {
                    tickDown();
                    delta--;
                    if (ticks < 10) { // 10 ticks is pretty fast
                        ticks = ticks + 0.01; // This will do 1000 ticks
                        ns = 1000000000 / ticks;
                    }
                }

            }
        }).start();
    }

    @Override
    public void stop() {
        running = false;
        System.exit(0);
    }

    public void pause() {
        running = false;
    }

    public void resume() {
        running = true;
    }

    public void returnToMenu() {
        running = false;
        menu.showMenu();
    }

    @Override
    public void init() {
        //Set up the play area
        for (int i = 0; i < playArea.length; i++) {
            playArea[i] = new Vector2I(i % PLAY_AREA_WIDTH, (int) Math.floor(i / PLAY_AREA_WIDTH));
        }
        /*for (int i = 0; i < playArea.length; i++) {
            System.out.println(Arrays.toString(playArea[i].getPos()));
        }*/
        //Spawn our first tile
        makeTile();

    }

    public void userMovement(String direction) {
        int scaleMult = screenSetup();
        boolean intersects = checkForCollision(direction);

        if (!intersects) {
            if ("left".equals(direction)) {
                currentBlock.boundedMove(moveLeft, PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT);
            }
            if ("right".equals(direction)) {
                currentBlock.boundedMove(moveRight, PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT);
            }
        }

        drawAllTiles(scaleMult);
    }

    public void tickDown() {
        int scaleMult = screenSetup();

        String direction = "down";
        boolean intersects = checkForCollision(direction);

        //Does it intersect with anything? If yes, make a new tile, if no, try to move down.
        if (!intersects) {
            //Tell the tile to move down. If it fails to move, it has hit the bottom and we should make a new tile
            if (!currentBlock.boundedMove(movement, PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT)) {
                //Mike is checking for line removal
                isCompletedRow();
                makeTile(); // The score per landed block is recorded in this method.
            }
        } else {
            //Mike is checking for line removal
            isCompletedRow();
            makeTile();
        }
        scorePerTick++; // Increase the score with each tick
        System.out.println(scorePerTick + " " + scorePerLandedBlock); // Print in console so BB can see it working

        drawAllTiles(scaleMult);
    }

    // To retrieve the cumulative value for score per tick in other classes
    public static int getTickScore(){
        return scorePerTick;
    }

    // To retrieve the cumulative value for score per landed block in other classes
    public static int getBlockScore(){
        return scorePerLandedBlock;
    }

    public boolean tickDown2() {
        int scaleMult = screenSetup();

        String direction = "down";
        boolean intersects = checkForCollision(direction);

        //Does it intersect with anything? If yes, make a new tile, if no, try to move down.
        if (!intersects) {
            //Tell the tile to move down. If it fails to move, it has hit the bottom and we should make a new tile
            if (!currentBlock.boundedMove(movement, PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT)) {
                //Mike is checking for line removal
                isCompletedRow();
                makeTile();
            }
        } else {
            //Mike is checking for line removal
            isCompletedRow();
            makeTile();
        }

        drawAllTiles(scaleMult);
        return intersects;
    }

    public boolean checkForCollision(String direction) {
        //Check collision with the current tile
        boolean intersects = false;
        for (TetrisBlock block : blocks) {
            //Don't check collisions with ourself!
            if (!(currentBlock == block)) {
                //Do we intersect a block?
                if ("down".equals(direction)) {
                    intersects = (currentBlock.moveTest(movement).intersects(block) || intersects);
                }
                if ("left".equals(direction)) {
                    intersects = (currentBlock.moveTest(moveLeft).intersects(block) || intersects);
                }
                if ("right".equals(direction)) {
                    intersects = (currentBlock.moveTest(moveRight).intersects(block) || intersects);
                }
            }
        }
        return intersects;
    }

    public int screenSetup() {
        //THIS IS THE DRAWING CODE
        //And also some game logic
        //Whoops
        //Just in case we decide to split logic and rendering, don't try to push frames to JavaFX more than about 1000 times per second, it gets mad

        //Scale multiplier, just equal to TILE_SIZE
        int scaleMult = TILE_SIZE;
        //Blank the screen
        GRAPHICS.setFill(Color.WHITE);
        GRAPHICS.fillRect(0, 0, PLAY_AREA_WIDTH * TILE_SIZE, PLAY_AREA_HEIGHT * TILE_SIZE);

        //Draw the background grid
        GRAPHICS.setFill(Color.BLACK);
        for (Vector2I tile : playArea) {
            GRAPHICS.strokeRect(tile.getX() * scaleMult, tile.getY() * scaleMult, scaleMult, scaleMult);
        }
        return scaleMult;
    }

    public void drawAllTiles(int scaleMult) {
        //Draw all known tiles
        for (TetrisBlock block : blocks) {
            block.drawSelf(GRAPHICS, scaleMult);
        }
    }

    //Make a new tile
    private void makeTile() {
        // Increase the scorePerBlockLanded (A block must've just landed if a new one is being made)
        scorePerLandedBlock++;

        //Semi-obsolete formatting from tech demo
        int selectedTile;

        //Get an existing pattern
        int pattern = r.nextInt(Data.patterns.length);
        newBlock = pattern;
        //Rotate it to one of four possible positions
        int rotate = r.nextInt(3);
        //Debug statement
        //System.out.println(rotate);

        //Get the position our block will start at
        if (pattern >= 1 && pattern < 5) {
            selectedTile = (PLAY_AREA_WIDTH / 2) - 2;
        } else {
            selectedTile = (PLAY_AREA_WIDTH / 2) - 1;
        }

        //Make a new block
        TetrisBlock block = new TetrisBlock(playArea[selectedTile], pattern, 0);
        currentRotation=0;


        //Add it to our list of blocks
        blocks.add(block);
        //Set it as our active block
        currentBlock = block;
    }

    // Mike trying to find completed rows
    public void isCompletedRow() {
        ArrayList<Integer> rowCheck = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int y = currentBlock.getArea()[i].getY();
            if (!rowCheck.contains(y)) {
                rowCheck.add(y);
                System.out.println(y);
            }
        }
        System.out.println();
        Collections.sort(rowCheck);
        for (var y : rowCheck) {
            System.out.println(y);
            if (checkFilledRow(y)) {
                removeRow(y);
            }
        }
        System.out.println();

    }

    // Mike trying to remove completed rows
    public void removeRow(int row) {
        System.out.println("Row " + row + " completed");
        for (TetrisBlock block : blocks) {
            block.completeTiles(row);
        }
        collectGarbage();
    }

    private boolean checkFilledRow(int row) {
        int rowIndex = row * PLAY_AREA_WIDTH;
        boolean rowFilled = true;
        for (int i = 0; i < PLAY_AREA_WIDTH; i++) {
            boolean hasTile = false;
            for (TetrisBlock block : blocks) {
                hasTile = block.checkContains(playArea[rowIndex + i]) || hasTile;
            }
            rowFilled = hasTile && rowFilled;
        }
        return rowFilled;
    }

    private void collectGarbage() {
        for (int i = 0; i < blocks.size(); i++){
            if (blocks.get(i).readyToDelete){
                blocks.remove(i);
            }
        }
    }
}
