package com.mobcom.rpw.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mobcom.rpw.GDXRoot;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Run Pass World Game";
		config.foregroundFPS = 60;
		config.width = GDXRoot.WIDTH;
		config.height = GDXRoot.HEIGHT;
		config.resizable = false;


		new LwjglApplication(new GDXRoot(), config);
	}
}
