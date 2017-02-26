package com.nanter1986.energyshooter;

/**
 * Created by user on 12/2/2017.
 */
public class Playlevel {
    String music;
    String enemy;
    String backImage;
    String movingBack;
    int laserFrequency;
    int gameStageID;
    int goal;
    int sff;
    int dif;
    public String title;

    public Playlevel(String title,String music, String backImage, String movingBack,int laserFrequency,int spawmFreq,int id,int goal,int difficulty) {
        this.title=title;
        this.music = music;
        this.backImage = backImage;
        this.movingBack=movingBack;
        this.laserFrequency=laserFrequency;
        this.gameStageID=id;
        this.goal=goal;
        this.sff=spawmFreq;
        this.dif=difficulty;
    }
}
