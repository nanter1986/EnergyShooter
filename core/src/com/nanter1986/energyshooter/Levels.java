package com.nanter1986.energyshooter;

import java.util.logging.Level;

/**
 * Created by user on 12/2/2017.
 */

public class Levels {
    public static Playlevel levelReturner(int gamestate){
        Playlevel l=null;
        if(gamestate==1) {
            l = new Playlevel("summer.mp3", "neptune.jpg", "meteor", 100,3000, 1, 50,0);
        }else if(gamestate==2){
            l = new Playlevel("summer.mp3", "neptune.jpg", "meteor", 100,3000, 2, 70,1);
        }else if(gamestate==3){
            l=new Playlevel("summer.mp3", "neptune.jpg","meteor",110,3000,3,90,2);
        }else if(gamestate==4){
            l=new Playlevel("summer.mp3", "neptune.jpg","meteor",120,3000,4,100,3);
        }else if(gamestate==5){
            l=new Playlevel("summer.mp3", "neptune.jpg","meteor",12,3000,5,120,4);
        }else if(gamestate==6){
            l=new Playlevel("summer.mp3", "neptune.jpg","meteor",12,3000,6,140,5);
        }else if(gamestate==7){
            l=new Playlevel("summer.mp3", "neptune.jpg","meteor",12,3000,7,150,6);
        }else if(gamestate==8){
            l=new Playlevel("graysky.mp3","uranus.jpg","cloud",12,3000,8,170,7);
        }else if(gamestate==9){
            l=new Playlevel("graysky.mp3","uranux.jpg","cloud",12,3000,9,190,8);
        }else if(gamestate==10){
            l=new Playlevel("graysky.mp3","uranux.jpg","cloud",12,3000,10,200,9);
        }else if(gamestate==11){
            l=new Playlevel("graysky.mp3","uranux.jpg","cloud",12,3000,11,220,10);
        }else if(gamestate==12){
            l=new Playlevel("graysky.mp3","uranux.jpg","cloud",12,3000,12,240,11);
        }
        return l;
    }
}
