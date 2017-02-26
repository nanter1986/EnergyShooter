package com.nanter1986.energyshooter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.nanter1986.energyshooter.Buttons.ShopBuy;
import com.nanter1986.energyshooter.Buttons.ShopExit;
import com.nanter1986.energyshooter.Buttons.ShopLeft;
import com.nanter1986.energyshooter.Buttons.ShopRight;
import com.nanter1986.energyshooter.Buttons.TouchableButtons;
import com.nanter1986.energyshooter.Enemies.Enemy;
import com.nanter1986.energyshooter.Enemies.EnemyBad;
import com.nanter1986.energyshooter.Enemies.EnemySmallBlue;
import com.nanter1986.energyshooter.Enemies.EnemySuperBad;
import com.nanter1986.energyshooter.Enemies.EnemyUFO;
import com.nanter1986.energyshooter.Enemies.LaserOfEnemy;
import com.nanter1986.energyshooter.playerships.LaserOfPlayer;
import com.nanter1986.energyshooter.playerships.f5s1;
import com.nanter1986.energyshooter.shop.ShopItem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Created by user on 11/2/2017.
 */

public class Screen extends ScreenAdapter {

    DisplayToolkit tool;

    private static final Color BACKGROUND_COLOR = new Color(0f, 0f, 0f, 1.0f);

    private static final Texture neptune = new Texture(Gdx.files.internal("neptune.jpg"));


    Playlevel l;


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

    public TouchableButtons shopLeft;
    public TouchableButtons shopRight;
    public TouchableButtons shopBuy;
    public TouchableButtons shopExit;


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
    private ArrayList<Artifact> artFinalList;
    private boolean effectsDone=false;
    private int spawnFrequencyFactor;


    @Override
    public void render(float delta) {
        if(whichScreen==SetOfScreens.GAME){

            if(effectsDone==false){
                spaceshipPlayer = SpaceshipChooseHelper.chosePlane(tool);
                artifactsTakeEffect(artFinalList);
                effectsDone=true;
            }
            renderGame(delta);
        }else if(whichScreen==SetOfScreens.EQUIP){
            equipScreen(delta);
        }else if(whichScreen==SetOfScreens.SHOP){
            theShop(delta);
        }else if(whichScreen==SetOfScreens.SELECT){
            selectPlane(delta);
        }else if(whichScreen==SetOfScreens.MENU){
            menuScreen(delta);
        }
    }

    private void menuScreen(float delta) {
        whichScreen=MenuMaker.makeMenu(tool);
    }

    private void selectPlane(float delta) {
        whichScreen=GarageManager.goToGame();
        GarageManager.manageGarage(tool.prefs,tool.batch,tool.font,screenWidth,screenHeight,shopRight,shopLeft,shopExit,tool);
    }

    private void theShop(float delta) {
        money=tool.prefs.getInteger("money",0);
        Gdx.app.log("mon",""+money);
        if(ShopManager.exitShop()){
            GarageManager.decisionMade=false;
            GarageManager.listMade=false;
            spaceshipPlayer = SpaceshipChooseHelper.chosePlane(tool);
            whichScreen=SetOfScreens.SELECT;
            ShopManager.doneWithShop=false;
        }


        ShopItem item=ShopManager.shopManage(shopRight,shopLeft,shopBuy,shopExit,money);


        money=money-ShopManager.boughtSomething();
        tool.prefs.putInteger("money",money);
        tool.prefs.flush();


        int angle=ShopManager.itemRotator;
        TextureRegion tr=new TextureRegion(item.texture);
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        tool.batch.draw(tr,screenWidth/2-100,screenHeight/2-100,100,100,
                200,200,1,01,angle);
        tool.font.draw(tool.batch,"Money:"+ money, 0, screenHeight*3/8);
        tool.font.draw(tool.batch,"Name:"+ item.name, 0, screenHeight*2/8);
        tool.font.draw(tool.batch,"Price:"+ item.price, 0, screenHeight*1/8);
        tool.batch.draw(shopRight.texture,shopRight.buttonX,shopRight.buttonY,shopRight.buttonW,shopRight.buttonH);
        tool.batch.draw(shopLeft.texture,shopLeft.buttonX,shopLeft.buttonY,shopLeft.buttonW,shopLeft.buttonH);
        tool.batch.draw(shopExit.texture,shopExit.buttonX,shopExit.buttonY,shopExit.buttonW,shopExit.buttonH);
        tool.font.draw(tool.batch,"SHOP", tool.scW*3/10,tool.scH*9/10);
        if(item.price<=money){
            tool.batch.draw(shopBuy.texture,shopBuy.buttonX,shopBuy.buttonY,shopBuy.buttonW,shopBuy.buttonH);
        }

        tool.batch.end();
    }

    private void artifactsTakeEffect(ArrayList<Artifact>aList) {
        for(Artifact a:aList){

            a.work(spaceshipPlayer);
        }

    }

    public void equipScreen(float delta){


        ArrayList<Artifact>temp=EquipManager.manageEquipment(spaceshipPlayer.listOfArtifacts,2,tool);

        if (EquipManager.proceed.isButtonTouched()) {
            changeGameState();
            artFinalList=temp;
            whichScreen=SetOfScreens.GAME;
        }



    }



    public void renderGame(float delta){
        if (Gdx.input.justTouched() && spaceshipPlayer.died == true) {
            backgroundMusic.dispose();
            tool.batch.dispose();
            whichScreen=SetOfScreens.SHOP;
            show();
            changeGameState();
        }
        if (killsTotal > killsRequired - 1) {
            backgroundMusic.dispose();
            tool.batch.dispose();
            stateOfGame++;

            tool.prefs.putInteger("shipindex",shipIndex);
            tool.prefs.putInteger("gamestate",stateOfGame);
            tool.prefs.putInteger("money",money+(int)spaceshipPlayer.spaceshipHealth);
            tool.prefs.flush();
            whichScreen=SetOfScreens.SHOP;
            show();
            changeGameState();

        }
        //nukeField();
        checkHealth();
        createEnemies();
        createBackgrounds();
        updatePosition();
        updateCamera();
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
        tool.batch.draw(level, 0, spaceshipPlayer.spaceshipY, screenWidth, screenHeight);
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

    private void drawLaser(float d, ArrayList<Enemy> enemies) {
        spaceshipPlayer.drawLaser(d,enemies,tool.batch,tool.font);
    }

    private void drawSpaceship() {
        spaceshipPlayer.updatePosition(tool.batch);

    }

    private void checkHealth() {
        if (spaceshipPlayer.spaceshipHealth == 0) {

        }
    }

    private void drawInstructions(float delta, String message, int x, int y) {
        int fpscounter = 0;
        if (fpsHelper < 200) {
            tool.font.draw(tool.batch, message, x, y);
            fpscounter++;
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
            tool.font.draw(tool.batch,l.title,0,screenHeight-25);
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

                e.updatePosition(tool.batch, spaceshipPlayer);
                float damage = e.checkCollisionWithPlayer(spaceshipPlayer);
                spaceshipPlayer.spaceshipHealth -= damage;
                if (damage > 0) {
                    instructions.add(new InstructionDrawer(spaceshipPlayer.spaceshipX + spaceshipPlayer.spaceshipW, spaceshipPlayer.spaceshipY + spaceshipPlayer.spaceshipH, "-" + damage, 1.0f, "red"));

                }
                if(e!=null){
                    createEnemyLasers(e);
                }

            } else if (e.health <= 0 && e.y > spaceshipPlayer.spaceshipY && e.x > 0 && e.x < screenWidth) {
                if (e.explodedSound == false) {
                    explosionSmall.play();
                    e.explodedSound = true;
                    spaceshipPlayer.spaceshipHealth += (e.energyBonus*spaceshipPlayer.energyDrawn);
                    killsTotal += e.energyBonus;
                    instructions.add(new InstructionDrawer(e.x, e.y, "+" + e.energyBonus, 1.0f, "green"));
                }
                e.updatePosition(tool.batch, spaceshipPlayer);
                e.checkCollisionWithPlayer(spaceshipPlayer);
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
                l.updatePosition(tool.batch);
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
        Enemy en=EnemyCreator.createEnemies(spawnFrequencyFactor,screenWidth,screenHeight,spaceshipPlayer,l.dif);
        if(en!=null) {
            enemies.add(en);
        }

    }


    private void updateCamera() {
        tool.camera.update();
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
                if (accel > 0) {
                    spaceshipPlayer.spaceshipX -= screenWidth / 100;
                } else if (accel < 0) {
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
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            screenHeight = 600;
            screenWidth = 400;
        } else if (Gdx.app.getType() == Application.ApplicationType.Android) {
            screenHeight = Gdx.graphics.getHeight();
            screenWidth = Gdx.graphics.getWidth();
        }
        tool=new DisplayToolkit(screenWidth,screenHeight);
        shopButtons();

    }

    private void changeGameState() {

        stateOfGame=tool.prefs.getInteger("gamestate",1);
        //stateOfGame=1;

        shipIndex=tool.prefs.getInteger("shipindex",0);

        l = Levels.levelReturner(stateOfGame);
        if(backgroundMusic!=null) {
            backgroundMusic.dispose();
        }
        makePlayLevel(l);
    }

    private void shopButtons(){
        shopLeft=new ShopLeft(tool);
        shopRight=new ShopRight(tool);
        shopBuy=new ShopBuy(tool);
        shopExit=new ShopExit(tool);
        tool.camera.update();

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
        tool.batch.dispose();
    }
}
