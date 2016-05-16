package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
    private int perfectHits;
    private int failedHits;
    private TextButton button;
    private Skin skin;

    private Label scoreLabel;
    private Label hitsLabel;
    private Label perfectHitsLabel;
    private Label failedHitsLabel;
    private Label scoreTxt;
    private Label hitsTxt;
    private Label perfectHitsTxt;
    private Label failedHitTxt;

    public GameoverHud(int score, int hits, int perfectHits, int failedHits, SpriteBatch batch) {
        this.score = score;
        this.hits = hits;
        this.perfectHits = perfectHits;
        this.failedHits = failedHits;
        viewport = new FitViewport(FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        skin = new Skin();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        skin.add("default", new BitmapFont());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);


        Table table = new Table();
        table.setFillParent(true);

        button = new TextButton("button1", skin);
        scoreLabel = new Label("score: ", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        hitsLabel = new Label("hits: ", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        perfectHitsLabel = new Label("hits: ", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        failedHitsLabel = new Label("hits: ", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        scoreTxt = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        hitsTxt = new Label(String.format("%03d", hits), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        perfectHitsTxt = new Label(String.format("%03d", perfectHits), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        failedHitTxt = new Label(String.format("%03d", failedHits), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        button = new TextButton("Button1", skin);
        table.add(button);
        table.add(scoreLabel);
        table.add(scoreTxt);
        table.row();
        table.add(hitsLabel);
        table.add(hitsTxt);
        table.row();
        table.add(perfectHitsLabel);
        table.add(perfectHitsTxt);
        table.row();
        table.add(failedHitsLabel);
        table.add(failedHitTxt);

        stage.addActor(table);
    }

    public void dispose() {
        skin.dispose();
        stage.dispose();
    }

}
