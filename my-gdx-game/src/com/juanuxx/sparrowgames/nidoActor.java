package com.juanuxx.sparrowgames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class nidoActor extends Actor {
	
	private Principal game;
	private TextureRegion imgFondo;
	private BitmapFont font;

	public nidoActor(Principal game) {
		this.game = game;
		imgFondo = new TextureRegion(game.assets.manager.get("nido.png", Texture.class),256,128);
		imgFondo.setRegion(0, 0, 256, 128);
		setSize(imgFondo.getRegionWidth(), imgFondo.getRegionHeight());
		setPosition(Gdx.graphics.getWidth()/2 - imgFondo.getRegionWidth()/2 ,Gdx.graphics.getHeight()/2 - imgFondo.getRegionHeight()/2+50);
		font = new BitmapFont();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		batch.draw(imgFondo, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());

		font.draw(batch, "BICHOS" ,getX() + 95,getY() + imgFondo.getRegionHeight() -10);
		font.draw(batch, "RECOLECTADOS: " + game.puntosPantalla,getX() + 95,getY() + (imgFondo.getRegionHeight() - 30));
		font.draw(batch, "PARA EL NIDO: " + game.puntosParcial, getX() + 95,getY() + (imgFondo.getRegionHeight() - 60));
		font.draw(batch, "TOTAL EN EL NIDO: " + game.puntosNido , getX() + 45,getY() + (imgFondo.getRegionHeight() -90));

		
	}
	
	
}


