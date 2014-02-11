package com.juanuxx.sparrowgames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

	
	
public class logoCargando extends AbstractScreen {
	
	private SpriteBatch batch;

	
	public logoCargando(Principal game) {
		super(game);
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		if(game.assets.manager.update()){
				
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//	ir al menu principal
			game.setScreen(game.pMenu);
						
		}
		
		int width, height;
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		if(game.assets.manager.isLoaded("logo.png", Texture.class)){
			batch.begin();
			batch.draw(game.assets.manager.get("logo.png", Texture.class), 0, 0, width, height);
			batch.end();
		}
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
	}

}
