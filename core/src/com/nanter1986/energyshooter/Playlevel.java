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
    int gameStageID;

    public Playlevel(String music, String enemy, String backImage, int enemyHealth, int spawnFrequency,int id) {
        this.music = music;
        this.enemy = enemy;
        this.backImage = backImage;
        this.enemyHealth = enemyHealth;
        this.spawnFrequency = spawnFrequency;
        this.gameStageID=id;
    }
}
