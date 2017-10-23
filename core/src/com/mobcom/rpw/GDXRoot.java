package com.mobcom.rpw;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mobcom.rpw.screens.SplashScreen;
import com.mobcom.rpw.tools.GameCamera;

public class GDXRoot extends Game {
	public static final int WIDTH = 720;
	public static final int HEIGHT = 480;

	public SpriteBatch batch;
	public GameCamera cam;


	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new GameCamera(WIDTH,HEIGHT);

		this.setScreen(new SplashScreen(this));
//		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(cam.combined());
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		cam.update(width, height);
		super.resize(width, height);
	}
}

