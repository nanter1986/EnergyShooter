package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by user on 12/2/2017.
 */
public abstract class LaserOfEnemy implements Collidable{
    int widthFactor;
    int heightFactor;
    int x;
    int y;
    String direction;
    static final Texture star=new Texture(Gdx.files.internal("laserEnemy.png"));
    static final Texture green=new Texture(Gdx.files.internal("laserenemygreen.png"));
    boolean used;

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

    public abstract int checkCollisionWithPlayer(PlayerShip ship);

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
