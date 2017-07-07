package com.gda.libgdxutils.gui.animation;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by smith on 10.06.17.
 */
public class FadeAnimation implements GroupAnimation {

    private float duration;

    public FadeAnimation(float duration) {
        this.duration = duration;
    }

    @Override
    public void show(Group group) {
        group.setColor(group.getColor().r, group.getColor().g, group.getColor().b, 0f);
        group.addAction(Actions.fadeIn(duration));
    }

    @Override
    public void hide(Group group) {
        group.addAction(Actions.fadeOut(duration));
    }


    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
