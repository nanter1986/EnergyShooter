package com.nanter1986.energyshooter.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.energyshooter.DisplayToolkit;

/**
 * Created by user on 23/2/2017.
 */

public class ShopRight extends TouchableButtons {
    public ShopRight(DisplayToolkit tool) {
        super(tool);

        this.buttonW=(int)screenW/5;
        this.buttonX=(int)screenW-this.buttonW;
        this.buttonH=(int)screenH/10;
        this.buttonY=(int)screenH-this.buttonH;
        texture= new Texture(Gdx.files.internal("rightshop.png"));
    }


}
