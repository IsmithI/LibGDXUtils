package com.gda.libgdxutils.gui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gda.libgdxutils.Input;
import com.gda.libgdxutils.gui.animation.Animation;
import com.gda.libgdxutils.model.JustTexture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANTON on 5/5/2017.
 * Group - it's actor, which accommodates another actors
 */
public class Visual {

    //  Отображают выводимые группы на экране
//    private Group main, multi, option, help;

    private JustTexture background;
    private List<Group> groups;
    private final float SCREEN_WIDTH, SCREEN_HEIGHT;

    Animation animation;

    public Visual(GroupBuilder builder) {
        animation = builder.animation;
        groups = builder.groups;
        SCREEN_WIDTH = Input.getScreenWidth();
        SCREEN_HEIGHT = Input.getScreenHeight();


        for (Group group : groups) {
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

    public List<Group> getGroups() {
        return groups;
    }

    public static class GroupBuilder {

        private Animation animation;

        private List<Group> groups;

        private Stage stage;
        private final float SCREEN_WIDTH, SCREEN_HEIGHT;

        public GroupBuilder(Stage stage) {
            SCREEN_WIDTH = Input.getScreenWidth();
            SCREEN_HEIGHT = Input.getScreenHeight();
            groups = new ArrayList<>();
            this.stage = stage;
        }

        public GroupBuilder(ExtendedScreen screen) {
            SCREEN_HEIGHT = screen.getScreenHeight();
            SCREEN_WIDTH = screen.getScreenWidth();
            groups = new ArrayList<>();
            this.stage = screen.getGameStage();
        }

        public GroupBuilder setAnimation(Animation animation) {
            this.animation = animation;
            return this;
        }

        public GroupBuilder addGroup(String groupName, Group group) {
            group.setVisible(true);
            groups.add(group);
            return this;
        }

        public GroupBuilder setMainBackground(JustTexture justTexture) {
            Group background = new Group();
            justTexture.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
            background.addActor(justTexture);
            addGroup("BACKGROUND", background);
            return this;
        }

        public Visual build() {
            return new Visual(this);
        }
    }
}
