package com.nanter1986.energyshooter.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.nanter1986.energyshooter.Artifacts.Artifact;
import com.nanter1986.energyshooter.Artifacts.BasicShield;
import com.nanter1986.energyshooter.Artifacts.Damager;
import com.nanter1986.energyshooter.Artifacts.DarknessKiller;
import com.nanter1986.energyshooter.Artifacts.EnergyBoosterOne;
import com.nanter1986.energyshooter.Artifacts.FireKiller;
import com.nanter1986.energyshooter.Artifacts.IceKiller;
import com.nanter1986.energyshooter.Artifacts.LightsDown;
import com.nanter1986.energyshooter.Artifacts.Speeder;
import com.nanter1986.energyshooter.Buttons.EquipProceed;
import com.nanter1986.energyshooter.Buttons.EquipSlotLeft1;
import com.nanter1986.energyshooter.Buttons.EquipSlotLeft2;
import com.nanter1986.energyshooter.Buttons.EquipSlotRight1;
import com.nanter1986.energyshooter.Buttons.EquipSlotRight2;
import com.nanter1986.energyshooter.Buttons.TouchableButtons;
import com.nanter1986.energyshooter.DisplayToolkit;
import com.nanter1986.energyshooter.EnergyShooter;

import java.util.ArrayList;

/**
 * Created by user on 27/2/2017.
 */

public class EquipScreen implements Screen{
    static Preferences prefs = Gdx.app.getPreferences("Shooter");

    EnergyShooter game;
    DisplayToolkit tool;

    public static int whichSlot;
    private static Artifact first;
    private static Artifact second;
    private static int it1 = 0;
    private static int it2 = 1;
    public static TouchableButtons left1;
    public static TouchableButtons left2;
    public static TouchableButtons right1;
    public static TouchableButtons right2;
    public static TouchableButtons proceed;
    public static boolean madeButtons = false;
    ArrayList<Artifact>artifacts=new ArrayList<Artifact>();

    public EquipScreen(EnergyShooter game) {
        this.game = game;
        this.tool=new DisplayToolkit(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        left1 = new EquipSlotLeft1(tool);
        left2 = new EquipSlotLeft2(tool);
        right1 = new EquipSlotRight1(tool);
        right2 = new EquipSlotRight2(tool);
        proceed=new EquipProceed(tool);
        artifacts=readArtifacts();
        resetSelectedArtifacts();
        tool.camera.update();
    }

    private void resetSelectedArtifacts() {
        for(Artifact a:artifacts){
            prefs.putBoolean(a.name,false);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ArrayList<Artifact> readyArt = new ArrayList<Artifact>();
        if (artifacts.size() == 0) {

        } else {

            if (left1.isButtonTouched()) {
                it1--;
                if (it1 < 0) {
                    it1 = artifacts.size() - 1;
                }
                first = artifacts.get(it1);

            } else if (right1.isButtonTouched()) {
                it1++;
                if (it1 >= artifacts.size()) {
                    it1 = 0;
                }

            } else if (left2.isButtonTouched()) {
                it2--;
                if (it2 < 0) {
                    it2 = artifacts.size() - 1;
                }
                second = artifacts.get(it2);
            } else if (right2.isButtonTouched()) {
                it2++;
                if (it2 >= artifacts.size()) {
                    it2 = 0;
                }

            }else if (proceed.isButtonTouched()) {
                prefs.putBoolean(artifacts.get(it1).name,true);
                prefs.putBoolean(artifacts.get(it2).name,true);
                game.setScreen(new GameplayScreen(game));

            }


        }

        first = artifacts.get(it1);
        second = artifacts.get(it2);

        readyArt.add(first);
        readyArt.add(second);

        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        tool.batch.draw(left1.texture,left1.buttonX,left1.buttonY,left1.buttonW,left1.buttonH);
        tool.batch.draw(left2.texture,left2.buttonX,left2.buttonY,left2.buttonW,left2.buttonH);
        tool.batch.draw(right1.texture,right1.buttonX,right1.buttonY,right1.buttonW,right1.buttonH);
        tool.batch.draw(right2.texture,right2.buttonX,right2.buttonY,right2.buttonW,right2.buttonH);
        tool.batch.draw(proceed.texture,proceed.buttonX,proceed.buttonY,proceed.buttonW,proceed.buttonH);
        tool.font.draw(tool.batch,first.name,left1.buttonX+left1.buttonW*2,left1.buttonY+left1.buttonH/2);
        tool.font.draw(tool.batch,second.name,left2.buttonX+left2.buttonW*2,left2.buttonY+left2.buttonH/2);
        tool.font.draw(tool.batch,"CHOOSE EQUIPMENT", tool.scW*3/10,tool.scH*19/20);
        tool.font.draw(tool.batch,first.description, left1.buttonX+left1.buttonW,left1.buttonY-left1.buttonH);
        tool.font.draw(tool.batch,second.description, left2.buttonX+left2.buttonW,left2.buttonY-left2.buttonH);
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

    public static ArrayList<Artifact> readArtifacts(){
        ArrayList<Artifact>art=new ArrayList<Artifact>();
        String selectedPlane=prefs.getString("choosenPlane");

            art.add(new BasicShield());
            art.add(new EnergyBoosterOne());
            art.add(new Speeder());
            art.add(new Damager());
            art.add(new FireKiller());
            art.add(new IceKiller());
            art.add(new DarknessKiller());
            art.add(new LightsDown());


        return art;
    }
}
