package com.gda.libgdxutils.gui.animation;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
/**
 * Created by smith on 19.05.17.
 */

public class SlideAnimation implements GroupAnimation {

    private Direction direction;
    private float time;
    private final float SCREEN_WIDTH, SCREEN_HEIGHT;

    public SlideAnimation(Direction direction, float time, float screen_width, float screen_height) {
        this.direction = direction;
        this.time = time;
        SCREEN_WIDTH = screen_width;
        SCREEN_HEIGHT = screen_height;
    }

    @Override
    public void show(Group group) {
        group.addAction(Actions.show());
        switch (direction) {
            case LEFT:
                group.setPosition(SCREEN_WIDTH, 0);
                group.addAction(Actions.moveBy(-SCREEN_WIDTH, 0, time));
                break;
            case RIGHT:
                group.setPosition(-SCREEN_WIDTH, 0);
                group.addAction(Actions.moveBy(SCREEN_WIDTH, 0, time));
                break;
            case UP:
                group.setPosition(0, SCREEN_HEIGHT);
                group.addAction(Actions.moveBy(0, -SCREEN_HEIGHT, time));
                break;
            case DOWN:
                group.setPosition(0, -SCREEN_HEIGHT);
                group.addAction(Actions.moveBy(0, SCREEN_HEIGHT, time));
                break;
        }
    }

    @Override
    public void hide(Group group) {
        switch (direction) {
            case LEFT:
                group.addAction(Actions.sequence(Actions.moveBy(-SCREEN_WIDTH, 0, time), Actions.hide()));
                break;
            case RIGHT:
                group.addAction(Actions.sequence(Actions.moveBy(SCREEN_WIDTH, 0, time), Actions.hide()));
                break;
            case UP:
                group.addAction(Actions.sequence(Actions.moveBy(0, SCREEN_HEIGHT, time), Actions.hide()));
                break;
            case DOWN:
                group.addAction(Actions.sequence(Actions.moveBy(0, -SCREEN_HEIGHT, time), Actions.hide()));
                break;
        }
    }

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }
}
