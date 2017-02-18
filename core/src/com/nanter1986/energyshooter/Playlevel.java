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

    public Playlevel(String music, String enemyType, String backImage, String movingBack,int laserFrequency,int id,int goal) {
        this.music = music;
        this.enemy = enemyType;
        this.backImage = backImage;
        this.movingBack=movingBack;
        this.laserFrequency=laserFrequency;
        this.gameStageID=id;
        this.goal=goal;
    }
}
