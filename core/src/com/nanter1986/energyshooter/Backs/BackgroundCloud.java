package com.nanter1986.energyshooter.Backs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by user on 18/2/2017.
 */

public class BackgroundCloud extends BackGround {
    public BackgroundCloud(com.nanter1986.energyshooter.playerships.PlayerShip ship, int screeW, int screenH) {
        super(ship, screeW, screenH);
    }

    @Override
    public void updatePosition(SpriteBatch b, int spY) {
        y=y-speed;
        int tempY= y+spY;
        if(y+h<0){
            passedShip=true;
        }
        if(passedShip==false) {
            b.draw(cloud, x, tempY, w, h);
        }
    }
}
