package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.FlappyDemo;

/**
 * Created by iguest on 5/10/16.
 */
public class MenuScreen implements Screen {

    private FlappyDemo game;
    private Texture background;
    private Texture playBtn;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    public MenuScreen(FlappyDemo game) {
        this.game = game;
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(800, 480, gamecam);
    }

    public void handleInput() {
        if(Gdx.input.justTouched()) {
            game.setScreen(new PlayScreen(game));
            dispose();
        }
    }

    public void update() {
        handleInput();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        game.batch.begin();
        game.batch.draw(background, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        game.batch.draw(playBtn, (FlappyDemo.WIDTH / 2) - (playBtn.getWidth() / 2), FlappyDemo.HEIGHT / 2);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
