package com.nanter1986.energyshooter.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.energyshooter.DisplayToolkit;

/**
 * Created by user on 25/2/2017.
 */

public class SaveManager extends TouchableButtons {
    public SaveManager(DisplayToolkit tool) {
        super(tool);


        this.buttonW=(int)screenW;
        this.buttonX=0;
        this.buttonH=(int)screenH/10;
        this.buttonY=(int)screenH*3/10;
        texture= new Texture(Gdx.files.internal("menusavebutton.png"));
    }
}
