package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    final ClassRankGame game;

    OrthographicCamera camera;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter2;
    private BitmapFont coordinates;
    private BitmapFont gameTitle;
    private int font2x = 100;
    private int font2y = 100;

    public MainMenuScreen(final ClassRankGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 30;
        coordinates = generator.generateFont(parameter);
        gameTitle = generator.generateFont(parameter2);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        coordinates.setColor(Color.BLACK);
        coordinates.draw(game.batch, "["+font2x+","+font2y+"]", font2x, font2y);
        gameTitle.setColor(Color.BLACK);
        gameTitle.draw(game.batch, "Welcome to the Class Rank Game", 149, 429);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            font2x = (int)touchPos.x;
            font2y = (int)touchPos.y;
        }


    }

    @Override
    public void resize(int width, int height) {

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
        game.batch.dispose();
        coordinates.dispose();
        generator.dispose();
    }
}
