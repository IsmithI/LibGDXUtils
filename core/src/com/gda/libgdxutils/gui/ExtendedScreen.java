package com.gda.libgdxutils.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by smith on 03.06.17.
 */
public class ExtendedScreen implements Screen {

    private Stage gameStage;
    private OrthographicCamera camera;

    //параметры видимого поля, используются для позиционирования элементов в игре
    private static float SCREEN_WIDTH, SCREEN_HEIGHT;
    private Viewport viewport;

    //Визуальная составляющая игры
    private Visual visual;

    //Обработчик нескольких InputProcessor'ов
    private InputMultiplexer input;

    public ExtendedScreen(final float WORLD_WIDTH, final float WORLD_HEIGHT) {
        /*
        создаем камеру и видимое поле, в качестве аргумента они принимают значения игровых единиц,
        при чем высота умножается на коефициент пропорциональности экрана, который был высчитан ранее
        и равен отношению высоты экрана к ширине, а также саму камеру
        */
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply();

        //применяем созданный вид на сцене
        gameStage = new Stage(viewport);

        //задаем параметры высоты и ширины игрового поля
        SCREEN_WIDTH = gameStage.getCamera().viewportWidth;
        SCREEN_HEIGHT = gameStage.getCamera().viewportHeight;

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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        не уверен что из этого нужно, но вроде работает
//        camera.update();
        gameStage.getCamera().update();

        gameStage.act(Gdx.graphics.getDeltaTime());
        gameStage.draw();
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
        viewport.update(width, height);
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
        gameStage.dispose();
    }
}
