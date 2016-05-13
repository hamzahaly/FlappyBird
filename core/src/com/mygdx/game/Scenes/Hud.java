package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.FlappyDemo;

/**
 * Created by iguest on 5/10/16.
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;

    private int score;
    private int health;

    Label scoreLabel;
    Label healthPoints;
    Label failedHits;
    Label scoreLabelTxt;
    Label healthPointsTxt;
    Label failedHitsTxt;

    public Hud(SpriteBatch batch) {
        score = 0;
        health = 150;
        viewport = new FitViewport(FlappyDemo.HEIGHT / 2 , FlappyDemo.WIDTH / 2, new OrthographicCamera());
        stage = new Stage(viewport, batch);

//        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("name_of_your_font_file.ttf"));
//        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
//        parameter.size = size_of_the_font_in_pixels;
//        parameter.characters = "characters you want to use";
////e.g. abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:
//// These characters should not repeat!
//
//        BitmapFont font = generator.generateFont(parameter);
//        generator.dispose();

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label("Score", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        healthPoints = new Label("HP", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        failedHits = new Label("Success", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreLabelTxt = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        healthPointsTxt = new Label(String.format("%03d", health), new Label.LabelStyle(new BitmapFont(), Color.BLACK));


        table.add(scoreLabel).expandX();
        table.add(healthPoints).expandX();
        table.add(failedHits).expandX();
        table.row(); //Creates a new row to put things under.
        table.add(scoreLabelTxt).expandX();
        table.add(healthPointsTxt).expandX();
        stage.addActor(table);

    }

    public void addScore(int value) {
        score = value;
        scoreLabelTxt.setText(String.format("%06d", score));
    }

    public void updateHP(int value) {
        health = value;
        healthPointsTxt.setText(String.format("%03d", health));
    }

    public void update(float dt) {

    }

    public void dispose() {
        stage.dispose();
    }

}
