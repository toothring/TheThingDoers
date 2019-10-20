/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjfxapp;


import java.awt.Graphics;
import java.util.Arrays;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import orion.number.Vector2I;

/**
 *
 * @author Orion
 */
public class TetrisBlock {

    private final Vector2I[] area;
    public final Color colour = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    private int pattern;

    public TetrisBlock(Vector2I tile1, Vector2I tile2, Vector2I tile3, Vector2I tile4, int pattern) {
        area = new Vector2I[4];
        area[0] = tile1;
        area[1] = tile2;
        area[2] = tile3;
        area[3] = tile4;
        this.pattern = pattern;
        for (Vector2I tile : area) {
            System.out.println(Arrays.toString(tile.getPos()));
        }
    }

    public TetrisBlock(Vector2I[] area, int pattern) {
        this.area = area;
    }

    public void drawSelf(GraphicsContext g, int scaleMultiplier) {
        g.setFill(colour);
        for (Vector2I i : area) {
            g.fillRect(i.getX() * scaleMultiplier, i.getY() * scaleMultiplier, scaleMultiplier, scaleMultiplier);
        }
    }

    public boolean intersects(TetrisBlock other) {
        Vector2I[] otherArea = other.getArea();
        boolean result = false;
        for (Vector2I otherTile : otherArea) {
            for (Vector2I tile : area) {
                result = (tile.equals(otherTile) || result);
            }
        }
        return result;
    }

    public Vector2I[] getArea() {
        return area;
    }

    public TetrisBlock moveTest(Vector2I delta) {
        Vector2I[] holder = new Vector2I[area.length];
        for (int i = 0; i < area.length; i++) {
            holder[i] = area[i].transformExternal(delta);
        }

        return new TetrisBlock(holder, pattern);
    }

    public void move(Vector2I delta) {
        for (int i = 0; i < area.length; i++) {
            area[i].transform(delta);
        }
    }

    public boolean boundedMove(Vector2I delta, int xBounds, int yBounds) {
        Vector2I[] holder = new Vector2I[area.length];
        boolean invalid = false;
        for (int i = 0; i < area.length; i++) {
            holder[i] = area[i].transformExternal(delta);
            invalid = (holder[i].getX() >= xBounds) || (holder[i].getY() >= yBounds) || invalid;
        }
        if (!invalid) {
            for (int i = 0; i < area.length; i++) {
                area[i] = holder[i];
            }
        }
        return !invalid;
    }
}
