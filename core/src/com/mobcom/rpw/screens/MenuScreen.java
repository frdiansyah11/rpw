package com.mobcom.rpw.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mobcom.rpw.GDXRoot;

/**
 * Created by Nicoy on 20/10/2017.
 */

public class MenuScreen implements Screen {

    private static final int LOGO_IMAGE_WIDTH = 200;
    private static final int LOGO_IMAGE_HEIGHT = 150;
    private static final int LOGO_IMAGE_Y = 320;

    private static final int LOGO_TEXT_WIDTH = 400;
    private static final int LOGO_TEXT_HEIGHT = 80;
    private static final int LOGO_TEXT_Y = 230;

    private static final int PLAY_BUTTON_WIDTH = 250;
    private static final int PLAY_BUTTON_HEIGHT = 120;
    private static final int PLAY_BUTTON_Y = 60;

    private static final int EXIT_BUTTON_WIDTH = 250;
    private static final int EXIT_BUTTON_HEIGHT = 120;
    private static final int EXIT_BUTTON_Y = 60;

    GDXRoot game;

    Texture logo1;//gambar logo
    Texture logo2;//tulisan logo
    Texture playButtonInactive;
    Texture playButtonActive;
    Texture exitButtonInactive;
    Texture exitButtonActive;

    public MenuScreen(final GDXRoot game){
        this.game = game;
        logo1 = new Texture("logo_image.png");
        logo2 = new Texture("logo_text.png");

        playButtonActive = new Texture("play_active.png");
        playButtonInactive = new Texture("play_inactive.png");

        exitButtonActive = new Texture("exit_active.png");
        exitButtonInactive = new Texture("exit_inactive.png");

        final MenuScreen menuScreen = this;

        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                //Play Button
                int x = GDXRoot.WIDTH / 4 - PLAY_BUTTON_WIDTH / 2;
                if (game.cam.getInputGameWorld().x < x + PLAY_BUTTON_WIDTH && game.cam.getInputGameWorld().x > x && GDXRoot.HEIGHT - game.cam.getInputGameWorld().y < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && GDXRoot.HEIGHT - game.cam.getInputGameWorld().y > PLAY_BUTTON_Y) {
                    menuScreen.dispose();
                    //System.out.println("Ow Hello");
                    game.setScreen(new GameScreen(game));
                }

                //Exit Button
                x = (GDXRoot.WIDTH * 3) / 4 - PLAY_BUTTON_WIDTH / 2;
                if (game.cam.getInputGameWorld().x < x + EXIT_BUTTON_WIDTH && game.cam.getInputGameWorld().x > x && GDXRoot.HEIGHT - game.cam.getInputGameWorld().y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && GDXRoot.HEIGHT - game.cam.getInputGameWorld().y > EXIT_BUTTON_Y) {
                    menuScreen.dispose();
                    Gdx.app.exit();
                }

                return super.touchUp(screenX, screenY, pointer, button);
            }
        });

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f,0.1f,0.4f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();


        //render Play Button
        int x = GDXRoot.WIDTH / 4 - PLAY_BUTTON_WIDTH / 2;
        if (game.cam.getInputGameWorld().x < x + PLAY_BUTTON_WIDTH && game.cam.getInputGameWorld().x > x && GDXRoot.HEIGHT - game.cam.getInputGameWorld().y < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && GDXRoot.HEIGHT - game.cam.getInputGameWorld().y > PLAY_BUTTON_Y) {
            game.batch.draw(playButtonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        } else {
            game.batch.draw(playButtonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }

        //render Exit Button
        x = (GDXRoot.WIDTH * 3) / 4 - PLAY_BUTTON_WIDTH / 2;
        if (game.cam.getInputGameWorld().x < x + EXIT_BUTTON_WIDTH && game.cam.getInputGameWorld().x > x && GDXRoot.HEIGHT - game.cam.getInputGameWorld().y < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && GDXRoot.HEIGHT - game.cam.getInputGameWorld().y > EXIT_BUTTON_Y) {
            game.batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        } else {
            game.batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }



        //render Logo Image & Text
        game.batch.draw(logo1, GDXRoot.WIDTH / 2 - LOGO_IMAGE_WIDTH / 2, LOGO_IMAGE_Y, LOGO_IMAGE_WIDTH, LOGO_IMAGE_HEIGHT);
        game.batch.draw(logo2, GDXRoot.WIDTH / 2 - LOGO_TEXT_WIDTH / 2, LOGO_TEXT_Y, LOGO_TEXT_WIDTH, LOGO_TEXT_HEIGHT);

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
