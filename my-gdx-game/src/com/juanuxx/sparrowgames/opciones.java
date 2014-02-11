package com.juanuxx.sparrowgames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class opciones {
	private Principal game;
	private Preferences pref;
	
	public opciones(Principal game) {
		this.game = game;
		pref = Gdx.app.getPreferences("preferencias-gorrion");
	}
	
	public void GetPreferences(){
           game.puntosNido = pref.getInteger("nido", 0);  
           game.sonido = pref.getBoolean("sonido", true);
  }
	public void SavePreferences(){
		pref.putInteger("nido", game.puntosNido);
		pref.putBoolean("sonido", game.sonido);
		pref.flush();
	}
}
