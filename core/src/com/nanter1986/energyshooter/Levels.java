package com.nanter1986.energyshooter;

import java.util.logging.Level;

/**
 * Created by user on 12/2/2017.
 */

public class Levels {
    public static Playlevel levelReturner(String s){
        Playlevel l=null;
        if(s.equals("milkyWay")){
            l=new Playlevel("summer.mp3","blue","milky.jpeg",5,100,150,1);
        }else if(s.equals("mars")){
            l=new Playlevel("darkfuture.mp3","blue","mars.jpg",6,90,135,2);
        }else if(s.equals("jupiter")){
            l=new Playlevel("graysky.mp3","red","jupiter.jpg",7,80,120,3);
        }
        return l;
    }
}
