package com.gda.libgdxutils.model;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by smith on 14.05.17.
 */

public class Text extends Actor {

    private BitmapFont font;
    private String value;
    private int textAlign;

    private Text(BitmapFont font, String value, Color color, float x, float y) {
        setPosition(x, y);
        this.font = font;
        this.value = value;
        font.setColor(color);
        textAlign = Align.center;
    }

    public Text(TextBuilder builder) {
        setPosition(builder.x, builder.y);
        font = builder.font;
        value = builder.value;
        textAlign = builder.textAlign;
        if (builder.color != null)
            font.setColor(builder.color);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, value, getX(), getY(), 0, textAlign, false);
    }

    public static Text createDefaultText() {
        return new Text(new BitmapFont(), "Default text", Color.BLACK, 100, 100);
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(int textAlign) {
        this.textAlign = textAlign;
    }

    public static class TextBuilder {

        BitmapFont font;
        String value;
        float x, y;
        int textAlign;
        Color color;

        public TextBuilder withFont(BitmapFont font) {
            this.font = font;
            return this;
        }

        public TextBuilder withText(String value) {
            this.value = value;
            return this;
        }

        public TextBuilder atPosition(float x, float y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public TextBuilder alignBy(int textAlign) {
            this.textAlign = textAlign;
            return this;
        }

        public TextBuilder color(Color color) {
            font.setColor(Color.BLACK);
            this.color = color;
//            font.setColor(color);
            return this;
        }

        public Text build() {
            return new Text(this);
        }

    }

    //Статический генератор шрифтов
    public static BitmapFont generateFont(FileHandle font, int size) {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(font);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = size;

        BitmapFont bitmapFont = generator.generateFont(parameter);
        generator.dispose();

        return bitmapFont;
    }
}
