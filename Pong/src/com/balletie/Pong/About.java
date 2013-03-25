package com.balletie.Pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class About extends Pong {

	private CharSequence aboutRule1 = "This is a clone of the game Pong.";
	
	About(SpriteBatch spriteBatch, BitmapFont white, Rectangle field, MenuSwitch currentMenu) {
		this.spriteBatch = spriteBatch;
		this.white = white;
		this.field = field;
		this.currentMenu = currentMenu;
	}

	MenuSwitch draw(MenuSwitch currentMenu) {
		spriteBatch.begin();
			white.draw(spriteBatch, aboutRule1, field.width / 2 - 284f, 500f);
		spriteBatch.end();
		if ( Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
//			Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
//			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			currentMenu = MenuSwitch.MAIN;
		}
//		return menu;
		return currentMenu;
	}
}
