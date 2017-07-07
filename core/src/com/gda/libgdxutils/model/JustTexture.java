package com.gda.libgdxutils.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by ANTON on 5/5/2017.
 */
public class JustTexture extends Actor {
    private Sprite sprite;
    private TextureRegion textureRegion;

    public JustTexture(String texture, float x, float y) {

        textureRegion = new TextureRegion(new Texture(texture));
        sprite = new Sprite(textureRegion);

        setPosition(x, y);
    }

    public JustTexture(String texture, float x, float y, float width, float height){
        textureRegion = new TextureRegion(new Texture(texture));
        sprite = new Sprite(textureRegion);
        setPosition(x, y);
        setSize(width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        Берём RGBA
        Color color = getColor();
//        Присваиваем цвет спрайту
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
//        Отрисовуем спрайт
        batch.draw(sprite, getX(), getY(), sprite.getWidth(), sprite.getHeight());
//        Не знаю для чего это, но так было на форуме, но и без этого работает..
//        batch.setColor(color.r, color.g, color.b, 1f);

//        Doesn't work
//        sprite.draw(batch);
    }

    //Без этого спрайт обьекта JustTexture не будет менять размер
    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        sprite.setSize(width, height);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}
