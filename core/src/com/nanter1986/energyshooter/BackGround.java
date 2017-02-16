package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by user on 12/2/2017.
 */

public class BackGround {
    public static final Texture texture1=new Texture(Gdx.files.internal("m1.png"));
    public static final Texture texture2=new Texture(Gdx.files.internal("m2.png"));
    public static final Texture texture3=new Texture(Gdx.files.internal("m3.png"));

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
        Random r = new Random();
        int which = r.nextInt(3);
        if (which == 0) {
            whichTexture = 1;
        } else if (which == 1) {
            whichTexture = 2;
        } else if (which == 2) {
            whichTexture = 3;
        }

        Random randomX = new Random();
        Random randomY = new Random();
        Random randomW = new Random();

        x = randomX.nextInt(screeW);
        y =ship.spaceshipY+screenH;
        yRelativeToShip = y-ship.spaceshipY;
        w = screeW/((randomW.nextInt(10))+1);
        h = w;

        this.speed = w/100;
        Gdx.app.log("speed",""+speed+" "+w+" ");

    }

    public void updatePosition(SpriteBatch b,int spY){
            y=y-speed;
         int tempY= y+spY;
        if(y+h<0){
            passedShip=true;
        }

        if(passedShip==false) {
            if(whichTexture==1){

                b.draw(texture1, x, tempY, w, h);
            }else if(whichTexture==2){

                b.draw(texture2, x, tempY, w, h);
            }else if(whichTexture==3){

                b.draw(texture3, x, tempY, w, h);
            }

        }
    }
}
