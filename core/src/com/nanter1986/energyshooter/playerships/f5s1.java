package com.nanter1986.energyshooter.playerships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.Enemies.Enemy;
import com.nanter1986.energyshooter.InstructionDrawer;

import java.util.ArrayList;

/**
 * Created by user on 19/2/2017.
 */

public class f5s1 extends PlayerShip {

    private static final Texture spaceship = new Texture(Gdx.files.internal("F5S1.png"));

    public f5s1(int screenW,int screenH) {
        this.spaceshipHealth = 10;
        this.spaceshipX = screenW/2;
        this.spaceshipY = 0;
        this.widthFactor=screenW/10;
        this.died = false;
        this.laserOfPlayer=new ArrayList<LaserOfPlayer>();
        this.screenW=screenW;
        this.screenH=screenH;
        this.shield=1;
    }

    public void updatePosition(SpriteBatch b) {
        spaceshipW = widthFactor;
        spaceshipH = widthFactor;

        if (spaceshipHealth > 0) {

            b.draw(spaceship, spaceshipX, spaceshipY, spaceshipW, spaceshipH);

        } else if (explosionAnimationY == 6) {
            died = true;
        } else {
            b.draw(explosion, spaceshipX, spaceshipY, explosionAnimationX * 100, 500 - explosionAnimationY * 100, 100, 100);
            explosionBig.play();
            explosionAnimationX++;
            if (explosionAnimationX == 6) {
                explosionAnimationX = 0;
                explosionAnimationY++;
            }
        }


    }

    @Override
    public void drawLaser(float d, ArrayList<Enemy> enemies, SpriteBatch b, BitmapFont font) {
        if(this.spaceshipHealth>49){
            timeLeftToReload = 0.10f;
        }else if(this.spaceshipHealth>39){
            timeLeftToReload = 0.20f;
        }else if(this.spaceshipHealth>29){
            timeLeftToReload = 0.30f;
        }else if(this.spaceshipHealth>19){
            timeLeftToReload = 0.40f;
        }else{
            timeLeftToReload = 0.50f;
        }
        if (Gdx.input.isTouched() && spaceshipHealth>10){
            touchedDown=true;
        }else{
            touchedDown=false;
        }
        if (cooledDown == false) {
            timeLeftToReload -= d;
            if (timeLeftToReload < 0.01f && spaceshipHealth>0) {
                cooledDown = true;
            }
        }else{
            LaserOfPlayer la = new LaserOfPlayer(spaceshipX + spaceshipW / 2, spaceshipY, spaceshipW, spaceshipH, "straightS",this.screenW);
            laserOfPlayer.add(la);
            la.playSound();
            cooledDown = false;
            if(touchedDown==false){

            }else{
                timeLeftToReload = timeLeftToReload/2;
                spaceshipHealth--;
                new InstructionDrawer(this.spaceshipX+this.spaceshipW,this.spaceshipY+this.spaceshipH,"-1",1.0f,"red").drawSelf(d,font,b);

            }
        }

        ArrayList<LaserOfPlayer> toRemove = new ArrayList<LaserOfPlayer>();
        for (LaserOfPlayer l : laserOfPlayer) {
            if (l.y > this.spaceshipY + this.screenH || l.x < 0 || l.x > this.screenH || l.exploded == true) {
                toRemove.add(l);
            }
        }

        laserOfPlayer.removeAll(toRemove);


        for (LaserOfPlayer l : laserOfPlayer) {
            l.updatePosition(b);
            for (Enemy e : enemies) {
                int damage = l.dealDamage(e);
                e.health -= damage;
            }
        }
    }
}
