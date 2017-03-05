package com.nanter1986.energyshooter.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.Elementaltypes;
import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 5/3/2017.
 */

public class FinalBoss extends Enemy {

    String xDirection;
    public FinalBoss(int x, float spaceshipY,int screenW,int screenH) {
        energyBonus=10;
        this.widthFactor=screenW/4;
        this.heightFactor=screenH/4;
        this.x = x-widthFactor;
        this.y = spaceshipY+screenH+heightFactor;
        this.health=1000;
        this.currentTexture=boss;
        this.screenWidth=screenW;
        this.screenHeight=screenH;
        this.xDirection="left";
        this.touchDamageGiven=10;
        this.touchDamageTaken=5;
        this.laserFrequency=100;
        doneColliding=false;

    }

    @Override
    public void updatePosition(SpriteBatch b, com.nanter1986.energyshooter.playerships.PlayerShip ship) {
        float badCenter=x+width()/2;
        float goodCenter=ship.positionX()+ship.spaceshipW/2;
        if(health>0) {
            if(y>screenHeight-widthFactor*2){
                y = y - widthFactor/50;
            }else{
                if(badCenter>goodCenter){
                    x = x - widthFactor/30;
                    if(this.x<5){
                        this.x=5;
                    }
                }else{
                    x = x + widthFactor/30;
                    if(this.x>screenWidth-5){
                        this.x=screenWidth-5;
                    }
                }
            }



            b.draw(currentTexture, x, y, widthFactor, widthFactor*2);

        }else if(exploded==false){
            explode(b);
            doneColliding=true;
        }
    }

    @Override
    public Elementaltypes whatType() {
        return Elementaltypes.LIGHT;
    }

    @Override
    public LaserOfEnemy laserMaker(float spX) {
        return new LaserFinalBoss(this, spX, screenWidth, screenHeight);
    }


    @Override
    public boolean done() {
        return doneColliding;
    }
}
