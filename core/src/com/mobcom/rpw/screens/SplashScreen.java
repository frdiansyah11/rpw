package com.mobcom.rpw.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mobcom.rpw.GDXRoot;
import com.mobcom.rpw.tween.SpriteAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Nicoy on 20/10/2017.
 */

public class SplashScreen implements Screen {

    private Texture img;
    private Sprite splash;
    private TweenManager tweenManager;

    GDXRoot game;

    public SplashScreen(GDXRoot game){
        this.game = game;

        img = new Texture("splash3.png");
        splash = new Sprite(img);

        tweenManager = new TweenManager();

    }

    @Override
    public void show() {
        //splash.setSize(GDXRoot.WIDTH/ 1.5f, GDXRoot.HEIGHT/2);
        splash.setPosition(GDXRoot.WIDTH/2 - splash.getWidth()/2,GDXRoot.HEIGHT/2 - splash.getHeight()/2);

        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(splash, SpriteAccessor.ALPHA, 2).target(1).repeatYoyo(1,5).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                game.setScreen(new MenuScreen(game));
            }
        }).start(tweenManager);
        //Tween.to(splash, SpriteAccessor.ALPHA, 2).target(0).delay(5).start(tweenManager);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        game.batch.begin();
        //game.batch.draw(img,Gdx.graphics.getWidth()/2 - img.getWidth()/2, Gdx.graphics.getHeight()/2 - img.getHeight()/2);
        splash.draw(game.batch);
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
        game.batch.dispose();
        splash.getTexture().dispose();
    }
}
