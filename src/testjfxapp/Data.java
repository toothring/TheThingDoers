
package testjfxapp;

import orion.number.Vector2I;

/**
 *
 * @author Orion
 */


public  class Data {
    public static final Vector2I[][] patterns = {
        {new Vector2I(0, 0), new Vector2I(1, 0), new Vector2I(0, 1), new Vector2I(1, 1)},
        {new Vector2I(0, 0), new Vector2I(1, 0), new Vector2I(2, 0), new Vector2I(1, 1)},
        {new Vector2I(0, 0), new Vector2I(1, 0), new Vector2I(1, 1), new Vector2I(2, 1)},
        {new Vector2I(0, 0), new Vector2I(1, 0), new Vector2I(2, 0), new Vector2I(3, 0)},
        {new Vector2I(0, 0), new Vector2I(1, 0), new Vector2I(2, 0), new Vector2I(2, 1)}
    };
    public static final Vector2I[] patternDimension = {
        new Vector2I(2, 2), new Vector2I(3, 2), new Vector2I(3, 2), new Vector2I(4, 1), new Vector2I(3, 2)
    };
    
    
    private static final TetsawBlockData[] easyModeLevel = {
        new TetsawBlockData(5, 5, 0, 0)
    };
    public static final TetsawLevelData easyMode = new TetsawLevelData(easyModeLevel);
    
    private static final TetsawBlockData[] normalModeLevel = {
        new TetsawBlockData(0, 0, 0, 0)
    };
    public static final TetsawLevelData normalMode = new TetsawLevelData(normalModeLevel);
    
    private static final TetsawBlockData[] hardModeLevel = {
        new TetsawBlockData(0, 0, 0, 0)
    };
    public static final TetsawLevelData hardMode = new TetsawLevelData(hardModeLevel);
}
