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

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import orion.general.graphics.SpriteSheet;
import orion.general.graphics.Texture;
import orion.number.Vector2I;

/**
 *
 * @author Orion
 */
public class TetsawBlock extends TetrisBlock {

    public final Texture[] tileImages;
    private final Vector2I finalPos;
    private final SpriteSheet ss;
    private final TetsawBlockData intendedResult;

    public TetsawBlock(Vector2I tile, TetsawBlockData intendedResult, int rotate, SpriteSheet ss) {
        super(tile, intendedResult.pattern, rotate);
        this.ss = ss;
        tileImages = new Texture[4];
        for (int i = 0; i < area.length; i++) {
            tileImages[i] = new Texture(this.ss, intendedResult.finishPos.transformExternal(Data.patterns[intendedResult.pattern][i]).getX(), intendedResult.finishPos.transformExternal(Data.patterns[intendedResult.pattern][i]).getY());
        }
        colour = Color.BLACK;
        System.out.println("Tetsaw BLAWK");
        this.intendedResult = intendedResult;
        finalPos = intendedResult.finishPos;
    }

    @Override
    public void drawSelf(GraphicsContext g, int scaleMultiplier) {
        //Set the current fill colour to our special colour
        g.setFill(Color.BLACK);
        //Draw each tile
        for (int i = 0; i < area.length; i++) {
            g.drawImage(tileImages[i].getTexture(), area[i].getX() * scaleMultiplier, area[i].getY() * scaleMultiplier, scaleMultiplier, scaleMultiplier);
        }
        for (int i = 0; i < area.length; i++) {
            
            g.drawImage(tileImages[i].getTexture(), (Data.patterns[intendedResult.pattern][i].getX() + finalPos.getX()) * scaleMultiplier, (Data.patterns[intendedResult.pattern][i].getY() + finalPos.getY()) * scaleMultiplier, scaleMultiplier, scaleMultiplier);
        }
    }

    @Override
    public String reportType() {
        return "Tetsaw";
    }
    @Override
    public boolean checkPositionFinality(){
        return finalPos.equals(realPos);
    }
    
    
}
