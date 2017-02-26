package com.nanter1986.energyshooter;

import java.util.logging.Level;

/**
 * Created by user on 12/2/2017.
 */

public class Levels {
    public static Playlevel levelReturner(int gamestate){
        Playlevel l=null;
        if(gamestate==1) {
            l = new Playlevel("Stage:Neptune 1/6","summer.mp3", "neptune.jpg", "meteor", 100,3000, 1, 50,0);
        }else if(gamestate==2){
            l = new Playlevel("Stage:Neptune 2/6","summer.mp3", "neptune.jpg", "meteor", 100,3000, 2, 70,1);
        }else if(gamestate==3){
            l=new Playlevel("Stage:Neptune 3/6","summer.mp3", "neptune.jpg","meteor",110,3000,3,90,2);
        }else if(gamestate==4){
            l=new Playlevel("Stage:Neptune 4/6","summer.mp3", "neptune.jpg","meteor",120,3000,4,100,3);
        }else if(gamestate==5){
            l=new Playlevel("Stage:Neptune 5/6","summer.mp3", "neptune.jpg","meteor",12,3000,5,120,4);
        }else if(gamestate==6){
            l=new Playlevel("Stage:Neptune 6/6","summer.mp3", "neptune.jpg","meteor",12,3000,6,140,5);
        }else if(gamestate==7){
            l=new Playlevel("Stage:Uranus 1/6","graysky.mp3", "uranus.jpg","meteor",12,3000,7,150,6);
        }else if(gamestate==8){
            l=new Playlevel("Stage:Uranus 2/6","graysky.mp3","uranus.jpg","meteor",12,3000,8,170,7);
        }else if(gamestate==9){
            l=new Playlevel("Stage:Uranus 3/6","graysky.mp3","uranus.jpg","meteor",12,3000,9,190,8);
        }else if(gamestate==10){
            l=new Playlevel("Stage:Uranus 4/6","graysky.mp3","uranus.jpg","meteor",12,3000,10,200,9);
        }else if(gamestate==11){
            l=new Playlevel("Stage:Uranus 5/6","graysky.mp3","uranus.jpg","meteor",12,3000,11,220,10);
        }else if(gamestate==12){
            l=new Playlevel("Stage:Uranus 6/6","graysky.mp3","uranus.jpg","meteor",12,3000,12,240,11);
        }else if(gamestate==13){
            l=new Playlevel("Stage:Saturn 1/6","graysky.mp3","saturn.png","meteor",12,3000,13,250,10);
        }else if(gamestate==14){
            l=new Playlevel("Stage:Saturn 2/6","graysky.mp3","saturn.png","meteor",12,3000,14,260,10);
        }else if(gamestate==15){
            l=new Playlevel("Stage:Saturn 3/6","graysky.mp3","saturn.png","meteor",12,3000,15,270,10);
        }else if(gamestate==16){
            l=new Playlevel("Stage:Saturn 4/6","graysky.mp3","saturn.png","meteor",12,3000,16,280,10);
        }else if(gamestate==17){
            l=new Playlevel("Stage:Saturn 5/6","graysky.mp3","saturn.png","meteor",12,3000,17,290,10);
        }else if(gamestate==18){
            l=new Playlevel("Stage:Saturn 6/6","graysky.mp3","saturn.png","meteor",12,3000,18,300,11);
        }else if(gamestate==19){
            l=new Playlevel("Stage:Jupiter 1/6","graysky.mp3","jupiter.jpg","meteor",12,3000,13,310,10);
        }else if(gamestate==20){
            l=new Playlevel("Stage:Jupiter 2/6","graysky.mp3","jupiter.jpg","meteor",12,3000,14,320,10);
        }else if(gamestate==21){
            l=new Playlevel("Stage:Jupiter 3/6","graysky.mp3","jupiter.jpg","meteor",12,3000,15,330,10);
        }else if(gamestate==22){
            l=new Playlevel("Stage:Jupiter 4/6","graysky.mp3","jupiter.jpg","meteor",12,3000,16,340,10);
        }else if(gamestate==23){
            l=new Playlevel("Stage:Jupiter 5/6","graysky.mp3","jupiter.jpg","meteor",12,3000,17,350,10);
        }else if(gamestate==24){
            l=new Playlevel("Stage:Jupiter 6/6","graysky.mp3","jupiter.jpg","meteor",12,3000,18,360,11);
        }else if(gamestate==25){
            l=new Playlevel("Stage:Mars 1/6","graysky.mp3","mars.jpg","meteor",12,3000,13,370,10);
        }else if(gamestate==26){
            l=new Playlevel("Stage:Mars 2/6","graysky.mp3","mars.jpg","meteor",12,3000,14,380,10);
        }else if(gamestate==27){
            l=new Playlevel("Stage:Mars 3/6","graysky.mp3","mars.jpg","meteor",12,3000,15,390,10);
        }else if(gamestate==28){
            l=new Playlevel("Stage:Mars 4/6","graysky.mp3","mars.jpg","meteor",12,3000,16,400,10);
        }else if(gamestate==29){
            l=new Playlevel("Stage:Mars 5/6","graysky.mp3","mars.jpg","meteor",12,3000,17,410,10);
        }else if(gamestate==30){
            l=new Playlevel("Stage:Mars 6/6","graysky.mp3","mars.jpg","meteor",12,3000,18,420,11);
        }else if(gamestate==31){
            l=new Playlevel("Stage:Earth 1/6","graysky.mp3","earth.jpg","meteor",12,3000,13,430,10);
        }else if(gamestate==32){
            l=new Playlevel("Stage:Earth 2/6","graysky.mp3","earth.jpg","meteor",12,3000,14,440,10);
        }else if(gamestate==33){
            l=new Playlevel("Stage:Earth 3/6","graysky.mp3","earth.jpg","meteor",12,3000,15,450,10);
        }else if(gamestate==34){
            l=new Playlevel("Stage:Earth 4/6","graysky.mp3","earth.jpg","meteor",12,3000,16,460,10);
        }else if(gamestate==35){
            l=new Playlevel("Stage:Earth 5/6","graysky.mp3","earth.jpg","meteor",12,3000,17,470,10);
        }else if(gamestate==36){
            l=new Playlevel("Stage:Earth 6/6","graysky.mp3","earth.jpg","meteor",12,3000,18,480,11);
        }
        return l;
    }
}
