package com.nanter1986.energyshooter.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.energyshooter.AdsController;
import com.nanter1986.energyshooter.Buttons.EquipProceed;
import com.nanter1986.energyshooter.Buttons.TouchableButtons;
import com.nanter1986.energyshooter.DisplayToolkit;
import com.nanter1986.energyshooter.EnergyShooter;

/**
 * Created by user on 9/3/2017.
 */

public class HelpScreen implements Screen {
    EnergyShooter game;
    DisplayToolkit tool;
    TouchableButtons proceed;
    private AdsController adsController;
    final Texture helpImage=new Texture(Gdx.files.internal("help.png"));

    public HelpScreen(EnergyShooter game,AdsController adsController) {
        this.adsController=adsController;
        this.game = game;
        this.tool=new DisplayToolkit(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        proceed=new EquipProceed(tool);
        tool.camera.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (proceed.isButtonTouched()) {
            game.setScreen(new MainMenu(game,adsController));
        }
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        tool.batch.draw(helpImage,0,0,tool.scW,tool.scH);
        tool.batch.draw(proceed.texture,proceed.buttonX,proceed.buttonY,proceed.buttonW,proceed.buttonH);
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
