package com.nanter1986.energyshooter.Enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.CollisionChecker;

/**
 * Created by user on 15/2/2017.
 */

public class EnemyLaserStar extends LaserOfEnemy {
    public EnemyLaserStar(int x, int y, int spaceshipX, int screenW, int screenH) {
        super(x, y, spaceshipX, screenW, screenH);
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
    public int checkCollisionWithPlayer(com.nanter1986.energyshooter.playerships.PlayerShip ship) {
        int damage=0;
        if(CollisionChecker.checkCollision(this,ship)){
            damage=3;
            used=true;
        }
        if(damage<=ship.shield){
            damage=1;
        }
        return damage;
    }
}
