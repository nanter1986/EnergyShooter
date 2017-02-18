package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by user on 12/2/2017.
 */

public abstract class BackGround {
    public static final Texture texture1=new Texture(Gdx.files.internal("m1.png"));
    public static final Texture texture2=new Texture(Gdx.files.internal("m2.png"));
    public static final Texture texture3=new Texture(Gdx.files.internal("m3.png"));
    public static final Texture cloud=new Texture(Gdx.files.internal("cloud.png"));

    int x;
    int y;
    int yRelativeToShip;
    int w;
    int h;
    int speed;
    int whichTexture;
    boolean passedShip;


    public BackGround(PlayerShip ship,int screeW,int screenH) {
        this.passedShip=false;


        Random randomX = new Random();
        Random randomY = new Random();
        Random randomW = new Random();

        x = randomX.nextInt(screeW);
        y =ship.spaceshipY+screenH;
        yRelativeToShip = y-ship.spaceshipY;
        w = screeW/((randomW.nextInt(10))+1);
        h = w;

        this.speed = w/100;
        //Gdx.app.log("speed",""+speed+" "+w+" ");

    }

    public abstract void updatePosition(SpriteBatch b,int spY);

}
