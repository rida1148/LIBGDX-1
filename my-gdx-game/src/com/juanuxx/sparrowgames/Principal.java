package com.juanuxx.sparrowgames;

import com.badlogic.gdx.Game;

public class Principal extends Game {

	public Manager assets;
	public int puntosNido;
	public int puntosPantalla;
	public int puntosParcial;
	public opciones pref;
	public boolean sonido;
	
	public Principal() {
		// TODO Auto-generated constructor stub
	}
	
	public AbstractScreen pLoading, pMenu, pJuego, pResumen, pOpciones;
	
	@Override
	public void create() {

		pref = new opciones(this);	
		assets = new Manager();
		pLoading = new logoCargando(this);
		pMenu = new mainMenu(this);
		pJuego = new gameScreen(this);
		pResumen = new resumenScreen(this);
		pOpciones = new opcionesScreen(this);
		assets.loadAssets();
		pref.GetPreferences();
		setScreen(pLoading);
		
		
	}
	
	@Override
	public void dispose() {
		pref.SavePreferences();
		super.dispose();
		assets.manager.dispose();
		
	}
}

