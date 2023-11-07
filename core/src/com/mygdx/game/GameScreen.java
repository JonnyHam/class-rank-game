package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class GameScreen implements Screen {
    OrthographicCamera camera;
    final ClassRankGame game;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont gameScreenTitle;
    //private PolygonSpriteBatch polygonSpriteBatch;
    private Texture texture;
    private TextureRegion region;
    private ShapeDrawer shapeDrawer;
    private int circleX = 100;
    private int circleY = 100;

    public GameScreen(final ClassRankGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        gameScreenTitle = generator.generateFont(parameter);

        //polygonSpriteBatch = new PolygonSpriteBatch();
        texture = new Texture(Gdx.files.internal("white_pixel.png"));
        region = new TextureRegion(texture);
        shapeDrawer = new ShapeDrawer(game.batch, region);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        gameScreenTitle.setColor(Color.BLACK);
        gameScreenTitle.draw(game.batch, "Game Screen", 206, 453);
        //polygonSpriteBatch.draw(texture, 50, 50);
        shapeDrawer.setColor(Color.BLUE);
        shapeDrawer.filledCircle(circleX, circleY, 100);

        game.batch.end();


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
        generator.dispose();
        gameScreenTitle.dispose();
        //polygonSpriteBatch.dispose();
        texture.dispose();
    }
}
