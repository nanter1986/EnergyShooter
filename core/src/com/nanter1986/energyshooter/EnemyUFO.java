package com.nanter1986.energyshooter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    public void updatePosition(SpriteBatch b,PlayerShip ship) {
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

    @Override
    public int checkCollisionWithPlayer(PlayerShip ship) {
        int damage=0;
        if(CollisionChecker.checkCollision(this,ship)){
            health-=5;
            damage=10;
        }
        return damage;
    }
}
