package com.gda.libgdxutils.model;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by smith on 14.05.17.
 * кнопка переключатель
 */
public abstract class ToggleButton extends Button {

    private boolean active;

    public ToggleButton(String touchUp, String touchDown, float x, float y) {
        super(touchUp, touchDown, x, y);
        active = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if (active)
            getSprite().setTexture(getTextureTouchDown());
        else
            getSprite().setTexture(getTextureTouchUp());

        super.draw(batch, parentAlpha);
    }

    @Override
    protected void touchUp() {
        super.touchUp();
    }

    @Override
    protected void exit() {
        super.exit();
    }

    @Override
    protected void touchDown() {
        active = !active;
        super.touchDown();
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
