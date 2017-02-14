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

    private static final Color BACKGROUND_COLOR = new Color(0f, 0f,
            0f, 1.0f);


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


    private boolean cooledDown;
    private int stateOfGame = 1;
    private int howOftenSpawn;
    private int howOftenLaser;
    private int enemyHealth;
    private float timeLeftToReload;
    private int killsTotal;

    @Override
    public void render(float delta) {
        Gdx.app.log("y",""+spaceshipPlayer.spaceshipY);
        if (Gdx.input.justTouched() && spaceshipPlayer.died == true) {
            backgroundMusic.dispose();
            batch.dispose();
            show();
        }
        if (killsTotal > 299) {
            backgroundMusic.dispose();
            batch.dispose();
            stateOfGame++;
            show();
        }
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

        drawLaser(delta);

        drawEnemies();
        drawEnemyLasers();
        drawFonts(delta);
        batch.end();
    }

    private void createBackgrounds() {
        Random randomSpawn = new Random();
        int create = randomSpawn.nextInt(70);
        if (create == 1) {
            backPlanets.add(new BackGround(spaceshipPlayer,screenWidth,screenHeight));
        }
    }

    private void drawLevelBackground() {
        //batch.draw(level, 0, 0, 480 , 8000);
        ArrayList<BackGround>toRemove=new ArrayList<BackGround>();
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

    private void drawLaser(float d) {
        if (cooledDown == false) {
            timeLeftToReload -= d;
            if(timeLeftToReload<0.01f){
                cooledDown=true;
            }
        } else {
            if(spaceshipPlayer.spaceshipHealth > 199){
                LaserOfPlayer a = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"straightS",screenWidth,screenHeight);
                LaserOfPlayer b = new LaserOfPlayer(spaceshipPlayer.spaceshipX ,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH, spaceshipPlayer.spaceshipY,"midL",screenWidth,screenHeight);
                LaserOfPlayer c = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,spaceshipPlayer.spaceshipY,"midR",screenWidth,screenHeight);
                LaserOfPlayer f = new LaserOfPlayer(spaceshipPlayer.spaceshipX ,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH, spaceshipPlayer.spaceshipY,"sideL",screenWidth,screenHeight);
                LaserOfPlayer e = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,spaceshipPlayer.spaceshipY,"sideR",screenWidth,screenHeight);
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
                timeLeftToReload = 0.1f;

            }else if(spaceshipPlayer.spaceshipHealth > 99){

                    LaserOfPlayer a = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"straightS",screenWidth,screenHeight);
                    LaserOfPlayer b = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"midL",screenWidth,screenHeight);
                    LaserOfPlayer c = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"midR",screenWidth,screenHeight);
                    LaserOfPlayer f = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"sideL",screenWidth,screenHeight);
                    LaserOfPlayer e = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"sideR",screenWidth,screenHeight);
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
                    timeLeftToReload = 0.3f;


            }else if(spaceshipPlayer.spaceshipHealth > 49){

                    LaserOfPlayer a = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"straightS",screenWidth,screenHeight);
                    LaserOfPlayer b = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"midL",screenWidth,screenHeight);
                    LaserOfPlayer c = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"midR",screenWidth,screenHeight);
                    LaserOfPlayer f = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"sideL",screenWidth,screenHeight);
                    LaserOfPlayer e = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"sideR",screenWidth,screenHeight);
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
                    timeLeftToReload = 0.5f;


            }else if (spaceshipPlayer.spaceshipHealth > 29) {


                    LaserOfPlayer b = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"midL",screenWidth,screenHeight);
                    LaserOfPlayer c = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"midR",screenWidth,screenHeight);
                    LaserOfPlayer f = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"sideL",screenWidth,screenHeight);
                    LaserOfPlayer e = new LaserOfPlayer(spaceshipPlayer.spaceshipX , spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"sideR",screenWidth,screenHeight);

                    laserOfPlayer.add(b);
                    laserOfPlayer.add(c);
                    laserOfPlayer.add(f);
                    laserOfPlayer.add(e);

                    b.playSound();
                    c.playSound();
                    f.playSound();
                    e.playSound();
                    cooledDown = false;
                    timeLeftToReload = 0.5f;



            } else if (spaceshipPlayer.spaceshipHealth > 19) {

                    LaserOfPlayer a = new LaserOfPlayer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"straightL",screenWidth,screenHeight);
                    LaserOfPlayer b = new LaserOfPlayer(spaceshipPlayer.spaceshipX + spaceshipPlayer.spaceshipW/2, spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"straightR",screenWidth,screenHeight);
                    laserOfPlayer.add(a);
                    laserOfPlayer.add(b);
                    a.playSound();
                    b.playSound();
                    cooledDown = false;
                    timeLeftToReload = 0.5f;



            } else {

                    LaserOfPlayer a = new LaserOfPlayer(spaceshipPlayer.spaceshipX + spaceshipPlayer.spaceshipW/2, spaceshipPlayer.spaceshipY,spaceshipPlayer.spaceshipW,spaceshipPlayer.spaceshipH,"straightS",screenWidth,screenHeight);
                    laserOfPlayer.add(a);
                    a.playSound();
                    cooledDown = false;
                    timeLeftToReload = 0.5f;





            }

        }
        ArrayList<LaserOfPlayer>toRemove=new ArrayList<LaserOfPlayer>();
        for (LaserOfPlayer l:laserOfPlayer) {
            if (l.y > spaceshipPlayer.spaceshipY + screenHeight || l.x<0 || l.x>screenWidth) {
                toRemove.add(l);
            }
        }


        for (LaserOfPlayer l : laserOfPlayer) {
            l.updatePosition(batch);
        }

        laserOfPlayer.removeAll(toRemove);


    }

    private void drawSpaceship() {
        spaceshipPlayer.updatePosition(batch);

    }

    private void checkHealth() {
        if (spaceshipPlayer.spaceshipHealth == 0) {

        }
    }

    private void drawFonts(float delta) {
        fpsHelper++;
        if(fpsHelper==200){
            fps= (int) ((Integer)1/delta);
            fpsHelper=0;
        }

        font.draw(batch, "FPS:"+fps, 0, spaceshipPlayer.spaceshipY + 75);
        if(spaceshipPlayer.spaceshipHealth<1){
            font.draw(batch, "Game Over", 200, spaceshipPlayer.spaceshipY + 200);
            font.draw(batch, "Health:0" , 0, spaceshipPlayer.spaceshipY + 50);
        }else {
            font.draw(batch, "Health:" +spaceshipPlayer.spaceshipHealth, 0, spaceshipPlayer.spaceshipY + 50);
            font.draw(batch, "Kills:" + killsTotal, 0, spaceshipPlayer.spaceshipY + 25);
        }
    }

    private void drawEnemies() {
        ArrayList<Enemy>toRemove=new ArrayList<Enemy>();
        for (Enemy e:enemies) {
            if (e.exploded == true || e.y < spaceshipPlayer.spaceshipY) {

                toRemove.add(e);

            }
        }
        enemies.removeAll(toRemove);
        for (Enemy e : enemies) {
            if (e.health > 0 && e.y > spaceshipPlayer.spaceshipY && e.x > 0 && e.x < screenWidth) {
                e.updatePosition(batch);
                int damage = e.checkCollisionWithPlayer(spaceshipPlayer);
                e.checkCollisionWithLaser(laserOfPlayer);
                spaceshipPlayer.spaceshipHealth -= damage;
                createEnemyLasers(e.x, e.y);
            } else if (e.health <= 0 && e.y > spaceshipPlayer.spaceshipY && e.x > 0 && e.x < screenWidth) {
                if (e.explodedSound == false) {
                    explosionSmall.play();
                    e.explodedSound = true;
                    spaceshipPlayer.spaceshipHealth++;
                    killsTotal++;
                }
                e.updatePosition(batch);
                e.checkCollisionWithPlayer(spaceshipPlayer);
            }
        }


    }

    private void createEnemyLasers(int x, int y) {
        Random randomSpawn = new Random();
        int spawn = randomSpawn.nextInt(150);
        if (spawn == 1) {
            laserOfEnemies.add(new LaserOfEnemy(x + 20, y, spaceshipPlayer.spaceshipX,screenWidth,screenHeight));
        }
        // Gdx.app.log("laser"," "+laserOfEnemies.size());

    }

    private void drawEnemyLasers() {
        ArrayList<LaserOfEnemy>toRemove=new ArrayList<LaserOfEnemy>();
        for (LaserOfEnemy l:laserOfEnemies) {
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
                    touchedEnemyLaser.play();
                }
            }
        }
    }

    private void createEnemies() {
        Random randomSpawn = new Random();
        Random whereToRandomlySpawnX = new Random();
        int spawn = randomSpawn.nextInt(howOftenSpawn);
        int xRandom = whereToRandomlySpawnX.nextInt(screenWidth-screenWidth/10);
        if (spawn == 1) {
            enemies.add(new Enemy(xRandom + 40, spaceshipPlayer.spaceshipY, enemyHealth, l.enemy,screenWidth,screenHeight));
        }
    }


    private void updateCamera() {

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && camera.position.y < 7700) {
            camera.translate(0, 5, 0);
            spaceshipPlayer.spaceshipY += 5;
        }

        camera.update();
    }

    private void updatePosition() {
        if(Gdx.app.getType()== Application.ApplicationType.Desktop){
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                spaceshipPlayer.spaceshipX  -= screenWidth/100;
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                spaceshipPlayer.spaceshipX += screenWidth/100;

            }

        }else if(Gdx.app.getType()== Application.ApplicationType.Android){
            float accel=Gdx.input.getAccelerometerX();
            if(accel>0){
                spaceshipPlayer.spaceshipX  -= screenWidth/100;
            }else if(accel<0){
                spaceshipPlayer.spaceshipX += screenWidth/100;
            }
        }

        if (spaceshipPlayer.spaceshipX < 0) {
            spaceshipPlayer.spaceshipX = 0;
        }
        if (spaceshipPlayer.spaceshipX > screenWidth-spaceshipPlayer.spaceshipW) {
            spaceshipPlayer.spaceshipX = screenWidth-spaceshipPlayer.spaceshipW;
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

        if (stateOfGame == 1) {
            l = Levels.levelReturner("milkyWay");
        } else if (stateOfGame == 0) {
            makeMenu();
        } else if (stateOfGame == 2) {
            l = Levels.levelReturner("mars");
        } else if (stateOfGame == 3) {
            l = Levels.levelReturner("jupiter");
        }
        makePlayLevel(l);
    }

    private void makeWorldMap() {

    }

    private void makeMenu() {

    }

    private void makePlayLevel(Playlevel pl) {
        if(Gdx.app.getType()==Application.ApplicationType.Desktop){
            screenHeight=480;
            screenWidth=640;
        }else if(Gdx.app.getType()==Application.ApplicationType.Android){
            screenHeight=Gdx.graphics.getHeight();
            screenWidth=Gdx.graphics.getWidth();
        }
        spaceshipPlayer=new PlayerShip(screenWidth);
        killsTotal=0;
        timeLeftToReload = 0;
        howOftenSpawn = pl.spawnFrequency;
        howOftenLaser = pl.laserFrequency;
        font = new BitmapFont();
        enemies = new ArrayList<Enemy>();
        laserOfEnemies = new ArrayList<LaserOfEnemy>();
        laserOfPlayer = new ArrayList<LaserOfPlayer>();
        backPlanets = new ArrayList<BackGround>();
        cooledDown = true;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(screenWidth, screenHeight);
        camera.position.set(screenWidth/2, screenHeight/2, 0);
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
