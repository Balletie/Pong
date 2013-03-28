package com.balletie.Pong;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Audio extends Pong{

	Audio(Sound ball_bump, Sound ball_score, Music background_music){
		this.ball_bump = ball_bump;
		this.ball_score = ball_score;
		this.background_music = background_music;
	}
	
	public void backgroundMusic(Music background_music) {
		background_music.setVolume(0.7f);
		background_music.setLooping(true);
		background_music.play();
	}

	public static void ballScore(Sound ball_score) {
		ball_score.play(1f);
	}

	public static void ballBump(Sound ball_bump) {
		ball_bump.play(1f);
	}
}