package com.nanter1986.energyshooter.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.nanter1986.energyshooter.AdsController;
import com.nanter1986.energyshooter.DisplayToolkit;
import com.nanter1986.energyshooter.EnergyShooter;

/**
 * Created by user on 5/3/2017.
 */

public class EndScreen implements Screen {

    EnergyShooter game;
    DisplayToolkit tool;
    private AdsController adsController;

    public EndScreen(EnergyShooter game,AdsController adsController) {
        this.adsController=adsController;
        this.game = game;
        this.tool = new DisplayToolkit(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        tool.camera.update();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            game.setScreen(new MainMenu(game,adsController));
           // backgroundMusic.dispose();
        }
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        tool.font.draw(tool.batch,"The End", tool.scW*3/10,tool.scH/2);
        tool.batch.end();
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

    }
}
