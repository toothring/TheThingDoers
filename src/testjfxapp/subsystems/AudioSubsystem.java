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
package testjfxapp.subsystems;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Orion
 */
public class AudioSubsystem {

    private Map<String, Media> musicData;
    private ArrayList<MediaPlayer> soundEffects;
    private MediaPlayer backgroundMusic;

    public AudioSubsystem() {
        musicData = new HashMap<>();
        soundEffects = new ArrayList<>();

    }

    public void playTest() {
        String path = getClass().getClassLoader().getResource("main.mp3").toExternalForm();
        String fallback;
        fallback = "file:///E:/User/Orion/Documents/NetBeansProjects/TheThingDoers-All/audiodata/testjfxapp/audiodata/main.mp3";
        System.out.println(path + "\n" + fallback);
        Media main;
        try {
            System.out.println("Trying main path");
            main = new Media(path);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Trying fallback path");
            main = new Media(fallback);
        }
        musicData.put("main", main);
        backgroundMusic = new MediaPlayer(musicData.get("main"));
        backgroundMusic.play();
    }
}
