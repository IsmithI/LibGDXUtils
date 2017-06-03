package com.gda.libgdxutils.model;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by smith on 03.06.17.
 */
public class ExtendedGroup extends Group {

    public ExtendedGroup() {}

    public ExtendedGroup(Actor... actors) {
        for (Actor actor : actors)
            addActor(actor);
    }
}
