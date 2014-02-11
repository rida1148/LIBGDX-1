package com.juanuxx.sparrowgames;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class puntuacionActor extends Actor {

	public int puntuacion;
	
	private BitmapFont font;

	public puntuacionActor(BitmapFont font) {
		this.font = font;
		puntuacion = 0;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		font.draw(batch, "BICHOS: " + puntuacion, getX(), getY());
	}
	
}
