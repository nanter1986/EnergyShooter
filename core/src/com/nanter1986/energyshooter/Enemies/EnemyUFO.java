package com.nanter1986.energyshooter.Enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.CollisionChecker;

/**
 * Created by user on 15/2/2017.
 */

public class EnemyUFO extends Enemy {

    String xDirection;
    public EnemyUFO(int x, int spaceshipY,int screenW,int screenH) {
        energyBonus=5;
        this.widthFactor=screenW/20;
        this.heightFactor=screenH/20;
        this.x = x;
        this.y = spaceshipY+screenH+heightFactor;
        this.health=100;
        this.currentTexture=ufo;
        this.screenWidth=screenW;
        this.screenHeight=screenH;
        this.xDirection="left";
    }

    @Override
    public void updatePosition(SpriteBatch b, com.nanter1986.energyshooter.playerships.PlayerShip ship) {
        if(health>0) {
                y-=widthFactor/10;
            b.draw(currentTexture, x, y, widthFactor*2,widthFactor*2);
            }
        else if(exploded==false){
            explode(b);
        }
    }

    @Override
    public LaserOfEnemy laserMaker(int spX) {
        return null;
    }


}
