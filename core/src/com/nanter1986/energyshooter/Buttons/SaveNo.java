package com.nanter1986.energyshooter.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.energyshooter.DisplayToolkit;

/**
 * Created by user on 25/2/2017.
 */

public class SaveNo extends TouchableButtons {
    public SaveNo(DisplayToolkit tool) {
        super(tool);

        this.buttonW=(int)screenW/2;
        this.buttonX=(int)screenW/2;
        this.buttonH=(int)screenH/10;
        this.buttonY=(int)screenH*2/10;
        texture= new Texture(Gdx.files.internal("menubutton.png"));
        Gdx.app.log("buttons",buttonX+" "+buttonY+" "+buttonW+" "+buttonH);
    }
}
