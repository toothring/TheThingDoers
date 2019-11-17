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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author Orion
 */
public class AudioSubsystem {
    
    private static Map<String, Media> musicData;
    private static Map<String, Media> seData;
    private static ArrayList<MediaPlayer> soundEffects;
    private static MediaPlayer backgroundMusic;
    
    private static double masterVolume, sfxVolume, musicVolume;
    private static boolean channelMuteMusic, channelMuteSFX;
    
    public AudioSubsystem() {
        masterVolume = 1.0;
        sfxVolume = 0.5;
        musicVolume = 0.2;
        musicData = new HashMap<>();
        seData = new HashMap<>();
        soundEffects = new ArrayList<>();
        channelMuteMusic = false;
        channelMuteSFX = false;
        
    }

    /**
     * This function was made to test the system. There should be no calls to
     * this in the final version of the game, but feel free to add stuff to this
     * function if you need to figure something out
     */
    public void playTest() {
        String path = getClass().getClassLoader().getResource("testjfxapp/audiodata/main.mp3").toExternalForm();
        Media main = new Media(path);
        
        musicData.put("main", main);
        backgroundMusic = new MediaPlayer(musicData.get("main"));
        backgroundMusic.play();
    }

    /**
     * Adds a music track to the game. The music track can be played using the
     * title string in playMusic(), and all files must be in
     * audiodata/testjfxapp/audiodata (testjfxapp.audiodata)
     *
     * @param title The title of the track
     * @param filename the name of the file in audiodata/testjfxapp/audiodata
     * (testjfxapp.audiodata)
     */
    public void registerMusic(String title, String filename) {
        String path = getClass().getClassLoader().getResource("testjfxapp/audiodata/" + filename).toExternalForm();
        Media track = new Media(path);
        musicData.put(title, track);
    }

    /**
     * Adds a sound effect to the game. The sound effect can be played using the
     * title string in playSound(), and all files must be in
     * audiodata/testjfxapp/audiodata (testjfxapp.audiodata)
     *
     * @param title The name of the sound
     * @param filename the name of the file in audiodata/testjfxapp/audiodata
     * (testjfxapp.audiodata)
     */
    public void registerSound(String title, String filename) {
        String path = getClass().getClassLoader().getResource("testjfxapp/audiodata/" + filename).toExternalForm();
        Media effect = new Media(path);
        seData.put(title, effect);
    }

    /**
     * Plays a sound effect
     *
     * @param title the title of the sound effect to play
     */
    public static void playSound(String title) {
        MediaPlayer sound = new MediaPlayer(seData.get(title));
        soundEffects.add(sound);
        sound.setOnEndOfMedia(() -> {
            soundEffects.remove(sound);
            sound.dispose();
        });
        sound.setVolume(sfxVolume * masterVolume);
        sound.play();
    }
    
    public static void playMusic(String title) {
        Media m = musicData.get(title);
        MediaPlayer music = new MediaPlayer(m);
        if (backgroundMusic != null) {
            backgroundMusic.dispose();
        }
        backgroundMusic = music;
        backgroundMusic.setVolume(musicVolume * masterVolume);
        backgroundMusic.setOnEndOfMedia(() -> {
            backgroundMusic.seek(Duration.ZERO);
            backgroundMusic.play();
        });
        backgroundMusic.play();
    }
    
    public static void setMasterVolume(double v) {
        masterVolume = v;
        updateVolumes();
    }
    
    public static void setMusicVolume(double v) {
        musicVolume = v;
        updateVolumes();
    }
    
    public static void setSFXVolume(double v) {
        sfxVolume = v;
        updateVolumes();
    }
    
    private static void updateVolumes() {
        backgroundMusic.setVolume(channelMuteMusic ? 0.0 : musicVolume * masterVolume);
        for (var sound : soundEffects) {
            sound.setVolume(channelMuteSFX ? 0.0 : sfxVolume * masterVolume);
        }
    }
    
    public static void toggleMusicMute(){
        channelMuteMusic = !channelMuteMusic;
        updateVolumes();
    }
    
    public static void toggleSoundMute(){
        channelMuteSFX = !channelMuteSFX;
        updateVolumes();
    }
    
    public static void setPlaybackMultiplier(double mult){
        backgroundMusic.setRate(mult);
    }
    
}
