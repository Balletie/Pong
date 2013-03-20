package com.balletie.Pong;

import com.badlogic.gdx.math.*;

public class Ball extends GameObject {
// Constructor
	public Ball() {
		super(32, 32);
	}
	
// This method handles the bouncing
	public void reflect(boolean x, boolean y) {
		Vector2 velocity = getVelocity();
		if(x)
			velocity.x *= -1;
		if(y)
			velocity.y *= -1;
	
		setVelocity(velocity);
	}
}
