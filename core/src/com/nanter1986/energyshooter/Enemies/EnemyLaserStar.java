package com.nanter1986.energyshooter.Enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.CollisionChecker;

/**
 * Created by user on 15/2/2017.
 */

public class EnemyLaserStar extends LaserOfEnemy {


    public EnemyLaserStar(Enemy enemy, float spaceshipX, int screenW, int screenH) {
        super(enemy, spaceshipX, screenW, screenH);
        this.touchDamageGiven=1;
        doneColliding=false;
    }

    @Override
    public void updatePosition(SpriteBatch b) {
        if(direction.equals("left")) {
            y = y - widthFactor/2;
            x = x - widthFactor/2;

            b.draw(star, x, y, widthFactor, heightFactor);
        }else{
            y = y - widthFactor/2;
            x = x + widthFactor/2;

            b.draw(star, x, y, widthFactor, heightFactor);
        }
    }

    @Override
    public boolean done() {
        return doneColliding;
    }
}
