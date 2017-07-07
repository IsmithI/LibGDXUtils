package com.gda.libgdxutils.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;

/**
 * Created by smith on 03.06.17.
 */
public class ExtendedScreen implements Screen {

    private Stage gameStage, guiStage;

    //параметры видимого поля, используются для позиционирования элементов в игре
    private float SCREEN_WIDTH, SCREEN_HEIGHT;
    private final float SCALE;

    private Batch batch;

    private FillViewport gameViewport;
    private ExtendViewport guiViewport;

    private Camera gameCamera;
    private Camera guiCamera;

    //Визуальная составляющая игры
    private Visual visual;

    //Обработчик нескольких InputProcessor'ов
    private InputMultiplexer input;

    public ExtendedScreen(final float WORLD_WIDTH, final float WORLD_HEIGHT) {

        SCALE = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();

        batch = new SpriteBatch();

        /*
        создаем камеру и видимое поле, в качестве аргумента они принимают значения игровых единиц,
        при чем высота умножается на коефициент пропорциональности экрана, который был высчитан ранее
        и равен отношению высоты экрана к ширине, а также саму камеру
        */
        gameCamera = new OrthographicCamera();
        guiCamera = new OrthographicCamera();

        gameViewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT * SCALE, gameCamera);
        guiViewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT * SCALE, guiCamera);


        //применяем созданный вид на сцене
        gameStage = new Stage(gameViewport, batch);
        gameStage.getViewport().apply();

        guiStage = new Stage(guiViewport);
        guiStage.getViewport().apply();

        //задаем параметры высоты и ширины игрового поля
        SCREEN_WIDTH = guiStage.getCamera().viewportWidth;
        SCREEN_HEIGHT = guiStage.getCamera().viewportHeight;

        //Создать обработчик и жобавить в него сцену события которой будут обработаны
        input = new InputMultiplexer();
        input.addProcessor(gameStage);

        Gdx.input.setInputProcessor(input);
    }

    public void createVisual(Visual visual) {
        this.visual = visual;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(guiCamera.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //        не уверен что из этого нужно, но вроде работает
        //Game objects
        gameViewport.apply();
        gameCamera.update();

        gameStage.act(Gdx.graphics.getDeltaTime());
        gameStage.draw();


        //Draw the GUI
        guiViewport.apply();
        guiCamera.update();

        guiStage.act(Gdx.graphics.getDeltaTime());
        guiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        /*проблема з resize в том что он то обновляет все, но обьекты которые используют тот же
        SCREEN_WIDTH и SCREEN_HEIGHT они создают свои локальные копии этих данных, и поэтому в игре
        они обновились, а в обьектах нет, поэтому удобнее всего будет пройтись по всем обьектам и
        обновить их положение.
        Такая проблема есть только если вручную изменять размер окна, если менять размер окна в
        самом начале с помощью config.width и config.height, то все шикарно работает.
        Я тестил с разными экранами и оно выглядит адекватно
         */
        guiStage.getViewport().update(width, height);
        gameStage.getViewport().update(width, height);

        SCREEN_WIDTH = gameStage.getCamera().viewportWidth;
        SCREEN_HEIGHT = gameStage.getCamera().viewportHeight;

        gameStage.getCamera().position.set(
                gameStage.getCamera().viewportWidth / 2,
                gameStage.getCamera().viewportHeight / 2,
                0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        guiStage.dispose();
        gameStage.dispose();
        batch.dispose();
    }

    public Stage getGameStage() {
        return gameStage;
    }

    public Stage getGuiStage() {
        return guiStage;
    }

    public void setGameStage(Stage gameStage) {
        this.gameStage = gameStage;
    }

    public float getGameViewportWidth() {
        return gameCamera.viewportWidth;
    }

    public float getGameViewportHeight() {
        return gameCamera.viewportHeight;
    }

    public float getGuiViewportWidth() {
        return guiCamera.viewportWidth;
    }

    public float getGuiViewportHeight() {
        return guiCamera.viewportHeight;
    }

    public void addInputProcessor(InputProcessor inputProcessor) {
        input.addProcessor(inputProcessor);
    }

    public InputMultiplexer getInput() {
        return input;
    }

    public Visual getVisual() {
        return visual;
    }
}
