package com.nanter1986.energyshooter;

import java.util.logging.Level;

/**
 * Created by user on 12/2/2017.
 */

public class Levels {
    public static Playlevel levelReturner(int gamestate){
        Playlevel l=null;
        if(gamestate==1) {
            l = new Playlevel("Stage:Neptune 1/6","neptune.ogg", "neptune.jpg", "meteor", 100,3000, 1, 50,0);
        }else if(gamestate==2){
            l = new Playlevel("Stage:Neptune 2/6","neptune.ogg", "neptune.jpg", "meteor", 100,3000, 2, 70,1);
        }else if(gamestate==3){
            l=new Playlevel("Stage:Neptune 3/6","neptune.ogg", "neptune.jpg","meteor",110,3000,3,90,1);
        }else if(gamestate==4){
            l=new Playlevel("Stage:Neptune 4/6","neptune.ogg", "neptune.jpg","meteor",120,3000,4,100,2);
        }else if(gamestate==5){
            l=new Playlevel("Stage:Neptune 5/6","neptune.ogg", "neptune.jpg","meteor",12,3000,5,120,2);
        }else if(gamestate==6){
            l=new Playlevel("Stage:Neptune 6/6","neptune.ogg", "neptune.jpg","meteor",12,3000,6,140,2);
        }else if(gamestate==7){
            l=new Playlevel("Stage:Uranus 1/6","uranus.ogg", "uranus.jpg","meteor",12,3000,7,150,3);
        }else if(gamestate==8){
            l=new Playlevel("Stage:Uranus 2/6","uranus.ogg","uranus.jpg","meteor",12,3000,8,170,3);
        }else if(gamestate==9){
            l=new Playlevel("Stage:Uranus 3/6","uranus.ogg","uranus.jpg","meteor",12,3000,9,190,3);
        }else if(gamestate==10){
            l=new Playlevel("Stage:Uranus 4/6","uranus.ogg","uranus.jpg","meteor",12,3000,10,200,4);
        }else if(gamestate==11){
            l=new Playlevel("Stage:Uranus 5/6","uranus.ogg","uranus.jpg","meteor",12,3000,11,220,4);
        }else if(gamestate==12){
            l=new Playlevel("Stage:Uranus 6/6","uranus.ogg","uranus.jpg","meteor",12,3000,12,240,4);
        }else if(gamestate==13){
            l=new Playlevel("Stage:Saturn 1/6","saturn.ogg","saturn.png","meteor",12,3000,13,250,5);
        }else if(gamestate==14){
            l=new Playlevel("Stage:Saturn 2/6","saturn.ogg","saturn.png","meteor",12,3000,14,260,5);
        }else if(gamestate==15){
            l=new Playlevel("Stage:Saturn 3/6","saturn.ogg","saturn.png","meteor",12,3000,15,270,5);
        }else if(gamestate==16){
            l=new Playlevel("Stage:Saturn 4/6","saturn.ogg","saturn.png","meteor",12,3000,16,280,6);
        }else if(gamestate==17){
            l=new Playlevel("Stage:Saturn 5/6","saturn.ogg","saturn.png","meteor",12,3000,17,290,6);
        }else if(gamestate==18){
            l=new Playlevel("Stage:Saturn 6/6","saturn.ogg","saturn.png","meteor",12,3000,18,300,6);
        }else if(gamestate==19){
            l=new Playlevel("Stage:Jupiter 1/6","jupiter.ogg","jupiter.jpg","meteor",12,3000,19,310,7);
        }else if(gamestate==20){
            l=new Playlevel("Stage:Jupiter 2/6","jupiter.ogg","jupiter.jpg","meteor",12,3000,20,320,7);
        }else if(gamestate==21){
            l=new Playlevel("Stage:Jupiter 3/6","jupiter.ogg","jupiter.jpg","meteor",12,3000,21,330,7);
        }else if(gamestate==22){
            l=new Playlevel("Stage:Jupiter 4/6","jupiter.ogg","jupiter.jpg","meteor",12,3000,22,340,7);
        }else if(gamestate==23){
            l=new Playlevel("Stage:Jupiter 5/6","jupiter.ogg","jupiter.jpg","meteor",12,3000,23,350,7);
        }else if(gamestate==24){
            l=new Playlevel("Stage:Jupiter 6/6","jupiter.ogg","jupiter.jpg","meteor",12,3000,24,360,7);
        }else if(gamestate==25){
            l=new Playlevel("Stage:Mars 1/6","mars.ogg","mars.jpg","meteor",12,3000,25,370,8);
        }else if(gamestate==26){
            l=new Playlevel("Stage:Mars 2/6","mars.ogg","mars.jpg","meteor",12,3000,26,380,8);
        }else if(gamestate==27){
            l=new Playlevel("Stage:Mars 3/6","mars.ogg","mars.jpg","meteor",12,3000,27,390,8);
        }else if(gamestate==28){
            l=new Playlevel("Stage:Mars 4/6","mars.ogg","mars.jpg","meteor",12,3000,28,400,8);
        }else if(gamestate==29){
            l=new Playlevel("Stage:Mars 5/6","mars.ogg","mars.jpg","meteor",12,3000,29,410,8);
        }else if(gamestate==30){
            l=new Playlevel("Stage:Mars 6/6","mars.ogg","mars.jpg","meteor",12,3000,30,420,8);
        }else if(gamestate==31){
            l=new Playlevel("Stage:Earth 1/6","earth.ogg","earth.jpg","meteor",12,3000,31,430,9);
        }else if(gamestate==32){
            l=new Playlevel("Stage:Earth 2/6","earth.ogg","earth.jpg","meteor",12,3000,32,440,9);
        }else if(gamestate==33){
            l=new Playlevel("Stage:Earth 3/6","earth.ogg","earth.jpg","meteor",12,3000,33,450,9);
        }else if(gamestate==34){
            l=new Playlevel("Stage:Earth 4/6","earth.ogg","earth.jpg","meteor",12,3000,34,460,9);
        }else if(gamestate==35){
            l=new Playlevel("Stage:Earth 5/6","earth.ogg","earth.jpg","meteor",12,3000,35,470,9);
        }else if(gamestate==36){
            l=new Playlevel("Stage:Earth 6/6","earth.ogg","earth.jpg","meteor",12,3000,36,480,9);
        }else if(gamestate==37){
            l=new Playlevel("Stage:Africa 1/6","africa.ogg","africa.png","cloud",12,3000,37,500,10);
        }else if(gamestate==38){
            l=new Playlevel("Stage:Africa 2/6","africa.ogg","africa.png","cloud",12,3000,38,500,10);
        }else if(gamestate==39){
            l=new Playlevel("Stage:Africa 3/6","africa.ogg","africa.png","cloud",12,3000,39,500,10);
        }else if(gamestate==40){
            l=new Playlevel("Stage:Africa 4/6","africa.ogg","africa.png","cloud",12,3000,40,500,10);
        }else if(gamestate==41){
            l=new Playlevel("Stage:Africa 5/6","africa.ogg","africa.png","cloud",12,3000,41,500,10);
        }else if(gamestate==42){
            l=new Playlevel("Stage:Africa 6/6","africa.ogg","africa.png","cloud",12,3000,42,500,10);
        }else if(gamestate==43){
            l=new Playlevel("Stage:Asia 1/6","europe.ogg","asia.png","cloud",12,3000,42,500,11);
        }else if(gamestate==44){
            l=new Playlevel("Stage:Asia 2/6","europe.ogg","asia.png","cloud",12,3000,42,510,11);
        }else if(gamestate==45){
            l=new Playlevel("Stage:Asia 3/6","europe.ogg","asia.png","cloud",12,3000,42,520,11);
        }else if(gamestate==46){
            l=new Playlevel("Stage:Asia 4/6","europe.ogg","asia.png","cloud",12,3000,42,530,11);
        }else if(gamestate==47){
            l=new Playlevel("Stage:Asia 5/6","europe.ogg","asia.png","cloud",12,3000,42,540,11);
        }else if(gamestate==48){
            l=new Playlevel("Stage:Asia 6/6","europe.ogg","asia.png","cloud",12,3000,42,550,11);
        }else if(gamestate==49){
            l=new Playlevel("Stage:Australia 1/6","australia.ogg","australia.png","cloud",12,3000,42,560,12);
        }else if(gamestate==50){
            l=new Playlevel("Stage:Australia 2/6","australia.ogg","australia.png","cloud",12,3000,42,570,12);
        }else if(gamestate==51){
            l=new Playlevel("Stage:Australia 3/6","australia.ogg","australia.png","cloud",12,3000,42,580,12);
        }else if(gamestate==52){
            l=new Playlevel("Stage:Australia 4/6","australia.ogg","australia.png","cloud",12,3000,42,590,12);
        }else if(gamestate==53){
            l=new Playlevel("Stage:Australia 5/6","australia.ogg","australia.png","cloud",12,3000,42,600,12);
        }else if(gamestate==54){
            l=new Playlevel("Stage:Australia 6/6","australia.ogg","australia.png","cloud",12,3000,42,610,12);
        }else if(gamestate==55){
            l=new Playlevel("Stage:Europe 1/6","europe.ogg","europe.png","cloud",12,3000,42,620,13);
        }else if(gamestate==56){
            l=new Playlevel("Stage:Europe 2/6","europe.ogg","europe.png","cloud",12,3000,42,630,13);
        }else if(gamestate==57){
            l=new Playlevel("Stage:Europe 3/6","europe.ogg","europe.png","cloud",12,3000,42,640,13);
        }else if(gamestate==58){
            l=new Playlevel("Stage:Europe 4/6","europe.ogg","europe.png","cloud",12,3000,42,650,13);
        }else if(gamestate==59){
            l=new Playlevel("Stage:Europe 5/6","europe.ogg","europe.png","cloud",12,3000,42,660,13);
        }else if(gamestate==60){
            l=new Playlevel("Stage:Europe 6/6","europe.ogg","europe.png","cloud",12,3000,42,670,13);
        }else if(gamestate==61){
            l=new Playlevel("Stage:N.America 1/6","europe.ogg","northamerica.png","cloud",12,3000,42,680,14);
        }else if(gamestate==62){
            l=new Playlevel("Stage:N.America 2/6","europe.ogg","northamerica.png","cloud",12,3000,42,690,14);
        }else if(gamestate==63){
            l=new Playlevel("Stage:N.America 3/6","europe.ogg","northamerica.png","cloud",12,3000,42,700,14);
        }else if(gamestate==64){
            l=new Playlevel("Stage:N.America 4/6","europe.ogg","northamerica.png","cloud",12,3000,42,710,14);
        }else if(gamestate==65){
            l=new Playlevel("Stage:N.America 5/6","europe.ogg","northamerica.png","cloud",12,3000,42,720,14);
        }else if(gamestate==66){
            l=new Playlevel("Stage:N.America 6/6","europe.ogg","northamerica.png","cloud",12,3000,42,730,14);
        }else if(gamestate==67){
            l=new Playlevel("Stage:S.America 1/6","southamerica.ogg","southamerica.png","cloud",12,3000,42,740,15);
        }else if(gamestate==68){
            l=new Playlevel("Stage:S.America 2/6","southamerica.ogg","southamerica.png","cloud",12,3000,42,750,15);
        }else if(gamestate==69){
            l=new Playlevel("Stage:S.America 3/6","southamerica.ogg","southamerica.png","cloud",12,3000,42,760,15);
        }else if(gamestate==70){
            l=new Playlevel("Stage:S.America 4/6","southamerica.ogg","southamerica.png","cloud",12,3000,42,770,15);
        }else if(gamestate==71){
            l=new Playlevel("Stage:S.America 5/6","southamerica.ogg","southamerica.png","cloud",12,3000,42,780,15);
        }else if(gamestate==72){
            l=new Playlevel("Stage:S.America 6/6","southamerica.ogg","southamerica.png","cloud",12,3000,42,790,15);
        }else if(gamestate==73){
            l=new Playlevel("Stage:Antarctica 1/3","europe.ogg","antarctica.png","cloud",12,3000,42,1000,20);
        }else if(gamestate==74){
            l=new Playlevel("Stage:Antarctica 2/3","europe.ogg","antarctica.png","cloud",12,3000,42,1000,21);
        }else if(gamestate==75){
            l=new Playlevel("Stage:Antarctica 3/3","europe.ogg","antarctica.png","cloud",12,3000,42,1,22);
        }
        return l;
    }
}
