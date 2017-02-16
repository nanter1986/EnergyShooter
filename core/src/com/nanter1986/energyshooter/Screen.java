package com.nanter1986.energyshooter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 11/2/2017.
 */

public class Screen extends ScreenAdapter {

    private static final Color BACKGROUND_COLOR = new Color(0f, 0f, 0f, 1.0f);

    private static final Texture neptune=new Texture(Gdx.files.internal("neptune.jpg"));


    Playlevel l;
    SpriteBatch batch;
    private OrthographicCamera camera;
    BitmapFont font;

    int fps;
    int fpsHelper;

    int screenWidth;
    int screenHeight;

    Music backgroundMusic;
    Sound explosionBig;
    Sound explosionSmall;
    Sound laserSound;
    Sound touchedEnemyLaser;

    PlayerShip spaceshipPlayer;

    private Texture level;
    private Texture laser;
    private Texture explosion;


    ArrayList<Enemy> enemies;
    ArrayList<LaserOfEnemy> laserOfEnemies;
    ArrayList<LaserOfPlayer> laserOfPlayer;
    ArrayList<BackGround> backPlanets;
    ArrayList<InstructionDrawer> instructions;


    private boolean cooledDown;
    private boolean nukedInformed;
    private boolean touchedDown;


    private int stateOfGame = 1;
    private int howOftenSpawn;
    private int howOftenLaser;
    private int enemyHealth;
    private float timeLeftToReload;
    private int killsTotal;
    private int killsRequired;

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched() && spaceshipPlayer.died == true) {
            backgroundMusic.dispose();
            batch.dispose();
            show();
        }
        if (killsTotal > killsRequired-1) {
            backgroundMusic.dispose();
            batch.dispose();
            stateOfGame++;
            show();
        }
        //nukeField();
        checkHealth();
        createEnemies();
        createBackgrounds();
        updatePosition();
        updateCamera();
        camera.update();

        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawLevelBackground();

        drawSpaceship();

        drawEnemies();
        drawLaser(delta,enemies);


        drawEnemyLasers();
        drawFonts(delta);
        batch.end();
    }

    private void nukeField() {
        if(spaceshipPlayer.spaceshipHealth>30){
            if(nukedInformed==false){
                instructions.add(new InstructionDrawer(0,100,"Touch screen to nuke the field",3.0f,"yellow"));
            }
            nukedInformed=true;
        }
        if (spaceshipPlayer.spaceshipHealth > 30) {
            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                if (Gdx.input.justTouched()) {
                    for (Enemy e : enemies) {
                        e.health = 0;
                    }
                    spaceshipPlayer.spaceshipHealth -= 20;
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX,spaceshipPlayer.spaceshipY,"-20",1.0f,"red"));
                    nukedInformed=false;
                }

            } else if (Gdx.app.getType() == Application.ApplicationType.Android) {
                if (Gdx.input.justTouched()) {
                    for (Enemy e : enemies) {
                        e.health = 0;
                    }
                    spaceshipPlayer.spaceshipHealth -= 20;
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX,spaceshipPlayer.spaceshipY,"-20",1.0f,"red"));
                    nukedInformed=false;

                }
            }
        }
    }

    private void createBackgrounds() {
        Random randomSpawn = new Random();
        int create = randomSpawn.nextInt(70);
        if (create == 1) {
            backPlanets.add(new BackGround(spaceshipPlayer, screenWidth, screenHeight));
        }
    }

    private void drawLevelBackground() {
        batch.draw(level, 0, spaceshipPlayer.spaceshipY, screenWidth , screenHeight);
        ArrayList<BackGround> toRemove = new ArrayList<BackGround>();
        for (BackGround b : backPlanets) {
            if (b.passedShip == true) {
                toRemove.add(b);
            }
        }
        for (BackGround b : backPlanets) {

            b.updatePosition(batch, spaceshipPlayer.spaceshipY);


        }
        backPlanets.removeAll(toRemove);
    }

    private void drawLaser(float d,ArrayList<Enemy>enemies) {
        if (Gdx.input.isTouched() && spaceshipPlayer.spaceshipHealth>10){
            touchedDown=true;
        }else{
            touchedDown=false;
        }
        if (cooledDown == false) {
            timeLeftToReload -= d;
            if (timeLeftToReload < 0.01f && spaceshipPlayer.spaceshipHealth>0) {
                cooledDown = true;
            }
        } else {
            if (spaceshipPlayer.spaceshipHealth > 199) {
                LaserOfPlayer a = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "straightS", screenWidth, screenHeight);
                LaserOfPlayer b = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, spaceshipPlayer.spaceshipY, "midL", screenWidth, screenHeight);
                LaserOfPlayer c = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, spaceshipPlayer.spaceshipY, "midR", screenWidth, screenHeight);
                LaserOfPlayer f = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, spaceshipPlayer.spaceshipY, "sideL", screenWidth, screenHeight);
                LaserOfPlayer e = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, spaceshipPlayer.spaceshipY, "sideR", screenWidth, screenHeight);
                laserOfPlayer.add(a);
                laserOfPlayer.add(b);
                laserOfPlayer.add(c);
                laserOfPlayer.add(f);
                laserOfPlayer.add(e);
                a.playSound();
                b.playSound();
                c.playSound();
                f.playSound();
                e.playSound();
                cooledDown = false;
                if(touchedDown==false){
                    timeLeftToReload = 0.1f;
                }else{
                    timeLeftToReload = 0.02f;
                    spaceshipPlayer.spaceshipHealth--;
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX+spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipY+spaceshipPlayer.spaceshipH,"-1",1.0f,"red"));

                }


            } else if (spaceshipPlayer.spaceshipHealth > 99) {

                LaserOfPlayer a = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "straightS", screenWidth, screenHeight);
                LaserOfPlayer b = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "midL", screenWidth, screenHeight);
                LaserOfPlayer c = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "midR", screenWidth, screenHeight);
                LaserOfPlayer f = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "sideL", screenWidth, screenHeight);
                LaserOfPlayer e = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "sideR", screenWidth, screenHeight);
                laserOfPlayer.add(a);
                laserOfPlayer.add(b);
                laserOfPlayer.add(c);
                laserOfPlayer.add(f);
                laserOfPlayer.add(e);
                a.playSound();
                b.playSound();
                c.playSound();
                f.playSound();
                e.playSound();
                cooledDown = false;
                if(touchedDown==false){
                    timeLeftToReload = 0.3f;
                }else{
                    timeLeftToReload = 0.1f;
                    spaceshipPlayer.spaceshipHealth--;
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX+spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipY+spaceshipPlayer.spaceshipH,"-1",1.0f,"red"));

                }


            } else if (spaceshipPlayer.spaceshipHealth > 49) {

                LaserOfPlayer a = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "straightS", screenWidth, screenHeight);
                LaserOfPlayer b = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "midL", screenWidth, screenHeight);
                LaserOfPlayer c = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "midR", screenWidth, screenHeight);
                LaserOfPlayer f = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "sideL", screenWidth, screenHeight);
                LaserOfPlayer e = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "sideR", screenWidth, screenHeight);
                laserOfPlayer.add(a);
                laserOfPlayer.add(b);
                laserOfPlayer.add(c);
                laserOfPlayer.add(f);
                laserOfPlayer.add(e);
                a.playSound();
                b.playSound();
                c.playSound();
                f.playSound();
                e.playSound();
                cooledDown = false;
                if(touchedDown==false){
                    timeLeftToReload = 0.5f;
                }else{
                    timeLeftToReload = 0.12f;
                    spaceshipPlayer.spaceshipHealth--;
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX+spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipY+spaceshipPlayer.spaceshipH,"-1",1.0f,"red"));

                }


            } else if (spaceshipPlayer.spaceshipHealth > 29) {


                LaserOfPlayer b = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "midL", screenWidth, screenHeight);
                LaserOfPlayer c = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "midR", screenWidth, screenHeight);
                LaserOfPlayer f = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "sideL", screenWidth, screenHeight);
                LaserOfPlayer e = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "sideR", screenWidth, screenHeight);

                laserOfPlayer.add(b);
                laserOfPlayer.add(c);
                laserOfPlayer.add(f);
                laserOfPlayer.add(e);

                b.playSound();
                c.playSound();
                f.playSound();
                e.playSound();
                cooledDown = false;
                if(touchedDown==false){
                    timeLeftToReload = 0.5f;
                }else{
                    timeLeftToReload = 0.12f;
                    spaceshipPlayer.spaceshipHealth--;
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX+spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipY+spaceshipPlayer.spaceshipH,"-1",1.0f,"red"));

                }


            } else if (spaceshipPlayer.spaceshipHealth > 19) {

                LaserOfPlayer a = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "straightL", screenWidth, screenHeight);
                LaserOfPlayer b = new LaserOfPlayer(spaceshipPlayer.spaceshipX + spaceshipPlayer.spaceshipW / 2, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "straightR", screenWidth, screenHeight);
                laserOfPlayer.add(a);
                laserOfPlayer.add(b);
                a.playSound();
                b.playSound();
                cooledDown = false;
                if(touchedDown==false){
                    timeLeftToReload = 0.5f;
                }else{
                    timeLeftToReload = 0.12f;
                    spaceshipPlayer.spaceshipHealth--;
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX+spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipY+spaceshipPlayer.spaceshipH,"-1",1.0f,"red"));

                }


            } else {

                LaserOfPlayer a = new LaserOfPlayer(spaceshipPlayer.spaceshipX + spaceshipPlayer.spaceshipW / 2, spaceshipPlayer.spaceshipY, spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipH, "straightS", screenWidth, screenHeight);
                laserOfPlayer.add(a);
                a.playSound();
                cooledDown = false;
                if(touchedDown==false){
                    timeLeftToReload = 0.5f;
                }else{
                    timeLeftToReload = 0.12f;
                    spaceshipPlayer.spaceshipHealth--;
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX+spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipY+spaceshipPlayer.spaceshipH,"-1",1.0f,"red"));

                }


            }

        }
        ArrayList<LaserOfPlayer> toRemove = new ArrayList<LaserOfPlayer>();
        for (LaserOfPlayer l : laserOfPlayer) {
            if (l.y > spaceshipPlayer.spaceshipY + screenHeight || l.x < 0 || l.x > screenWidth || l.exploded==true) {
                toRemove.add(l);
            }
        }

        laserOfPlayer.removeAll(toRemove);



            for (LaserOfPlayer l : laserOfPlayer) {
                l.updatePosition(batch);
                for(Enemy e:enemies) {
                    int damage=l.dealDamage(e);
                    e.health-=damage;
                }
            }





    }

    private void drawSpaceship() {
        spaceshipPlayer.updatePosition(batch);

    }

    private void checkHealth() {
        if (spaceshipPlayer.spaceshipHealth == 0) {

        }
    }

    private void drawInstructions(float delta,String message,int x,int y){
        int fpscounter=0;
        if (fpsHelper < 200) {
            font.draw(batch, message, x, y);
            fpscounter++;
        }
    }

    private void drawFonts(float delta) {
        fpsHelper++;
        if (fpsHelper == 200) {
            fps = (int) ( 1 / delta);
            fpsHelper = 0;
        }

        font.setColor(Color.WHITE);
        font.draw(batch, "FPS:" + fps, 0, spaceshipPlayer.spaceshipY + 75);
        if (spaceshipPlayer.spaceshipHealth < 1) {
            font.setColor(Color.WHITE);
            font.draw(batch, "Game Over", 200, spaceshipPlayer.spaceshipY + 200);
            font.draw(batch, "Energy:0", 0, spaceshipPlayer.spaceshipY + 50);
        } else {
            font.setColor(Color.WHITE);
            font.draw(batch, "Energy:" + spaceshipPlayer.spaceshipHealth, 0, spaceshipPlayer.spaceshipY + 50);
            font.draw(batch, "Kills:" + killsTotal+"/"+killsRequired, 0, spaceshipPlayer.spaceshipY + 25);
        }

        for(InstructionDrawer i:instructions){
            if(i.finished==false){
                i.drawSelf(delta,font,batch);
            }
        }
        ArrayList<InstructionDrawer> toRemove = new ArrayList<InstructionDrawer>();
        for (InstructionDrawer i:instructions) {
            if (i.finished == true ) {

                toRemove.add(i);

            }
        }
        instructions.removeAll(toRemove);
    }

    private void drawEnemies() {
        ArrayList<Enemy> toRemove = new ArrayList<Enemy>();
        for (Enemy e : enemies) {
            if (e.exploded == true || e.y < spaceshipPlayer.spaceshipY) {

                toRemove.add(e);

            }
        }
        enemies.removeAll(toRemove);
        for (Enemy e : enemies) {
            if (e.health > 0 && e.y > spaceshipPlayer.spaceshipY && e.x > 0 && e.x < screenWidth) {
                e.updatePosition(batch,spaceshipPlayer);
                int damage = e.checkCollisionWithPlayer(spaceshipPlayer);
                spaceshipPlayer.spaceshipHealth -= damage;
                if(damage>0){
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX+spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipY+spaceshipPlayer.spaceshipH,"-"+damage,1.0f,"red"));

                }
                createEnemyLasers(e);
            } else if (e.health <= 0 && e.y > spaceshipPlayer.spaceshipY && e.x > 0 && e.x < screenWidth) {
                if (e.explodedSound == false) {
                    explosionSmall.play();
                    e.explodedSound = true;
                    spaceshipPlayer.spaceshipHealth+=e.energyBonus;
                    killsTotal+=e.energyBonus;
                    instructions.add(new InstructionDrawer(e.x,e.y,"+"+e.energyBonus,1.0f,"green"));
                }
                e.updatePosition(batch,spaceshipPlayer);
                e.checkCollisionWithPlayer(spaceshipPlayer);
            }
        }


    }

    private void createEnemyLasers(Enemy e) {
        Random randomSpawn = new Random();
        int spawn = randomSpawn.nextInt(150);
        if (spawn == 1) {
            if(e.laserMaker(spaceshipPlayer.positionX())==null){

            }else{
                laserOfEnemies.add(e.laserMaker(spaceshipPlayer.positionX()));
            }

        }
        //Gdx.app.log("laser"," "+e.getClass().toString());

    }

    private void drawEnemyLasers() {
        ArrayList<LaserOfEnemy> toRemove = new ArrayList<LaserOfEnemy>();
        for (LaserOfEnemy l : laserOfEnemies) {
            if (l.used == true || l.y < spaceshipPlayer.spaceshipY) {
                toRemove.add(l);

            }
        }
        laserOfEnemies.removeAll(toRemove);
        for (LaserOfEnemy l : laserOfEnemies) {
            if (l.y > spaceshipPlayer.spaceshipY && l.x > 0 && l.x < screenWidth && l.used == false) {
                l.updatePosition(batch);
                int damage = l.checkCollisionWithPlayer(spaceshipPlayer);
                spaceshipPlayer.spaceshipHealth -= damage;
                if (damage > 0) {
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX+spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipY+spaceshipPlayer.spaceshipH,"-"+damage,1.0f,"red"));
                    touchedEnemyLaser.play();
                }
            }
        }
    }

    private void createEnemies() {
        Random randomSpawn = new Random();
        Random whereToRandomlySpawnX = new Random();
        int spawn = randomSpawn.nextInt(howOftenSpawn);
        int xRandom = whereToRandomlySpawnX.nextInt(screenWidth - screenWidth / 10);
        if (spawn<30) {
            enemies.add(new EnemySmallBlue(xRandom + 40, spaceshipPlayer.spaceshipY, screenWidth, screenHeight));
        }else if(spawn == 101) {
            enemies.add(new EnemyBad(xRandom + 40, spaceshipPlayer.spaceshipY, screenWidth, screenHeight));
        }else if(spawn == 102) {
            enemies.add(new EnemySuperBad(xRandom + 40, spaceshipPlayer.spaceshipY, screenWidth, screenHeight));
        }else if(spawn == 103) {
            enemies.add(new EnemyUFO(xRandom + 40, spaceshipPlayer.spaceshipY, screenWidth, screenHeight));
        }
    }


    private void updateCamera() {
        camera.update();
    }

    private void updatePosition() {
        if(spaceshipPlayer.spaceshipHealth>0){
            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    spaceshipPlayer.spaceshipX -= screenWidth / 100;
                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    spaceshipPlayer.spaceshipX += screenWidth / 100;

                }

            } else if (Gdx.app.getType() == Application.ApplicationType.Android) {
                float accel = Gdx.input.getAccelerometerX();
                if (accel > 1) {
                    spaceshipPlayer.spaceshipX -= screenWidth / 100;
                } else if (accel < -1) {
                    spaceshipPlayer.spaceshipX += screenWidth / 100;
                }
            }
        }


        if (spaceshipPlayer.spaceshipX < 0) {
            spaceshipPlayer.spaceshipX = 0;
        }
        if (spaceshipPlayer.spaceshipX > screenWidth - spaceshipPlayer.spaceshipW) {
            spaceshipPlayer.spaceshipX = screenWidth - spaceshipPlayer.spaceshipW;
        }


    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
        changeGameState();
    }

    private void changeGameState() {


            l = Levels.levelReturner(stateOfGame);

        makePlayLevel(l);
    }

    private void makeWorldMap() {

    }

    private void makeMenu() {

    }

    private void makePlayLevel(Playlevel pl) {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            screenHeight = 480;
            screenWidth = 640;
        } else if (Gdx.app.getType() == Application.ApplicationType.Android) {
            screenHeight = Gdx.graphics.getHeight();
            screenWidth = Gdx.graphics.getWidth();
        }

        touchedDown=false;
        nukedInformed=false;
        spaceshipPlayer = new PlayerShip(screenWidth);
        killsRequired=pl.goal;
        killsTotal = 0;
        timeLeftToReload = 0;
        howOftenSpawn = pl.spawnFrequency;
        howOftenLaser = pl.laserFrequency;
        font = new BitmapFont();
        enemies = new ArrayList<Enemy>();
        laserOfEnemies = new ArrayList<LaserOfEnemy>();
        laserOfPlayer = new ArrayList<LaserOfPlayer>();
        backPlanets = new ArrayList<BackGround>();
        instructions=new ArrayList<InstructionDrawer>();
        cooledDown = true;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(screenWidth, screenHeight);
        camera.position.set(screenWidth / 2, screenHeight / 2, 0);
        camera.update();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(pl.music));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        explosionBig = Gdx.audio.newSound(Gdx.files.internal("explosionBig.wav"));
        explosionSmall = Gdx.audio.newSound(Gdx.files.internal("explosionSmall.wav"));
        laserSound = Gdx.audio.newSound(Gdx.files.internal("laser.wav"));
        touchedEnemyLaser = Gdx.audio.newSound(Gdx.files.internal("touchedEnemyLaser.wav"));
        font.setColor(0.5f, 0.5f, 0.5f, 1.0f);

        level = new Texture(Gdx.files.internal(pl.backImage));
        laser = new Texture(Gdx.files.internal("laserRed.png"));


        explosion = new Texture(Gdx.files.internal("explosion.png"));
        level.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        enemyHealth = pl.enemyHealth;
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
