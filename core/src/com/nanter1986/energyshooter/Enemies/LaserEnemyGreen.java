package com.nanter1986.energyshooter.Enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.CollisionChecker;
import com.nanter1986.energyshooter.Enemies.LaserOfEnemy;

/**
 * Created by user on 15/2/2017.
 */

public class LaserEnemyGreen extends LaserOfEnemy {
    public LaserEnemyGreen(int x, int y, int spaceshipX, int screenW, int screenH) {
        super(x, y, spaceshipX, screenW, screenH);
        this.touchDamageGiven=5;
    }

    @Override
    public void updatePosition(SpriteBatch b) {
        y = y - widthFactor/2;

        b.draw(green, x, y, widthFactor, heightFactor);
    }


}
