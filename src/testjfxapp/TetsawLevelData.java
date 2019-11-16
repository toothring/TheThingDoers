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
import java.util.Arrays;

/**
 *
 * @author Orion
 */
public class TetsawLevelData {
    public ArrayList<TetsawBlockData> data = new ArrayList<>();
    
    public TetsawLevelData(){
        
    }
    
    public TetsawLevelData(TetsawBlockData[] initData){
        data.addAll(Arrays.asList(initData));
    }
    
    public void addBlock(TetsawBlockData d){
        data.add(d);
    }
    
    public TetsawBlockData getBlock(int i){
        return data.get(i);
    }
}
