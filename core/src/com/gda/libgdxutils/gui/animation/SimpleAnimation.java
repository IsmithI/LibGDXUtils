package com.gda.libgdxutils.gui.animation;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Created by ANTON on 5/9/2017.
 */

public class SimpleAnimation extends Actions implements Animation {

    private final float SCREEN_WIDTH, SCREEN_HEIGHT;

    public SimpleAnimation(float screen_width, float screen_height) {
        SCREEN_WIDTH = screen_width;
        SCREEN_HEIGHT = screen_height;
    }

    @Override
    public void open(Group upper, Group lower) {
//        Нижнюю группу назад, чтобы не появилась поверх
        lower.toBack();
        lower.setVisible(true);
        upper.setTouchable(Touchable.disabled);
//        Ниже всё написано
        upper.addAction(parallel(
                scaleTo(2, 2, 0.4f),
                moveTo(
                        -(SCREEN_WIDTH * 2 - SCREEN_WIDTH) / 2f,
                        -(SCREEN_HEIGHT * 2 - SCREEN_HEIGHT) / 2f,
                        0.4f),
                fadeOut(0.4f)));
    }
//      Есть Баг, когда нажимаешь на назад и бытро на настройки, окно скрывается после открытия
    @Override
    public void close(Group lower, Group upper) {
//        Выполняем действия параллельно
        upper.addAction(sequence(
                parallel(scaleTo(1f, 1f, 0.3f), moveTo(0, 0, 0.3f), fadeIn(0.3f)),
                touchable(Touchable.enabled)));
//      Скрываем объект
        lower.addAction(delay(0.3f, hide()));
    }
}
