package com.nanter1986.energyshooter;

import com.nanter1986.energyshooter.Enemies.Enemy;
import com.nanter1986.energyshooter.Enemies.EnemyBad;
import com.nanter1986.energyshooter.Enemies.EnemySmallBlue;
import com.nanter1986.energyshooter.Enemies.EnemySuperBad;
import com.nanter1986.energyshooter.Enemies.EnemyUFO;
import com.nanter1986.energyshooter.Enemies.GreenFast;
import com.nanter1986.energyshooter.playerships.PlayerShip;

import java.util.Random;

/**
 * Created by user on 26/2/2017.
 */

public class EnemyCreator {
    public static Enemy createEnemies(int sff, int scW,int scH, PlayerShip spaceshipPlayer,int difficulty){
        Random randomSpawn = new Random();
        Random whereToRandomlySpawnX = new Random();
        int spawn = randomSpawn.nextInt(sff);
        int xRandom = whereToRandomlySpawnX.nextInt(scW);
        Enemy enemy=null;
        if(difficulty==0){
            if (spawn < 20) {
                enemy=new EnemySmallBlue(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 30) {
                enemy=new GreenFast(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }
        }else if(difficulty==1){
            if (spawn < 30) {
                enemy=new EnemySmallBlue(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 40) {
                enemy=new GreenFast(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }
            else if (spawn == 101) {
                enemy=new EnemyUFO(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }

        }else if(difficulty==2){
            if (spawn < 40) {
                enemy=new EnemySmallBlue(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 50) {
                enemy=new GreenFast(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }
            else if (spawn == 101) {
                enemy=new EnemyUFO(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }
        }else if(difficulty==3){
            if (spawn < 50) {
                enemy=new EnemySmallBlue(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 60) {
                enemy=new GreenFast(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }
            else if (spawn == 101) {
                enemy=new EnemyUFO(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }
        }else if(difficulty==4){
            if (spawn < 50) {
                enemy=new EnemySmallBlue(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 60) {
                enemy=new GreenFast(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            } else if (spawn == 101) {
                enemy=new EnemyUFO(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn == 102) {
                enemy=new EnemyBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }

        }else if(difficulty==5){
            if (spawn < 50) {
                enemy=new EnemySmallBlue(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 60) {
                enemy=new GreenFast(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            } else if (spawn == 101) {
                enemy=new EnemyUFO(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn == 102) {
                enemy=new EnemyBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn == 103) {
                enemy=new EnemySuperBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }
        }else if(difficulty==6){
            if (spawn < 50) {
                enemy=new EnemySmallBlue(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            } else if (spawn < 55) {
                enemy=new EnemyUFO(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 60) {
                enemy=new EnemyBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 65 ) {
                enemy=new EnemySuperBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 75) {
                enemy=new GreenFast(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }
        }else if(difficulty==7){
            if (spawn < 40) {
                enemy=new EnemySmallBlue(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            } else if (spawn < 50) {
                enemy=new EnemyUFO(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 60) {
                enemy=new EnemyBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 70 ) {
                enemy=new EnemySuperBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 80) {
                enemy=new GreenFast(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }
        }else if(difficulty==8){
            if (spawn < 30) {
                enemy=new EnemySmallBlue(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            } else if (spawn < 45) {
                enemy=new EnemyUFO(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 60) {
                enemy=new EnemyBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 75 ) {
                enemy=new EnemySuperBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 85) {
                enemy=new GreenFast(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }
        }else if(difficulty==9){
            if (spawn < 30) {
                enemy=new EnemyUFO(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 60) {
                enemy=new EnemyBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 90 ) {
                enemy=new EnemySuperBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 100) {
                enemy=new GreenFast(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }
        }else if(difficulty==10){
            if (spawn < 40) {
                enemy=new EnemyUFO(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 80) {
                enemy=new EnemyBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 120 ) {
                enemy=new EnemySuperBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }else if (spawn < 160) {
                enemy=new GreenFast(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }
        }else if(difficulty==11){
            if (spawn < 80) {
                enemy=new EnemySuperBad(xRandom, spaceshipPlayer.spaceshipY, scW, scH);
            }
        }

        return enemy;

    }
}
