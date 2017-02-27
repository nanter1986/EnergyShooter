package com.nanter1986.energyshooter.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.Elementaltypes;
import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 26/2/2017.
 */

public class GreenFast extends Enemy {



    public GreenFast(int x, int spaceshipY,int screenW,int screenH) {
        energyBonus=5;
        this.widthFactor=screenW/10;
        this.heightFactor=screenH/30;
        this.x = x-widthFactor;
        this.y = spaceshipY+screenH+heightFactor;
        this.health=10;
        this.currentTexture=green;
        this.screenWidth=screenW;
        this.screenHeight=screenH;
        this.touchDamageGiven=2;
        this.touchDamageTaken=10;
        this.laserFrequency=50;
        doneColliding=false;
    }

    @Override
    public void updatePosition(SpriteBatch b, PlayerShip ship) {
        if(health>0) {
            y = y - widthFactor/40;
            b.draw(currentTexture, x, y, widthFactor, widthFactor);



        }else if(exploded==false){
            explode(b);
            doneColliding=true;
        }
    }

    @Override
    public Elementaltypes whatType() {
        return Elementaltypes.FIRE;
    }

    @Override
    public LaserOfEnemy laserMaker(float spX) {
        return new LaserEnemyGreen(x + widthFactor/2, y, spX, screenWidth, screenHeight);
    }

    @Override
    public boolean done() {
        return doneColliding;
    }
}
