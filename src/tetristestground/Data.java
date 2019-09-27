
package tetristestground;

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
}
