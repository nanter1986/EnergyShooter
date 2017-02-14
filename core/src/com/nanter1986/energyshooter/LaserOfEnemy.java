package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by user on 12/2/2017.
 */
public class LaserOfEnemy implements Collidable{
    int widthFactor;
    int heightFactor;
    int x;
    int y;
    String direction;
    static final Texture texture=new Texture(Gdx.files.internal("laserEnemy.png"));
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

    public void updatePosition(SpriteBatch b){
        if(direction.equals("left")) {
            y = y - widthFactor/2;
            x = x - widthFactor/2;

            b.draw(texture, x, y, widthFactor, heightFactor);
        }else{
            y = y - widthFactor/2;
            x = x + widthFactor/2;

            b.draw(texture, x, y, widthFactor, heightFactor);
        }
    }

    public int checkCollisionWithPlayer(PlayerShip ship){
        int damage=0;
        if(CollisionChecker.checkCollision(this,ship)){
            damage=3;
            used=true;
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
