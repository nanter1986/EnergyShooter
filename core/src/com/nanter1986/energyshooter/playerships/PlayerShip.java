package com.nanter1986.energyshooter.playerships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.Artifacts.Artifact;
import com.nanter1986.energyshooter.Collidable;
import com.nanter1986.energyshooter.Enemies.Enemy;
import com.nanter1986.energyshooter.InstructionDrawer;

import java.util.ArrayList;

/**
 * Created by user on 13/2/2017.
 */

public abstract class PlayerShip implements Collidable {
    public static final Texture explosion= new Texture(Gdx.files.internal("explosion.png"));
    public static final Sound explosionBig=Gdx.audio.newSound(Gdx.files.internal("explosionBig.wav"));
    public float spaceshipHealth;
    public float spaceshipSpeed;
    public float shield;
    public float energyDrawn;
    public float damageFactor;
    public float damageFactorFire;
    public float damageFactorIce;
    public float damageFactorDark;
    public float damageFactorLight;
    public int spaceshipX;
    public int spaceshipY;
    public int spaceshipW;
    public int spaceshipH;
    int widthFactor;
    public boolean died;
    int explosionAnimationX;
    int explosionAnimationY;
    public boolean touchedDown=false;
    public boolean cooledDown=false;
    public float timeLeftToReload;
    public float timeLeftToReloadMax;
    public ArrayList<LaserOfPlayer> laserOfPlayer;
    ArrayList<InstructionDrawer> instructions;
    public ArrayList<Artifact>listOfArtifacts;


    public int screenW;
    public int screenH;

    public int numOfSlots;


    public void drawInfo(float delta,BitmapFont font,SpriteBatch batch){
        for (InstructionDrawer i : instructions) {
            if (i.finished == false) {
                i.drawSelf(delta, font, batch);
            }
        }
        ArrayList<InstructionDrawer> toRemove = new ArrayList<InstructionDrawer>();
        for (InstructionDrawer i : instructions) {
            if (i.finished == true) {

                toRemove.add(i);

            }
        }
        instructions.removeAll(toRemove);
    }


    public abstract void updatePosition(SpriteBatch b);

    public abstract void drawLaser(float d, ArrayList<Enemy> enemies, SpriteBatch b, BitmapFont font);

    @Override
    public float positionX() {
        return spaceshipX;
    }

    @Override
    public float positionY() {
        return spaceshipY;
    }

    @Override
    public float width() {
        return spaceshipW;
    }

    @Override
    public float height() {
        return spaceshipH;
    }


}
