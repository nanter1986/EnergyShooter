package com.nanter1986.energyshooter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 15/2/2017.
 */

public class EnemySmallBlue extends Enemy {
    public EnemySmallBlue(int x, int spaceshipY,int screenW,int screenH) {
        energyBonus=2;
        this.widthFactor=screenW/30;
        this.heightFactor=screenH/5;
        this.x = x;
        this.y = spaceshipY+screenH+heightFactor;
        this.health=5;
        this.currentTexture=blue;
        this.screenWidth=screenW;
        this.screenHeight=screenH;

    }

    @Override
    public void updatePosition(SpriteBatch b,PlayerShip ship) {
        if(health>0) {
            y = y - widthFactor/20;
            b.draw(currentTexture, x, y, widthFactor, widthFactor*2);

        }else if(exploded==false){
            explode(b);
        }
    }

    @Override
    public LaserOfEnemy laserMaker(int spX) {

            return new EnemyLaserStar(x + 20, y, spX, screenWidth, screenHeight);

    }

    @Override
    public int checkCollisionWithPlayer(PlayerShip ship) {
        int damage=0;
        if(CollisionChecker.checkCollision(this,ship)){
            health=0;
            damage=2;
        }
        return damage;
    }


}
