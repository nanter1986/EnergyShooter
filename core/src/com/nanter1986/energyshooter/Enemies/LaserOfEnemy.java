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
    float widthFactor;
    float heightFactor;
    public float x;
    public float y;
    String direction;
    static final Texture star=new Texture(Gdx.files.internal("laserEnemy.png"));
    static final Texture green=new Texture(Gdx.files.internal("laserenemygreen.png"));
    public boolean used;
    public int touchDamageGiven;
    public boolean doneColliding;

    public LaserOfEnemy(Enemy enemy,float spaceshipX,int screenW,int screenH) {
        this.widthFactor=screenW/20;
        this.heightFactor=screenH/20;
        used=false;
        this.x = enemy.positionX()+enemy.width()/2-this.widthFactor/2;
        this.y = enemy.positionY();
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
            used=true;
        }

        return damage;
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
        return heightFactor;
    }
}
