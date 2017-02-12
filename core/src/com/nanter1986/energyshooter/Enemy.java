package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by user on 12/2/2017.
 */

public class Enemy {
    int x;
    int y;
    Texture texture;
    int health;

    public Enemy(int x, int spaceshipY, String textureLocation) {
        this.x = x;
        this.y = spaceshipY+380;
        texture=new Texture(Gdx.files.internal(textureLocation));
        this.health=5;
    }

    public void updatePosition(SpriteBatch b){
        y=y-2;
        b.draw(texture,x,y,25f,50f);
    }

    public int checkCollisionWithPlayer(int spaceshipX,int spaceshipY){
        int damage=0;
        if(x>spaceshipX && x<spaceshipX+50 && y>spaceshipY && y<spaceshipY+100){
            health=0;
            damage=2;
        }
        return damage;

    }
}
