package com.nanter1986.energyshooter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by user on 16/2/2017.
 */

public class InstructionDrawer {
    int x;
    int y;
    String message;
    float counter;
    public boolean finished;
    String type;

    public InstructionDrawer(int x, int y, String message, float counter,String type) {
        this.x = x;
        this.y = y;
        this.message = message;
        this.counter = counter;
        this.finished = false;
        this.type=type;
    }

    public void drawSelf(float delta, BitmapFont font,SpriteBatch b){
        if(type.equals("red")){
            font.setColor(Color.RED);
        }else if(type.equals("green")){
            font.setColor(Color.GREEN);
        }else if(type.equals("yellow")){
            font.setColor(Color.YELLOW);
        }
        if(counter>0){
            font.draw(b, message, x, y);
            counter-=delta;
        }else{
            finished=true;
        }
    }
}
