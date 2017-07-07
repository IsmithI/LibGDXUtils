package com.gda.libgdxutils.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gda.libgdxutils.Setting;

/**
 * Created by ANTON on 5/5/2017.
 */
public abstract class Button extends Actor {
    private Texture textureTouchUp;
    private Texture textureTouchDown;

    private Sprite sprite;

    private Text text;

    private boolean checkTouch;

    public Button(Texture textureTouchUp, Texture textureTouchDown, float x, float y) {
        this.textureTouchUp = textureTouchUp;
        this.textureTouchDown = textureTouchDown;

        sprite = new Sprite(this.textureTouchUp);

        setBounds(x, y, sprite.getWidth(), sprite.getHeight());
        setPosition(x, y);
        addListener(new ClickListener() {
            //            Нажимаем на кнопку
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Button.this.touchDown();
                return true;
            }

            //            Уводим в сторону палец/курсор с кнопки
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Button.this.exit();
            }

            //            Отпускаем кнопку
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Button.this.touchUp();
            }
        });
    }

    public Button(final String touchUp, final String touchDown, float x, float y) {
        this.textureTouchUp = new Texture(touchUp);
        this.textureTouchDown = new Texture(touchDown);
        sprite = new Sprite(this.textureTouchUp);

        setBounds(x, y, sprite.getWidth(), sprite.getHeight());
        setPosition(x, y);
        addListener(new ClickListener() {
            //            Нажимаем на кнопку
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Button.this.touchDown();
                return true;
            }

            //            Уводим в сторону палец/курсор с кнопки
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Button.this.exit();
            }

            //            Отпускаем кнопку
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Button.this.touchUp();
            }
        });
    }

    public Button(TextureRegion touchUp, TextureRegion touchDown, float x, float y) {

    }

    //Позволяет наложить на кнопку текст
    public void addText(Text text) {
        this.text = text;
        GlyphLayout layout = new GlyphLayout(text.getFont(), text.getValue());
        this.text.setPosition(getX() + getWidth()/2, getY() + layout.height/2 + getHeight()/2);
    }

    protected void touchUp() {
        if (checkTouch)
            act();
    }

    protected void exit() {
        checkTouch = false;
        sprite.setTexture(textureTouchUp);
    }

    protected void touchDown() {
        checkTouch = true;
        sprite.setTexture(textureTouchDown);
//                Проверяем включены ли вибрации и звуки(в будущем)
        if (Setting.getInstance().getVibration())
            Gdx.input.vibrate(50);
    }

    public abstract void act();

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        Берём RGBA
        Color color = getColor();
//        Присваиваем цвет спрайту
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
//        Отрисовуем спрайт
        batch.draw(sprite, getX(), getY(), sprite.getWidth(), sprite.getHeight());

        if (text != null)
            text.draw(batch, parentAlpha);
    }

    //    Сеттеры и Геттеры
    public void setTextureTouchUp(String touchUp) {
        textureTouchUp = new Texture(touchUp);
    }

    public void setTextureTouchDown(String touchDown) {
        textureTouchDown = new Texture(touchDown);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Texture getTextureTouchUp() {
        return textureTouchUp;
    }

    public Texture getTextureTouchDown() {
        return textureTouchDown;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
