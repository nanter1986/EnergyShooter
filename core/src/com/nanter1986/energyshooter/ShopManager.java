package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.shop.ShopItem;

import java.util.ArrayList;

/**
 * Created by user on 22/2/2017.
 */

public class ShopManager {
    static Preferences prefs = Gdx.app.getPreferences("Shooter");
    static int itemIterator=0;
    static int itemRotator=0;
    static boolean listMade=false;
    static ArrayList<ShopItem>itemList=new ArrayList<ShopItem>();
    public static ShopItem shopManage(){
        if(listMade==false){
            makeItemList();
            listMade=true;
        }
        itemRotator++;
        if(itemRotator>360){
            itemRotator=0;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            itemIterator--;
            if(itemIterator<0){
                itemIterator=itemList.size()-1;
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            itemIterator++;
            if(itemIterator>=itemList.size()){
                itemIterator=0;
            }
        }


        return itemList.get(itemIterator);
    }



    public static void makeItemList(){
        ArrayList<ShopItem>temp=new ArrayList<ShopItem>();
        temp.add(new ShopItem("Bullet",500,new Texture(Gdx.files.internal("F5S4.png")),prefs));
        temp.add(new ShopItem("Gold Saucer",1000,new Texture(Gdx.files.internal("spaceshipGood.png")),prefs));
        temp.add(new ShopItem("Bat",2000,new Texture(Gdx.files.internal("coolBlue.png")),prefs));
        temp.add(new ShopItem("Sinister",5000,new Texture(Gdx.files.internal("sinister.png")),prefs));

        for (ShopItem s:temp){
            if(s.haveIt==false){
                itemList.add(s);
            }
        }
    }
}
