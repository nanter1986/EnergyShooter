package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by user on 13/2/2017.
 */

public class PlayerShip implements Collidable{
    private static final Texture spaceship= new Texture(Gdx.files.internal("F5S1.png"));
    private static final Texture spaceshipGood=new Texture(Gdx.files.internal("spaceshipGood.png"));
    private static final Texture spaceshipSinister=new Texture(Gdx.files.internal("sinister.png"));
    private static final Texture spaceshipCoolBlue=new Texture(Gdx.files.internal("coolBlue.png"));
    private static final Texture spaceshipWingShip=new Texture(Gdx.files.internal("wingship.png"));
    private static final Texture explosion= new Texture(Gdx.files.internal("explosion.png"));
    private static final Sound explosionBig=Gdx.audio.newSound(Gdx.files.internal("explosionBig.wav"));
    int spaceshipHealth;
    int spaceshipX;
    int spaceshipY;
    int spaceshipW;
    int spaceshipH;
    int widthFactor;
    String whichShipImage="f5";
    boolean died;
    private int explosionAnimationX;
    private int explosionAnimationY;

    public PlayerShip(int screenW) {
        this.spaceshipHealth = 10;
        this.spaceshipX = screenW/2;
        this.spaceshipY = 0;
        this.widthFactor=screenW/10;


        this.died = false;
    }

    public void updatePosition(SpriteBatch b){
        if(spaceshipHealth>199){
            spaceshipW=widthFactor;
            spaceshipH=widthFactor*2;
            whichShipImage="sinister";
        }else if(spaceshipHealth>99){
            spaceshipW=widthFactor;
            spaceshipH=widthFactor*2;
            whichShipImage="coolBlue";
        }else if(spaceshipHealth>49){
            spaceshipW=widthFactor;
            spaceshipH=widthFactor*2;
            whichShipImage="wingship";
        }else if(spaceshipHealth>29){
            spaceshipW=widthFactor*2;
            spaceshipH=widthFactor;
            whichShipImage="good";
        }else if(spaceshipHealth>19){
            spaceshipW=widthFactor;
            spaceshipH=widthFactor*2;
            whichShipImage="f5";
        }else{
            spaceshipW=widthFactor;
            spaceshipH=widthFactor;
            whichShipImage="f5";
        }
        if(spaceshipHealth>0){
            if(whichShipImage.equals("sinister")){
                b.draw(spaceshipSinister, spaceshipX, spaceshipY, spaceshipW, spaceshipH);
            }else if(whichShipImage.equals("coolBlue")){
                b.draw(spaceshipCoolBlue, spaceshipX, spaceshipY, spaceshipW, spaceshipH);
            }else if(whichShipImage.equals("wingship")){
                b.draw(spaceshipWingShip, spaceshipX, spaceshipY, spaceshipW, spaceshipH);
            }else if(whichShipImage.equals("good")){
                b.draw(spaceshipGood, spaceshipX, spaceshipY, spaceshipW, spaceshipH);
            }else if(whichShipImage.equals("f5")){
                b.draw(spaceship, spaceshipX, spaceshipY, spaceshipW, spaceshipH);
            }
        }else if(explosionAnimationY==6){
            died = true;
        }else{
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
