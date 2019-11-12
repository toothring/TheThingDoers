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
import java.util.Scanner;

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
    private final Random r = new Random();
    private static Vector2I[] playArea;
    private static ArrayList<TetrisBlock> blocks;
    private static TetrisBlock currentBlock;
    private Boolean running = true;
    private int x = 0;

    private static Group root;
    private static Canvas CANVAS;
    private static GraphicsContext GRAPHICS;

    private MainMenu menu;
    private InGameMenu igm;
    private Scene scene;

    private double ticks = 2.0; // The larger this number is, the faster the game
    private double ns = 1000000000 / ticks;

    public TestJFXApp(int Width, int Height, int Scale, MainMenu menu) {
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
            Button rtm = new Button();
            rtm.setText("Main Menu");
            rtm.setOnAction(e -> {
                this.returnToMenu();
            });

            //Can possibly replace button with escape key
            Button igmbutton = new Button("Open Menu");
            igmbutton = new Button("Open In-Game Menu");
            igmbutton.setOnAction(e -> {
                try {
                    this.pause();
                    igm.start(menu.window);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            root.getChildren().addAll(CANVAS, igmbutton);
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
                case W: boolean touchdown = false;
                    int maxFall = 0;
                    while (touchdown == false && maxFall != 20) {
                        touchdown = tickDown2();
                        maxFall++;
                    }
                    break;
                case O: currentBlock.rotateBlock(-1);
                    break;
                case P: currentBlock.rotateBlock(1);
                    break;
                case ESCAPE: menu.showMenu();
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

    public void returnToMenu(){
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
            if ("left".equals(direction))
                currentBlock.boundedMove(moveLeft, PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT);
            if ("right".equals(direction))
                currentBlock.boundedMove(moveRight, PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT);
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
                makeTile();
            }
        } else {
            makeTile();
        }
        //Brendan's way of clogging STDOut, removed while I work on stuff and use STDOut for debugging
        //System.out.println("tick, tock");

        drawAllTiles(scaleMult);
    }

    public boolean tickDown2() {
        int scaleMult = screenSetup();

        String direction = "down";
        boolean intersects = checkForCollision(direction);

        //Does it intersect with anything? If yes, make a new tile, if no, try to move down.
        if (!intersects) {
            //Tell the tile to move down. If it fails to move, it has hit the bottom and we should make a new tile
            if (!currentBlock.boundedMove(movement, PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT)) {
                makeTile();
            }
        } else {
            makeTile();
        }
        //Brendan's way of clogging STDOut, removed while I work on stuff and use STDOut for debugging
        //System.out.println("tick, tock");

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
                if ("down".equals(direction))
                    intersects = (currentBlock.moveTest(movement).intersects(block) || intersects);
                if ("left".equals(direction))
                    intersects = (currentBlock.moveTest(moveLeft).intersects(block) || intersects);
                if ("right".equals(direction))
                    intersects = (currentBlock.moveTest(moveRight).intersects(block) || intersects);
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
        //Semi-obsolete formatting from tech demo
        int selectedTile;
        
        //Get an existing pattern
        int pattern = r.nextInt(Data.patterns.length);
        //Rotate it to one of four possible positions
        int rotate = r.nextInt(3);
        //Debug statement
        //System.out.println(rotate);
        
        //Get the position our block will start at
        if (pattern >= 1 && pattern <5)
            selectedTile = (PLAY_AREA_WIDTH / 2) - 2;
        else
            selectedTile = (PLAY_AREA_WIDTH / 2) - 1;
        
        //Make a new block
        TetrisBlock block = new TetrisBlock(playArea[selectedTile], pattern, 0);
        
        //Add it to our list of blocks
        blocks.add(block);
        //Set it as our active block
        currentBlock = block;
    }
}
