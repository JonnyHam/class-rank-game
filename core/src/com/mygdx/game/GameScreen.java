package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.ArrayList;

public class GameScreen implements Screen {
    OrthographicCamera camera;
    final ClassRankGame game;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont gameScreenTitle;
    private Texture texture;
    private TextureRegion region;
    private ShapeDrawer rectangleOutline;
    private ShapeDrawer rectangle;
    private ArrayList<Integer[]> rectPositions;

    public GameScreen(final ClassRankGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        gameScreenTitle = generator.generateFont(parameter);

        texture = new Texture(Gdx.files.internal("white_pixel.png"));
        region = new TextureRegion(texture);
        rectangle = new ShapeDrawer(game.batch, region);
        rectangleOutline = new ShapeDrawer(game.batch, region);

        rectPositions = new ArrayList<Integer[]>();

        for (int i = 0; i < 4; i++) {
            rectPositions.add(new Integer[]{20+(i*155), 20});
        }
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
        gameScreenTitle.setColor(Color.BLACK);
        gameScreenTitle.draw(game.batch, "Game Screen", 206, 453);

        for (int i = 0; i < rectPositions.size(); i++) {
            rectangleOutline.setColor(Color.BLACK);
            rectangle.setColor(Color.GOLDENROD);
            rectangle.filledRectangle(rectPositions.get(i)[0], rectPositions.get(i)[1], 150, 75);
            rectangleOutline.rectangle(rectPositions.get(i)[0], rectPositions.get(i)[1], 150, 75, 3);
        }

        game.batch.end();

        for (int i = 0; i < rectPositions.size(); i++) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
                rectPositions.set(i, new Integer[]{20, 100+(i*80)});
            }
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
        generator.dispose();
        gameScreenTitle.dispose();
        texture.dispose();
    }
}
