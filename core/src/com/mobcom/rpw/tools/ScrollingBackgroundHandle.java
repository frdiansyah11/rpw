package com.mobcom.rpw.tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mobcom.rpw.GDXRoot;

/**
 * Created by Nicoy on 23/10/2017.
 */

public class ScrollingBackgroundHandle {

    private static final float SPEED = 0.1f;

    Texture background_stage1;// jungle
    Sprite backgroundsprite_stage1;


    float timer;//timer for speed scrolling


    public ScrollingBackgroundHandle(){

        //Stage 1 - Jungle
        background_stage1 = new Texture("example_jungle.jpg");
        background_stage1.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        backgroundsprite_stage1 = new Sprite(background_stage1,0,0, GDXRoot.WIDTH + background_stage1.getWidth() ,background_stage1.getHeight());
        backgroundsprite_stage1.setSize(backgroundsprite_stage1.getWidth() + GDXRoot.WIDTH, GDXRoot.HEIGHT);

        timer = 0;

    }

    public void updateAndRender(float delta, SpriteBatch batch){
        timer += SPEED * delta;
        if (timer > 1.0f){
            timer -= 1.0f;
        }

        backgroundsprite_stage1.setU(timer+1);
        backgroundsprite_stage1.setU2(timer+5);
        backgroundsprite_stage1.draw(batch);
    }
}
