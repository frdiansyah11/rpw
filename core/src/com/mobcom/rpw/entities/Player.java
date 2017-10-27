package com.mobcom.rpw.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Nicoy on 23/10/2017.
 */

public class Player {

    private static final int POSITION_X = 140;
    private static final int POSITION_Y = 300;
//    private static final int PLAYER_WIDTH = 86;
//    private static final int PLAYER_HEIGHT = 102;

    private static final int BOX_WIDTH = 43;
    private static final int BOX_HEIGHT = 51;

    // Constant rows and columns of the sprite sheet
    private static final int FRAME_COLS = 6, FRAME_ROWS = 5;

    // Objects used
    Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    Texture walkSheet;
    World world;
    Body body;

    // A variable for tracking elapsed time for the animation
    float stateTime;


    public Player(World world){
        this.world = world;

        walkSheet = new Texture("example_player.png");
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);

        stateTime = 0;

        createBody(world);
    }

    public void render(float delta, SpriteBatch batch){


        stateTime += delta;

        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, body.getPosition().x - currentFrame.getRegionWidth() / 2f, body.getPosition().y - currentFrame.getRegionHeight()/2f);
    }

    private void createBody(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(POSITION_X,POSITION_Y);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(BOX_WIDTH,BOX_HEIGHT);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    public  void dispose(){
        walkSheet.dispose();
    }

}
