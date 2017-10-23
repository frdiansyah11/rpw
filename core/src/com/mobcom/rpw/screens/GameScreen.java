package com.mobcom.rpw.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mobcom.rpw.GDXRoot;
import com.mobcom.rpw.tools.ScrollingBackgroundHandle;

/**
 * Created by Nicoy on 23/10/2017.
 */

public class GameScreen implements Screen {

    GDXRoot game;
    ScrollingBackgroundHandle scrollingBackgroundHandle;

    float backgroundTimer;

    public GameScreen(GDXRoot game){
        this.game = game;
        scrollingBackgroundHandle = new ScrollingBackgroundHandle();
        backgroundTimer = 0;

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        scrollingBackgroundHandle.updateAndRender(delta, game.batch);
        //game.batch.draw(image,0,0);
        game.batch.end();
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
}
