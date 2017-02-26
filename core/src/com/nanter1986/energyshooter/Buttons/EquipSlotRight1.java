package com.nanter1986.energyshooter.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.energyshooter.DisplayToolkit;

/**
 * Created by user on 26/2/2017.
 */

public class EquipSlotRight1 extends TouchableButtons {
    public EquipSlotRight1(DisplayToolkit tool) {
        super(tool);

        this.buttonW=(int)screenW/10;
        this.buttonX=(int)screenW-this.buttonW;
        this.buttonH=(int)screenH/10;
        this.buttonY=(int)screenH*5/10;

        texture= new Texture(Gdx.files.internal("rightshop.png"));
    }
}
