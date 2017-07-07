package com.gda.libgdxutils.gui;

import com.badlogic.gdx.Screen;
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
    private List<Group> gameGroups, guiGroups;
    private ExtendedScreen screen;

    Animation animation;

    public Visual(GroupBuilder builder) {
        animation = builder.animation;
        gameGroups = builder.gameGroups;
        guiGroups = builder.guiGroups;
        screen = builder.screen;

        for (Group group : gameGroups) {
            builder.gameStage.addActor(group);
        }

        for (Group guiGroup : guiGroups) {
            builder.guiStage.addActor(guiGroup);
        }

    }

    //      Создаём бэкграунд, поскольку в кадой группе свой бэкграунд, но он одинаковый, по анимации увидишь
    public void addBackground(Group group) {
        background = new JustTexture("Menu/Background.png", 0, 0, screen.getGuiViewportWidth(), screen.getGuiViewportHeight());
        background.toBack();
        group.addActor(background);
    }

    public Animation getAnimation() {
        return animation;
    }

    public JustTexture getBackground() {
        return background;
    }

    public List<Group> getGameGroups() {
        return gameGroups;
    }

    public List<Group> getGuiGroups() {
        return guiGroups;
    }

    public static class GroupBuilder {

        private Animation animation;

        private List<Group> gameGroups, guiGroups;

        private Stage gameStage, guiStage;
        private ExtendedScreen screen;

        public GroupBuilder(ExtendedScreen screen) {
            this.screen = screen;

            gameGroups = new ArrayList<>();
            guiGroups = new ArrayList<>();

            this.gameStage = screen.getGameStage();
            this.guiStage = screen.getGuiStage();
        }

        public GroupBuilder setAnimation(Animation animation) {
            this.animation = animation;
            return this;
        }

        public GroupBuilder addGroupToGameStage(String groupName, Group group) {
            group.setVisible(true);
            group.setName(groupName);
            gameGroups.add(group);
            return this;
        }

        public GroupBuilder addGroupToGUIStage(String groupName, Group group) {
            group.setVisible(true);
            group.setName(groupName);
            guiGroups.add(group);
            return this;
        }

        public GroupBuilder setMainBackground(JustTexture justTexture) {
            Group background = new Group();
            justTexture.setSize(screen.getGuiViewportWidth(), screen.getGuiViewportHeight());
            background.addActor(justTexture);
            addGroupToGameStage("BACKGROUND", background);
            return this;
        }

        public Visual build() {
            return new Visual(this);
        }
    }
}
