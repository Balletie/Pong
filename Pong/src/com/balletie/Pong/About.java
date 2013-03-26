package com.balletie.Pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class About extends Pong {

	CharSequence aboutRule1 = "This is a clone of the game Pong, made by Balletie";
	CharSequence aboutRule2 = "The source code for this game is available at:";
	CharSequence aboutRule3 = "http://github.com/Balletie/Pong";
	
	About(SpriteBatch spriteBatch, BitmapFont white, Rectangle field, MenuSwitch currentMenu) {
		this.spriteBatch = spriteBatch;
		this.white = white;
		this.field = field;
		this.currentMenu = currentMenu;
	}

	public void drawAbout() {
		spriteBatch.begin();
			white.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			white.setScale(1, 1);
			white.draw(spriteBatch, aboutRule1, 100f, 500f);
			white.draw(spriteBatch, aboutRule2, 100f, 468f);
			white.draw(spriteBatch, aboutRule3, 100f, 436f);
		spriteBatch.end();
		
		aboutInput(currentMenu);
	}

	MenuSwitch aboutInput(MenuSwitch currentMenu) {
		if (   Gdx.input.isKeyPressed(Input.Keys.ESCAPE) 
			|| Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
			currentMenu = MenuSwitch.MAIN;
		}
		return currentMenu;
	}
}