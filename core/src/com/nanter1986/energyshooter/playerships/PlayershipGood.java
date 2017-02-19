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

public class PlayershipGood extends PlayerShip {

    private static final Texture spaceshipGood=new Texture(Gdx.files.internal("spaceshipGood.png"));

    public PlayershipGood(int screenW) {
        this.spaceshipHealth = 20;
        this.spaceshipX = screenW / 2;
        this.spaceshipY = 0;
        this.widthFactor = screenW / 10;
        this.died = false;
    }

    @Override
    public void updatePosition(SpriteBatch b) {
        spaceshipW = widthFactor;
        spaceshipH = 2*widthFactor;

        if (spaceshipHealth > 0) {

            b.draw(spaceshipGood, spaceshipX, spaceshipY, spaceshipW, spaceshipH);

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
            LaserOfPlayer la = new LaserOfPlayer(this.spaceshipX, this.spaceshipY, this.spaceshipW, this.spaceshipH, "straightL", this.screenW);
            LaserOfPlayer lb = new LaserOfPlayer(this.spaceshipX + this.spaceshipW / 2, this.spaceshipY, this.spaceshipW, this.spaceshipH, "straightR", this.screenW);
            laserOfPlayer.add(la);
            laserOfPlayer.add(lb);
            la.playSound();
            lb.playSound();
            cooledDown = false;
            if (touchedDown == false) {
                timeLeftToReload = 0.5f;
            } else {
                timeLeftToReload = 0.12f;
                this.spaceshipHealth--;
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
