package com.gda.libgdxutils.gui.animation;

import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by smith on 12.05.17.
 */

public interface Animation {
    void open(Group upper, Group lower);
    void close(Group lower, Group upper);
}
