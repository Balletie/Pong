package com.balletie.Pong;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;

public class Pong implements ApplicationListener {
/// Fields	
	public static final String VERSION = "1.0.0";
	private Rectangle field = new Rectangle();
	private Ball ball = new Ball();
	private Paddle paddle1 = new Paddle(), paddle2 = new Paddle();
	private ShapeRenderer shapeRenderer;
	private float fieldTop, fieldBottom, fieldRight, fieldLeft;
	BitmapFont white;
	SpriteBatch spriteBatch;
	int Player1;
	int Player2;
	boolean score1 = false;
	boolean	score2 = false;
	CharSequence cPlayer1;
	CharSequence cPlayer2;
	CharSequence str;
	float textCorrect = 84;
	float initVelocity = 400f;
	float currentVelocity = initVelocity;
	private GameState currentState = GameState.NEW;
	
	private enum GameState {
		NEW,
		RESET,
		PLAY,
	}
	
	@Override
	public void create() {		
		field.set(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fieldLeft = field.x;
		fieldRight = field.x + field.width;
		fieldBottom = field.y;
		fieldTop = field.y + field.height;
		
		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();
		white = new BitmapFont(Gdx.files.internal("data/pongfont.fnt"), Gdx.files.internal("data/pongfont.png"), false);
		newGame();
		reset();
	}

	@Override
	public void dispose() {
		white.dispose();
	}

	@Override
	public void render() {		
		float dt = Gdx.graphics.getRawDeltaTime();
		update(dt);
		draw(dt);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
// Methods
	
	public float textCor(float textCorrect){
		if(Player1 > 9){
			textCorrect = textCorrect + 24f;
		}
		
		if(Player1 > 19){
			textCorrect = textCorrect + 16f;
		}
		
		return textCorrect;
	}
	
	private void draw(float dt){
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		CharSequence cPlayer1 = String.valueOf(Player1);
		CharSequence cPlayer2 = String.valueOf(Player2);
//		CharSequence str = cPlayer1 + " " + cPlayer2;

		spriteBatch.begin();
			white.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			white.setScale(2, 2);
			white.draw(spriteBatch, cPlayer1, (field.width / 2) - textCor(textCorrect), field.height - 50f);
			white.draw(spriteBatch, cPlayer2, (field.width / 2) + 52, field.height - 50f);
		spriteBatch.end();
		
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			drawBall(dt);
			drawPaddle(dt);
		shapeRenderer.end();	
	}
	
	private void drawBall (float dt){
		shapeRenderer.rect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
	}
	
	private void drawPaddle (float dt) {
		shapeRenderer.rect(paddle1.getX(), paddle1.getY(), paddle1.getWidth(), paddle1.getHeight());
		shapeRenderer.rect(paddle2.getX(), paddle2.getY(), paddle2.getWidth(), paddle2.getHeight());
	}
	
	private void update(float dt){
		switch(currentState) {
		case NEW:
			newGame();
			reset();
			currentState = GameState.PLAY;
			break;
		case RESET:
			reset();
			currentState = GameState.PLAY;
			break;
		case PLAY:
			handleInput();
			updateBall(dt);
			updatePaddle1(dt);
			updatePaddle2(dt);
			break;
		}
		
//		updateScore();
	}

	private void handleInput() {
		if(Gdx.input.isKeyPressed(Input.Keys.N)) {
			currentState = GameState.NEW;
		} else if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		} else if(Gdx.input.isKeyPressed(Input.Keys.R)) {
			currentState = GameState.RESET;
		}

	}
	
	private void newGame() {
		Player1 = 0;
		Player2 = 0;
	}
	
	private void updateBall(float dt) {
		
		ball.integrate(dt);
		ball.updateBounds();
		
//		if(ball.right() < paddle2.left() && ball.getY() > paddle2.getY() && ball.getHeight() < paddle2.getHeight()) {
//			ball.move(paddle2.left() - ball.getWidth(), ball.getY());
//			ball.reflect(true, false);
//		}
//		
//		if(ball.left() < paddle1.right() && ball.getY() > paddle1.getY() && ball.getHeight() < paddle1.getHeight()) {
//			ball.move(paddle1.right(), ball.getY());
//			ball.reflect(true, false);
//		}
		
		//Field collision
		if(ball.left() < fieldLeft) {
//			ball.move(fieldLeft, ball.getY());
//			ball.reflect(true, false);
			score(true, false);
		}
		
		if(ball.right() > fieldRight) {
//			ball.move(fieldRight - ball.getWidth(), ball.getY());
//			ball.reflect(true, false);
			score(false, true);
		}
		
		if(ball.bottom() < fieldBottom) {
			ball.move(ball.getX(), fieldBottom);
			ball.reflect(false, true);
		}
		
		if(ball.top() > fieldTop) {
			ball.move(ball.getX(), fieldTop - ball.getHeight());
			ball.reflect(false, true);
		}
		
		//Paddle collision
		if(ball.getBounds().overlaps(paddle1.getBounds())) {
			if(ball.left() < paddle1.right() && ball.right() > paddle1.right()){
				ball.move(paddle1.right(), ball.getY());
				ball.reflect(true, false);
				float ballCenterY = ball.getY() + (ball.getHeight() / 2f);
				float paddleCenterY = paddle1.getY() + (paddle1.getHeight() / 2f);
				float difference = ballCenterY - paddleCenterY;
				float position = difference / (paddle1.getHeight() / 2f);
				if(position > 1) {
					position = 1;
				} else if(position < -1) {
					position = -1;
				}
				float angle = 70f * position;
				currentVelocity = currentVelocity + (initVelocity * .05f);
				
				Vector2 velocity = ball.getVelocity();
				velocity.set(currentVelocity, 0f);
				velocity.setAngle(angle);
				ball.setVelocity(velocity);
				System.out.println("Paddle1: " + angle + " - " + currentVelocity);
			}
		} else if(ball.getBounds().overlaps(paddle2.getBounds())) {
			if(ball.right() > paddle2.left() && ball.left() < paddle2.left()){
				ball.move(paddle2.left() - ball.getWidth(), ball.getY());
				ball.reflect(true, false);
				float ballCenterY = ball.getY() + (ball.getHeight() / 2f);
				float paddleCenterY = paddle2.getY() + (paddle2.getHeight() / 2f);
				float difference = ballCenterY - paddleCenterY;
				float position = difference / (paddle2.getHeight() / 2f);
				if(position > 1) {
					position = 1;
				} else if(position < -1) {
					position = -1;
				}
				float angle = 70f * position;
				
				currentVelocity = currentVelocity + (initVelocity * .05f);
				
				Vector2 velocity = ball.getVelocity();
				velocity.set(currentVelocity, 0f);
				velocity.setAngle(180f - angle);
				ball.setVelocity(velocity);
				System.out.println("Paddle2: " + angle + " - " + currentVelocity);
			}
		}
		
	}
	
//	public void onCollision(boolean paddleReflect, boolean boundReflect, float currentVelocity){
//		if(paddleReflect){
//			Vector2 velocity = ball.getVelocity();
//			currentVelocity = currentVelocity * .1f;
//			velocity.set(currentVelocity, 0f);
//		}
//	}
	
	private void updatePaddle1(float dt) {
		boolean moveDown = false, moveUp = false;
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			moveUp = true;
			moveDown = false;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.S)){
			moveUp = false;
			moveDown = true;
		}
		
		if(moveDown) {
			paddle1.setVelocity(0f, -500f);
		} else if(moveUp) {
			paddle1.setVelocity(0f, 500f);
		} else {
			paddle1.setVelocity(0f, 0f);
		}
		
		paddle1.integrate(dt);
		paddle1.updateBounds();
		
		if(paddle1.top() > fieldTop) {
			paddle1.move( paddle1.getX(), fieldTop - paddle1.getHeight());
			paddle1.setVelocity(0f, 0f);
		}
		
		if(paddle1.bottom() < fieldBottom) {
			paddle1.move( paddle1.getX(), fieldBottom);
			paddle1.setVelocity(0f, 0f);
		}
		
	}
	
	
	private void updatePaddle2(float dt) {
		boolean moveDown = false, moveUp = false;
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			moveUp = true;
			moveDown = false;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			moveUp = false;
			moveDown = true;
		}

		paddle2.integrate(dt);
		paddle2.updateBounds();
		
		if(moveDown) {
			paddle2.setVelocity(0f, -500f);
		} else if(moveUp) {
			paddle2.setVelocity(0f, 500f);
		} else {
			paddle2.setVelocity(0f, 0f);
		}
		
		if(paddle2.top() > fieldTop) {
			paddle2.move(paddle2.getX(), fieldTop - paddle2.getHeight());
			paddle2.setVelocity(0f, 0f);
		}
		
		if(paddle2.bottom() < fieldBottom) {
			paddle2.move(paddle2.getX(), fieldBottom);
			paddle2.setVelocity(0f, 0f);
		}
		
	}
	
	private void score(boolean score1, boolean score2) {

		if(score2) {
			Player1 = Player1 + 1;
		} 
		
		if(score1) {
			Player2 = Player2 + 1;
		}
		
		System.out.println(Player1 + " " + Player2);
		
		reset();
	}
	
// Reset various things (initial ball position, initial ball velocity, initial paddle position)
	public void reset() {
		//Reset Ball
		currentVelocity = initVelocity;
		ball.move((field.x + (field.width - ball.getWidth())) / 2, field.y + (field.height - ball.getHeight()) / 2);
		Vector2 velocity = ball.getVelocity();
		velocity.set(initVelocity, 0f);
		velocity.setAngle(0f);
		ball.setVelocity(velocity);
		
		//Reset Paddles
		paddle1.move(field.x + (field.width * .1f), field.y + (field.height - paddle1.getHeight()) / 2);
		paddle2.move(field.x + field.width - (field.width * .1f) - paddle1.getWidth(), field.y + (field.height - paddle2.getHeight()) / 2);
	}
}
