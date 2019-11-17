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
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import orion.general.graphics.SpriteSheet;
import orion.general.graphics.Texture;
import orion.number.Vector2I;
import static testjfxapp.Tetris.GRAPHICS;
import testjfxapp.subsystems.AudioSubsystem;

/**
 *
 * @author Orion
 */
public class Tetsaw extends Tetris {

    private SpriteSheet ss;
    private boolean[] selectedChunks;
    private int currentPositionBlock = 0;
    private Image gameImage = new Image("/test2.png", true);
    private TetsawLevelData level;

    public Tetsaw(int Width, int Height, int Scale, MainMenu menu, TetsawLevelData level) {
        super(Width, Height, Scale, menu);
        System.out.println("Oh lawd we doin dis");
        selectedChunks = new boolean[playArea.length];
        this.level = level;
        AudioSubsystem.playMusic(this.level.track);
    }

    @Override
    protected void makeTile() {
        if(currentPositionBlock >= Data.easyMode.data.size()) {
            gameIsOver();
            return;
        }
        if (currentBlock == null || currentBlock.checkPositionFinality()) {
            //Semi-obsolete formatting from tech demo
            int selectedBlock;

            //Get an existing pattern
            int pattern = r.nextInt(Data.patterns.length);
            //Rotate it to one of four possible positions
            int rotate =0;
            //Debug statement
            //System.out.println(rotate);

            //Get the position our block will start at
            selectedBlock = (PLAY_AREA_WIDTH / 2) - 1;
            //selectedBlock = r.nextInt(playArea.length);
            //TetsawBlockData d = new TetsawBlockData(playArea[selectedBlock], rotate, pattern);
            int targetBlock;
            do {
                targetBlock = r.nextInt(PLAY_AREA_WIDTH * (PLAY_AREA_HEIGHT - 3));
            } while (selectedChunks[targetBlock] == true);
            selectedChunks[targetBlock] = true;
            System.out.println("We doin dis bois");
            //Make a new block
            TetsawBlock block = new TetsawBlock(playArea[selectedBlock], level.getBlock(currentPositionBlock), rotate, ss);
            newBlock = level.getBlock(currentPositionBlock).pattern;
            currentPositionBlock++;

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
        //makeTile();
        scorePerTick++; // Increase the score with each tick
        //System.out.println(scorePerTick + " " + scorePerLandedBlock); // Print in console so BB can see it working
        drawAllTiles(scaleMult);
    }

    @Override
    public void init() {
        initialiseImageHandler();
        super.init();

    }

    private void initialiseImageHandler() {
        ss = new SpriteSheet(gameImage, TILE_SIZE);
    }
    
    @Override
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
        //GRAPHICS.drawImage(gameImage, 0, 0);

        //Draw the background grid
        GRAPHICS.setFill(Color.BLACK);
        GRAPHICS.setGlobalAlpha(0.2);
        for (Vector2I tile : playArea) {
            Texture t = new Texture(ss, tile.getY(), tile.getX());
            GRAPHICS.drawImage(t.getTexture(), tile.getX() * scaleMult, tile.getY() * scaleMult, scaleMult, scaleMult);
        }
        GRAPHICS.setGlobalAlpha(1.0);
        return scaleMult;
    }
    @Override
    protected void tickRateUp(){
    }
}
