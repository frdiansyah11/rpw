package com.mobcom.rpw.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mobcom.rpw.GDXRoot;
import com.mobcom.rpw.entities.Player;

/**
 * Created by Nicoy on 27/10/2017.
 */

public class GameUI{
    private Stage stage;
    private Skin skin;

    //Progress Bar
    private Pixmap pixmap;
    private TextureRegionDrawable drawable;
    private ProgressBar.ProgressBarStyle progressBarStyle;

    //Health Bar
    private ProgressBar healthBar;
    private float healthVal = 100f;

    //Run bar
    private ProgressBar runBar;
    private float runVal = 0f;

    Player player;

    public GameUI(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);


        //button
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        final TextButton button = new TextButton("JUMP", skin, "default");
        button.setWidth(200f);
        button.setHeight(50f);
        button.setPosition(10, 50);

        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("JUMP");
            }
        });

        stage.addActor(drawHealthBar());
        stage.addActor(drawRunBar());
        stage.addActor(button);
    }

    public void render(float delta){

        stage.act(delta);
        barHandle(delta);
        stage.draw();

    }

    public void dispose(){
        stage.dispose();
    }

    private void barHandle(float delta){
        healthVal -= delta;
        runVal += delta;

        healthBar.setValue(healthVal);
        runBar.setValue(runVal);
    }

    private Actor drawHealthBar(){
        //Progress Bar

        pixmap = new Pixmap(350,20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();

        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = drawable;

        pixmap = new Pixmap(0, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle.knob = drawable;

        pixmap = new Pixmap(350, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle.knobBefore = drawable;

        healthBar = new ProgressBar(0.0f, 100f, 0.01f, false, progressBarStyle);
        healthBar.setValue(healthVal);
        healthBar.setAnimateDuration(0.25f);
        healthBar.setBounds(30, GDXRoot.HEIGHT - healthBar.getHeight() * 2, 350, 20);

        return healthBar;
    }

    private Actor drawRunBar(){
        pixmap = new Pixmap(500,20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GRAY);
        pixmap.fill();

        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = drawable;

        pixmap = new Pixmap(0, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.MAROON);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle.knob = drawable;

        pixmap = new Pixmap(500, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.MAROON);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle.knobBefore = drawable;

        runBar = new ProgressBar(0.0f, 200f, 0.01f, false, progressBarStyle);
        runBar.setValue(runVal);
        runBar.setAnimateDuration(0.25f);
        runBar.setBounds(30, GDXRoot.HEIGHT - runBar.getHeight() * 4    , 500, 20);

        return runBar;
    }

}
