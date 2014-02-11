package com.juanuxx.sparrowgames;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class mainMenu extends AbstractScreen {
	
	private SpriteBatch batch;
	private Stage stage;
	
	public mainMenu(Principal game) {
		super(game);
		
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		stage.draw();
		
	}

	Image btnGo, btnSal, btnAj;
	
	@Override
	public void show() {

		if(game.sonido) {
			game.assets.manager.get("mus1.mp3", Music.class).setLooping(true);
			if(!game.assets.manager.get("mus1.mp3", Music.class).isPlaying()){
				game.assets.manager.get("mus1.mp3", Music.class).play();
			}
		}
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		stage = new Stage(width, height, true, batch);
		Gdx.input.setInputProcessor(stage);
		
		Image imgFondo = new Image(game.assets.manager.get("fondo.png", Texture.class));
		imgFondo.setFillParent(true);
		
		
		btnGo = new Image(game.assets.manager.get("boton_go.png", Texture.class));
		btnGo.setPosition(width/3 - btnGo.getWidth()/2 , height - btnGo.getHeight()*6);
		
		btnAj = new Image(game.assets.manager.get("btnajustes.png", Texture.class));
		btnAj.setPosition(width/2 - btnAj.getWidth()/2, height - btnGo.getHeight()*6);
		
		btnSal = new Image(game.assets.manager.get("salbtn.png", Texture.class));
		btnSal.setPosition((width/3)*2 - btnSal.getWidth()/2, height - btnGo.getHeight()*6);
		
		if(Gdx.app.getType() == ApplicationType.Android) {
			
			colocarBotonesAndroid();
			
		}else if(Gdx.app.getType() == ApplicationType.Desktop){
			
			colocarBotonesDesktop();
			
		}
		
		stage.addActor(imgFondo);
		stage.addActor(btnGo);
		stage.addActor(btnAj);
		stage.addActor(btnSal);
		
		btnGo.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
			
				if(game.sonido) {
					game.assets.manager.get("mus1.mp3", Music.class).stop();
					game.assets.manager.get("click1.wav", Sound.class).play();
				}
				game.setScreen(game.pJuego);
				return true;
			}
		});
		
		btnAj.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(game.sonido) {
					game.assets.manager.get("click1.wav", Sound.class).play();
				}
				game.setScreen(game.pOpciones);
				return true;
			}
		});
		
		btnSal.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(game.sonido) {
					game.assets.manager.get("mus1.mp3", Music.class).stop();
					game.assets.manager.get("click1.wav", Sound.class).play();
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Gdx.app.exit();
				return true;
			}
		});
	}
	
private void colocarBotonesDesktop(){
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		btnGo.setPosition(width/3 - btnGo.getWidth()/2 , height/2 - btnGo.getHeight());
	
		btnAj.setPosition(width/2 - btnAj.getWidth()/2, height/2 - btnGo.getHeight());
	
		btnSal.setPosition((width/3)*2 - btnSal.getWidth()/2, height/2 - btnGo.getHeight());
	}

	private void colocarBotonesAndroid(){
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		btnGo.setPosition(width/3 - btnGo.getWidth()/2 , height - btnGo.getHeight()*6);
	
		btnAj.setPosition(width/2 - btnAj.getWidth()/2, height - btnGo.getHeight()*6);
	
		btnSal.setPosition((width/3)*2 - btnSal.getWidth()/2, height - btnGo.getHeight()*6);
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		stage.dispose();
	}

}
