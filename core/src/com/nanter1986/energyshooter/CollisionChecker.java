package com.nanter1986.energyshooter;

/**
 * Created by user on 14/2/2017.
 */

public class CollisionChecker {
    public static Boolean checkCollision(Collidable c1,Collidable c2){
        boolean collided=false;
        if(c1.positionX()+c1.width()>c2.positionX() &&
                c1.positionX()<c2.positionX()+c2.width() &&
                c1.positionY()+c1.height()>c2.positionY() &&
                c1.positionY()<c2.positionY()+c2.height() &&
                c1.done()==false && c2.done()==false){
            collided=true;
        }


        return collided;
    }
}
