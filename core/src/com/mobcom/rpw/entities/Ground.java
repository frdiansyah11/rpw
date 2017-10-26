package com.mobcom.rpw.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.mobcom.rpw.tools.GameCamera;

/**
 * Created by Nicoy on 26/10/2017.
 */

public class Ground {

    public static final int SPEED = 250;
    public static final int GROUND_WIDTH = 80;
    public static final int GROUND_HEIGHT = 240;
    private static Texture img;

    float x,y;


    public boolean remove = false;

    World world;
    GameCamera cam;


    public Ground(float x){
        if (img == null)
            img = new Texture("ground.png");

        this.x = x;
    }

    public void update(float delta){
        x -= SPEED * delta;
        if (x < -GROUND_WIDTH)
            remove = true;
    }

    public void render(SpriteBatch batch){
        batch.draw(img, x, y, GROUND_WIDTH, GROUND_HEIGHT);
    }

}
