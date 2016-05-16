package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.Scenes.GameoverHud;

/**
 * Created by iguest on 5/11/16.
 */
public class GameoverScreen implements Screen {
    private FlappyDemo game;
    private Texture background;
    private Texture gameover;
    private GameoverHud gameoverhud;

    public GameoverScreen(FlappyDemo game, int score, int hits, int perfectHits, int failedHits) {
        this.game = game;
        gameoverhud = new GameoverHud(score, hits, perfectHits, failedHits, game.batch);
        background = new Texture("bg.png");
        gameover = new Texture("gameover.png");
    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
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
        game.batch.setProjectionMatrix(gameoverhud.stage.getCamera().combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.draw(gameover, (FlappyDemo.WIDTH / 4) - (gameover.getWidth() / 2), FlappyDemo.HEIGHT / 3);
        game.batch.end();
        gameoverhud.stage.draw();


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
