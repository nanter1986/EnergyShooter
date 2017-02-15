package com.nanter1986.energyshooter;

import java.util.logging.Level;

/**
 * Created by user on 12/2/2017.
 */

public class Levels {
    public static Playlevel levelReturner(int gamestate){
        Playlevel l=null;
        if(gamestate==1){
            l=new Playlevel("summer.mp3","blue","neptune.jpg",5,3000,100,1,100);
        }else if(gamestate==2){
            l=new Playlevel("darkfuture.mp3","blue","uranus.jpg",6,2800,110,2,150);
        }else if(gamestate==3){
            l=new Playlevel("graysky.mp3","red","saturn.png",7,2600,12,3,200);
        }
        return l;
    }
}
