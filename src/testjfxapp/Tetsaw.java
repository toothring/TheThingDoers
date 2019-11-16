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

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import orion.general.graphics.SpriteSheet;
import orion.number.Vector2I;

/**
 *
 * @author Orion
 */
public class Tetsaw extends Tetris {

    private SpriteSheet ss;
    private boolean[] selectedChunks;

    public Tetsaw(int Width, int Height, int Scale, MainMenu menu) {
        super(Width, Height, Scale, menu);
        System.out.println("Oh lawd we doin dis");
        selectedChunks = new boolean[playArea.length];
    }

    @Override
    protected void makeTile() {
        if (currentBlock == null || currentBlock.checkPositionFinality()) {
            //Semi-obsolete formatting from tech demo
            int selectedBlock;

            //Get an existing pattern
            int pattern = r.nextInt(Data.patterns.length);
            //Rotate it to one of four possible positions
            int rotate = r.nextInt(3);
            //Debug statement
            //System.out.println(rotate);

            //Get the position our block will start at
            selectedBlock = (PLAY_AREA_WIDTH / 2) - 1;
            int targetBlock;
            do {
                targetBlock = r.nextInt(PLAY_AREA_WIDTH * (PLAY_AREA_HEIGHT - 3));
            } while (selectedChunks[targetBlock] == true);
            selectedChunks[targetBlock] = true;
            System.out.println("We doin dis bois");
            //Make a new block
            TetsawBlock block = new TetsawBlock(playArea[selectedBlock], playArea[targetBlock], pattern, rotate, ss);

            //Add it to our list of blocks
            blocks.add(block);
            //Set it as our active block
            currentBlock = block;
        } else {
            currentBlock.resetLocation(playArea[(PLAY_AREA_WIDTH / 2) - 1]);
        }
        
    }

    @Override
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
        scorePerTick++; // Increase the score with each tick
        //System.out.println(scorePerTick + " " + scorePerLandedBlock); // Print in console so BB can see it working
        //System.out.println("Meep");
        drawAllTiles(scaleMult);
    }

    @Override
    public void init() {
        initialiseImageHandler();
        super.init();

    }

    private void initialiseImageHandler() {
        ss = new SpriteSheet("/TetsawImage.jpg", TILE_SIZE);
    }
}
