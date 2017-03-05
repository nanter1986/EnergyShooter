package com.nanter1986.energyshooter.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.Collidable;
import com.nanter1986.energyshooter.CollisionChecker;
import com.nanter1986.energyshooter.Elementaltypes;
import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 12/2/2017.
 */

public abstract class Enemy implements Collidable {
    public boolean doneColliding;
    public int energyBonus;
    float widthFactor;
    float heightFactor;
    public float x;
    public float y;
    public boolean exploded=false;
    public boolean explodedSound=false;
    int screenWidth;
    int screenHeight;
    int explosionAnimationX;
    int explosionAnimationY;
    Texture currentTexture;
    static final Texture blue=new Texture(Gdx.files.internal("enemyBlue1.png"));
    static final Texture green=new Texture(Gdx.files.internal("greenfast.png"));
    static final Texture ufo=new Texture(Gdx.files.internal("ufoBlue.png"));
    static final Texture badass=new Texture(Gdx.files.internal("badassenemy.png"));
    static final Texture boss=new Texture(Gdx.files.internal("finalboss.png"));
    static final Texture red=new Texture(Gdx.files.internal("enemyRed1.png"));
    static final Texture explosion=new Texture(Gdx.files.internal("explosion.png"));
    public int health;
    public int touchDamageGiven;
    public int touchDamageTaken;
    public int laserFrequency;


    public abstract void updatePosition(SpriteBatch b, PlayerShip ship);

    public abstract Elementaltypes whatType();

    public abstract LaserOfEnemy laserMaker(float spX);

    public float checkCollisionWithPlayer(PlayerShip ship) {
        float damage=0;
        if(CollisionChecker.checkCollision(this,ship)){
            health-=touchDamageTaken;
            damage=touchDamageGiven/ship.shield;
        }
        if(health<=0){

        }

        return damage;
    }




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
    public float positionX() {
        return x;
    }

    @Override
    public float positionY() {
        return y;
    }

    @Override
    public float width() {
        return widthFactor;
    }

    @Override
    public float height() {
        return widthFactor*2;
    }
}
