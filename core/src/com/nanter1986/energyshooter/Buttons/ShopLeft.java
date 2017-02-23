package com.nanter1986.energyshooter.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by user on 23/2/2017.
 */

public class ShopLeft extends TouchableButtons {
    public ShopLeft(float screenW, float ScreenH) {
        super(screenW, ScreenH);
        this.buttonX=0;
        this.buttonW=(int)(screenW/5);
        this.buttonH=(int)(ScreenH/10);
        this.buttonY=(int)(ScreenH-this.buttonH);
        texture= new Texture(Gdx.files.internal("leftshop.png"));
        Gdx.app.log("button",buttonX+" "+buttonW+" "+buttonY+" "+buttonH+" "+screenW+" "+ScreenH);
    }


}
