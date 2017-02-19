package com.nanter1986.energyshooter.Enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.CollisionChecker;

/**
 * Created by user on 15/2/2017.
 */

public class EnemyBad extends Enemy {

    String xDirection;
    public EnemyBad(int x, int spaceshipY,int screenW,int screenH) {
        energyBonus=5;
        this.widthFactor=screenW/10;
        this.heightFactor=screenH/5;
        this.x = x;
        this.y = spaceshipY+screenH+heightFactor;
        this.health=50;
        this.currentTexture=badass;
        this.screenWidth=screenW;
        this.screenHeight=screenH;
        this.xDirection="left";
    }

    @Override
    public void updatePosition(SpriteBatch b, com.nanter1986.energyshooter.playerships.PlayerShip ship) {
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




}
