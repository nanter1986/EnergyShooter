package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by user on 12/2/2017.
 */

public class Enemy {
    int widthFactor;
    int heightFactor;
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

    public Enemy(int x, int spaceshipY,int health, String type,int screenW,int screenH) {
        this.widthFactor=screenW/10;
        this.heightFactor=screenH/10;
        this.x = x;
        this.y = spaceshipY+380;
        this.health=health;
        this.type=type;
    }

    public void updatePosition(SpriteBatch b){
        if(health>0) {
            y = y - 2;
            if(type.equals("blue")){
                b.draw(blue, x, y, widthFactor, heightFactor);
            }else if(type.equals("red")){
                b.draw(red, x, y, widthFactor, heightFactor);
            }
        }else if(exploded==false){
            b.draw(explosion,x,y,widthFactor/2,heightFactor/2,explosionAnimationX*100,500-explosionAnimationY*100,100,100,false,false);
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

    public void checkCollisionWithLaser(ArrayList<LaserOfPlayer>lasers){
        for(LaserOfPlayer l:lasers){
            if(x>l.x-20 && x<l.x+45 && y>l.y && y<l.y+50){
                health=0;

            }
        }


    }


}
