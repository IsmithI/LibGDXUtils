package com.gda.libgdxutils.gui.animation;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by smith on 10.06.17.
 */
public class FadeAnimation implements ScreenAnimation {

    private float duration;

    public FadeAnimation(float duration) {
        this.duration = duration;
    }

    @Override
    public void animate(Group hideGroup, Group showGroup) {
        hideGroup.addAction(Actions.fadeOut(duration));
        showGroup.setColor(showGroup.getColor().r, showGroup.getColor().g, showGroup.getColor().b, 0f);
        showGroup.addAction(Actions.fadeIn(duration));
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
