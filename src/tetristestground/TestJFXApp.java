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

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import orion.number.Vector2I;

/**
 *
 * @author Orion
 */
public class TestJFXApp extends Application {

    private static final long serialVersionUID = 1L;

    private static final int PLAY_AREA_WIDTH = 10;
    private static final int PLAY_AREA_HEIGHT = 20;
    private static final int TILE_SIZE = 30;

    private final Vector2I movement = new Vector2I(0, 1);
    private final Random r = new Random();
    private final Vector2I[] playArea = new Vector2I[PLAY_AREA_WIDTH * PLAY_AREA_HEIGHT];
    private final ArrayList<TetrisBlock> block = new ArrayList<>();
    private Boolean running = true;
    private int x = 0;

    private final Group root = new Group();
    private static final Canvas CANVAS = new Canvas(PLAY_AREA_WIDTH * TILE_SIZE, PLAY_AREA_HEIGHT * TILE_SIZE);
    private static final GraphicsContext GRAPHICS = CANVAS.getGraphicsContext2D();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void start(Stage arg0) throws Exception {
        arg0.setTitle("Test Program");
        root.getChildren().add(CANVAS);
        Scene scene = new Scene(root);
        arg0.setScene(scene);
        arg0.show();
        System.out.println("ARGH");
        new Thread(() -> {
            double delta = 0;
            long timer = System.nanoTime();
            final double ticks = 1.0;
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

    @Override
    public void init() {
        for (int i = 0; i < playArea.length; i++) {
            playArea[i] = new Vector2I(i % PLAY_AREA_WIDTH, (int) Math.floor(i / PLAY_AREA_WIDTH));
        }
        /*for (int i = 0; i < playArea.length; i++) {
            System.out.println(Arrays.toString(playArea[i].getPos()));
        }*/
        int selectedTile;
        while (block.size() < 15) {
            int pattern = r.nextInt(Data.patterns.length);
            System.out.println(pattern);
            do {
                selectedTile = r.nextInt(playArea.length);
            } while (playArea[selectedTile].transformExternal(Data.patternDimension[pattern]).getX() > PLAY_AREA_WIDTH || playArea[selectedTile].transformExternal(Data.patternDimension[pattern]).getY() > PLAY_AREA_HEIGHT);
            TetrisBlock tile = new TetrisBlock(playArea[selectedTile].transformExternal(Data.patterns[pattern][0]), playArea[selectedTile].transformExternal(Data.patterns[pattern][1]), playArea[selectedTile].transformExternal(Data.patterns[pattern][2]), playArea[selectedTile].transformExternal(Data.patterns[pattern][3]), pattern);
            boolean intersects = false;
            for (TetrisBlock blocks : block) {
                intersects = (tile.intersects(blocks) || intersects);
            }
            if (!intersects) {
                block.add(tile);
            }
        }
    }

    public void testRender() {
        int scaleMult = TILE_SIZE;
        
        GRAPHICS.setFill(Color.WHITE);
        GRAPHICS.fillRect(0, 0, PLAY_AREA_WIDTH * TILE_SIZE, PLAY_AREA_HEIGHT * TILE_SIZE);
        GRAPHICS.setFill(Color.BLACK);
        for (Vector2I tile : playArea) {
            GRAPHICS.strokeRect(tile.getX() * scaleMult, tile.getY() * scaleMult, scaleMult, scaleMult);
        }
        for (TetrisBlock tile : block) {
            boolean intersects = false;
            for (TetrisBlock blocks : block) {
                if (!(tile == blocks)) {
                    intersects = (tile.moveTest(movement).intersects(blocks) || intersects);
                }
            }
            if (!intersects) {
                if(tile.boundedMove(movement, PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT)){
                }
            }
            System.out.println();
            tile.drawSelf(GRAPHICS, scaleMult);
        }
    }
}
