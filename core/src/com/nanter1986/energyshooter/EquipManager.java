package com.nanter1986.energyshooter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.nanter1986.energyshooter.Artifacts.Artifact;

import java.util.ArrayList;

/**
 * Created by user on 21/2/2017.
 */

public class EquipManager {
    public static int whichSlot ;
    private static Artifact first;
    private static Artifact second;
    private static int it1=0;
    private static int it2=1;

    public static ArrayList<Artifact> manageEquipment(ArrayList<Artifact> artifacts,int numOfslots) {
        artifacts.remove(first);
        artifacts.remove(second);
        Gdx.app.log("size",""+artifacts.size());
        ArrayList<Artifact>readyArt=new ArrayList<Artifact>();
        if(artifacts.size()==0){

        }else{
            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {

                if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                    whichSlot++;
                    if(whichSlot>=numOfslots){
                        whichSlot=0;
                    }
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                    whichSlot--;
                    if(whichSlot<0){
                        whichSlot=numOfslots-1;
                    }
                }
                if (whichSlot == 0) {
                    if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                        it1--;
                        if (it1 < 0) {
                            it1 = artifacts.size() - 1;
                        }
                        first=artifacts.get(it1);

                    } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                        it1++;
                        if (it1 >= artifacts.size()) {
                            it1 = 0;
                        }
                        first=artifacts.get(it1);
                    }



                } else if (whichSlot == 1) {
                    if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                        it2--;
                        if (it2 < 0) {
                            it2 = artifacts.size() - 1;
                        }
                        second=artifacts.get(it2);
                    } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                        it2++;
                        if (it2 >= artifacts.size()) {
                            it2 = 0;
                        }
                        second=artifacts.get(it2);
                    }




                }
            }

            readyArt.add(first);
            readyArt.add(second);
        }


        Gdx.app.log("it","it"+it1+" "+it2 +" "+whichSlot);

        return readyArt;
    }
}
