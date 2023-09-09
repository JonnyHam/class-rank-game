package com.mygdx.drop;

public class CodeBucket {

    //RENDER stuff
    //paused = true;

    // process user input
    //word = "Hello";

            /*
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

            // check if we need to create a new raindrop
            if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

             */

    // move the raindrops, remove any that are beneath the bottom edge of
    // the screen or that hit the bucket. In the latter case we play back
    // a sound effect as well.
            /*
            for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
                Rectangle raindrop = iter.next();
                raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
                if (raindrop.y + 64 < 0) {
                    iter.remove();
                    paused = true;
                    break;
                }
                if (raindrop.overlaps(bucket)) {
                    //wordTime = TimeUtils.nanoTime();
                    //word = "Bruh";
                    dropSound.play();
                    iter.remove();
                    score += 1;
                    word = "" + score;
                }
            }

             */
        /*
        if (TimeUtils.nanoTime() - wordTime > 100000000) {
            word = "Hello";
        }
        */

    //wordTime = TimeUtils.nanoTime();
    //word = "Bruh";

     /*
        font.draw(batch, "Hello World!", 400, 240);
        font.getData().setScale(5f);
         */

    //CREATE stuff
    //for pausing and resuming
    //Gdx.graphics.setContinuousRendering(false);
    //Gdx.graphics.requestRendering();
    //font = new BitmapFont();

    //PRIVATE VARIABLE stuff
    //private BitmapFont font;
}
