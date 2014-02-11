package com.juanuxx.sparrowgames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class opcionesScreen extends AbstractScreen {

	private Image imgFondo;
	private SpriteBatch batch;
	private Stage stage;	
	
	public opcionesScreen(Principal game) {
		super(game);
		batch = new SpriteBatch();
	}
		
	@Override
	public void render(float delta) {
			stage.draw();		
	}

	Image btnRst, btnSal, btnSo;
	
	@Override
	public void show() {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		stage = new Stage(width, height, true, batch);
		Gdx.input.setInputProcessor(stage);
		
		imgFondo = new Image(game.assets.manager.get("cielo.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);
		
		btnSal = new Image(game.assets.manager.get("boton_off.png", Texture.class));
		btnRst = new Image(game.assets.manager.get("reset.png", Texture.class));
				
		TextureRegion btnAudio = new TextureRegion(game.assets.manager.get("audio.png", Texture.class), 64,64);
		
		if(game.sonido){
			btnAudio.setRegion(0, 0, 64, 64);
		}else{
			btnAudio.setRegion(64, 0, 64, 64);
		}
		
		btnSo = new Image(btnAudio);
		
		
		btnSo.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(game.sonido) {
					game.assets.manager.get("click1.wav", Sound.class).play();
					game.sonido = false;
					game.assets.manager.get("mus1.mp3", Music.class).stop();
				}else{
					game.sonido = true;
					game.assets.manager.get("mus1.mp3", Music.class).play();
				}
				game.setScreen(game.pOpciones);
				return true;
			}
			
		});
		

		btnRst.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(game.sonido) {
					game.assets.manager.get("click1.wav", Sound.class).play();
				}
				
				game.puntosNido = 0;
				
				game.setScreen(game.pOpciones);
				return true;
			}
			
		});
		
		btnSal.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(game.sonido) {
					game.assets.manager.get("click1.wav", Sound.class).play();
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				game.setScreen(game.pMenu);
				return true;
			}
		});

		if(Gdx.app.getType() == ApplicationType.Android) {
			
			colocarBotonesAndroid();
			
		}else if(Gdx.app.getType() == ApplicationType.Desktop){
			
			colocarBotonesDesktop();
			
		}
		
		stage.addActor(btnSo);
		stage.addActor(btnRst);
		stage.addActor(btnSal);
	}

private void colocarBotonesDesktop(){
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		btnSo.setPosition(width/3 - btnSo.getWidth()/2 , height/2 - btnSo.getHeight());
	
		btnRst.setPosition(width/2 - btnRst.getWidth()/2, height/2 - btnRst.getHeight());
	
		btnSal.setPosition((width/3)*2 - btnSal.getWidth()/2, height/2 - btnSal.getHeight());
	}

	private void colocarBotonesAndroid(){
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		btnSo.setPosition(width/3 - btnSo.getWidth()/2 , height - btnSo.getHeight()*6);
	
		btnRst.setPosition(width/2 - btnRst.getWidth()/2, height - btnRst.getHeight()*6);
	
		btnSal.setPosition((width/3)*2 - btnSal.getWidth()/2, height - btnSal.getHeight()*6);
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		
		game.pref.SavePreferences();
		batch.dispose();
		stage.dispose();

	}

}
