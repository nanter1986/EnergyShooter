package com.nanter1986.energyshooter.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by user on 23/2/2017.
 */

public class ShopExit extends TouchableButtons {
    public ShopExit(float screenW, float ScreenH) {
        super(screenW, ScreenH);

        this.buttonW=(int)(screenW/5);
        this.buttonX=(int)(screenW/2-buttonW/2);
        this.buttonH=(int)(ScreenH/10);
        this.buttonY=0;
        texture= new Texture(Gdx.files.internal("forward.png"));
        Gdx.app.log("button",buttonX+" "+buttonW+" "+buttonY+" "+buttonH+" "+screenW+" "+ScreenH);
    }
}
