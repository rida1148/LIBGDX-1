package com.juanuxx.sparrowgames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class bichoActor extends Actor {
		
	
		public Rectangle bb;
		private TextureRegion bicho;
		private int currentBicho;
		private int velocidad;
		
		public bichoActor(Principal game) {
			currentBicho = (int)(Math.random() * ((4 - 0) + 1));
			velocidad = 10 + ((int)(Math.random() * ((2 - 0) + 1)));
			
			bicho = new TextureRegion(game.assets.manager.get("bugs.png",
					Texture.class), 32 , 32);
			setSize(bicho.getRegionWidth()/2, bicho.getRegionHeight()/2);
			
		//	setX(Gdx.graphics.getWidth()  - getWidth());
		//	setY(Gdx.graphics.getHeight() / 2);
			bicho.setRegion(32*currentBicho, 0, 18, 23);
			bb = new Rectangle(getX(), getY(), getWidth(), getHeight());
			
		}
		
		@Override
		public void draw(SpriteBatch batch, float parentAlpha) {
			setX(getX()-velocidad);
			bb.x = getX();
			bb.y = getY();
			bb.width = getWidth();
			bb.height = getHeight();
			
			batch.draw(bicho, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());
		}
		
		public boolean comprobarColision(Rectangle bbPlayer){
			return false;
		}
}
