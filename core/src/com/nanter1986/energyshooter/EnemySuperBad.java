package com.nanter1986.energyshooter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by user on 15/2/2017.
 */

public class EnemySuperBad extends Enemy {
    String xDirection;
    public EnemySuperBad(int x, int spaceshipY,int screenW,int screenH) {
        energyBonus=5;
        this.widthFactor=screenW/5;
        this.heightFactor=screenH/5;
        this.x = x;
        this.y = spaceshipY+screenH+heightFactor;
        this.health=100;
        this.currentTexture=badass;
        this.screenWidth=screenW;
        this.screenHeight=screenH;
        this.xDirection="left";
    }

    @Override
    public void updatePosition(SpriteBatch b,PlayerShip ship) {
        int badCenter=x+width()/2;
        int goodCenter=ship.positionX()+ship.spaceshipW/2;
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
        }
    }

    @Override
    public LaserOfEnemy laserMaker(int spX) {
        return new LaserEnemyGreen(x + 20, y, spX, screenWidth, screenHeight);
    }

    @Override
    public int checkCollisionWithPlayer(PlayerShip ship) {
        int damage=0;
        if(CollisionChecker.checkCollision(this,ship)){
            health-=5;
            damage=5;
        }
        return damage;
    }


}
