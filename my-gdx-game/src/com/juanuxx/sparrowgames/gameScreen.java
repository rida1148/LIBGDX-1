package com.juanuxx.sparrowgames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import java.util.ArrayList;
import java.util.List;

public class gameScreen extends AbstractScreen {

	private Stage stage;
	private SpriteBatch batch;
	private playerActor player;
	
	private puntuacionActor puntuacion;
	private List<bichoActor> bichos;
	private float timer;
	
	public gameScreen(Principal game) {
		super(game);
		
	}

	@Override
	public void render(float delta) {
		
		int width = Gdx.graphics.getWidth();
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if(imgFondo3.getX()+imgFondo3.getWidth() <0){
			imgFondo3.setX(width);
		}else{
			imgFondo3.setX(imgFondo3.getX()-1);
		}
		if(imgFondo33.getX()+imgFondo33.getWidth() <0){
			imgFondo33.setX(width);
		}else{
			imgFondo33.setX(imgFondo33.getX()-1);
		}
		if(imgFondo2.getX()+ imgFondo2.getWidth() < 0){
			imgFondo2.setX(width);
		}else{
			imgFondo2.setX(imgFondo2.getX()-2);
		}
		if(imgFondo22.getX() + imgFondo22.getWidth() < 0){
			imgFondo22.setX(width);
		}else{
			imgFondo22.setX(imgFondo22.getX()-2);
		}
			
		if(timer > 35 && player.darGravedad()){
			crearBicho();
			timer = 0;
		}else
			timer++;
		comprobarColisiones();
		comprobarListas();
		bVida.actualizar(player.hambre);	
		if(player.getY() + player.getHeight() < 0){
			
			if(game.sonido){
				game.assets.manager.get("Shut_Down1.wav", Sound.class).play();
			
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				game.assets.manager.get("mus2.mp3", Music.class).stop();
				
			}
			
			game.setScreen(game.pResumen);
		}
		stage.draw();

	}

	private Image imgFondo1;
	private Image imgFondo2;
	private Image imgFondo22;
	private Image imgFondo3;
	private Image imgFondo33;

	private barraVida bVida;
	
	@Override
	public void show() {
		
		if(game.sonido){
			game.assets.manager.get("mus2.mp3", Music.class).setLooping(true);
			game.assets.manager.get("mus2.mp3", Music.class).play();
		}
		bichos = new ArrayList<bichoActor>();
		 
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		stage = new Stage(width, height, true, batch);
		Gdx.input.setInputProcessor(stage);
		
		imgFondo1 = new Image(game.assets.manager.get("cielo.png", Texture.class));
		imgFondo1.setFillParent(true);
		stage.addActor(imgFondo1);
		
		
		imgFondo2 = new Image(game.assets.manager.get("ciudad.png", Texture.class));
		imgFondo22 = new Image(game.assets.manager.get("ciudad.png", Texture.class));
		imgFondo2.setFillParent(true);
		imgFondo22.setFillParent(true);
		
		stage.addActor(imgFondo2);
		imgFondo22.setPosition(width, imgFondo22.getY());
		stage.addActor(imgFondo22);
		
		
		player = new playerActor(game);
		stage.addActor(player);
		
		imgFondo33 = new Image(game.assets.manager.get("fondonuves.png", Texture.class));
		imgFondo3 = new Image(game.assets.manager.get("fondonuves.png", Texture.class));
		imgFondo3.setFillParent(true);
		imgFondo33.setFillParent(true);
		stage.addActor(imgFondo3);
		imgFondo33.setPosition(width, imgFondo3.getY());
		stage.addActor(imgFondo33);
				
			Image btnSalir = new Image(game.assets.manager.get("salir.png", Texture.class));
		btnSalir.setSize(32, 32);
		btnSalir.setPosition(width-btnSalir.getWidth(), height-btnSalir.getHeight());
		stage.addActor(btnSalir);
		btnSalir.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				//Gdx.app.exit();
				
				if(game.sonido){
					game.assets.manager.get("click1.wav", Sound.class).play();
					game.assets.manager.get("mus2.mp3", Music.class).stop();
				}
				game.setScreen(game.pResumen);
				return true;
			}
		});
		
		puntuacion = new puntuacionActor(new BitmapFont());
		game.puntosPantalla = game.puntosParcial = puntuacion.puntuacion;
		puntuacion.setPosition(10, stage.getHeight() - 10);
		stage.addActor(puntuacion);
		stage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// CLICK
								player.cambiarGravedad();
				return true;
			}
		});
		
		bVida = new barraVida(game);
		bVida.setPosition(10, stage.getHeight() - 40);
		stage.addActor(bVida);
	}
	
	private void crearBicho() {
		bichoActor bicho = new bichoActor(game);
		bicho.setPosition(stage.getWidth(), 0.1f * stage.getHeight() + 
				0.8f * stage.getHeight() * (float) Math.random());
		
		bicho.bb.x = bicho.getX();
		bicho.bb.y = bicho.getY();
		stage.addActor(bicho);
		bichos.add(bicho);
		
	}
	
	private void comprobarListas() {
		for(int i = 0; i < bichos.size(); i++) {
			if(bichos.get(i).getRight() < 0) {
				bichos.get(i).remove();
				bichos.remove(i);
				
			}
		}
		
	}
	private void comprobarColisiones() {
		bichoActor b;
		for(int i = 0; i < bichos.size(); i++) {
			b = bichos.get(i);
			
			 
			if(b.bb.overlaps(player.bb)) {
			   			   
				bichos.get(i).remove();
				bichos.remove(i);
				
				if(player.hambre >= 120){
					 game.puntosNido++;
					 game.puntosParcial++;
			    }
							
				if(player.hambre + 40 < 128){
					player.hambre += 40;
				
				}else{
					player.hambre = 128;
				}
				
							
				 puntuacion.puntuacion++;
				 game.puntosPantalla = puntuacion.puntuacion;
			 
				 if(game.sonido){
					 game.assets.manager.get("comebicho.mp3", Sound.class).play();
				 }
			}
		}
			
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		game.pref.SavePreferences();
		batch.dispose();
		stage.dispose();

	}

}
