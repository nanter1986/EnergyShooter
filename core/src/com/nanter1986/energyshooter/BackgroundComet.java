package com.nanter1986.energyshooter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by user on 18/2/2017.
 */

public class BackgroundComet extends BackGround {
    public BackgroundComet(PlayerShip ship, int screeW, int screenH) {
        super(ship, screeW, screenH);
        Random r = new Random();
        int which = r.nextInt(3);
        if (which == 0) {
            whichTexture = 1;
        } else if (which == 1) {
            whichTexture = 2;
        } else if (which == 2) {
            whichTexture = 3;
        }
    }

    @Override
    public void updatePosition(SpriteBatch b, int spY) {
        y = y - speed;
        int tempY = y + spY;
        if (y + h < 0) {
            passedShip = true;
        }

        if (passedShip == false) {
            if (whichTexture == 1) {

                b.draw(texture1, x, tempY, w, h);
            } else if (whichTexture == 2) {

                b.draw(texture2, x, tempY, w, h);
            } else if (whichTexture == 3) {

                b.draw(texture3, x, tempY, w, h);
            }

        }
    }
}
