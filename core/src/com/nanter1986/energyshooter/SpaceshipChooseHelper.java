package com.nanter1986.energyshooter;

import com.badlogic.gdx.Preferences;
import com.nanter1986.energyshooter.playerships.PlayerShip;
import com.nanter1986.energyshooter.playerships.PlayershipBlue;
import com.nanter1986.energyshooter.playerships.PlayershipGood;
import com.nanter1986.energyshooter.playerships.PlayershipManta;
import com.nanter1986.energyshooter.playerships.PlayershipPathFinder;
import com.nanter1986.energyshooter.playerships.PlayershipSinister;
import com.nanter1986.energyshooter.playerships.PlayershipWingship;
import com.nanter1986.energyshooter.playerships.f5s1;

/**
 * Created by user on 23/2/2017.
 */

public class SpaceshipChooseHelper {
    public static PlayerShip chosePlane(DisplayToolkit tool){
        PlayerShip ship=null;
        String choosen=tool.prefs.getString("choosenPlane","f5s1");
        if(choosen.equals("f5s1")){
            ship=new f5s1(tool.scW,tool.scH);
        }else if(choosen.equals("Bullet")){
            ship=new PlayershipGood(tool);
        }else if(choosen.equals("Sinister")){
            ship=new PlayershipSinister(tool.scW,tool.scH);
        }else if(choosen.equals("Bat")){
            ship=new PlayershipBlue(tool.scW,tool.scH);
        }else if(choosen.equals("Demon")){
            ship=new PlayershipWingship(tool.scW,tool.scH);
        }else if(choosen.equals("Manta")){
            ship=new PlayershipManta(tool);
        }else if(choosen.equals("PathFinder")){
            ship=new PlayershipPathFinder(tool.scW,tool.scH);
        }

        return ship;

    }
}
