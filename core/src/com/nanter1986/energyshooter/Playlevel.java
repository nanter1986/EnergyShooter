package com.nanter1986.energyshooter;

/**
 * Created by user on 12/2/2017.
 */
public class Playlevel {
    String music;
    String enemy;
    String backImage;
    int enemyHealth;
    int spawnFrequency;
    int laserFrequency;
    int gameStageID;

    public Playlevel(String music, String enemyType, String backImage, int enemyHealth, int spawnFrequency,int laserFrequency,int id) {
        this.music = music;
        this.enemy = enemyType;
        this.backImage = backImage;
        this.enemyHealth = enemyHealth;
        this.spawnFrequency = spawnFrequency;
        this.laserFrequency=laserFrequency;
        this.gameStageID=id;
    }
}
