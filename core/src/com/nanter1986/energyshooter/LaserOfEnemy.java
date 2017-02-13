package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by user on 12/2/2017.
 */
public class LaserOfEnemy {
    int x;
    int y;
    String direction;
    static final Texture texture=new Texture(Gdx.files.internal("laserEnemy.png"));
    boolean used;

    public LaserOfEnemy(int x, int y,int spaceshipX) {
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
            y = y - 3;
            x = x - 3;

            b.draw(texture, x, y, 10f, 20f);
        }else{
            y = y - 3;
            x = x + 3;

            b.draw(texture, x, y, 10f, 20f);
        }
    }

    public int checkCollisionWithPlayer(int spaceshipX,int spaceshipY){
        int damage=0;
        if(x>spaceshipX && x<spaceshipX+50 && y>spaceshipY && y<spaceshipY+100){
            damage=3;
            used=true;
        }
        return damage;

    }
}
