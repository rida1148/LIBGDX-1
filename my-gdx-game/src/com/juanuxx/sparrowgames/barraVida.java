package com.juanuxx.sparrowgames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class barraVida extends Actor {

	private TextureRegion imgvida;
	
	public barraVida(Principal game) {
		imgvida = new TextureRegion(game.assets.manager.get("vida.png",
				Texture.class), 128 , 8);
		setSize(imgvida.getRegionWidth(), imgvida.getRegionHeight());
		
	}
	
	public void actualizar(int vida){
		imgvida.setRegion(0, 0, vida , 8);
		setSize(imgvida.getRegionWidth(), imgvida.getRegionHeight());
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(imgvida, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());
	}
	
}
