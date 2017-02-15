package com.nanter1986.energyshooter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by user on 15/2/2017.
 */

public class EnemySuperBad extends Enemy {
    String xDirection;
    public EnemySuperBad(int x, int spaceshipY,int screenW,int screenH) {
        this.widthFactor=screenW/5;
        this.heightFactor=screenH/5;
        this.x = x;
        this.y = spaceshipY+screenH+heightFactor;
        this.health=10;
        this.currentTexture=badass;
        this.screenWidth=screenW;
        this.screenHeight=screenH;
        this.xDirection="left";
    }

    @Override
    public void updatePosition(SpriteBatch b) {
        if(health>0) {
            if(y>screenHeight-widthFactor*2){
                y = y - widthFactor/50;
            }else{
                if(xDirection.equals("left")){
                    x = x - widthFactor/50;
                    if(x<5){
                        xDirection="right";
                    }
                }else{
                    x = x + widthFactor/50;
                    if(x>screenWidth-widthFactor-5){
                        xDirection="left";
                    }
                }
            }



            b.draw(currentTexture, x, y, widthFactor, widthFactor*2);

        }else if(exploded==false){
            explode(b);
        }
    }

    @Override
    public int checkCollisionWithPlayer(PlayerShip ship) {
        int damage=0;
        if(CollisionChecker.checkCollision(this,ship)){
            health-=5;
            damage=5;
        }
        return damage;
    }

    @Override
    public void checkCollisionWithLaser(ArrayList<LaserOfPlayer> lasers, PlayerShip ship) {
        for(LaserOfPlayer l:lasers){
            if(CollisionChecker.checkCollision(l,this)){
                health-=5;

            }
        }
    }
}
