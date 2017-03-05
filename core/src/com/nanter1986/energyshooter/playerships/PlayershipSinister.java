package com.nanter1986.energyshooter.playerships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.Artifacts.Artifact;
import com.nanter1986.energyshooter.Artifacts.BasicShield;
import com.nanter1986.energyshooter.Artifacts.Damager;
import com.nanter1986.energyshooter.Artifacts.DarknessKiller;
import com.nanter1986.energyshooter.Artifacts.EnergyBoosterOne;
import com.nanter1986.energyshooter.Artifacts.FireKiller;
import com.nanter1986.energyshooter.Artifacts.IceKiller;
import com.nanter1986.energyshooter.Artifacts.LightsDown;
import com.nanter1986.energyshooter.Artifacts.Speeder;
import com.nanter1986.energyshooter.Enemies.Enemy;
import com.nanter1986.energyshooter.InstructionDrawer;

import java.util.ArrayList;

/**
 * Created by user on 19/2/2017.
 */

public class PlayershipSinister extends PlayerShip {

    private static final Texture spaceshipSinister=new Texture(Gdx.files.internal("sinister.png"));


    public PlayershipSinister(int screenW, int screenH) {
        doneColliding=false;
        this.spaceshipHealth = 10;
        this.spaceshipX = screenW / 2;
        this.spaceshipY = 0;
        this.widthFactor = screenW / 7;
        this.died = false;

        this.laserOfPlayer = new ArrayList<LaserOfPlayer>();
        this.instructions = new ArrayList<InstructionDrawer>();
        this.screenW = screenW;
        this.screenH = screenH;
        this.shield = 1;
        this.numOfSlots = 1;
        this.spaceshipSpeed = 1;
        this.energyDrawn = 1;
        this.damageFactorDark = 1;
        this.damageFactorFire = 1;
        this.damageFactorIce = 1;
        this.damageFactorLight = 1;


        this.listOfArtifacts = new ArrayList<Artifact>();
        listOfArtifacts.add(new BasicShield());
        listOfArtifacts.add(new EnergyBoosterOne());
        listOfArtifacts.add(new Speeder());
        listOfArtifacts.add(new Damager());
        listOfArtifacts.add(new FireKiller());
        listOfArtifacts.add(new IceKiller());
        listOfArtifacts.add(new DarknessKiller());
        listOfArtifacts.add(new LightsDown());

    }

    @Override
    public void updatePosition(SpriteBatch b) {
        spaceshipW = widthFactor;
        spaceshipH = 2*widthFactor;

        if (spaceshipHealth > 0) {

            b.draw(spaceshipSinister, spaceshipX, spaceshipY, spaceshipW, spaceshipH);

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
    public int drawLaser(float d, ArrayList<Enemy> enemies, SpriteBatch b, BitmapFont font) {
        float speedModifier;
        int hitC=0;
        if (touchedDown == true) {
            speedModifier = 2 * spaceshipSpeed;
        } else {
            speedModifier = 1 * spaceshipSpeed;
        }
        if (this.spaceshipHealth > 49) {
            timeLeftToReloadMax = 0.10f;
        } else if (this.spaceshipHealth > 39) {
            timeLeftToReloadMax = 0.20f;
        } else if (this.spaceshipHealth > 29) {
            timeLeftToReloadMax = 0.30f;
        } else if (this.spaceshipHealth > 19) {
            timeLeftToReloadMax = 0.40f;
        } else {
            timeLeftToReloadMax = 0.50f;
        }
        if (Gdx.input.isTouched() && spaceshipHealth > 10) {
            touchedDown = true;
            hitC =-4;
        } else {
            touchedDown = false;
        }
        if (cooledDown == false) {
            timeLeftToReload -= d;
            if (timeLeftToReload < 0.01f && spaceshipHealth > 0) {
                cooledDown = true;
            }
        } else {
            LaserOfPlayer la = new LaserOfPlayer(spaceshipX, spaceshipY, spaceshipW, spaceshipH, "straightL", this.screenW);
            LaserOfPlayer lb = new LaserOfPlayer(spaceshipX, spaceshipY, spaceshipW, spaceshipH, "straightR", this.screenW);
            LaserOfPlayer le = new LaserOfPlayer(spaceshipX, spaceshipY, spaceshipW, spaceshipH, "straightS", this.screenW);
            LaserOfPlayer lc = new LaserOfPlayer(spaceshipX, spaceshipY, spaceshipW, spaceshipH, "midL", this.screenW);
            LaserOfPlayer ld = new LaserOfPlayer(spaceshipX, spaceshipY, spaceshipW, spaceshipH, "midR", this.screenW);
            laserOfPlayer.add(la);
            laserOfPlayer.add(lb);
            laserOfPlayer.add(lc);
            laserOfPlayer.add(ld);
            laserOfPlayer.add(le);

            la.playSound();
            lb.playSound();
            lc.playSound();
            ld.playSound();
            le.playSound();
            cooledDown = false;
            timeLeftToReload = timeLeftToReloadMax / speedModifier;
            if (touchedDown == false) {

            } else {
                spaceshipHealth--;
                instructions.add(new InstructionDrawer(this.spaceshipX + this.spaceshipW, this.spaceshipY + this.spaceshipH, "-1", 1.0f, "red"));

            }
        }
        drawInfo(d, font, b);


        ArrayList<LaserOfPlayer> toRemove = new ArrayList<LaserOfPlayer>();
        for (LaserOfPlayer l : laserOfPlayer) {
            if (l.y > this.spaceshipY + this.screenH || l.x < 0 || l.x > this.screenW || l.exploded == true) {
                toRemove.add(l);
            }
        }

        laserOfPlayer.removeAll(toRemove);


        for (LaserOfPlayer l : laserOfPlayer) {
            l.updatePosition(b);
            for (Enemy e : enemies) {
                float damage = l.dealDamage(e,this);
                e.health -= damage;
            }
        }
        return hitC;
    }

    @Override
    public boolean done() {
        return doneColliding;
    }
}
