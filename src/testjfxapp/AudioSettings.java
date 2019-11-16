package testjfxapp;
// Work in progress

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import testjfxapp.subsystems.AudioSubsystem;

public class AudioSettings extends ReversableMenu {

    Stage window;
    Scene audioSettings;
    Button btm1, btm2;

    Label audioSettingLabel,masterVolumeLabel,musicVolumeLabel,soundfxLabel, masterVolValue;
    CheckBox muteMusic,muteSoundFX, muteMaster;
    Slider masterVolume, musicVolume, soundfxVolume;
    //AudioSubsystem audio;

    MainMenu mainMenu;

    public AudioSettings(ReversableMenu m, MainMenu menu){
        this.m = m;
        mainMenu = menu;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        audioSettingLabel = new Label("Audio Setting");

        btm1 = new Button("Back to Main Menu");
        btm1.setOnAction(e -> mainMenu.showMenu());

        btm2 = new Button("Back");
        btm2.setOnAction(e -> setPreviousScene());

        masterVolumeLabel = new Label("Master Volume");
        musicVolumeLabel = new Label("Music Volume");
        soundfxLabel = new Label("Sound Effects Volume");
        masterVolValue = new Label();

        musicVolume = new Slider(0, 100, 50);
        //unsure how to handle volume change

        soundfxVolume = new Slider(0, 100, 50);
        //unsure how to handle volume change

        masterVolume = new Slider(0.0,100,50);
        //listen for master volume change
        masterVolume.valueProperty().addListener(observable -> {

            if (masterVolume.isValueChanging()) {
                AudioSubsystem.setMasterVolume(masterVolume.getValue() / 100.0);
            }
        });

        //create music volume slider
        musicVolume = new Slider(0,100,50);
        musicVolume.setShowTickLabels(true);
        musicVolume.setShowTickMarks(true);

        //listen for music volume change
        musicVolume.valueProperty().addListener(observable -> {
            if (!muteMusic.isSelected()) {
                if (musicVolume.isValueChanging()) {
                    AudioSubsystem.setMusicVolume(musicVolume.getValue() / 100.0);
                }
            }
        });

        //listen for sound effects volume change
        soundfxVolume = new Slider(0,100,50);

        soundfxVolume.valueProperty().addListener(observable -> {
            if (!muteSoundFX.isSelected()){
                if (soundfxVolume.isValueChanging()) {
                    AudioSubsystem.setSFXVolume(soundfxVolume.getValue() / 100.0);
                }
            }
        });

        muteMaster = new CheckBox("Mute Master Volume");
        //if checked then mute music
        muteMaster.setOnAction(e -> {
            if (muteMaster.isSelected()) {
                AudioSubsystem.setMasterVolume(0);
            }
            //else set volume to slider value
            else{
                AudioSubsystem.setMasterVolume(masterVolume.getValue()/100.0);
            }
        });


        //Create mute music checkbox
        muteMusic = new CheckBox("Mute Music");

        //if checked then mute music
        muteMusic.setOnAction(e -> {
            if (muteMusic.isSelected()) {
                AudioSubsystem.setMusicVolume(0);
            }
            //else set volume to slider value
            else{
                AudioSubsystem.setMusicVolume(musicVolume.getValue()/100.0);
            }
        });

        muteSoundFX = new CheckBox("Mute Sound Effects");


        muteSoundFX.setOnAction(e -> {
            if (muteSoundFX.isSelected()) {
                AudioSubsystem.setSFXVolume(0);
            }
            else{
                AudioSubsystem.setSFXVolume(soundfxVolume.getValue()/100.0);
            }
        });
//
//        btm3 = new Button("Back to Menu");
//        btm3.setOnAction(e -> window.setScene(settings));

        // Audio Settings layout:
        VBox AudioSettingsLayout = new VBox(40);
        AudioSettingsLayout.getChildren().addAll(btm1,audioSettingLabel,muteMaster,masterVolumeLabel,masterVolume,musicVolumeLabel,muteMusic,musicVolume,soundfxLabel,muteSoundFX,soundfxVolume);
        AudioSettingsLayout.setAlignment(Pos.CENTER);
        audioSettings = new Scene(AudioSettingsLayout, 300, 500);
        audioSettings.getStylesheets().add(getClass().getResource("TetsawStylesheet.css").toString());

        

    }

    @Override
    public void setCurrentScene() {
        window.setScene(audioSettings);
    }
}

