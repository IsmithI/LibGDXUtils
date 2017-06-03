package com.gda.libgdxutils;

import com.badlogic.gdx.Gdx;

import static com.sun.webkit.graphics.GraphicsDecoder.SCALE;

/**
 * Created by smith on 03.06.17.
 */
public class Input {

    public static int getInputX() {
        return Gdx.input.getX()*SCALE;
    }
    public static int getInputX(int pointer) {
        return Gdx.input.getX(pointer)*SCALE;
    }

    public static int getInputY() {
        return Gdx.input.getY()*SCALE;
    }
    public static int getInputY(int pointer) {
        return Gdx.input.getY(pointer)*SCALE;
    }

    public static float getScreenWidth() {
        return Gdx.graphics.getWidth()*SCALE;
    }

    public static float getScreenHeight() {
        return Gdx.graphics.getHeight()*SCALE;
    }


}
