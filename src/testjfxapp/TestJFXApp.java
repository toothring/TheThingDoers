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
            root.getChildren().addAll(CANVAS, rtm, igmbutton);
            scene = new Scene(root);
        }
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
                    testRender();
                    delta--;
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

    public void testRender() {
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
        
        //Check collision with the current tile
        boolean intersects = false;
        for (TetrisBlock blocks : block) {
            //Don't check collisions with ourself!
            if (!(currentTile == blocks)) {
                //Do we intersect a block?
                intersects = (currentTile.moveTest(movement).intersects(blocks) || intersects);
            }
        }
        //Does it intersect with anything? If yes, make a new tile, if no, try to move down.
        if (!intersects) {
            //Tell the tile to move down. If it fails to move, it has hit the bottom and we should make a new tile
            if (!currentTile.boundedMove(movement, PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT)) {
                makeTile();
            }
        } else {
            makeTile();
        }
        //Brendan's way of clogging STDOut, removed while I work on stuff and use STDOut for debugging
        //System.out.println("tick, tock");
        
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
        selectedTile = (PLAY_AREA_WIDTH / 2) - 1;
        
        //Make a new block
        TetrisBlock tile = new TetrisBlock(playArea[selectedTile], pattern, rotate);
        
        //Add it to our list of blocks
        block.add(tile);
        //Set it as our active block
        currentTile = tile;
    }
}