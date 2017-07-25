package com.gda.libgdxutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by ANTON on 5/9/2017.
 * Настройки исходя из названий переменных
 * Test
 */

public class Setting {
    private static final Setting ourInstance = new Setting();

    private Preferences preferences;

    public static Setting getInstance(){return ourInstance;}

    private Setting() {
        preferences = Gdx.app.getPreferences("Preference");
    }

    //        Изменение имени при первом запуске, всплывает окно с вводом Имени до 10-ти символов
    //        Наброски
    /*public void firstStart(String name) {
        if (!getFirstStart()) {
            setName(name);
            setFirstStart(true);
        }
    }*/

    private void setFirstStart(boolean firstStart) {
        preferences.putBoolean("FirstStart", firstStart);
    }

    public void setFlush() {
        preferences.flush();
    }

    public void setName(String name) {
        preferences.putString("Name", name);
    }

    public void setSound(boolean sound) {
        preferences.putBoolean("Sound", sound);
    }

    public void setMusic(boolean music) {preferences.putBoolean("Music", music); }

    public void setVibration(boolean vibration) {
        preferences.putBoolean("Vibration", vibration);
    }

    private boolean getFirstStart() {
        return preferences.getBoolean("FirstStart", false);
    }

    public String getName() {
        return preferences.getString("Name", "Player");
    }

    public boolean getSound() {
        return preferences.getBoolean("Sound", true);
    }

    public boolean getMusic() { return preferences.getBoolean("Music", true); }

    public boolean getVibration() {
        return preferences.getBoolean("Vibration", true);
    }
}
