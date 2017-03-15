package com.nanter1986.energyshooter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.energyshooter.Screens.MainMenu;

public class EnergyShooter extends Game {

	EnergyShooter enGame;
	private AdsController adsController;

	public EnergyShooter(AdsController adsController) {
		this.adsController=adsController;
		this.enGame = this;


	}

	public EnergyShooter() {
		this.enGame = this;


	}

	@Override
	public void create () {
		setScreen(new MainMenu(enGame,adsController));

	}


}
