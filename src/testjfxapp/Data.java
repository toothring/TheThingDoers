
package testjfxapp;

import orion.number.Vector2I;

/**
 *
 * @author Orion
 */


public  class Data {
    public static final Vector2I[][] patterns = {
        {new Vector2I(0, 0), new Vector2I(1, 0), new Vector2I(0, 1), new Vector2I(1, 1)}, // O
        {new Vector2I(-1, 0), new Vector2I(0, 0), new Vector2I(1, 0), new Vector2I(0, 1)}, // T
        {new Vector2I(-1, 0), new Vector2I(0, 0), new Vector2I(0, 1), new Vector2I(1, 1)}, // Z
        {new Vector2I(-1, 1), new Vector2I(0, 1), new Vector2I(0, 0), new Vector2I(1, 0)}, // S
        {new Vector2I(-1, 0), new Vector2I(0, 0), new Vector2I(1, 0), new Vector2I(2, 0)}, // I
        {new Vector2I(-1, 0), new Vector2I(0, 0), new Vector2I(1, 0), new Vector2I(1, -1)}, // L
        {new Vector2I(-1, -1), new Vector2I(-1, 0), new Vector2I(0, 0), new Vector2I(1, 0)} // J
    };
    public static final Vector2I[] patternDimension = {
        new Vector2I(2, 2), new Vector2I(3, 2), new Vector2I(3, 2), new Vector2I(3, 2), new Vector2I(4, 1), new Vector2I(3, 2), new Vector2I(3, 2)
    };
}
