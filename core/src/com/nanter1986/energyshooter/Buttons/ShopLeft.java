package com.nanter1986.energyshooter.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.energyshooter.DisplayToolkit;

/**
 * Created by user on 23/2/2017.
 */

public class ShopLeft extends TouchableButtons {
    public ShopLeft(DisplayToolkit tool) {
        super(tool);
        this.buttonX=0;
        this.buttonW=(int)(screenW/5);
        this.buttonH=(int)(screenH/10);
        this.buttonY=(int)(screenH-this.buttonH);
        texture= new Texture(Gdx.files.internal("leftshop.png"));
    }


}
