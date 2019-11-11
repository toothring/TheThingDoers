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
import orion.number.Vector2I;
import static testjfxapp.TestJFXApp.PLAY_AREA_WIDTH;

/**
 *
 * @author Orion
 */
public class Tetsaw extends TestJFXApp {
    
    public Tetsaw(int Width, int Height, int Scale, MainMenu menu) {
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
    
    
    
    
    @Override
    void makeTile() {
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
