package com.gda.libgdxutils.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gda.libgdxutils.Input;

import static com.gda.libgdxutils.Input.getInputX;
import static com.gda.libgdxutils.Input.getInputY;

/**
 * Created by smith on 03.06.17.
 */
public class Joystick extends Actor implements InputProcessor {

    private float angle = 0f;
//    private boolean touch = false;

    private Sprite gamepad, joystick;
    private Vector2 gamepadPos, joystickPos;

    private float SCREEN_WIDTH, SCREEN_HEIGHT;

    public Joystick(String gamepadPath, String joystickPath, float screen_width, float screen_height) {
        SCREEN_WIDTH = screen_width;
        SCREEN_HEIGHT = screen_height;

        gamepad = new Sprite(new Texture(Gdx.files.internal(gamepadPath)));
        gamepad.setOrigin(0, 0);

        gamepadPos = new Vector2();

        joystick = new Sprite(new Texture(Gdx.files.internal(joystickPath)));
        joystick.setOrigin(0, 0);

        joystickPos = new Vector2();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        gamepad.setPosition(gamepadPos.x - gamepad.getWidth() / 2, SCREEN_HEIGHT - gamepadPos.y - gamepad.getHeight() / 2);
        gamepad.draw(batch, parentAlpha);

        joystick.setPosition(joystickPos.x - joystick.getWidth() / 2, SCREEN_HEIGHT - joystickPos.y - joystick.getHeight() / 2);
        joystick.draw(batch, parentAlpha);
    }

//    private void act() {
//
//        if (Gdx.input.isTouched(0) && getInputX(0) > Input.getScreenHeight() / 2) i = 0;
//        else if (Gdx.input.isTouched(1) && getInputX(1) > Input.getScreenHeight() / 2) i = 1;
//
//        if (i >= 0) {
//            if (!touch) {
//                gamepadPos.set(getInputX(i), getInputY(i));
//                joystickPos.set(getInputX(i), getInputY(i));
//                touch = true;
//            }
//
//            angle = -MathUtils.radiansToDegrees * MathUtils.atan2(getInputY(i) - gamepadPos.y, getInputX(i) - gamepadPos.x);
//            if (angle < 0) angle += 360;
//
//            if (Math.hypot(getInputY(i) - gamepadPos.y, getInputX(i) - gamepadPos.x) < gamepad.getWidth() / 2) {
//                joystickPos.x = getInputX(i);
//                joystickPos.y = getInputY(i);
//            } else {
//                joystickPos.x = gamepadPos.x + gamepad.getWidth() / 2 * MathUtils.cosDeg(-angle);
//                joystickPos.y = gamepadPos.y + gamepad.getWidth() / 2 * MathUtils.sinDeg(-angle);
//            }
//
//            if (!Gdx.input.isTouched(i)) touch = false;
//        } else touch = false;
//
//    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        gamepadPos.set(screenX, screenY);
        joystickPos.set(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        joystickPos.set(gamepadPos.x, gamepadPos.y);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        angle = -MathUtils.radiansToDegrees * MathUtils.atan2(screenY - gamepadPos.y, screenX - gamepadPos.x);
        if (angle < 0) angle += 360;

        if (Math.hypot(screenY - gamepadPos.y, screenX - gamepadPos.x) < gamepad.getWidth() / 2) {
            joystickPos.x = screenX;
            joystickPos.y = screenY;
        } else {
            joystickPos.x = gamepadPos.x + gamepad.getWidth() / 2 * MathUtils.cosDeg(-angle);
            joystickPos.y = gamepadPos.y + gamepad.getWidth() / 2 * MathUtils.sinDeg(-angle);
        }

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public float getSCREEN_WIDTH() {
        return SCREEN_WIDTH;
    }

    public void setSCREEN_WIDTH(float SCREEN_WIDTH) {
        this.SCREEN_WIDTH = SCREEN_WIDTH;
    }

    public float getSCREEN_HEIGHT() {
        return SCREEN_HEIGHT;
    }

    public void setSCREEN_HEIGHT(float SCREEN_HEIGHT) {
        this.SCREEN_HEIGHT = SCREEN_HEIGHT;
    }
}
