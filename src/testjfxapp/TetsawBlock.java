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

    public Texture[] tileImages;
    private final Vector2I finalPos;
    private final SpriteSheet ss;
    private final TetsawBlockData intendedResult;

    public TetsawBlock(Vector2I tile, TetsawBlockData intendedResult, int rotate, SpriteSheet ss) {
        super(tile, intendedResult.pattern, rotate);
        realPos.transform(0, 2);
        generateAreaData();
        this.ss = ss;
        tileImages = new Texture[4];
        colour = Color.BLACK;
        System.out.println("Tetsaw BLAWK");
        this.intendedResult = intendedResult;
        finalPos = intendedResult.finishPos;
        for (int i = 0; i < area.length; i++) {
            Vector2I thisTile = Data.patterns[intendedResult.pattern][i].transformExternal(0, 0);
            thisTile.rotate(intendedResult.rotation);
            thisTile.transform(finalPos);
            tileImages[i] = new Texture(this.ss, thisTile.getY(), thisTile.getX());
        }
    }

    @Override
    public void drawSelf(GraphicsContext g, int scaleMultiplier) {
        //Set the current fill colour to our special colour
        g.setFill(Color.BLACK);
        //Draw each tile
        for (int i = 0; i < area.length; i++) {
            g.strokeRect(area[i].getX() * scaleMultiplier, area[i].getY() * scaleMultiplier, scaleMultiplier, scaleMultiplier);
            g.drawImage(tileImages[i].getTexture(), area[i].getX() * scaleMultiplier, area[i].getY() * scaleMultiplier, scaleMultiplier, scaleMultiplier);
        }
        g.setGlobalAlpha(0.7);
        for (int i = 0; i < area.length; i++) {
            Vector2I thisTile = Data.patterns[intendedResult.pattern][i].transformExternal(0, 0);
            thisTile.rotate(intendedResult.rotation);
            thisTile.transform(finalPos);
            g.drawImage(tileImages[i].getTexture(), (thisTile.getX()) * scaleMultiplier, (thisTile.getY()) * scaleMultiplier, scaleMultiplier, scaleMultiplier);
        }
        g.setGlobalAlpha(1.0);
    }

    @Override
    public String reportType() {
        return "Tetsaw";
    }

    @Override
    public boolean checkPositionFinality() {
        return finalPos.equals(realPos);
    }

    @Override
    protected void generateAreaData() {
        super.generateAreaData();
        if (tileImages != null) {

            for (int i = 0; i < area.length; i++) {
                Vector2I thisTile = Data.patterns[intendedResult.pattern][i].transformExternal(0, 0);
            thisTile.rotate(intendedResult.rotation);
            thisTile.transform(finalPos);
                tileImages[i] = new Texture(this.ss, thisTile.getY(), thisTile.getX());
            }
        }
    }

}
