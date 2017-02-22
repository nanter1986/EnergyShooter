package com.nanter1986.energyshooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nanter1986.energyshooter.EnergyShooter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable=false;
		config.height=600;
		config.width=400;
		new LwjglApplication(new EnergyShooter(), config);
	}
}
