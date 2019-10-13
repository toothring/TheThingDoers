/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetristestground;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import orion.number.Vector2I;

/**
 *
 * @author Orion
 */
public class TetrisTestGround extends Canvas {

    private static final long serialVersionUID = 1L;

    private static final int PLAY_AREA_WIDTH = 10;
    private static final int PLAY_AREA_HEIGHT = 20;
    private static final int TILE_SIZE = 30;

    private final Vector2I movement = new Vector2I(0, 1);
    private final Random r = new Random();
    private final Vector2I[] playArea = new Vector2I[PLAY_AREA_WIDTH * PLAY_AREA_HEIGHT];
    private final ArrayList<TetrisBlock> block = new ArrayList<>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TetrisTestGround game = new TetrisTestGround();
        System.out.println((1.1 + 1.1));
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(PLAY_AREA_WIDTH * TILE_SIZE + (int) (TILE_SIZE * 0.5), PLAY_AREA_HEIGHT * TILE_SIZE + TILE_SIZE));
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        double delta = 0;
        long timer = System.nanoTime();
        final double ticks = 1.0;
        double ns = 1000000000 / ticks;
        while (true) {
            long now = System.nanoTime();
            delta += (now - timer) / ns;
            timer = now;
            while (delta > 1) {
                game.testRender();
                delta--;
            }

        }
    }

    public TetrisTestGround() {
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
        Graphics g = this.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, PLAY_AREA_WIDTH * TILE_SIZE, PLAY_AREA_HEIGHT * TILE_SIZE);
        g.setColor(Color.black);
        for (Vector2I tile : playArea) {
            g.drawRect(tile.getX() * scaleMult, tile.getY() * scaleMult, scaleMult, scaleMult);
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
            tile.drawSelf(g, scaleMult);
        }
    }

}
