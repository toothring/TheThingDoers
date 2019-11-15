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

    protected final Vector2I[] area;
    //We're all special snowflakes with unique colours
    public Color colour;
    protected Vector2I[] pattern = new Vector2I[4];
    protected Vector2I realPos;
    protected int rotate;

    public TetrisBlock(Vector2I tile, int pattern, int rotate) {
        //Initialise the area
        area = new Vector2I[4];
        
        //Store our rotation
        this.rotate = rotate;
        
        //Store a copy of our pattern, to ensure that we aren't messing up the master pattern list
        System.arraycopy(Data.patterns[pattern], 0, this.pattern, 0, 4);
        
        //Rotate our pattern
        for (var offset : this.pattern){
            //Debug Statement
            //System.out.println(Arrays.toString(offset.getPos()));
            offset.rotate(rotate);
        }
        //This is the real position
        realPos = tile.transformExternal(0, -2);
        
        generateAreaData();
        
        colour = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        //Debug Statement
        /*for (Vector2I thing : area) {
            System.out.println(Arrays.toString(thing.getPos()));
        }*/
    }

    public void rotateBlock(int direction) {
        for (var offset : this.pattern){
            offset.rotate(direction);
        }
        generateAreaData();
    }

    //For use when cloning blocks, ignore this constructor
    public TetrisBlock(Vector2I[] area, Vector2I[] pattern) {
        this.area = area;
        this.pattern = pattern;
    }

    //Draw ourself
    public void drawSelf(GraphicsContext g, int scaleMultiplier) {
        //Set the current fill colour to our special colour
        g.setFill(colour);
        //Draw each tile
        for (Vector2I i : area) {
            g.fillRect(i.getX() * scaleMultiplier, i.getY() * scaleMultiplier, scaleMultiplier, scaleMultiplier);
        }
    }
    
    //Check if we intersect with another block
    public boolean intersects(TetrisBlock other) {
        //Store the other area
        Vector2I[] otherArea = other.getArea();
        boolean result = false;
        for (Vector2I otherTile : otherArea) {
            for (Vector2I tile : area) {
                //Check each of our tiles with each of the other tiles
                result = (tile.equals(otherTile) || result);
            }
        }
        return result;
    }

    //Show what area we occupy
    public Vector2I[] getArea() {
        return area;
    }

    //Move without actually moving ourself, for comparisons
    public TetrisBlock moveTest(Vector2I delta) {
        Vector2I[] holder = new Vector2I[area.length];
        for (int i = 0; i < area.length; i++) {
            holder[i] = area[i].transformExternal(delta);
        }

        return new TetrisBlock(holder, pattern);
    }

    //Move, with no constraints
    public void move(Vector2I delta) {
        for (int i = 0; i < area.length; i++) {
            area[i].transform(delta);
        }
        realPos.transform(delta);
    }

    
    //Move, but between 0 -> xBounds and 0 -> yBounds
    public boolean boundedMove(Vector2I delta, int xBounds, int yBounds) {
        Vector2I[] holder = new Vector2I[area.length];
        boolean invalid = false;
        for (int i = 0; i < area.length; i++) {
            //Copy a moved tile into a holding variable
            holder[i] = area[i].transformExternal(delta);
            //Check if it is within our bounds
            invalid = (holder[i].getX() >= xBounds) || (holder[i].getX() <= -1) || (holder[i].getY() >= yBounds) || invalid;
        }
        //If we didn't fail the test, move
        if (!invalid) {
            for (int i = 0; i < area.length; i++) {
                area[i] = holder[i];
            }
            realPos.transform(delta);
        }
        //To let others know if we failed the test
        return !invalid;
    }
    
    //Generate our tiles from our pattern and actual position
    private void generateAreaData(){
        area[0] = realPos.transformExternal(this.pattern[0]);
        area[1] = realPos.transformExternal(this.pattern[1]);
        area[2] = realPos.transformExternal(this.pattern[2]);
        area[3] = realPos.transformExternal(this.pattern[3]);
    }
    
    public String reportType(){
        return "Tetris";
    }
}
