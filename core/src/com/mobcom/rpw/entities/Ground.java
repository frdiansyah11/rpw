package com.mobcom.rpw.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Nicoy on 26/10/2017.
 */

public class Ground {

    public static final int SPEED = 250;
    public static final int GROUND_WIDTH = 80;
    public static final int GROUND_HEIGHT = 240;
    public static final int GROUND_Y = 0;
    private static Texture img;

    float x;
    Body body;


    public boolean remove = false;


    public Ground(float x, World world){
        if (img == null)
            img = new Texture("ground1.png");

        this.x = x;
        makeBody(world);
    }

    public void update(float delta){
        x -= SPEED * delta;
        body.setLinearVelocity(-SPEED,0.0f);
        if (x < -GROUND_WIDTH )
            remove = true;
    }

    public void render(SpriteBatch batch){
        batch.draw(img, x, GROUND_Y, GROUND_WIDTH, GROUND_HEIGHT);
    }

    private void makeBody(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x,GROUND_Y);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(GROUND_WIDTH,GROUND_HEIGHT);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

}
