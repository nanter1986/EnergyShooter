package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nanter1986.energyshooter.Buttons.TouchableButtons;
import com.nanter1986.energyshooter.shop.ShopItem;

import java.util.ArrayList;

import static javax.swing.text.html.CSS.Attribute.BACKGROUND_COLOR;

/**
 * Created by user on 23/2/2017.
 */

public class GarageManager {
    public static boolean listMade=false;
    public static int iterator=0;
    public static int rotator=0;
    public static boolean decisionMade=false;
    public static ArrayList<ShopItem>owned=new ArrayList<ShopItem>();
    public static void manageGarage(Preferences prefs, SpriteBatch b, BitmapFont font, int scW, int scH,
                                    TouchableButtons r, TouchableButtons l, TouchableButtons c){
        if(listMade==false){
            makeList(prefs);
        }
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
            decisionMade=true;
        }

        TextureRegion tr=new TextureRegion(owned.get(iterator).texture);
        Gdx.gl.glClearColor(0,0, 0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b.begin();
        b.draw(tr,scW/2-100,scH/2-100,100,100,
                200,200,1,01,rotator);
        b.draw(r.texture,r.buttonX,r.buttonY,r.buttonW,r.buttonH);
        b.draw(l.texture,l.buttonX,l.buttonY,l.buttonW,l.buttonH);
        b.draw(c.texture,c.buttonX,c.buttonY,c.buttonW,c.buttonH);

        b.end();


    }

    public static SetOfScreens goToGame(){
        SetOfScreens s=SetOfScreens.SELECT;
        if(decisionMade){
            s=SetOfScreens.EQUIP;
        }
        return s;
    }

    public static void makeList(Preferences prefs){
        if(prefs.getBoolean("Bullet")){
            owned.add(new ShopItem("Bullet",500,new Texture(Gdx.files.internal("F5S4.png")),prefs));
        }
        if(prefs.getBoolean("Gold Saucer")){
            owned.add(new ShopItem("Gold Saucer",1000,new Texture(Gdx.files.internal("spaceshipGood.png")),prefs));

        }
        if(prefs.getBoolean("Sinister")){
            owned.add(new ShopItem("Sinister",5000,new Texture(Gdx.files.internal("sinister.png")),prefs));
        }
        if(prefs.getBoolean("Bat")){
            owned.add(new ShopItem("Bat",2000,new Texture(Gdx.files.internal("coolBlue.png")),prefs));
        }

        owned.add(new ShopItem("f5s1",0,new Texture(Gdx.files.internal("F5S1.png")),prefs));

        listMade=true;
    }
}
