package com.nanter1986.energyshooter.shop;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by user on 22/2/2017.
 */

public class ShopItem {
    public String name;
    public float price;
    public Texture texture;
    public boolean haveIt;
    public boolean hide;

    public ShopItem(String name, float price, Texture texture, Preferences prefs) {
        this.name = name;
        this.price = price;
        this.texture = texture;
        this.haveIt = prefs.getBoolean(this.name,false);
        this.hide=false;
    }
}
