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

import orion.number.Vector2I;

/**
 *
 * @author Orion
 */
public class TetsawBlockData {
    public Vector2I finishPos;
    public int rotation;
    public int pattern;
    
    public TetsawBlockData(Vector2I finishPos, int rotation, int pattern){
        this.finishPos = finishPos;
        this.rotation = rotation;
        this.pattern = pattern;
    }
    
    public TetsawBlockData(int finishPosX, int finishPosY, int rotation, int pattern){
        this.finishPos = new Vector2I(finishPosX, finishPosY);
        this.rotation = rotation;
        this.pattern = pattern;
    }
}
