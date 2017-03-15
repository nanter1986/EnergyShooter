package com.nanter1986.energyshooter.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nanter1986.energyshooter.AdsController;
import com.nanter1986.energyshooter.Buttons.ShopBuy;
import com.nanter1986.energyshooter.Buttons.ShopExit;
import com.nanter1986.energyshooter.Buttons.ShopLeft;
import com.nanter1986.energyshooter.Buttons.ShopRight;
import com.nanter1986.energyshooter.Buttons.TouchableButtons;
import com.nanter1986.energyshooter.DisplayToolkit;
import com.nanter1986.energyshooter.EnergyShooter;
import com.nanter1986.energyshooter.GarageManager;
import com.nanter1986.energyshooter.SetOfScreens;
import com.nanter1986.energyshooter.ShopManager;
import com.nanter1986.energyshooter.SpaceshipChooseHelper;
import com.nanter1986.energyshooter.shop.ShopItem;

import java.util.ArrayList;

/**
 * Created by user on 27/2/2017.
 */

public class Shop implements Screen {

    EnergyShooter game;
    DisplayToolkit tool;

    static Preferences prefs = Gdx.app.getPreferences("Shooter");
    static int itemIterator=0;
    static int itemRotator=0;
    static boolean listMade=false;
    static int selectedItemPrice=0;
    static ArrayList<ShopItem> itemList=new ArrayList<ShopItem>();
    public TouchableButtons l;
    public TouchableButtons r;
    public TouchableButtons b;
    public TouchableButtons e;
    int money;
    private AdsController adsController;


    public Shop(EnergyShooter game,AdsController adsController) {
        this.adsController=adsController;
        this.game = game;
        this.tool=new DisplayToolkit(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        l=new ShopLeft(tool);
        r=new ShopRight(tool);
        b=new ShopBuy(tool);
        e=new ShopExit(tool);
        makeItemList();
        money=prefs.getInteger("money");
        tool.camera.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
        } else if (r.isButtonTouched()) {
            itemIterator++;
            if(itemIterator>=itemList.size()){
                itemIterator=0;
            }
        }else if (b.isButtonTouched() && money>=itemList.get(itemIterator).price) {
            prefs.putBoolean(itemList.get(itemIterator).name,true);
            money=(int)(money-itemList.get(itemIterator).price);
            prefs.putInteger("money",money);
            prefs.flush();
            selectedItemPrice=(int)itemList.get(itemIterator).price;
            makeItemList();
        }else if (l.isButtonTouched()) {
            itemIterator--;
            if (itemIterator < 0) {
                itemIterator = itemList.size() - 1;
            }
        }else if (e.isButtonTouched()) {
           game.setScreen(new ChoosePlane(game,adsController));
        }



        money=money-ShopManager.boughtSomething();
        tool.prefs.putInteger("money",money);
        tool.prefs.flush();


        TextureRegion tr=new TextureRegion(itemList.get(itemIterator).texture);
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        tool.batch.draw(tr,tool.scW/2-100,tool.scH/2-100,100,100,
                200,200,1,01,itemRotator);
        tool.font.draw(tool.batch,"Money:"+ money, 0, tool.scH*3/8);
        tool.font.draw(tool.batch,"Name:"+ itemList.get(itemIterator).name, 0, tool.scH*2/8);
        tool.font.draw(tool.batch,"Price:"+ itemList.get(itemIterator).price, 0, tool.scH*1/8);
        tool.batch.draw(r.texture,r.buttonX,r.buttonY,r.buttonW,r.buttonH);
        tool.batch.draw(l.texture,l.buttonX,l.buttonY,l.buttonW,l.buttonH);
        tool.batch.draw(e.texture,e.buttonX,e.buttonY,e.buttonW,e.buttonH);
        tool.font.draw(tool.batch,"SHOP", tool.scW*3/10,tool.scH*9/10);
        if(itemList.get(itemIterator).price<=money){
            tool.batch.draw(b.texture,b.buttonX,b.buttonY,b.buttonW,b.buttonH);
        }

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

    public static void makeItemList(){
        ArrayList<ShopItem>temp=new ArrayList<ShopItem>();
        temp.add(new ShopItem("Bullet",500,new Texture(Gdx.files.internal("F5S4.png")),prefs));
        temp.add(new ShopItem("Demon",1000,new Texture(Gdx.files.internal("wingship.png")),prefs));
        temp.add(new ShopItem("Bat",2000,new Texture(Gdx.files.internal("coolBlue.png")),prefs));
        temp.add(new ShopItem("Sinister",3000,new Texture(Gdx.files.internal("sinister.png")),prefs));
        temp.add(new ShopItem("Manta",5000,new Texture(Gdx.files.internal("manta.png")),prefs));
        temp.add(new ShopItem("PathFinder",10000,new Texture(Gdx.files.internal("pathfinder.png")),prefs));
        itemList.clear();
        itemIterator=0;
        itemRotator=0;

        for (ShopItem s:temp){
            if(s.haveIt==false && prefs.getInteger(s.name)==0){
                itemList.add(s);
            }
        }

    }


}
