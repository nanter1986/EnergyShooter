package com.nanter1986.energyshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.Buttons.ShopBuy;
import com.nanter1986.energyshooter.Buttons.ShopExit;
import com.nanter1986.energyshooter.Buttons.ShopLeft;
import com.nanter1986.energyshooter.Buttons.ShopRight;
import com.nanter1986.energyshooter.Buttons.TouchableButtons;

/**
 * Created by user on 24/2/2017.
 */

public class DisplayToolkit {
    public Preferences prefs;
    public SpriteBatch batch;
    public BitmapFont font;
    public OrthographicCamera camera;
    public int scW;
    public int scH;



    public DisplayToolkit(int screenWidth,int screenHeight) {
        prefs= Gdx.app.getPreferences("Shooter");
        scW=screenWidth;
        scH=screenHeight;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(screenWidth, screenHeight);
        camera.position.set(screenWidth / 2, screenHeight / 2, 0);

        font=new BitmapFont();
        font.setColor(0.5f, 0.5f, 0.5f, 1.0f);



    }
}
