package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.FlappyDemo;

/**
 * Created by iguest on 5/13/16.
 */
public class GameoverHud {
    public Stage stage;
    public Viewport viewport;

    private int score;
    private int hits;

    Label scoreLabel;
    Label hitsLabel;
    Label scoreTxt;
    Label hitsTxt;

    public GameoverHud(int score, int hits, SpriteBatch batch) {
        this.score = score;
        this.hits = hits;
        viewport = new FitViewport(FlappyDemo.HEIGHT / 2 , FlappyDemo.WIDTH / 2, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.setFillParent(true);

        scoreLabel = new Label("score: ", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        hitsLabel = new Label("hits: ", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreTxt = new Label(String.format("%06d"), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        hitsTxt = new Label(String.format("%03d"), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(scoreLabel);
        table.add(scoreTxt);
        table.row();
        table.add(hitsLabel);
        table.add(hitsTxt);

    }
}
