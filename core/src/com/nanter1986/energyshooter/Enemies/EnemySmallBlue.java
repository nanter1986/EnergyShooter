package com.nanter1986.energyshooter.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.CollisionChecker;
import com.nanter1986.energyshooter.Elementaltypes;
import com.nanter1986.energyshooter.playerships.PlayerShip;

/**
 * Created by user on 15/2/2017.
 */

public class EnemySmallBlue extends Enemy {
    public EnemySmallBlue(int x, int spaceshipY,int screenW,int screenH) {
        energyBonus=2;
        this.widthFactor=screenW/30;
        this.heightFactor=screenH/30;
        this.x = x-widthFactor;
        this.y = spaceshipY+screenH+heightFactor;
        this.health=5;
        this.currentTexture=blue;
        this.screenWidth=screenW;
        this.screenHeight=screenH;
        this.touchDamageGiven=2;
        this.touchDamageTaken=10;

    }

    @Override
    public void updatePosition(SpriteBatch b, com.nanter1986.energyshooter.playerships.PlayerShip ship) {
        if(health>0) {
            y = y - widthFactor/20;
            Gdx.app.log("EnemyPos",""+y+" "+widthFactor+" "+widthFactor/20);
            b.draw(currentTexture, x, y, widthFactor, widthFactor*2);

        }else if(exploded==false){
            explode(b);
        }
    }

    @Override
    public Elementaltypes whatType() {
        return Elementaltypes.ICE;
    }

    @Override
    public LaserOfEnemy laserMaker(float spX) {
        return new EnemyLaserStar(x + 20, y, spX, screenWidth, screenHeight);

    }




}
