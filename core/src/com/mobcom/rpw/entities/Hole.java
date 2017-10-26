package com.mobcom.rpw.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Nicoy on 26/10/2017.
 */

public class Hole {
    public static final int SPEED = 250;
    public static final int HOLE_WIDTH = 80;
    public static final int HOLE_HEIGHT = 240;
    private static Texture img;

    float x,y;


    public boolean remove = false;


    public Hole(float x){
        if (img == null)
            img = new Texture("hole.png");

        this.x = x;
    }

    public void update(float delta){
        x -= SPEED * delta;
        if (x < -HOLE_WIDTH)
            remove = true;
    }

    public void render(SpriteBatch batch){
        batch.draw(img, x, y, HOLE_WIDTH, HOLE_HEIGHT);
    }
}
