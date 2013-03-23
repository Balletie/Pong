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
	CharSequence option1 = "Play";
	CharSequence option2 = "About";
	
	Menu(SpriteBatch spriteBatch, BitmapFont white, Rectangle field) {
		this.spriteBatch = spriteBatch;
		this.white = white;
		this.field = field; 
		opt1BoxLeft = field.width / 2 - 32f;
		opt1BoxRight = field.width / 2 + 32f;
		opt1BoxBottom = field.height - 100f;
		opt1BoxTop = field.height - 100f + 32f;
//		justTouched = Gdx.input.justTouched();
	}
	
	public void drawMenu() {
		spriteBatch.begin();
			white.draw(spriteBatch, option1, opt1BoxLeft, opt1BoxTop);
			white.draw(spriteBatch, option2, field.width / 2 - 50f, field.height - 300f);
		spriteBatch.end();
		newGame();
//		about();
	}

//	private void about() {
//	}

	public boolean newGame() {
		if (   Gdx.input.justTouched()
			&& Gdx.input.getX() < opt1BoxRight && Gdx.input.getX() > opt1BoxLeft 
			&& Gdx.graphics.getHeight() - Gdx.input.getY() < opt1BoxTop 
			&& Gdx.graphics.getHeight() - Gdx.input.getY() > opt1BoxBottom) {
			play = true;
		}
		return play;
	}
}
