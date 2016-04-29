package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.States.Enemy;

/**
 * Created by Hamzah on 4/25/2016.
 */
public class PlayState extends State {

    private Texture bird;
    private Texture background;
    private Enemy enemy;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Texture("bird.png");
        background = new Texture("bg.png");
        enemy = new Enemy(100, 100);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
        spriteBatch.draw(enemy.getTexture(), enemy.getPosition().x, enemy.getPosition().y);
        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}
