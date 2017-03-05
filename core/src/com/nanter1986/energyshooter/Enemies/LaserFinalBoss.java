package com.nanter1986.energyshooter.Enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by user on 5/3/2017.
 */

public class LaserFinalBoss extends LaserOfEnemy {


    public LaserFinalBoss(Enemy enemy, float spaceshipX, int screenW, int screenH) {
        super(enemy, spaceshipX, screenW, screenH);
        Random random=new Random();
        int side=random.nextInt(3);
        if(side==0){
            this.x = enemy.positionX()+(enemy.width()*1/6)-this.widthFactor/2;
        }else if(side==1){
            this.x = enemy.positionX()+(enemy.width()*3/6)-this.widthFactor/2;
        }else{
            this.x = enemy.positionX()+(enemy.width()*5/6)-this.widthFactor/2;
        }

        this.touchDamageGiven=5;
        doneColliding=false;
    }

    @Override
    public void updatePosition(SpriteBatch b) {
        y = y - widthFactor/2;

        b.draw(green, x, y, widthFactor, heightFactor);
    }


    @Override
    public boolean done() {
        return doneColliding;
    }
}
