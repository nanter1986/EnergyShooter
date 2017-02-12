package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by user on 12/2/2017.
 */

public class BackGround {
    String t;
    int x;
    int y;
    int w;
    int h;
    int speed;


    public BackGround(String t, int x, int y, int w, int h) {
        this.t = t;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = w/100;
    }

    public void updatePosition(SpriteBatch b,int spY){
            y=y-speed;
         int tempY= y+spY;
        Gdx.app.log("speed","speed "+speed);
        Gdx.app.log("speed","y "+tempY);
        b.draw(new Texture(Gdx.files.internal(t)),x,tempY,w,h);
    }
}
