package com.jhonn.game;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("GameWithLibgdx");
		config.useVsync(false);
		//config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		config.setWindowedMode(850,480);
		new Lwjgl3Application(new GameEngine(), config);
	}
}
