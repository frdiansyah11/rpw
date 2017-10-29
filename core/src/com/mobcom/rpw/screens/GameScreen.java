package com.mobcom.rpw.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mobcom.rpw.GDXRoot;
import com.mobcom.rpw.entities.Ground;
import com.mobcom.rpw.entities.Player;
import com.mobcom.rpw.tools.ScrollingBackgroundHandle;
import com.mobcom.rpw.ui.GameUI;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nicoy on 23/10/2017.
 */

public class GameScreen implements Screen {

    public final static float GRAVITY = -5000.0f;

    GDXRoot game;
    ScrollingBackgroundHandle scrollingBackgroundHandle;
    Player player;
    GameUI ui;

    ArrayList<Ground> grounds;

    float ground_x;
    float backgroundTimer;


    Random random;

    World world;
    Sound backSound;

    private ShapeRenderer hole;

    private Box2DDebugRenderer box2DDebugRenderer;

    public GameScreen(GDXRoot game){
        this.game = game;

        world = new World(new Vector2(0, GRAVITY),true);

        box2DDebugRenderer = new Box2DDebugRenderer();


        scrollingBackgroundHandle = new ScrollingBackgroundHandle();
        player = new Player(world);
        grounds = new ArrayList<Ground>();

        backSound = Gdx.audio.newSound(Gdx.files.internal("sounds/jungle.ogg"));
        hole = new ShapeRenderer();
        hole.setProjectionMatrix(game.cam.combined());

        backgroundTimer = 0;
        ground_x = 0;

        random = new Random();

        //UI Class
        ui = new GameUI();


        //------------------ INITIATE -------------
        makeVerticalBorder();

//        backSound.setLooping(0,true);
//        backSound.play();

        //initiate first grounds
        for (int i = 1 ; i < 10 ; i++){
            grounds.add(new Ground(ground_x + (Ground.GROUND_WIDTH * i), world));
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.step(delta, 6, 2);


        //update ground to the left
        ArrayList<Ground> groundsToRemove = new ArrayList<Ground>();
        for (Ground ground : grounds) {
            ground.update();
            if (ground.remove){
                groundsToRemove.add(ground);
            }
        }
        if(grounds.removeAll(groundsToRemove)){
            //System.out.println("ground removed");
            // 95% for spawn ground at right
            if (random.nextInt(101) <= 95){
                grounds.add(new Ground(GDXRoot.WIDTH + Ground.BOX_WIDTH, world));
                //System.out.println(random.nextInt(101));
            }

        }



        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        makeHole();
        player.setInput();



        game.batch.begin();
        //scrollingBackgroundHandle.updateAndRender(delta, game.batch);

        for (Ground ground : grounds){
            ground.render(game.batch);
        }

        player.render(delta, game.batch);



        game.batch.end();

        ui.render(delta);

        box2DDebugRenderer.render(world,game.cam.combined());
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
        player.dispose();
        backSound.dispose();
        ui.dispose();
    }

    private void makeVerticalBorder(){
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 10));

        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(140, GDXRoot.HEIGHT);
        groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();
    }

    private void makeHole(){
        hole.begin(ShapeRenderer.ShapeType.Filled);
        hole.setColor(Color.BLACK);
        hole.rect(0, 0, GDXRoot.WIDTH, 100);
        hole.end();
    }
}
