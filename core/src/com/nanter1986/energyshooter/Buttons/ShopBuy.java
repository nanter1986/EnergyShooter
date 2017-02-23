package com.nanter1986.energyshooter.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by user on 23/2/2017.
 */

public class ShopBuy extends TouchableButtons {
    public ShopBuy(float screenW, float ScreenH) {
        super(screenW, ScreenH);

        this.buttonW=(int)screenW/5;
        this.buttonX=(int)screenW-this.buttonW;
        this.buttonH=(int)ScreenH/10;
        this.buttonY=0;
        texture= new Texture(Gdx.files.internal("buy.png"));
    }


}
