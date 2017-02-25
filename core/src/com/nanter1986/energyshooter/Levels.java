package com.nanter1986.energyshooter;

import java.util.logging.Level;

/**
 * Created by user on 12/2/2017.
 */

public class Levels {
    public static Playlevel levelReturner(int gamestate){
        Playlevel l=null;
        if(gamestate==1) {
            l = new Playlevel("summer.mp3", "blue", "neptune.jpg", "meteor", 100, 1, 100);
        }else if(gamestate==2){
            l = new Playlevel("summer.mp3", "blue", "neptune.jpg", "meteor", 100, 2, 100);
        }else if(gamestate==2){
            l=new Playlevel("darkfuture.mp3","blue","uranus.jpg","meteor",110,2,120);
        }else if(gamestate==3){
            l=new Playlevel("graysky.mp3","red","saturn.png","meteor",12,3,140);
        }else if(gamestate==4){
            l=new Playlevel("summer.mp3","red","jupiter.jpg","meteor",12,3,160);
        }else if(gamestate==5){
            l=new Playlevel("graysky.mp3","red","mars.jpg","meteor",12,3,180);
        }else if(gamestate==6){
            l=new Playlevel("graysky.mp3","red","earth.jpg","meteor",12,3,200);
        }else if(gamestate==7){
            l=new Playlevel("graysky.mp3","red","earth.jpg","cloud",12,3,200);
        }
        return l;
    }
}
