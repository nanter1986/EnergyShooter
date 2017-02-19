package com.nanter1986.energyshooter.playerships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.Enemies.Enemy;

import java.util.ArrayList;

/**
 * Created by user on 19/2/2017.
 */

public class PlayershipBlue extends PlayerShip {

    private static final Texture spaceshipCoolBlue=new Texture(Gdx.files.internal("coolBlue.png"));

    public PlayershipBlue(int screenW) {
        this.spaceshipHealth = 40;
        this.spaceshipX = screenW / 2;
        this.spaceshipY = 0;
        this.widthFactor = screenW / 10;
        this.died = false;
    }

    @Override
    public void updatePosition(SpriteBatch b) {
        spaceshipW = 2*widthFactor;
        spaceshipH = widthFactor;

        if (spaceshipHealth > 0) {

            b.draw(spaceshipCoolBlue, spaceshipX, spaceshipY, spaceshipW, spaceshipH);

        } else if (explosionAnimationY == 6) {
            died = true;
        } else {
            b.draw(explosion, spaceshipX, spaceshipY, explosionAnimationX * 100, 500 - explosionAnimationY * 100, 100, 100);
            explosionBig.play();
            explosionAnimationX++;
            if (explosionAnimationX == 6) {
                explosionAnimationX = 0;
                explosionAnimationY++;
            }
        }
    }

    @Override
    public void drawLaser(float d, ArrayList<Enemy> enemies, SpriteBatch b, BitmapFont font) {

    }
}
