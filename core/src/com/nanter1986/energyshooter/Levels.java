package com.nanter1986.energyshooter;

import java.util.logging.Level;

/**
 * Created by user on 12/2/2017.
 */

public class Levels {
    public static Playlevel levelReturner(String s){
        Playlevel l=null;
        if(s.equals("milkyWay")){
            l=new Playlevel("summer.mp3","enemyBlue1.png","milky.jpeg",5,100,1);
        }else if(s.equals("mars")){
            l=new Playlevel("darkfuture.mp3","enemyBlue1.png","mars.jpg",6,90,2);
        }
        return l;
    }
}
