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
import com.nanter1986.energyshooter.Artifacts.Artifact;
import com.nanter1986.energyshooter.Artifacts.BasicShield;
import com.nanter1986.energyshooter.Artifacts.Damager;
import com.nanter1986.energyshooter.Artifacts.EnergyBoosterOne;
import com.nanter1986.energyshooter.Artifacts.Speeder;
import com.nanter1986.energyshooter.Backs.BackGround;
import com.nanter1986.energyshooter.Backs.BackgroundCloud;
import com.nanter1986.energyshooter.Backs.BackgroundComet;
import com.nanter1986.energyshooter.Enemies.Enemy;
import com.nanter1986.energyshooter.Enemies.EnemyBad;
import com.nanter1986.energyshooter.Enemies.EnemySmallBlue;
import com.nanter1986.energyshooter.Enemies.EnemySuperBad;
import com.nanter1986.energyshooter.Enemies.EnemyUFO;
import com.nanter1986.energyshooter.Enemies.LaserOfEnemy;
import com.nanter1986.energyshooter.playerships.LaserOfPlayer;
import com.nanter1986.energyshooter.playerships.f5s1;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 11/2/2017.
 */

public class Screen extends ScreenAdapter {

    private static final Color BACKGROUND_COLOR = new Color(0f, 0f, 0f, 1.0f);

    private static final Texture neptune = new Texture(Gdx.files.internal("neptune.jpg"));


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
    private String movingBackImage;
    private String whichScreen="equip";
    private ArrayList<Artifact> artFinalList;
    private boolean effectsDone=false;


    @Override
    public void render(float delta) {
        if(whichScreen.equals("game")){
            if(effectsDone==false){
                artifactsTakeEffect(artFinalList);
                effectsDone=true;
            }
            renderGame(delta);
        }else if(whichScreen.equals("equip")){
            equipScreen(delta);
        }
    }

    private void artifactsTakeEffect(ArrayList<Artifact>aList) {
        for(Artifact a:aList){
            Gdx.app.log("arts",""+a.name+" "+spaceshipPlayer.energyDrawn);
            a.work(spaceshipPlayer);
        }

    }

    public void equipScreen(float delta){


        ArrayList<Artifact>temp=EquipManager.manageEquipment(makeArtifactList(),2);

        if (Gdx.input.justTouched()) {
            artFinalList=temp;
            whichScreen="game";
        }


        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();


        for(int i=0;i<temp.size();i++){
            if(temp.get(i)!=null){
                font.draw(batch,"Slot "+(i+1)+":"+temp.get(i).name,200,100*(i+1));
            }else{
                font.draw(batch,"Slot "+(i+1)+":",200,100*(i+1));
            }
        }

        batch.end();
    }

    private ArrayList<Artifact> makeArtifactList() {
        ArrayList<Artifact>artifacts=new ArrayList<Artifact>();
        artifacts.add(new BasicShield());
        artifacts.add(new EnergyBoosterOne());
        artifacts.add(new Speeder());
        artifacts.add(new Damager());

        return artifacts;
    }

    public void renderGame(float delta){
        if (Gdx.input.justTouched() && spaceshipPlayer.died == true) {
            backgroundMusic.dispose();
            batch.dispose();
            show();
        }
        if (killsTotal > killsRequired - 1) {
            backgroundMusic.dispose();
            batch.dispose();
            stateOfGame++;
            show();
        }
        //nukeField();
        checkHealth();
        artifactsWork();
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
        drawLaser(delta, enemies);


        drawEnemyLasers();
        drawFonts(delta);
        batch.end();
    }

    private void artifactsWork() {

    }

    private void nukeField() {
        if (spaceshipPlayer.spaceshipHealth > 30) {
            if (nukedInformed == false) {
                instructions.add(new InstructionDrawer(0, 100, "Touch screen to nuke the field", 3.0f, "yellow"));
            }
            nukedInformed = true;
        }
        if (spaceshipPlayer.spaceshipHealth > 30) {
            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                if (Gdx.input.justTouched()) {
                    for (Enemy e : enemies) {
                        e.health = 0;
                    }
                    spaceshipPlayer.spaceshipHealth -= 20;
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, "-20", 1.0f, "red"));
                    nukedInformed = false;
                }

            } else if (Gdx.app.getType() == Application.ApplicationType.Android) {
                if (Gdx.input.justTouched()) {
                    for (Enemy e : enemies) {
                        e.health = 0;
                    }
                    spaceshipPlayer.spaceshipHealth -= 20;
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX, spaceshipPlayer.spaceshipY, "-20", 1.0f, "red"));
                    nukedInformed = false;

                }
            }
        }
    }

    private void createBackgrounds() {
        Random randomSpawn = new Random();
        int create = randomSpawn.nextInt(70);

        if (create == 1) {
            if (movingBackImage.equals("meteor")) {
                backPlanets.add(new BackgroundComet(spaceshipPlayer, screenWidth, screenHeight));
            } else if (movingBackImage.equals("cloud")) {
                backPlanets.add(new BackgroundCloud(spaceshipPlayer, screenWidth, screenHeight));

            }
        }

    }

    private void drawLevelBackground() {
        batch.draw(level, 0, spaceshipPlayer.spaceshipY, screenWidth, screenHeight);
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

    private void drawLaser(float d, ArrayList<Enemy> enemies) {
        spaceshipPlayer.drawLaser(d,enemies,batch,font);
    }

    private void drawSpaceship() {
        spaceshipPlayer.updatePosition(batch);

    }

    private void checkHealth() {
        if (spaceshipPlayer.spaceshipHealth == 0) {

        }
    }

    private void drawInstructions(float delta, String message, int x, int y) {
        int fpscounter = 0;
        if (fpsHelper < 200) {
            font.draw(batch, message, x, y);
            fpscounter++;
        }
    }

    private void drawFonts(float delta) {
        fpsHelper++;
        if (fpsHelper == 200) {
            fps = (int) (1 / delta);
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
            font.draw(batch, "Slot 2:" + artFinalList.get(1).name, 0, spaceshipPlayer.spaceshipY + 125);
            font.draw(batch, "Slot 1:" + artFinalList.get(0).name, 0, spaceshipPlayer.spaceshipY + 100);
            font.draw(batch, "Energy:" + spaceshipPlayer.spaceshipHealth, 0, spaceshipPlayer.spaceshipY + 50);
            font.draw(batch, "Kills:" + killsTotal + "/" + killsRequired, 0, spaceshipPlayer.spaceshipY + 25);
        }

        for (InstructionDrawer i : instructions) {
            if (i.finished == false) {
                i.drawSelf(delta, font, batch);
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
                e.updatePosition(batch, spaceshipPlayer);
                float damage = e.checkCollisionWithPlayer(spaceshipPlayer);
                spaceshipPlayer.spaceshipHealth -= damage;
                if (damage > 0) {
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX + spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipY + spaceshipPlayer.spaceshipH, "-" + damage, 1.0f, "red"));

                }
                createEnemyLasers(e);
            } else if (e.health <= 0 && e.y > spaceshipPlayer.spaceshipY && e.x > 0 && e.x < screenWidth) {
                if (e.explodedSound == false) {
                    explosionSmall.play();
                    e.explodedSound = true;
                    spaceshipPlayer.spaceshipHealth += (e.energyBonus*spaceshipPlayer.energyDrawn);
                    Gdx.app.log("drawn",""+e.energyBonus*spaceshipPlayer.energyDrawn);
                    killsTotal += e.energyBonus;
                    instructions.add(new InstructionDrawer(e.x, e.y, "+" + e.energyBonus, 1.0f, "green"));
                }
                e.updatePosition(batch, spaceshipPlayer);
                e.checkCollisionWithPlayer(spaceshipPlayer);
            }
        }


    }

    private void createEnemyLasers(Enemy e) {
        Random randomSpawn = new Random();
        int spawn = randomSpawn.nextInt(150);
        if (spawn == 1) {
            if (e.laserMaker(spaceshipPlayer.positionX()) == null) {

            } else {
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
                float damage = l.checkCollisionWithPlayer(spaceshipPlayer);
                spaceshipPlayer.spaceshipHealth -= damage;
                if (damage > 0) {
                    l.used=true;
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX + spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipY + spaceshipPlayer.spaceshipH, "-" + damage, 1.0f, "red"));
                    touchedEnemyLaser.play();
                }
            }
        }
    }

    private void createEnemies() {
        Random randomSpawn = new Random();
        Random whereToRandomlySpawnX = new Random();
        int spawn = randomSpawn.nextInt(3000);
        int xRandom = whereToRandomlySpawnX.nextInt(screenWidth - screenWidth / 10);
        if (spawn < 30) {
            enemies.add(new EnemySmallBlue(xRandom + 40, spaceshipPlayer.spaceshipY, screenWidth, screenHeight));
        } else if (spawn == 101) {
            enemies.add(new EnemyBad(xRandom + 40, spaceshipPlayer.spaceshipY, screenWidth, screenHeight));
        } else if (spawn == 102) {
            enemies.add(new EnemySuperBad(xRandom + 40, spaceshipPlayer.spaceshipY, screenWidth, screenHeight));
        } else if (spawn == 103) {
            enemies.add(new EnemyUFO(xRandom + 40, spaceshipPlayer.spaceshipY, screenWidth, screenHeight));
        }
    }


    private void updateCamera() {
        camera.update();
    }

    private void updatePosition() {
        if (spaceshipPlayer.spaceshipHealth > 0) {
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
        makeArtifactList();

        touchedDown = false;
        nukedInformed = false;
        spaceshipPlayer = new f5s1(screenWidth,screenHeight);
        killsRequired = pl.goal;
        killsTotal = 0;
        timeLeftToReload = 0;
        movingBackImage = pl.movingBack;
        howOftenLaser = pl.laserFrequency;
        font = new BitmapFont();
        enemies = new ArrayList<Enemy>();
        laserOfEnemies = new ArrayList<LaserOfEnemy>();
        laserOfPlayer = new ArrayList<LaserOfPlayer>();
        backPlanets = new ArrayList<BackGround>();
        instructions = new ArrayList<InstructionDrawer>();
        slots=new ArrayList<Artifact>();
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

        level.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

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
