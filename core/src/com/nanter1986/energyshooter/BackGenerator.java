package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by user on 12/2/2017.
 */

public class BackGenerator {
    public static BackGround maybeGiveBack(int spaceshipX,int spaceshipY){

            String texture = "";
            Random r = new Random();
            int which = r.nextInt(7);
            if (which == 0) {
                texture = "backs/earth.png";
            } else if (which == 1) {
                texture = "backs/galaxy2.png";
            } else if (which == 2) {
                texture = "backs/milky.jpeg";
            } else if (which == 3) {
                texture = "backs/nebula.png";
            } else if (which == 4) {
                texture = "backs/nebula2.png";
            } else if (which == 5) {
                texture = "backs/redPlanet.png";
            } else if (which == 6) {
                texture = "backs/sun.png";
            }

            Random randomX = new Random();
            Random randomY = new Random();
            Random randomW = new Random();
            Random randomH = new Random();
            int x = spaceshipX + (randomX.nextInt(800) - 400);
            int y = spaceshipY + randomY.nextInt(500);
            int w = 100*(randomW.nextInt(3)+1);
            int h =w;


            return new BackGround(texture,x,y,w,h);
    }
}
