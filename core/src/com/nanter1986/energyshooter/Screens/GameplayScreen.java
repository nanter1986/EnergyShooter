package com.nanter1986.energyshooter.Screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.energyshooter.Artifacts.Artifact;
import com.nanter1986.energyshooter.Artifacts.BasicShield;
import com.nanter1986.energyshooter.Artifacts.Damager;
import com.nanter1986.energyshooter.Artifacts.DarknessKiller;
import com.nanter1986.energyshooter.Artifacts.EnergyBoosterOne;
import com.nanter1986.energyshooter.Artifacts.FireKiller;
import com.nanter1986.energyshooter.Artifacts.IceKiller;
import com.nanter1986.energyshooter.Artifacts.LightsDown;
import com.nanter1986.energyshooter.Artifacts.Speeder;
import com.nanter1986.energyshooter.Backs.BackGround;
import com.nanter1986.energyshooter.Backs.BackgroundCloud;
import com.nanter1986.energyshooter.Backs.BackgroundComet;
import com.nanter1986.energyshooter.DisplayToolkit;
import com.nanter1986.energyshooter.Enemies.Enemy;
import com.nanter1986.energyshooter.Enemies.LaserOfEnemy;
import com.nanter1986.energyshooter.EnemyCreator;
import com.nanter1986.energyshooter.EnergyShooter;
import com.nanter1986.energyshooter.InstructionDrawer;
import com.nanter1986.energyshooter.Levels;
import com.nanter1986.energyshooter.Playlevel;
import com.nanter1986.energyshooter.SetOfScreens;
import com.nanter1986.energyshooter.SpaceshipChooseHelper;
import com.nanter1986.energyshooter.playerships.LaserOfPlayer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 27/2/2017.
 */

public class GameplayScreen implements Screen{

    static Preferences prefs = Gdx.app.getPreferences("Shooter");
    EnergyShooter game;
    DisplayToolkit tool;

    private static final Color BACKGROUND_COLOR = new Color(0f, 0f, 0f, 1.0f);


    Playlevel l;


    int fps;
    int fpsHelper;


    Music backgroundMusic;
    Sound explosionBig;
    Sound explosionSmall;
    Sound laserSound;
    Sound touchedEnemyLaser;

    com.nanter1986.energyshooter.playerships.PlayerShip spaceshipPlayer;

    private Texture level;




    ArrayList<Enemy> enemies;
    ArrayList<LaserOfEnemy> laserOfEnemies;
    ArrayList<LaserOfPlayer> laserOfPlayer;
    ArrayList<BackGround> backPlanets;
    ArrayList<InstructionDrawer> instructions;
    ArrayList<Artifact>slots;


    private boolean cooledDown;
    private boolean nukedInformed;
    private boolean touchedDown;



    private int stateOfGame = 1;
    private int howOftenLaser;
    private float timeLeftToReload;
    private int killsTotal;
    private int killsRequired;
    private int money=0;
    private int shipIndex=0;
    private String movingBackImage;
    private SetOfScreens whichScreen=SetOfScreens.MENU;
    private ArrayList<Artifact> artFinalList=new ArrayList<Artifact>();
    private boolean effectsDone=false;
    private int spawnFrequencyFactor;
    private int hitCounter=0;

    public GameplayScreen(EnergyShooter game) {
        this.game = game;
        this.tool=new DisplayToolkit(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stateOfGame=tool.prefs.getInteger("gamestate",1);
        stateOfGame=75;

        shipIndex=tool.prefs.getInteger("shipindex",0);
        money=prefs.getInteger("money");

        l = Levels.levelReturner(stateOfGame);
        if(backgroundMusic!=null) {
            backgroundMusic.dispose();
        }
        makePlayLevel(l);
    }

    @Override
    public void show() {
        spaceshipPlayer = SpaceshipChooseHelper.chosePlane(tool);
        if(stateOfGame==75){
            spaceshipPlayer.spaceshipHealth=100;
        }
        artifactsTakeEffect();
    }

    @Override
    public void render(float delta) {
        renderGame(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void makePlayLevel(Playlevel pl) {

        touchedDown = false;
        nukedInformed = false;

        killsRequired = pl.goal;
        spawnFrequencyFactor=pl.sff;
        killsTotal = 0;
        timeLeftToReload = 0;
        movingBackImage = pl.movingBack;
        howOftenLaser = pl.laserFrequency;
        enemies = new ArrayList<Enemy>();
        laserOfEnemies = new ArrayList<LaserOfEnemy>();
        laserOfPlayer = new ArrayList<LaserOfPlayer>();
        backPlanets = new ArrayList<BackGround>();
        instructions = new ArrayList<InstructionDrawer>();
        slots=new ArrayList<Artifact>();
        cooledDown = true;
        effectsDone=false;

        tool.camera.update();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(pl.music));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        explosionBig = Gdx.audio.newSound(Gdx.files.internal("explosionBig.wav"));
        explosionSmall = Gdx.audio.newSound(Gdx.files.internal("explosionSmall.wav"));
        laserSound = Gdx.audio.newSound(Gdx.files.internal("laser.wav"));
        touchedEnemyLaser = Gdx.audio.newSound(Gdx.files.internal("touchedEnemyLaser.wav"));


        level = new Texture(Gdx.files.internal(pl.backImage));

        level.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

    }

    private void updatePosition() {
        if (spaceshipPlayer.spaceshipHealth > 0) {
            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    spaceshipPlayer.spaceshipX -= tool.scW / 100;
                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    spaceshipPlayer.spaceshipX += tool.scW / 100;
                }

            } else if (Gdx.app.getType() == Application.ApplicationType.Android) {
                float accel = Gdx.input.getAccelerometerX();
                if (accel > 0) {
                    spaceshipPlayer.spaceshipX -= tool.scW / 100;
                } else if (accel < 0) {
                    spaceshipPlayer.spaceshipX += tool.scW / 100;
                }
            }
        }


        if (spaceshipPlayer.spaceshipX < 0) {
            spaceshipPlayer.spaceshipX = 0;
        }
        if (spaceshipPlayer.spaceshipX > tool.scW - spaceshipPlayer.spaceshipW) {
            spaceshipPlayer.spaceshipX = tool.scW - spaceshipPlayer.spaceshipW;
        }


    }

    private void createEnemies() {
        Enemy en= EnemyCreator.createEnemies(spawnFrequencyFactor,tool.scW,tool.scH,spaceshipPlayer,l.dif);
        if(en!=null) {
            enemies.add(en);
        }

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
            if (l.y > spaceshipPlayer.spaceshipY && l.x > 0 && l.x < tool.scW && l.used == false) {
                l.updatePosition(tool.batch);
                float damage = l.checkCollisionWithPlayer(spaceshipPlayer);
                spaceshipPlayer.spaceshipHealth -= damage;
                if (damage > 0) {
                    l.used=true;
                    l.doneColliding=true;
                    hitCounter=-4;
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX + spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipY + spaceshipPlayer.spaceshipH, "-" + damage, 1.0f, "red"));
                    touchedEnemyLaser.play();
                }
            }
        }
    }

    private void createEnemyLasers(Enemy e) {
        if(e.laserFrequency>0){
            Random randomSpawn = new Random();
            int spawn = randomSpawn.nextInt(e.laserFrequency);
            if (spawn == 1) {
                if (e.laserMaker(spaceshipPlayer.positionX()) == null) {

                } else {
                    laserOfEnemies.add(e.laserMaker(spaceshipPlayer.positionX()));
                }

            }
        }

    }

    private void drawEnemies() {
        ArrayList<Enemy> toRemove = new ArrayList<Enemy>();
        for (Enemy e : enemies) {
            if (e.exploded == true || e.y <=0 || e.positionX()<0 || e.positionX()> tool.scW|| (e.y>tool.scH && e.health<0)) {
                Gdx.app.log("remove",e.whatType().toString()+" "+tool.scW+" "+tool.scH);
                toRemove.add(e);

            }
        }
        enemies.removeAll(toRemove);
        for (Enemy e : enemies) {
            if (e.health > 0 && e.y > spaceshipPlayer.spaceshipY && e.x > 0 && e.x < tool.scW) {

                e.updatePosition(tool.batch, spaceshipPlayer);
                float damage = e.checkCollisionWithPlayer(spaceshipPlayer);
                spaceshipPlayer.spaceshipHealth -= damage;
                if (damage > 0) {
                    hitCounter=-4;
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX + spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipY + spaceshipPlayer.spaceshipH, "-" + damage, 1.0f, "red"));

                }
                if(e!=null){
                    createEnemyLasers(e);
                }

            } else if (e.health <= 0 && e.y > spaceshipPlayer.spaceshipY && e.x > 0 && e.x < tool.scW) {
                if (e.explodedSound == false) {
                    e.doneColliding=true;
                    explosionSmall.play();
                    e.explodedSound = true;
                    if(spaceshipPlayer.spaceshipHealth>0){
                        spaceshipPlayer.spaceshipHealth += (e.energyBonus*spaceshipPlayer.energyDrawn);
                        killsTotal += e.energyBonus;
                    }

                    instructions.add(new InstructionDrawer(e.x, e.y, "+" + e.energyBonus, 1.0f, "green"));
                }
                e.updatePosition(tool.batch, spaceshipPlayer);
                //e.checkCollisionWithPlayer(spaceshipPlayer);
            }
        }
    }

    private void drawFonts(float delta) {
        fpsHelper++;
        if (fpsHelper == 200) {
            fps = (int) (1 / delta);
            fpsHelper = 0;
        }

        tool.font.setColor(Color.WHITE);
        tool.font.draw(tool.batch, "FPS:" + fps, 0, spaceshipPlayer.spaceshipY + 75);
        if (spaceshipPlayer.spaceshipHealth < 1) {
            tool.font.setColor(Color.WHITE);
            tool.font.draw(tool.batch, "Game Over", 200, spaceshipPlayer.spaceshipY + 200);
            tool.font.draw(tool.batch, "Energy:0", 0, spaceshipPlayer.spaceshipY + 50);
        } else {
            tool.font.setColor(Color.WHITE);
            tool.font.draw(tool.batch,l.title,0,tool.scH-25);
            tool.font.draw(tool.batch, "Money:" + money, 0, spaceshipPlayer.spaceshipY + 150);
            tool.font.draw(tool.batch, "Slot 2:" + artFinalList.get(1).name, 0, spaceshipPlayer.spaceshipY + 125);
            tool.font.draw(tool.batch, "Slot 1:" + artFinalList.get(0).name, 0, spaceshipPlayer.spaceshipY + 100);
            DecimalFormat df=new DecimalFormat("0.00");
            String formatted=df.format(spaceshipPlayer.spaceshipHealth);
            tool.font.draw(tool.batch, "Energy:" + formatted, 0, spaceshipPlayer.spaceshipY + 50);
            tool.font.draw(tool.batch, "Kills:" + killsTotal + "/" + killsRequired, 0, spaceshipPlayer.spaceshipY + 25);
        }

        for (InstructionDrawer i : instructions) {
            if (i.finished == false) {
                i.drawSelf(delta, tool.font, tool.batch);
            }
        }
        ArrayList<InstructionDrawer> toRemove = new ArrayList<InstructionDrawer>();
        for (InstructionDrawer i : instructions) {
            if (i.finished == true) {

                toRemove.add(i);

            }
        }
        instructions.removeAll(toRemove);
    }

    private void drawLaser(float d, ArrayList<Enemy> enemies) {
        spaceshipPlayer.drawLaser(d,enemies,tool.batch,tool.font);
    }

    private void drawSpaceship() {
        if(hitCounter<0){
            tool.batch.setColor(Color.RED);
            spaceshipPlayer.updatePosition(tool.batch);
            tool.batch.setColor(Color.WHITE);
            hitCounter++;
        }else{
            spaceshipPlayer.updatePosition(tool.batch);
        }




    }

    private void drawLevelBackground() {
        tool.batch.draw(level, 0, spaceshipPlayer.spaceshipY, tool.scW, tool.scH);
        ArrayList<BackGround> toRemove = new ArrayList<BackGround>();
        for (BackGround b : backPlanets) {
            if (b.passedShip == true) {
                toRemove.add(b);
            }
        }
        for (BackGround b : backPlanets) {

            b.updatePosition(tool.batch, spaceshipPlayer.spaceshipY);


        }
        backPlanets.removeAll(toRemove);
    }

    private void createBackgrounds() {
        Random randomSpawn = new Random();
        int create = randomSpawn.nextInt(70);

        if (create == 1) {
            if (movingBackImage.equals("meteor")) {
                backPlanets.add(new BackgroundComet(spaceshipPlayer, tool.scW, tool.scH));
            } else if (movingBackImage.equals("cloud")) {
                backPlanets.add(new BackgroundCloud(spaceshipPlayer, tool.scW, tool.scH));

            }
        }

    }

    public void renderGame(float delta){
        if (Gdx.input.justTouched() && spaceshipPlayer.died == true) {

            EnemyCreator.resetBossFight();
            game.setScreen(new Shop(game));
            backgroundMusic.dispose();
            //tool.batch.dispose();
            //dispose();

        }
        if (killsTotal > killsRequired - 1 && spaceshipPlayer.died==false) {

            stateOfGame++;

            tool.prefs.putInteger("shipindex",shipIndex);
            tool.prefs.putInteger("gamestate",stateOfGame);
            tool.prefs.putInteger("money",money+(int)spaceshipPlayer.spaceshipHealth);
            tool.prefs.flush();
            game.setScreen(new Shop(game));
            backgroundMusic.dispose();
           // tool.batch.dispose();
            //dispose();

        }
        //nukeField();
        createEnemies();
        createBackgrounds();
        updatePosition();
        tool.camera.update();

        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        drawLevelBackground();

        drawSpaceship();

        drawEnemies();
        drawLaser(delta, enemies);


        drawEnemyLasers();
        drawFonts(delta);
        tool.batch.end();
    }

    private void artifactsTakeEffect() {
        ArrayList<Artifact>art=new ArrayList<Artifact>();

        art.add(new BasicShield());
        art.add(new EnergyBoosterOne());
        art.add(new Speeder());
        art.add(new Damager());
        art.add(new FireKiller());
        art.add(new IceKiller());
        art.add(new DarknessKiller());
        art.add(new LightsDown());
        for(Artifact a:art){
            if(prefs.getBoolean(a.name)){
                a.work(spaceshipPlayer);
                artFinalList.add(a);
            }
        }
        if(artFinalList.size()==1){
            artFinalList.add(artFinalList.get(0));
        }

    }
}
