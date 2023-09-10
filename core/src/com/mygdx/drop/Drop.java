package com.mygdx.drop;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Drop extends ApplicationAdapter {
    private Texture dropImage;
    private Texture bucketImage;
    private Sound dropSound;
    private Music rainMusic;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Rectangle bucket;
    private Array<Rectangle> raindrops;
    private long lastDropTime;
    private BitmapFont font;
    private BitmapFont font2;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter2;
    //private String word = "Hello";
    private String word = "0";
    private String endGameWord = "";
    private int score = 0;
    private int highScore = score;
    //private long wordTime;
    @Override
    public void create() {

        // load the text
        generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        font = generator.generateFont(parameter);
        parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 75;
        font2 = generator.generateFont(parameter2);

        // load the images for the droplet and the bucket, 64x64 pixels each
        dropImage = new Texture(Gdx.files.internal("droplet.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        // start the playback of the background music immediately
        rainMusic.setLooping(true);
        rainMusic.play();

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        // create a Rectangle to logically represent the bucket
        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
        bucket.y = 20; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
        bucket.width = 64;
        bucket.height = 64;

        // create the raindrops array and spawn the first raindrop
        raindrops = new Array<Rectangle>();
        spawnRaindrop();
    }

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800-64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    public boolean paused = false;

    @Override
    public void render() {
        if (!paused) {
            try {
                motion(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                motion(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Gdx.graphics.requestRendering();
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        batch.begin();
        batch.draw(bucketImage, bucket.x, bucket.y);
        for (Rectangle raindrop : raindrops) {
            batch.draw(dropImage, raindrop.x, raindrop.y);
        }

        font.draw(batch, word, 400, 240);
        font2.draw(batch, endGameWord, 50, 440);
        batch.end();
    }


    public void motion (boolean isPlaying) throws IOException {
        if (isPlaying) {
            if (Gdx.input.isTouched()) {
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos);
                bucket.x = touchPos.x - 64 / 2;
            }
            if (Gdx.input.isKeyPressed(Keys.LEFT)) bucket.x -= 500 * Gdx.graphics.getDeltaTime();
            if (Gdx.input.isKeyPressed(Keys.RIGHT)) bucket.x += 500 * Gdx.graphics.getDeltaTime();

            // make sure the bucket stays within the screen bounds
            if (bucket.x < 0) bucket.x = 0;
            if (bucket.x > 800 - 64) bucket.x = 800 - 64;

            if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();


            for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
                Rectangle raindrop = iter.next();
                raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
                if (raindrop.y + 64 < 0) {
                    iter.remove();
                    paused = true;
                    //word = "Gameover! Score: " + score;
                    word = "";
                    setHighScore(score);
                    endGameWord = "Game over! Score: " + score + "\n" + "High score: " + highScore;
                    break;
                }
                if (raindrop.overlaps(bucket)) {
                    dropSound.play();
                    iter.remove();
                    score += 1;
                    word = "" + score;
                }
            }
        }
    }

    public void setHighScore(int score) throws IOException {
        // variable declaration
        int ch;
        String highScoreString = "";

        // check if File exists or not
        FileReader fr=null;
        try
        {
            fr = new FileReader("C:\\Users\\Jonathan Kim\\Documents\\class-rank-game\\assets\\High_Score_Tracker");
        }
        catch (FileNotFoundException fe)
        {

        }

        // read from FileReader till the end of file
        while ((ch=fr.read())!=-1)
            highScoreString += (char)ch;
        // close the file
        fr.close();

        highScore = Integer.parseInt(highScoreString, 10);

        if (score > highScore) {
            highScore = score;
            FileWriter fw=new FileWriter("C:\\Users\\Jonathan Kim\\Documents\\class-rank-game\\assets\\High_Score_Tracker");

            // read character wise from string and write
            // into FileWriter
            for (int i = 0; i < Integer.toString(highScore).length(); i++)
                fw.write(Integer.toString(highScore).charAt(i));
            //close the file
            fw.close();
        } else {

        }
    }

    @Override
    public void dispose() {
        // dispose of all the native resources
        dropImage.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
        batch.dispose();
        //font.dispose();
        generator.dispose();
        font.dispose();
    }
}