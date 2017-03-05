package com.nanter1986.energyshooter.Enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.CollisionChecker;
import com.nanter1986.energyshooter.Elementaltypes;

/**
 * Created by user on 15/2/2017.
 */

public class EnemyBad extends Enemy {



    String xDirection;
    public EnemyBad(float x, int spaceshipY,int screenW,int screenH) {
        energyBonus=5;
        this.widthFactor=screenW/10;
        this.heightFactor=screenH/5;
        this.x = x-widthFactor;
        this.y = spaceshipY+screenH+heightFactor;
        this.health=50;
        this.currentTexture=badass;
        this.screenWidth=screenW;
        this.screenHeight=screenH;
        this.xDirection="left";
        this.touchDamageGiven=5;
        this.touchDamageTaken=5;
        this.laserFrequency=200;
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
                    x = x - widthFactor/50;
                    if(this.x<5){
                        this.x=5;
                    }
                }else{
                    x = x + widthFactor/50;
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
        return Elementaltypes.DARKNESS;
    }

    @Override
    public LaserOfEnemy laserMaker(float spX) {
        return new LaserEnemyGreen(this, spX, screenWidth, screenHeight);
    }


    @Override
    public boolean done() {
        return doneColliding;
    }
}
