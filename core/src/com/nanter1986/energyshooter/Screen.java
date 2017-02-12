package com.nanter1986.energyshooter;

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

    Music backgroundMusic;
    Sound explosionBig;
    Sound explosionSmall;
    Sound laserSound;

    private Texture spaceship;
    private Texture level;
    private Texture laser;
    private Texture enemy1;
    private Texture explosion;
    int explosionAnimationX;
    int explosionAnimationY;
    private int spaceshipX;
    private int spaceshipY;
    private int spaceshipHealth;
    private int laserX;
    private int laserY;
    ArrayList<Enemy>enemies;


    private boolean cooledDown;
    private boolean died;
    private int stateOfGame=1;
    private int howOftenSpawn;
    private int enemyHealth;

    @Override
    public void render(float delta) {
        Gdx.app.log("where","y:"+spaceshipY);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && died==true){
            backgroundMusic.dispose();
            batch.dispose();
            show();
        }
        if(spaceshipY>7400){
            backgroundMusic.dispose();
            batch.dispose();
            stateOfGame++;
            show();
        }
        checkHealth();
        createEnemies();
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

        drawLaser();

        drawEnemies();
        drawFonts();
        batch.end();
    }

    private void drawLevelBackground() {
        batch.draw(level, 0, 0, 480 , 8000);
    }

    private void drawLaser() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && cooledDown==true){
            laserY=spaceshipY+100;
            laserX=spaceshipX+13;
            cooledDown=false;
            laserSound.play();

        }
        batch.draw(laser, laserX, laserY, 25f , 50f);

    }

    private void drawSpaceship() {
        if(spaceshipHealth>0){
            batch.draw(spaceship, spaceshipX, spaceshipY, 50f, 100f);
        }else if(explosionAnimationY==6){
            font.draw(batch, "Game Over" , 100, spaceshipY+200);
            died=true;

        }else{
            batch.draw(explosion,spaceshipX,spaceshipY,explosionAnimationX*100,500-explosionAnimationY*100,100,100);
            explosionBig.play();
            explosionAnimationX++;
            if(explosionAnimationX==6){
                explosionAnimationX=0;
                explosionAnimationY++;
            }
        }

    }

    private void checkHealth() {
        if(spaceshipHealth==0){

        }
    }

    private void drawFonts() {
        font.draw(batch, "Health:" + spaceshipHealth, 100, spaceshipY+50);
    }

    private void drawEnemies() {
        for(Enemy e:enemies){
            if(e.health>0 && e.y>spaceshipY && e.x>0 && e.x<640) {
                e.updatePosition(batch);
                int damage = e.checkCollisionWithPlayer(spaceshipX, spaceshipY);
                e.checkCollisionWithLaser(laserX,laserY);
                spaceshipHealth -= damage;
            }else if(e.health<=0 && e.y>spaceshipY && e.x>0 && e.x<640){
                if(e.explodedSound==false){
                    explosionSmall.play();
                    e.explodedSound=true;
                }
                e.updatePosition(batch);
                e.checkCollisionWithPlayer(spaceshipX,spaceshipY);
            }
        }
    }

    private void createEnemies() {
        Random randomSpawn=new Random();
        Random whereToRandomlySpawnX=new Random();
        int spawn=randomSpawn.nextInt(howOftenSpawn);
        int xRandom=whereToRandomlySpawnX.nextInt(560);
        if (spawn==1){
            enemies.add(new Enemy(xRandom+40,spaceshipY,enemyHealth,"enemyBlue1.png"));
        }
    }


    private void updateCamera() {

        if(Gdx.input.isKeyPressed(Input.Keys.UP) && camera.position.y<7700){
            camera.translate(0,5,0);
            spaceshipY+=5;
        }

        camera.update();
    }

    private void updatePosition() {
        laserY+=10;
        if(laserY>spaceshipY+550){
            cooledDown=true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            spaceshipX = spaceshipX - 5;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            spaceshipX = spaceshipX + 5;

        }
        if (spaceshipX < 0) {
            spaceshipX = 0;
        }
        if (spaceshipX > 590) {
            spaceshipX = 590;
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

        if(stateOfGame==1) {
            l= Levels.levelReturner("milkyWay");
        }else if(stateOfGame==0){
            makeMenu();
        }else if(stateOfGame==2){
            l= Levels.levelReturner("mars");
        }
        makePlayLevel(l);
    }

    private void makeWorldMap() {

    }

    private void makeMenu() {

    }

    private void makePlayLevel(Playlevel pl) {
        howOftenSpawn=pl.spawnFrequency;
        font = new BitmapFont();
        enemies=new ArrayList<Enemy>();
        cooledDown=true;
        died=false;
        explosionAnimationX=0;
        explosionAnimationY=0;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(640,480);
        camera.position.set(320,240,0);
        camera.update();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(pl.music));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        explosionBig = Gdx.audio.newSound(Gdx.files.internal("explosionBig.wav"));
        explosionSmall = Gdx.audio.newSound(Gdx.files.internal("explosionSmall.wav"));
        laserSound = Gdx.audio.newSound(Gdx.files.internal("laser.wav"));
        font.setColor(0.5f, 0.5f, 0.5f, 1.0f);
        spaceship = new Texture(Gdx.files.internal("F5S1.png"));
        level = new Texture(Gdx.files.internal(pl.backImage));
        laser = new Texture(Gdx.files.internal("laserRed.png"));
        enemy1 = new Texture(Gdx.files.internal(pl.enemy));
        explosion = new Texture(Gdx.files.internal("explosion.png"));
        level.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        spaceshipX = 300;
        spaceshipY = 0;
        spaceshipHealth=10;
        laserY=5000;
        enemyHealth=pl.enemyHealth;
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
        spaceship.dispose();
    }
}
