package com.nanter1986.energyshooter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by user on 15/2/2017.
 */

public class LaserEnemyGreen extends LaserOfEnemy {
    public LaserEnemyGreen(int x, int y, int spaceshipX, int screenW, int screenH) {
        super(x, y, spaceshipX, screenW, screenH);
    }

    @Override
    public void updatePosition(SpriteBatch b) {
        y = y - widthFactor/2;

        b.draw(green, x, y, widthFactor, heightFactor);
    }

    @Override
    public int checkCollisionWithPlayer(PlayerShip ship) {
        int damage=0;
        if(CollisionChecker.checkCollision(this,ship)){
            damage=6;
            used=true;
        }
        return damage;
    }
}
