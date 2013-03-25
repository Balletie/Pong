package com.balletie.Pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Menu extends Pong {
	private float opt1BoxLeft;
	private float opt1BoxRight;
	private float opt1BoxBottom;
	private float opt1BoxTop;
	private float opt2BoxLeft;
	private float opt2BoxRight;
	private float opt2BoxBottom;
	private float opt2BoxTop;
	private float opt3BoxLeft;
	private float opt3BoxRight;
	private float opt3BoxBottom;
	private float opt3BoxTop;
	CharSequence option1 = "Play";
	CharSequence option2 = "About";
	CharSequence option3 = "Exit";
	
	Menu(SpriteBatch spriteBatch, BitmapFont white, Rectangle field, MenuSwitch currentMenu) {
		this.spriteBatch = spriteBatch;
		this.white = white;
		this.field = field; 
		this.currentMenu = currentMenu;
		opt1BoxLeft = field.width / 2 - 32f;
		opt1BoxRight = field.width / 2 + 32f;
		opt1BoxBottom = field.height / 2 - 32f;
		opt1BoxTop = field.height / 2;
		opt2BoxLeft = field.width / 2 - 40f;
		opt2BoxRight = field.width / 2 + 40f;
		opt2BoxBottom = field.height / 2 - 72f;
		opt2BoxTop = field.height / 2 - 40f;
		opt3BoxLeft = field.width / 2 - 32f;
		opt3BoxRight = field.width / 2 + 32f;
		opt3BoxBottom = field.height / 2 - 112;
		opt3BoxTop = field.height / 2 - 80f;
	}
	
	public void drawMenu() {
		spriteBatch.begin();
			white.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			white.setScale(1, 1);
			white.draw(spriteBatch, option1, opt1BoxLeft, opt1BoxTop);
			white.draw(spriteBatch, option2, opt2BoxLeft, opt2BoxTop);
			white.draw(spriteBatch, option3, opt3BoxLeft, opt3BoxTop);
		spriteBatch.end();
		newGame(currentMenu);
		about(currentMenu);
		exit();
	}


	MenuSwitch newGame(MenuSwitch currentMenu) {
		if (   Gdx.input.justTouched()
				&& Gdx.input.getX() < opt1BoxRight && Gdx.input.getX() > opt1BoxLeft 
				&& Gdx.graphics.getHeight() - Gdx.input.getY() < opt1BoxTop 
				&& Gdx.graphics.getHeight() - Gdx.input.getY() > opt1BoxBottom) {
			currentMenu = MenuSwitch.PLAY;
		}
		return currentMenu;
	}

	MenuSwitch about(MenuSwitch currentMenu) {
		if (   Gdx.input.justTouched()
			&& Gdx.input.getX() < opt2BoxRight && Gdx.input.getX() > opt2BoxLeft 
			&& Gdx.graphics.getHeight() - Gdx.input.getY() < opt2BoxTop 
			&& Gdx.graphics.getHeight() - Gdx.input.getY() > opt2BoxBottom) {
			currentMenu = MenuSwitch.ABOUT;
		}
		return currentMenu;
	}

	private void exit() {
		if (   Gdx.input.justTouched()
				&& Gdx.input.getX() < opt3BoxRight && Gdx.input.getX() > opt3BoxLeft 
				&& Gdx.graphics.getHeight() - Gdx.input.getY() < opt3BoxTop
				&& Gdx.graphics.getHeight() - Gdx.input.getY() > opt3BoxBottom) {
			Gdx.app.exit();
		}
	}
}
