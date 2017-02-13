package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by user on 13/2/2017.
 */
public class LaserOfPlayer {
    static final Texture laser=new Texture(Gdx.files.internal("laserRed.png"));
    static final Texture laserMulti=new Texture(Gdx.files.internal("laserRedMulti.png"));
    static final Sound laserSound=Gdx.audio.newSound(Gdx.files.internal("laser.wav"));

    int x;
    int y;
    int w;
    int h;
    String typeOfLaser;
    boolean used;


    public LaserOfPlayer(int spaceshipX, int spaceshipY,int spaceshipW,int spaceshipH,String type) {

        this.typeOfLaser=type;
        this.used = false;
        if(typeOfLaser.equals("straightS")) {
            this.w = 25;
            this.h = 50;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX+spaceshipW/2-w/2;
        }else if(typeOfLaser.equals("straightL")){
            this.w = 25;
            this.h = 50;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX+spaceshipW/2-w/2+20;
        }else if(typeOfLaser.equals("straightR")){
            this.w = 25;
            this.h = 50;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX+spaceshipW/2-w/2+20;
        }else if(typeOfLaser.equals("midL")){
            this.w=25;
            this.h=25;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX+spaceshipW/2-w/2;
        }else if(typeOfLaser.equals("midR")){
            this.w=25;
            this.h=25;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX+spaceshipW/2-w/2;
        }else if(typeOfLaser.equals("sideL")){
            this.w=25;
            this.h=25;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX+spaceshipW/2-w/2;
        }else if(typeOfLaser.equals("sideR")){
            this.w=25;
            this.h=25;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX+spaceshipW/2-w/2;
        }
        this.y = spaceshipY+spaceshipH;
        this.x = spaceshipX+spaceshipW/2-w/2;

        Gdx.app.log("lasers",""+x+" "+y+" "+w+" "+h);
    }

    public void updatePosition(SpriteBatch b){
        if(typeOfLaser.equals("straightS")){
            y = y + 15;
            b.draw(laser, x, y, w, h);
        }else if(typeOfLaser.equals("straightL")){
            y = y + 15;
            b.draw(laser, x, y, w, h);
        }else if(typeOfLaser.equals("straightR")){
            y = y + 15;
            b.draw(laser, x, y, w, h);
        }else if(typeOfLaser.equals("midL")){
            y = y + 10;
            x = x - 10;
            b.draw(laserMulti, x, y, w, h);
        }else if(typeOfLaser.equals("midR")){
            y = y + 10;
            x = x + 10;
            b.draw(laserMulti, x, y, w, h);
        }else if(typeOfLaser.equals("sideL")){
            x = x - 15;
            b.draw(laserMulti, x, y, w, h);
        }else if(typeOfLaser.equals("sideR")){
            x = x + 15;
            b.draw(laserMulti, x, y, w, h);
        }



    }

    public void playSound(){
        laserSound.play();
    }




}
