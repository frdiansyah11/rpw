package com.mobcom.rpw.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mobcom.rpw.GDXRoot;
import com.mobcom.rpw.entities.Ground;
import com.mobcom.rpw.entities.Hole;
import com.mobcom.rpw.entities.Player;
import com.mobcom.rpw.tools.ScrollingBackgroundHandle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nicoy on 23/10/2017.
 */

public class GameScreen implements Screen {

    GDXRoot game;
    ScrollingBackgroundHandle scrollingBackgroundHandle;
    Player player;

    ArrayList<Ground> grounds;
    ArrayList<Hole> holes;


    float ground_x;
    float groudSpawnTimer = 0;
    float backgroundTimer;


    Random random;

    World world;

    Sound backSound;

    private Box2DDebugRenderer box2DDebugRenderer;

    public GameScreen(GDXRoot game){
        this.game = game;

        world = new World(new Vector2(0, -9.8f),true);

        box2DDebugRenderer = new Box2DDebugRenderer();

        scrollingBackgroundHandle = new ScrollingBackgroundHandle();
        player = new Player(world);
        grounds = new ArrayList<Ground>();
        holes = new ArrayList<Hole>();

        backSound = Gdx.audio.newSound(Gdx.files.internal("sounds/jungle.ogg"));

        backgroundTimer = 0;
        ground_x = 0;
        groudSpawnTimer = 0;

        random = new Random();



        //------------------ INITIATE -------------
        backSound.setLooping(0,true);
        backSound.play();

        //initiate first grounds
        for (int i = 0 ; i < 17 ; i++){
            grounds.add(new Ground(ground_x + (Ground.GROUND_WIDTH * i), world));
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.step(delta, 6, 2);

//        //spawn ground or hole at right
//        groudSpawnTimer += delta;
//        if (groudSpawnTimer >= 0.28f){
//            groudSpawnTimer = 0;
//            // 90% for spawn ground at right
//            if (random.nextInt(100) + 1 <= 85){
//                grounds.add(new Ground(GDXRoot.WIDTH, world));
//            }else{
//                holes.add(new Hole(GDXRoot.WIDTH));
//            }
//        }

        //update ground to the left
        ArrayList<Ground> groundsToRemove = new ArrayList<Ground>();
        for (Ground ground : grounds) {
            ground.update(delta);
            if (ground.remove){
                groundsToRemove.add(ground);
            }

        }
        grounds.removeAll(groundsToRemove);

        //update hole to the left
        ArrayList<Hole> holesToRemove = new ArrayList<Hole>();
        for (Hole hole : holes) {
            hole.update(delta);
            if (hole.remove)
                holesToRemove.add(hole);
        }
        holes.removeAll(holesToRemove);

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        //scrollingBackgroundHandle.updateAndRender(delta, game.batch);

        for (Ground ground : grounds){
            ground.render(game.batch);
        }

        for (Hole hole : holes){
            hole.render(game.batch);
        }

        player.render(delta, game.batch);

        game.batch.end();

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
    }
}
