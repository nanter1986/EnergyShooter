package com.nanter1986.energyshooter.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.energyshooter.DisplayToolkit;

/**
 * Created by user on 25/2/2017.
 */

public class MenuSettings extends TouchableButtons {
    public MenuSettings(DisplayToolkit tool) {
        super(tool);


        this.buttonW=(int)screenW;
        this.buttonX=0;
        this.buttonH=(int)screenH/10;
        this.buttonY=(int)screenH*4/10;
        texture= new Texture(Gdx.files.internal("menusettingsbutton.png"));
        Gdx.app.log("buttons",this.buttonX+" "+this.buttonY+" "+this.buttonW+" "+this.buttonH);
    }
}
