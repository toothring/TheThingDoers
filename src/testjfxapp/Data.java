
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
        {new Vector2I(0, -1), new Vector2I(0, 0), new Vector2I(0, 1), new Vector2I(1, -1)}, // L
        {new Vector2I(-1, -1), new Vector2I(0, 1), new Vector2I(0, 0), new Vector2I(0, -1)} // J
    };
    public static final Vector2I[] patternDimension = {
        new Vector2I(2, 2), new Vector2I(3, 2), new Vector2I(3, 2), new Vector2I(3, 2), new Vector2I(4, 1), new Vector2I(3, 2), new Vector2I(3, 2)
    };

    
    private static final TetsawBlockData[] easyModeLevel = {
        new TetsawBlockData(1, 23, 3, 5),
        new TetsawBlockData(4, 23, 0, 4),
        new TetsawBlockData(1, 21, 0, 2),
        new TetsawBlockData(3, 21, 1, 1),
        new TetsawBlockData(4, 21, 0, 0)
    };
    public static final TetsawLevelData easyMode = new TetsawLevelData(easyModeLevel, "easy");
    
    private static final TetsawBlockData[] normalModeLevel = {
        new TetsawBlockData(0, 0, 0, 0)
    };
    public static final TetsawLevelData normalMode = new TetsawLevelData(normalModeLevel, "main");
    
    private static final TetsawBlockData[] hardModeLevel = {
        new TetsawBlockData(0, 0, 0, 0)
    };
    public static final TetsawLevelData hardMode = new TetsawLevelData(hardModeLevel, "hard");
}
