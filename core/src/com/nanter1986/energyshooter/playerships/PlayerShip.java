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


    public int screenW;
    public int screenH;

    public int numOfSlots;
    public float damageFactor;

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
    public int positionX() {
        return spaceshipX;
    }

    @Override
    public int positionY() {
        return spaceshipY;
    }

    @Override
    public int width() {
        return spaceshipW;
    }

    @Override
    public int height() {
        return spaceshipH;
    }


}