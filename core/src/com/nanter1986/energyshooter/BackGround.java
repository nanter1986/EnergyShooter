package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by user on 12/2/2017.
 */

public class BackGround {
    public static final Texture texture1=new Texture(Gdx.files.internal("meteors/meteor1.png"));
    public static final Texture texture2=new Texture(Gdx.files.internal("meteors/meteor2.png"));
    public static final Texture texture3=new Texture(Gdx.files.internal("meteors/meteor3.png"));
    public static final Texture texture4=new Texture(Gdx.files.internal("meteors/meteor4.png"));
    int x;
    int y;
    int yRelativeToShip;
    int w;
    int h;
    int speed;
    int whichTexture;
    boolean passedShip;


    public BackGround(int spaceshipX, int spaceshipY) {
        this.passedShip=false;
        Random r = new Random();
        int which = r.nextInt(4);
        if (which == 0) {
            whichTexture = 1;
        } else if (which == 1) {
            whichTexture = 2;
        } else if (which == 2) {
            whichTexture = 3;
        } else if (which == 3) {
            whichTexture = 4;
        }

        Random randomX = new Random();
        Random randomY = new Random();
        Random randomW = new Random();

        x = spaceshipX + (randomX.nextInt(800) - 400);
        y =600;
        yRelativeToShip = y-spaceshipY;
        w = 100*(randomW.nextInt(3)+1);
        h = w;

        this.speed = w/100;
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
            }else if(whichTexture==4){
                b.draw(texture4, x, tempY, w, h);
            }

        }
    }
}
