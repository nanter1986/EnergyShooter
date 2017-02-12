package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by user on 11/2/2017.
 */

public class Screen extends ScreenAdapter {

    private static final Color BACKGROUND_COLOR = new Color(0f, 0f,
            0f, 1.0f);

    private static final float SCENE_WIDTH = 640;
    private static final float SCENE_HEIGHT = 480;

    SpriteBatch batch;
    private OrthographicCamera camera;

    private Texture spaceship;
    private Texture level;
    private Texture laser;
    private int spaceshipX;
    private int spaceshipY;
    private int laserY;

    int width;
    int height;
    float originX;
    float originY;
    private boolean cooledDown=true;

    @Override
    public void render(float delta) {
        checkForShooting();
        updatePosition();
        updateCamera();
        camera.update();

        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(level, 0, 0, 480 , 4000);
        batch.draw(spaceship, spaceshipX, spaceshipY, 50f, 100f);
        batch.draw(laser, spaceshipX+25, spaceshipY+laserY, 25f , 50f);
        batch.end();
    }

    private void checkForShooting() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && cooledDown==true){
            laserY=spaceshipY+100;
            cooledDown=false;
        }
    }

    private void updateCamera() {

        if(Gdx.input.isKeyPressed(Input.Keys.UP) && camera.position.y<3700){
            camera.translate(0,5,0);
            spaceshipY+=5;
        }

        camera.update();
    }

    private void updatePosition() {
        laserY+=5;
        if(laserY>spaceshipY+640){
            cooledDown=true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            spaceshipX = spaceshipX - 5;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            spaceshipX = spaceshipX + 5;

        }
        if (spaceshipX < 0) {
            spaceshipX = 0;
        }
        if (spaceshipX > 590) {
            spaceshipX = 590;
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(640,480);
        camera.position.set(320,240,0);
        camera.update();
        spaceship = new Texture(Gdx.files.internal("F5S1.png"));
        level = new Texture(Gdx.files.internal("milky.jpeg"));
        laser = new Texture(Gdx.files.internal("laserRed.png"));
        level.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spaceshipX = 300;
        laserY=5000;

    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        batch.dispose();
        spaceship.dispose();
    }
}
