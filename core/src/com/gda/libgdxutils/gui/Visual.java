package com.gda.libgdxutils.gui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gda.libgdxutils.gui.animation.Animation;
import com.gda.libgdxutils.model.JustTexture;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ANTON on 5/5/2017.
 * Group - it's actor, which accommodates another actors
 */
public class Visual {

    //  Отображают выводимые группы на экране
//    private Group main, multi, option, help;

    private JustTexture background;
    private Map<String, Group> groups;
    private final float SCREEN_WIDTH, SCREEN_HEIGHT;

    Animation animation;

    public Visual(GroupBuilder builder, float screen_width, float screen_height) {
        animation = builder.animation;
        groups = builder.groups;
        SCREEN_WIDTH = screen_width;
        SCREEN_HEIGHT = screen_height;

        for (Group group : groups.values()) {
            builder.stage.addActor(group);
        }
    }

    //      Создаём бэкграунд, поскольку в кадой группе свой бэкграунд, но он одинаковый, по анимации увидишь
    public void addBackground(Group group) {
        background = new JustTexture("Menu/Background.png", 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        background.toBack();
        group.addActor(background);
    }

    public Animation getAnimation() {
        return animation;
    }

    public JustTexture getBackground() {
        return background;
    }

    public Map<String, Group> getGroups() {
        return groups;
    }

    public static class GroupBuilder {

        private Animation animation;

        private Map<String, Group> groups;

        private Stage stage;
        private final float SCREEN_WIDTH, SCREEN_HEIGHT;

        public GroupBuilder(Stage stage, float screen_width, float screen_height) {
            SCREEN_WIDTH = screen_width;
            SCREEN_HEIGHT = screen_height;
            groups = new HashMap<String, Group>();
            this.stage = stage;
        }

        public GroupBuilder setAnimation(Animation animation) {
            this.animation = animation;
            return this;
        }

        public GroupBuilder addGroup(String groupName, Group group) {
            group.setVisible(true);
            groups.put(groupName, group);
            return this;
        }

        public GroupBuilder setMainBackground(JustTexture justTexture) {
            if (groups.containsKey("BACKGROUND")) {
                groups.get("BACKGROUND").addActor(justTexture);
            } else {
                Group background = new Group();
                justTexture.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
                background.addActor(justTexture);
                addGroup("BACKGROUND", background);
            }
            return this;
        }

        public Visual build() {
            return new Visual(this, SCREEN_WIDTH, SCREEN_HEIGHT);
        }
    }
}
