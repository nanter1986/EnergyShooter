package com.nanter1986.energyshooter.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.Collidable;
import com.nanter1986.energyshooter.CollisionChecker;
import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 12/2/2017.
 */
public abstract class LaserOfEnemy implements Collidable {
    int widthFactor;
    int heightFactor;
    public int x;
    public int y;
    String direction;
    static final Texture star=new Texture(Gdx.files.internal("laserEnemy.png"));
    static final Texture green=new Texture(Gdx.files.internal("laserenemygreen.png"));
    public boolean used;
    public int touchDamageGiven;

    public LaserOfEnemy(int x, int y,int spaceshipX,int screenW,int screenH) {
        this.widthFactor=screenW/20;
        this.heightFactor=screenH/20;
        used=false;
        this.x = x;
        this.y = y;
        if(x>spaceshipX){
            direction="left";
        }else{
            direction="right";
        }
    }

    public abstract void updatePosition(SpriteBatch b);

    public float checkCollisionWithPlayer(PlayerShip ship) {
        float damage=0;
        if(CollisionChecker.checkCollision(this,ship)){
            damage=touchDamageGiven/ship.shield;
        }

        return damage;
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
        return heightFactor;
    }
}
