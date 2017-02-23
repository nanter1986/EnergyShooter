package com.nanter1986.energyshooter.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by user on 23/2/2017.
 */

public abstract class TouchableButtons {
    public int buttonX;
    public int buttonY;
    public int buttonW;
    public int buttonH;
    public Texture texture;
    public boolean touchedOnce;
    public float screenW;
    public float screenH;

    public TouchableButtons(float screenW,float ScreenH) {
        this.screenH=ScreenH;
        this.screenW=screenW;
    }


    public boolean isButtonTouched(){
        boolean t=false;
        int x= Gdx.input.getX();
        int y= Gdx.input.getY();

        if(Gdx.input.justTouched() && x>buttonX && x<buttonX+buttonW && y<screenH-buttonY && y>screenH-buttonY-buttonH){
            t=true;
        }
        return t;

    }
}
