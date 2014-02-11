package com.juanuxx.sparrowgames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class resumenScreen extends AbstractScreen {

	private Image imgFondo;
	private SpriteBatch batch;
	private Stage stage;
	private nidoActor marcador;
		
	public resumenScreen(Principal game) {
		super(game);
		batch = new SpriteBatch();
	}
	
	@Override
	public void render(float delta) {
		
		stage.draw();
				
	}

	@Override
	public void show() {
		
		if(game.sonido) {
			game.assets.manager.get("mus2.mp3", Music.class).stop();
			game.assets.manager.get("mus1.mp3", Music.class).play();
			
		}
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		stage = new Stage(width, height, true, batch);
		Gdx.input.setInputProcessor(stage);
		
		imgFondo = new Image(game.assets.manager.get("cielo.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		marcador = new nidoActor(this.game);
		stage.addActor(marcador);
		
		Image btnGo = new Image(game.assets.manager.get("gobtn.png", Texture.class));
		btnGo.setPosition((width/3)*2 - btnGo.getWidth()/2, height - btnGo.getHeight()*4);
		
		Image btnSal = new Image(game.assets.manager.get("boton_off.png", Texture.class));
		btnSal.setPosition(width/3 - btnSal.getWidth()/2 , height - btnSal.getHeight()*4);
		
		
		stage.addActor(btnGo);
		stage.addActor(btnSal);
		
		btnGo.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
			//	musica.stop();
				if(game.sonido){
					game.assets.manager.get("mus1.mp3", Music.class).stop();

					game.assets.manager.get("click1.wav", Sound.class).play();
				}
				
				game.setScreen(game.pJuego);
				return true;
			}
		});
		
		btnSal.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(game.sonido){
					game.assets.manager.get("click1.wav", Sound.class).play();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				game.setScreen(game.pMenu);
				return true;
			}
		});
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();
		
	}

}
