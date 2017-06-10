package com.gda.libgdxutils;

import com.badlogic.gdx.Gdx;

/**
 * Created by smith on 03.06.17.
 */
public class Input {

    public static final float SCALE = (float) Gdx.graphics.getHeight()/Gdx.graphics.getWidth();

    public static float getInputX() {
        return Gdx.input.getX()*SCALE;
    }
    public static float getInputX(int pointer) {
        return Gdx.input.getX(pointer)*SCALE;
    }

    public static float getInputY() {
        return Gdx.input.getY()*SCALE;
    }
    public static float getInputY(int pointer) { return Gdx.input.getY(pointer)*SCALE;}

    public static float getScreenWidth() {
        return Gdx.graphics.getWidth()*SCALE;
    }

    public static float getScreenHeight() {
        return Gdx.graphics.getHeight()*SCALE;
    }


}
