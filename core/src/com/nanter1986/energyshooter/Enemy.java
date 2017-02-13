package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by user on 12/2/2017.
 */

public class Enemy {
    int x;
    int y;
    boolean exploded=false;
    boolean explodedSound=false;
    int explosionAnimationX;
    int explosionAnimationY;
    static final Texture blue=new Texture(Gdx.files.internal("enemyBlue1.png"));
    static final Texture red=new Texture(Gdx.files.internal("enemyRed1.png"));
    static final Texture explosion=new Texture(Gdx.files.internal("explosion.png"));
    int health;
    String type;

    public Enemy(int x, int spaceshipY,int health, String type) {
        this.x = x;
        this.y = spaceshipY+380;
        this.health=health;
        this.type=type;
    }

    public void updatePosition(SpriteBatch b){
        if(health>0) {
            y = y - 2;
            if(type.equals("blue")){
                b.draw(blue, x, y, 25f, 50f);
            }else if(type.equals("red")){
                b.draw(red, x, y, 25f, 50f);
            }
        }else if(exploded==false){
            b.draw(explosion,x,y,25,25,explosionAnimationX*100,500-explosionAnimationY*100,100,100,false,false);
            explosionAnimationX++;
            if(explosionAnimationX==6){
                explosionAnimationX=0;
                explosionAnimationY++;
            }
            if(explosionAnimationY==6){
                exploded=true;
            }
        }
    }

    public int checkCollisionWithPlayer(int spaceshipX,int spaceshipY){
        int damage=0;
        if(x>spaceshipX && x<spaceshipX+50 && y>spaceshipY && y<spaceshipY+100){
            health=0;
            damage=2;
        }
        return damage;

    }

    public void checkCollisionWithLaser(int laserX,int laserY){
        if(x>laserX-10 && x<laserX+35 && y>laserY && y<laserY+50){
            health=0;

        }

    }


}
