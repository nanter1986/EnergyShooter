package com.nanter1986.energyshooter.playerships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.Collidable;
import com.nanter1986.energyshooter.Enemies.Enemy;

import java.util.ArrayList;

/**
 * Created by user on 13/2/2017.
 */

public abstract class PlayerShip implements Collidable {
    public static final Texture explosion= new Texture(Gdx.files.internal("explosion.png"));
    public static final Sound explosionBig=Gdx.audio.newSound(Gdx.files.internal("explosionBig.wav"));
    public int spaceshipHealth;
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
    public ArrayList<LaserOfPlayer> laserOfPlayer;
    public int screenW;
    public int screenH;
    public int shield;


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
