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
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import orion.number.Vector2I;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Orion
 */
public class TestJFXApp extends Application {

    private static final long serialVersionUID = 1L;

    private static int PLAY_AREA_WIDTH;
    private static int PLAY_AREA_HEIGHT;
    private static int TILE_SIZE;

    private final Vector2I movement = new Vector2I(0, 1);
    private final Vector2I moveLeft = new Vector2I(-1, 0);
    private final Vector2I moveRight = new Vector2I(1, 0);
    private final Random r = new Random();
    private static Vector2I[] playArea;
    private static ArrayList<TetrisBlock> block;
    private static TetrisBlock currentTile;
    private Boolean running = true;
    private int x = 0;

    private static Group root;
    private static Canvas CANVAS;
    private static GraphicsContext GRAPHICS;

    private MainMenu menu;
    private InGameMenu igm;
    private Scene scene;

    private int completedLines;

    public TestJFXApp(int Width, int Height, int Scale, MainMenu menu) {
        PLAY_AREA_WIDTH = Width;
        PLAY_AREA_HEIGHT = Height;
        TILE_SIZE = Scale;
        root = new Group();
        CANVAS = new Canvas(PLAY_AREA_WIDTH * TILE_SIZE, PLAY_AREA_HEIGHT * TILE_SIZE);
        GRAPHICS = CANVAS.getGraphicsContext2D();
        System.out.println(GRAPHICS.toString());
        block = new ArrayList<>();
        playArea = new Vector2I[PLAY_AREA_WIDTH * PLAY_AREA_HEIGHT];
        this.menu = menu;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
// Easier to run this class via the main menu than directly.
        Application.launch(args);

    }

    @Override
    public void start(Stage arg0) throws Exception {
        arg0.setTitle("Tetris");
        running = true;
        if (root.getChildren().size() < 1) {
            Button rtm = new Button();
            rtm.setText("Main Menu");
            rtm.setOnAction(e -> {
                running = false;
                menu.showMenu();
            });
//            Button igmbutton = new Button("Open Menu");
//            igmbutton.setOnAction(e -> {
//                try {
//                    running = false;
//                    igm.start(menu.window);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            });
            root.getChildren().addAll(CANVAS, rtm);
            scene = new Scene(root);
        }

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A: userMovement("left");
                    break;
                case D: userMovement("right");
                    break;
                case S: tickDown();
                    break;
                case W: tickDown();
                    break;
                case O: currentTile.rotateBlock(-1);
                    break;
                case P: currentTile.rotateBlock(1);
                    break;
                case ESCAPE: checkRun();
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
            final double ticks = 2.0; // The larger this number is, the faster the game
            double ns = 1000000000 / ticks;
            while (running) {
                long now = System.nanoTime();
                delta += (now - timer) / ns;
                timer = now;
                while (delta > 1) {
                    tickDown();

                    delta--;
                }

            }
        }).start();
    }

    public void checkRun(){
        if (running == true)
            pause();
        if (running == false)
            resume();
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
            if (direction == "left")
                currentTile.boundedMove(moveLeft, PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT);
            if (direction == "right")
                currentTile.boundedMove(moveRight, PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT);
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
            if (!currentTile.boundedMove(movement, PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT)) {
                makeTile();
            }
        } else {
            findCompletedLines();
            makeTile();
        }
        //Brendan's way of clogging STDOut, removed while I work on stuff and use STDOut for debugging
        //System.out.println("tick, tock");

        drawAllTiles(scaleMult);
    }

    public void findCompletedLines() {
//        int lineCount=0;
//        for(int i=1; i<=20; i++){
//            for(int j=0; j<10; j++) {
//                if (currentTile == blocks) {
//                    lineCount++;
//                }
//            }
//            if (lineCount == 10) {
//                System.out.println("Completed line found!");
//            }
//            lineCount=0;
//        }
    }

    public boolean checkForCollision(String direction) {
        //Check collision with the current tile
        boolean intersects = false;
        for (TetrisBlock blocks : block) {
            //Don't check collisions with ourself!
            if (!(currentTile == blocks)) {
                //Do we intersect a block?
                if (direction == "down")
                    intersects = (currentTile.moveTest(movement).intersects(blocks) || intersects);
                if (direction == "left")
                    intersects = (currentTile.moveTest(moveLeft).intersects(blocks) || intersects);
                if (direction == "right")
                    intersects = (currentTile.moveTest(moveRight).intersects(blocks) || intersects);
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
        for (TetrisBlock tile : block) {
            tile.drawSelf(GRAPHICS, scaleMult);
        }
    }

    //Make a new tile
    private void makeTile() {
        //Semi-obsolete formatting from tech demo
        int selectedTile;
        
        //Get an existing pattern
        int pattern = r.nextInt(Data.patterns.length);
        //Rotate it to one of four possible positions
        int rotate = r.nextInt(3);
        //Debug statement
        //System.out.println(rotate);
        
        //Get the position our block will start at
        // pattern 0=o, 1=T, 2=s, 3=l, 4=L
        if (pattern >= 1 && pattern < 5)
            selectedTile = (PLAY_AREA_WIDTH / 2) - 2;
        else
            selectedTile = (PLAY_AREA_WIDTH / 2) - 1;
        
        //Make a new block
        TetrisBlock tile = new TetrisBlock(playArea[selectedTile], pattern, 0);
        
        //Add it to our list of blocks
        block.add(tile);
        //Set it as our active block
        currentTile = tile;
    }
}
