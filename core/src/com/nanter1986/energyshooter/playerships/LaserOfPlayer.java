package com.nanter1986.energyshooter.playerships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.Collidable;
import com.nanter1986.energyshooter.CollisionChecker;
import com.nanter1986.energyshooter.Elementaltypes;
import com.nanter1986.energyshooter.Enemies.Enemy;

/**
 * Created by user on 13/2/2017.
 */
public class LaserOfPlayer implements Collidable {
    static final Texture explosion=new Texture(Gdx.files.internal("explosion.png"));
    public static final Texture laser=new Texture(Gdx.files.internal("laserRed.png"));
    static final Texture laserMulti=new Texture(Gdx.files.internal("laserRedMulti.png"));
    static final Sound laserSound=Gdx.audio.newSound(Gdx.files.internal("laser.wav"));

    int widthFactor;
    int heightFactor;
    int x;
    int y;
    int w;
    int h;
    String typeOfLaser;
    boolean used;
    private int explosionAnimationX;
    private int explosionAnimationY;
    public boolean exploded;
    public boolean collided;
    private boolean dealt;


    public LaserOfPlayer(int spaceshipX, int spaceshipY,int spaceshipW,int spaceshipH,String type,int screenW) {

        this.widthFactor=screenW/20;
        this.heightFactor=screenW/10;
        this.typeOfLaser=type;
        this.used = false;
        if(typeOfLaser.equals("straightS")) {
            this.w = widthFactor;
            this.h = widthFactor*2;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX+spaceshipW/2-w/2;
        }else if(typeOfLaser.equals("straightL")){
            this.w = widthFactor;
            this.h = widthFactor*2;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX;
            Gdx.app.log("sp",x+" "+w);
        }else if(typeOfLaser.equals("straightR")){
            this.w = widthFactor;
            this.h = widthFactor*2;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX+spaceshipW-w;
            Gdx.app.log("sp",x+" "+y);
        }else if(typeOfLaser.equals("midL")){
            this.w=widthFactor;
            this.h=widthFactor;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX+spaceshipW/2-w/2;
        }else if(typeOfLaser.equals("midR")){
            this.w=widthFactor;
            this.h=widthFactor;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX+spaceshipW/2-w/2;
        }else if(typeOfLaser.equals("sideL")){
            this.w=widthFactor;
            this.h=widthFactor;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX+spaceshipW/2-w/2;
        }else if(typeOfLaser.equals("sideR")){
            this.w=widthFactor;
            this.h=widthFactor;
            this.y = spaceshipY+spaceshipH;
            this.x = spaceshipX+spaceshipW/2-w/2;
        }


        //Gdx.app.log("lasers",""+x+" "+y+" "+w+" "+h);
    }

    public void updatePosition(SpriteBatch b){
        
        if(exploded==true){

        }else if(dealt==true){
           
            explode(b);

        }else{
            if(typeOfLaser.equals("straightS")){
                y = y + widthFactor;
                b.draw(laser, x, y, w, h);
            }else if(typeOfLaser.equals("straightL")){
                y = y + widthFactor;
                b.draw(laser, x, y, w, h);
                Gdx.app.log("left",x+" ");
            }else if(typeOfLaser.equals("straightR")){
                y = y + widthFactor;
                b.draw(laser, x, y, w, h);
                Gdx.app.log("right",x+" ");
            }else if(typeOfLaser.equals("midL")){
                y = y +2* widthFactor;
                x = x - widthFactor;
                b.draw(laserMulti, x, y, w, h);
            }else if(typeOfLaser.equals("midR")){
                y = y + 2*widthFactor;
                x = x + widthFactor;
                b.draw(laserMulti, x, y, w, h);
            }else if(typeOfLaser.equals("sideL")){
                x = x - widthFactor;
                b.draw(laserMulti, x, y, w, h);
            }else if(typeOfLaser.equals("sideR")){
                x = x + widthFactor;
                b.draw(laserMulti, x, y, w, h);
            }
        }

        




    }

    public float dealDamage(Enemy e,PlayerShip ship){
        float damage=0;
        if(CollisionChecker.checkCollision(this,e) && dealt==false){
            if(e.whatType()== Elementaltypes.ICE){
                damage=5*ship.damageFactorIce;
            }else if(e.whatType()== Elementaltypes.FIRE){
                damage=5*ship.damageFactorFire;
            }else if(e.whatType()== Elementaltypes.DARKNESS){
                damage=5*ship.damageFactorDark;
            }else if(e.whatType()== Elementaltypes.LIGHT){
                damage=5*ship.damageFactorLight;
            }
            dealt=true;
        }
        return damage;
    }

    public void explode(SpriteBatch b){
        b.draw(explosion,x,y,widthFactor,widthFactor,explosionAnimationX*100,500-explosionAnimationY*100,100,100,false,false);
        explosionAnimationX++;
        if(explosionAnimationX==6){
            explosionAnimationX=0;
            explosionAnimationY++;
        }
        if(explosionAnimationY==6){
            exploded=true;
        }
    }



    public void playSound(){
        laserSound.play();
    }


    @Override
    public float positionX() {
        return x;
    }

    @Override
    public float positionY() {
        return y;
    }

    @Override
    public float width() {
        return w;
    }

    @Override
    public float height() {
        return h;
    }
}
