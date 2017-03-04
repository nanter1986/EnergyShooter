package com.nanter1986.energyshooter.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nanter1986.energyshooter.Buttons.ShopBuy;
import com.nanter1986.energyshooter.Buttons.ShopExit;
import com.nanter1986.energyshooter.Buttons.ShopLeft;
import com.nanter1986.energyshooter.Buttons.ShopRight;
import com.nanter1986.energyshooter.Buttons.TouchableButtons;
import com.nanter1986.energyshooter.DisplayToolkit;
import com.nanter1986.energyshooter.EnergyShooter;
import com.nanter1986.energyshooter.SetOfScreens;
import com.nanter1986.energyshooter.shop.ShopItem;

import java.util.ArrayList;

/**
 * Created by user on 27/2/2017.
 */

public class ChoosePlane implements Screen{
    static Preferences prefs = Gdx.app.getPreferences("Shooter");

    EnergyShooter game;
    DisplayToolkit tool;


    public static int iterator=0;
    public static int rotator=0;
    public static ArrayList<ShopItem> owned=new ArrayList<ShopItem>();
    public TouchableButtons l;
    public TouchableButtons r;
    public TouchableButtons c;

    public ChoosePlane(EnergyShooter game) {
        this.game = game;
        this.tool=new DisplayToolkit(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        l=new ShopLeft(tool);
        r=new ShopRight(tool);
        c=new ShopExit(tool);

        makeList(prefs);
        tool.camera.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        rotator++;
        if(rotator>360){
            rotator=0;
        }
        if (r.isButtonTouched()) {
            iterator++;
            if(iterator>=owned.size()){
                iterator=0;
            }
        }else if (l.isButtonTouched()) {
            iterator--;
            if (iterator < 0) {
                iterator = owned.size() - 1;
            }
        }else if (c.isButtonTouched()) {
            prefs.putString("choosenPlane",owned.get(iterator).name);
            prefs.flush();
            game.setScreen(new EquipScreen(game));

        }

        TextureRegion tr=new TextureRegion(owned.get(iterator).texture);
        Gdx.gl.glClearColor(0,0, 0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.begin();
        tool.batch.draw(tr,tool.scW/2-100,tool.scW/2-100,100,100,
                200,200,1,01,rotator);
        tool.batch.draw(r.texture,r.buttonX,r.buttonY,r.buttonW,r.buttonH);
        tool.batch.draw(l.texture,l.buttonX,l.buttonY,l.buttonW,l.buttonH);
        tool.batch.draw(c.texture,c.buttonX,c.buttonY,c.buttonW,c.buttonH);
        tool.font.draw(tool.batch,"CHOOSE SPACECRAFT", tool.scW*3/10,tool.scH*9/10);

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

    public static void makeList(Preferences prefs){
        iterator=0;
        rotator=0;
        owned.clear();
        owned.add(new ShopItem("f5s1",0,new Texture(Gdx.files.internal("F5S1.png")),prefs));
        /*owned.add(new ShopItem("Bullet",500,new Texture(Gdx.files.internal("F5S4.png")),prefs));
        owned.add(new ShopItem("Bat",1000,new Texture(Gdx.files.internal("coolBlue.png")),prefs));
        owned.add(new ShopItem("Demon",2000,new Texture(Gdx.files.internal("wingship.png")),prefs));
        owned.add(new ShopItem("Sinister",5000,new Texture(Gdx.files.internal("sinister.png")),prefs));*/
        if(prefs.getBoolean("Bullet")){
            owned.add(new ShopItem("Bullet",500,new Texture(Gdx.files.internal("F5S4.png")),prefs));
        }
        if(prefs.getBoolean("Bat")){
            owned.add(new ShopItem("Bat",2000,new Texture(Gdx.files.internal("coolBlue.png")),prefs));
        }
        if(prefs.getBoolean("Demon")){
            owned.add(new ShopItem("Demon",1000,new Texture(Gdx.files.internal("wingship.png")),prefs));

        }
        if(prefs.getBoolean("Sinister")){
            owned.add(new ShopItem("Sinister",5000,new Texture(Gdx.files.internal("sinister.png")),prefs));
        }

        if(prefs.getBoolean("Manta")){
            owned.add(new ShopItem("Manta",5000,new Texture(Gdx.files.internal("manta.png")),prefs));
        }

        if(prefs.getBoolean("PathFinder")){
            owned.add(new ShopItem("PathFinder",100,new Texture(Gdx.files.internal("pathfinder.png")),prefs));
        }





    }
}
