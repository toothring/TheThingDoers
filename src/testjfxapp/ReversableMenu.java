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

import javafx.stage.Stage;

/**
 *
 * @author Orion
 */
public abstract class ReversableMenu {
    //The previous menu
    ReversableMenu m;
    
    //This is to allow setting the previous menu's scene, due to potential differences in how it may handle setting the scene
    public abstract void setCurrentScene();
    
    //To allow compatability with current setup, we should be handling this in the class constructor
    public void start(Stage PrimaryStage) throws Exception{};
    
    //Set the scene to the currently stored ReversableMenu's scene
    public void setPreviousScene(){
        if(m != null)
            m.setCurrentScene();
    }
}
