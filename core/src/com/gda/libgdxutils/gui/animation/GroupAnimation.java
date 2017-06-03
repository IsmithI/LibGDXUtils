package com.gda.libgdxutils.gui.animation;

import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by smith on 19.05.17.
 * Этот интерфейс дает большую свободу действий нежели предыдущий, можно создать разную анимацию
 * появления и исчезновения, вот только почему-то я не могу заставить появиться MainMenu, допустим
 * Multi появляется, а та штука не хочет, но оно работает
 */

public interface GroupAnimation {
    void show(Group group);
    void hide(Group group);
}
