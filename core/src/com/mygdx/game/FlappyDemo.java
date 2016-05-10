package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.MenuScreen;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class FlappyDemo extends Game {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Flappy Demo";
	private GameStateManager gsm;
	//private SpriteBatch batch;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//gsm = new GameStateManager();
		setScreen(new MenuScreen(this));
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		//gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		super.render();
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//gsm.update(Gdx.graphics.getDeltaTime());
		//gsm.render(batch);
	}
}
