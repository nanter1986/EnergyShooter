package com.nanter1986.energyshooter.playerships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.Enemies.Enemy;
import com.nanter1986.energyshooter.InstructionDrawer;

import java.util.ArrayList;

/**
 * Created by user on 4/3/2017.
 */

public class PlayershipPathFinder extends f5s1{


    public PlayershipPathFinder(int screenW, int screenH) {
        super(screenW, screenH);
        spaceship = new Texture(Gdx.files.internal("pathfinder.png"));
        widthFactor=screenW/5;

    }

    @Override
    public int drawLaser(float d, ArrayList<Enemy> enemies, SpriteBatch b, BitmapFont font) {
        float speedModifier;
        int hitC=0;
        if(touchedDown==true){
            speedModifier=2*spaceshipSpeed;
        }else{
            speedModifier=1*spaceshipSpeed;
        }
        if(this.spaceshipHealth>49){
            timeLeftToReloadMax = 0.05f;
        }else if(this.spaceshipHealth>39){
            timeLeftToReloadMax = 0.20f;
        }else if(this.spaceshipHealth>29){
            timeLeftToReloadMax = 0.30f;
        }else if(this.spaceshipHealth>19){
            timeLeftToReloadMax = 0.40f;
        }else{
            timeLeftToReloadMax = 0.50f;
        }
        if (Gdx.input.isTouched() && spaceshipHealth>10){
            touchedDown=true;
            hitC =-4;
        }else{
            touchedDown=false;
        }
        if (cooledDown == false) {
            timeLeftToReload -= d;
            if (timeLeftToReload < 0.01f && spaceshipHealth>0) {
                cooledDown = true;
            }
        }else{
            LaserOfPlayer la = new LaserOfPlayer(spaceshipX, spaceshipY, spaceshipW, spaceshipH, "straightS",this.screenW);
            laserOfPlayer.add(la);
            la.playSound();
            cooledDown = false;
            timeLeftToReload=timeLeftToReloadMax/speedModifier;
            if(touchedDown==false){

            }else{
                spaceshipHealth--;
                instructions.add(new InstructionDrawer(this.spaceshipX+this.spaceshipW,this.spaceshipY+this.spaceshipH,"-1",1.0f,"red"));

            }
        }
        drawInfo(d,font,b);


        ArrayList<LaserOfPlayer> toRemove = new ArrayList<LaserOfPlayer>();
        for (LaserOfPlayer l : laserOfPlayer) {
            if (l.y > this.spaceshipY + this.screenH || l.x < 0 || l.x > this.screenW || l.exploded == true) {
                toRemove.add(l);
            }
        }

        laserOfPlayer.removeAll(toRemove);


        for (LaserOfPlayer l : laserOfPlayer) {
            l.updatePosition(b,enemies);
            for (Enemy e : enemies) {
                float damage = l.dealDamage(e,this);
                e.health -= damage;
            }
        }
        return hitC;
    }
}
