package com.balletie.Pong;

import com.badlogic.gdx.math.*;

public abstract class GameObject {
//Fields	
	private Vector2 position = new Vector2();
	private Vector2 velocity = new Vector2();
	private Rectangle bounds = new Rectangle();
	
//Constructors	
	
	protected GameObject(int width, int height) {
		bounds.setWidth(width);
		bounds.setHeight(height);
	}
	
//Methods	
	
	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}
	
	public float bottom() {
		return bounds.y;
	}
	
	public float left() {
		return bounds.x;
	}
	
	public float top() {
		return bounds.y + bounds.height;
	}
	
	public float right() {
		return bounds.x + bounds.width;
	}
	
	public float getHeight() {
		return bounds.height;
	}

	public float getWidth() {
		return bounds.width;
	}
	
	public float getVelocityX() {
		return velocity.x;
	}
	
	public float getVelocityY() {
		return velocity.y;
	}	
	
	public void setVelocity(float x, float y) {
		velocity.set(x, y);
	}
	
	public void move(float x, float y) {
		position.set(x, y);
	}
	
	public void translate(float x, float y) {
		position.add(x, y);
	}
	
	public void integrate(float dt) {
		position.add(velocity.x * dt, velocity.y * dt);
	}
	
// Getters/Setters	
	
	public Rectangle getBounds() {
		updateBounds();
		return bounds;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public void updateBounds() {
		bounds.set(position.x, position.y, bounds.width, bounds.height);
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
}
