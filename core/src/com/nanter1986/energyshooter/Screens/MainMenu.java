package com.nanter1986.energyshooter.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.energyshooter.AdsController;
import com.nanter1986.energyshooter.Buttons.MenuPlay;
import com.nanter1986.energyshooter.Buttons.MenuSettings;
import com.nanter1986.energyshooter.Buttons.SaveManager;
import com.nanter1986.energyshooter.Buttons.SaveNo;
import com.nanter1986.energyshooter.Buttons.SaveYes;
import com.nanter1986.energyshooter.Buttons.TouchableButtons;
import com.nanter1986.energyshooter.DisplayToolkit;
import com.nanter1986.energyshooter.EnergyShooter;
import com.nanter1986.energyshooter.SetOfScreens;

/**
 * Created by user on 27/2/2017.
 */

public class MainMenu implements Screen{
    public static TouchableButtons playB;
    public static TouchableButtons settingsB;
    public static TouchableButtons saveB;
    public static TouchableButtons saveYes;
    public static TouchableButtons saveNo;
    public static boolean saveManage=false;
    public static Texture textureYes=new Texture(Gdx.files.internal("menuyesbutton.png"));
    public static Texture texture=new Texture(Gdx.files.internal("menubutton.png"));
    public static Texture textureNo=new Texture(Gdx.files.internal("menunobutton.png"));
    public static Texture logo=new Texture(Gdx.files.internal("logo.png"));
    EnergyShooter game;
    DisplayToolkit tool;
    Music backgroundMusic;
    private AdsController adsController;

    public MainMenu(EnergyShooter game,AdsController adsController) {
        this.adsController=adsController;
        this.game = game;
        this.tool=new DisplayToolkit(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
        makeButtons(tool);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("europe.ogg"));
        backgroundMusic.play();
    }

    @Override
    public void render(float delta) {

        if(playB.isButtonTouched()){
            game.setScreen(new Shop(game,adsController));
            backgroundMusic.dispose();
        }
        if(settingsB.isButtonTouched()){
            game.setScreen(new HelpScreen(game,adsController));
            backgroundMusic.dispose();
        }
        if(saveB.isButtonTouched()){
            saveManage=true;
        }
        if(saveYes.isButtonTouched() && saveManage==true){
            tool.prefs.clear();
            tool.prefs.flush();
            Gdx.app.log("mon",""+tool.prefs.getInteger("money"));
            saveManage=false;

        }
        if(saveNo.isButtonTouched() && saveManage==true){
            saveManage=false;
        }

        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.begin();
        tool.batch.draw(playB.texture,playB.buttonX,playB.buttonY,playB.buttonW,playB.buttonH);
        tool.batch.draw(settingsB.texture,settingsB.buttonX,settingsB.buttonY,settingsB.buttonW,settingsB.buttonH);
        tool.batch.draw(saveB.texture,saveB.buttonX,saveB.buttonY,saveB.buttonW,saveB.buttonH);
        tool.batch.draw(logo,0,tool.scH*7/10,tool.scW,tool.scH*3/10);
        if(saveManage==true){
            saveNo.texture=textureNo;
            saveYes.texture=textureYes;
        }else{
            saveNo.texture=texture;
            saveYes.texture=texture;
        }
        tool.batch.draw(saveYes.texture,saveYes.buttonX,saveYes.buttonY,saveYes.buttonW,saveYes.buttonH);
        tool.batch.draw(saveNo.texture,saveNo.buttonX,saveNo.buttonY,saveNo.buttonW,saveNo.buttonH);

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

    public static void makeButtons(DisplayToolkit tool){
        playB=new MenuPlay(tool);
        settingsB=new MenuSettings(tool);
        saveB=new SaveManager(tool);
        saveYes=new SaveYes(tool);
        saveNo=new SaveNo(tool);

    }
}
