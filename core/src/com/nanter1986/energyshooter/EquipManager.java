package com.nanter1986.energyshooter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.nanter1986.energyshooter.Artifacts.Artifact;
import com.nanter1986.energyshooter.Buttons.EquipProceed;
import com.nanter1986.energyshooter.Buttons.EquipSlotLeft1;
import com.nanter1986.energyshooter.Buttons.EquipSlotLeft2;
import com.nanter1986.energyshooter.Buttons.EquipSlotRight1;
import com.nanter1986.energyshooter.Buttons.EquipSlotRight2;
import com.nanter1986.energyshooter.Buttons.TouchableButtons;

import java.util.ArrayList;

/**
 * Created by user on 21/2/2017.
 */

public class EquipManager {
    public static int whichSlot;
    private static Artifact first;
    private static Artifact second;
    private static int it1 = 0;
    private static int it2 = 1;
    public static TouchableButtons left1;
    public static TouchableButtons left2;
    public static TouchableButtons right1;
    public static TouchableButtons right2;
    public static TouchableButtons proceed;
    public static boolean madeButtons = false;

    public static ArrayList<Artifact> manageEquipment(ArrayList<Artifact> artifacts, int numOfslots, DisplayToolkit tool) {

        if (madeButtons == false) {
            makeButtons(tool);
        }
        ArrayList<Artifact> readyArt = new ArrayList<Artifact>();
        if (artifacts.size() == 0) {

        } else {

            if (left1.isButtonTouched()) {
                it1--;
                if (it1 < 0) {
                    it1 = artifacts.size() - 1;
                }
                first = artifacts.get(it1);

            } else if (right1.isButtonTouched()) {
                it1++;
                if (it1 >= artifacts.size()) {
                    it1 = 0;
                }

            } else if (left2.isButtonTouched()) {
                it2--;
                if (it2 < 0) {
                    it2 = artifacts.size() - 1;
                }
                second = artifacts.get(it2);
            } else if (right2.isButtonTouched()) {
                it2++;
                if (it2 >= artifacts.size()) {
                    it2 = 0;
                }

            }else if (proceed.isButtonTouched()) {
                it2++;
                if (it2 >= artifacts.size()) {
                    it2 = 0;
                }

            }


        }

        first = artifacts.get(it1);
        second = artifacts.get(it2);

        readyArt.add(first);
        readyArt.add(second);

        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        tool.batch.draw(left1.texture,left1.buttonX,left1.buttonY,left1.buttonW,left1.buttonH);
        tool.batch.draw(left2.texture,left2.buttonX,left2.buttonY,left2.buttonW,left2.buttonH);
        tool.batch.draw(right1.texture,right1.buttonX,right1.buttonY,right1.buttonW,right1.buttonH);
        tool.batch.draw(right2.texture,right2.buttonX,right2.buttonY,right2.buttonW,right2.buttonH);
        tool.batch.draw(proceed.texture,proceed.buttonX,proceed.buttonY,proceed.buttonW,proceed.buttonH);
        tool.font.draw(tool.batch,first.name,left1.buttonX+left1.buttonW*2,left1.buttonY+left1.buttonH/2);
        tool.font.draw(tool.batch,second.name,left2.buttonX+left2.buttonW*2,left2.buttonY+left2.buttonH/2);
        tool.font.draw(tool.batch,"CHOOSE EQUIPMENT", tool.scW*3/10,tool.scH*9/10);
        tool.batch.end();


        Gdx.app.log("it", "it" + it1 + " " + it2 + " " + whichSlot);

        return readyArt;
    }

    public static void makeButtons(DisplayToolkit tool) {
        left1 = new EquipSlotLeft1(tool);
        left2 = new EquipSlotLeft2(tool);
        right1 = new EquipSlotRight1(tool);
        right2 = new EquipSlotRight2(tool);
        proceed=new EquipProceed(tool);
        madeButtons = true;
    }
}
