package com.nanter1986.energyshooter.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.energyshooter.DisplayToolkit;

/**
 * Created by user on 26/2/2017.
 */

public class EquipProceed extends TouchableButtons {
    public EquipProceed(DisplayToolkit tool) {
        super(tool);

        this.buttonW=(int)screenW/10;
        this.buttonX=(int)screenW/2-buttonW/2;
        this.buttonH=(int)screenH/10;
        this.buttonY=(int)0;

        texture= new Texture(Gdx.files.internal("forward.png"));
    }
}
