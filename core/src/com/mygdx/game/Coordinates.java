package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

public class Coordinates {
    /*
    final ClassRankGame game;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont coordinates;
    private int xCoord;
    private int yCoord;
    OrthographicCamera camera;
    public Coordinates(final ClassRankGame game, OrthographicCamera camera) {
        this.camera = camera;
        this.game = game;
        xCoord = 100;
        yCoord = 100;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        coordinates = generator.generateFont(parameter);
    }

    public void getCoordinateText() {
        coordinates.draw(game.batch, "["+xCoord+","+yCoord+"]", xCoord, yCoord);
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            xCoord = (int)touchPos.x;
            yCoord = (int)touchPos.y;
        }
    }
     */
    /*
    public static void addCoordinates () {
        FreeTypeFontGenerator generator new FreeTypeFontGenerator();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont coordinates = new BitmapFont();
        int xCoord = 100;
        int yCoord = 100;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        coordinates = generator.generateFont(parameter);
        coordinates.draw(game.batch, "["+xCoord+","+yCoord+"]", xCoord, yCoord);
    }
     */
}
