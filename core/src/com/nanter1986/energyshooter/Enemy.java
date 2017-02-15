package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by user on 12/2/2017.
 */

public abstract class Enemy implements Collidable{
    int energyBonus;
    int widthFactor;
    int heightFactor;
    int x;
    int y;
    boolean exploded=false;
    boolean explodedSound=false;
    int screenWidth;
    int screenHeight;
    int explosionAnimationX;
    int explosionAnimationY;
    Texture currentTexture;
    static final Texture blue=new Texture(Gdx.files.internal("enemyBlue1.png"));
    static final Texture ufo=new Texture(Gdx.files.internal("ufoBlue.png"));
    static final Texture badass=new Texture(Gdx.files.internal("badassenemy.png"));
    static final Texture red=new Texture(Gdx.files.internal("enemyRed1.png"));
    static final Texture explosion=new Texture(Gdx.files.internal("explosion.png"));
    int health;



    public abstract void updatePosition(SpriteBatch b,PlayerShip ship);

    public abstract LaserOfEnemy laserMaker(int spX);

    public abstract int checkCollisionWithPlayer(PlayerShip ship);









    public void explode(SpriteBatch b){
        b.draw(explosion,x,y,widthFactor,widthFactor,explosionAnimationX*100,500-explosionAnimationY*100,100,100,false,false);
        explosionAnimationX++;
        if(explosionAnimationX==6){
            explosionAnimationX=0;
            explosionAnimationY++;
        }
        if(explosionAnimationY==6){
            exploded=true;
        }
    }


    @Override
    public int positionX() {
        return x;
    }

    @Override
    public int positionY() {
        return y;
    }

    @Override
    public int width() {
        return widthFactor;
    }

    @Override
    public int height() {
        return widthFactor*2;
    }
}
