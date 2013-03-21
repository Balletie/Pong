package com.balletie.Pong;

import com.badlogic.gdx.Gdx;

public class Menu extends Pong {
	static int touchX = Gdx.input.getX();
	static int touchY = Gdx.input.getY();
	static CharSequence option1 = "Play";
	static CharSequence option2 = "About";

	public static void drawMenu(float dt) {
		newGame();
		about();
	}

	private static void about() {
	spriteBatch.begin();
//		white.setColor(1.0f, 1.0f, 1.0f, 1.0f);
//		white.setScale(2, 2);
		white.draw(spriteBatch, option2, field.width / 2 - 50f, field.height - 300f);
	spriteBatch.end();
	}

	public static boolean newGame() {
		spriteBatch.begin();
//			white.setColor(1.0f, 1.0f, 1.0f, 1.0f);
//			white.setScale(2, 2);
			white.draw(spriteBatch, option1, field.width / 2 - 32f, field.height - 100f);
		spriteBatch.end();
		
//		if (touchX > (field.width / 2 - 32f) && touchX < 2 + 32f && touchY < field.height - 84 && touchY > field.height -100f) {
//			currentState = GameState.NEW;
//		}
		if (Gdx.input.justTouched()) {
			play = true;
		}
		return play;
	}
}
