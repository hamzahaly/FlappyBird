package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Scenes.SpawnArray;
import com.mygdx.game.Sprites.Enemy;
import java.util.Iterator;

/**
 * Created by iguest on 5/10/16.
 */
public class PlayScreen implements Screen {
    private FlappyDemo game;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Texture background;
    private Vector3 touchPos;
    private int points;
    private int maxPlayerHealth = 150;
    private int playerHealthPoints = maxPlayerHealth;
    private HealthBar playerHealthBar;
    private Array<Enemy> enemies;
    private SpawnArray spawnArray;
    private Hud hud;
    private int enemiesSize;
    private int perfectHits;
    private int hits;
    private int failedHits;

    public PlayScreen(FlappyDemo game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2, gamecam);
        background = new Texture("bg.png");
        enemies = new Array<Enemy>();
        gamecam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        playerHealthPoints = 150;
        points = 0;
        enemiesSize = 4;
        playerHealthBar = new HealthBar(new Texture("playerHealthbg.png"), new Texture("playerHealthfg.png"));
        spawnArray = new SpawnArray();
        hud = new Hud(game.batch);
        spawnEnemy();
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {

    }

    public void update(float dt) {
        handleInput(dt);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        game.batch.setProjectionMatrix(gamecam.combined);

        game.batch.begin();
        game.batch.draw(background, gamecam.position.x - (gamecam.viewportWidth / 2), 0);
//        spriteBatch.draw(enemy.getTexture(), enemy.getPosition().x, enemy.getPosition().y);
        for (Enemy enemy: enemies) {
            game.batch.draw(enemy.getTexture(), enemy.getPosition().x, enemy.getPosition().y);
        }
        game.batch.end();

        hud.stage.draw();

        playerHealthBar.update();
        playerHealthBar.render(game.batch);

        if (enemies.size < enemiesSize) {
            spawnEnemy();
        }

        Iterator<Enemy> iterator = enemies.iterator();
        while(iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.render(game.batch);
            enemy.update();
            touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

            // Player misses an enemy target.
            if (TimeUtils.millis() - enemy.getSpawnTime() >= 7000) {
                playerHealthPoints -= 20;
                hud.updateHP(playerHealthPoints);
                failedHits++;
                iterator.remove();
            }

            if (Gdx.input.justTouched()) {

                gamecam.unproject(touchPos);
                float x = touchPos.x;
                float y = touchPos.y;

                float enemyWidth = enemy.getPosition().x;
                float enemyHeight = enemy.getPosition().y;

                float enemyWidthEnd = enemy.getPosition().x + enemy.getTexture().getWidth();
                float enemyHeightEnd = enemy.getPosition().y + enemy.getTexture().getHeight();

                long currentTime = TimeUtils.millis();
                long timePassed = currentTime - enemy.getSpawnTime();
                if ((x >= enemyWidth && x <= enemyWidthEnd) && (y >= enemyHeight && y <= enemyHeightEnd)) {
                    // Can add damage to enemy class.
                    // Can add health which correlates to time to enemy class
                    // This if never passes.
                    if (timePassed >= 7000) {
                        System.out.println(points);
                        System.out.println(currentTime);
                        System.out.println(enemy.getSpawnTime());
                        System.out.println(currentTime - enemy.getSpawnTime());
                        playerHealthPoints -= 20;
                        iterator.remove();
                    } else {
                        points += timePassed / 1000.0;
                        //if the points is within 1 second of the time limit for the enemy, give extra points.
                        if (currentTime - enemy.getSpawnTime() >= 6250) {
                            points += 100;
                            perfectHits++;
                            if (perfectHits % 5.0 == 0.0) {
                                enemiesSize++;
                            }
                        }
                        if (currentTime - enemy.getSpawnTime() <= 3500) {
                            playerHealthPoints -= 10;
                            hud.updateHP(playerHealthPoints);
                        }
                        System.out.println(points);
                        System.out.println(currentTime);
                        System.out.println(enemy.getSpawnTime());
                        System.out.println(currentTime - enemy.getSpawnTime());
                        System.out.println("You touched the enemy before 7 seconds of spawning");
                        hud.addScore(points);
                        hits++;
                        iterator.remove();
                    }

                }
            }
        }
        if (playerHealthPoints <= 0) {
            game.setScreen(new GameoverScreen(game, points, hits, perfectHits, failedHits));
            perfectHits = 0;
            hits = 0;
            failedHits = 0;
            points = 0;
        }
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

    private void spawnEnemy() {
        System.out.println("size: " + spawnArray.getSize());
        int r = MathUtils.random(0, 34);
        Vector3 pos = spawnArray.getPosition(r);
        int xPos = (int) pos.x;
        int yPos = (int) pos.y;
        System.out.println("X-POS: " + xPos);
        System.out.println("Y-POS: " + yPos);
        for (Enemy enemy: enemies) {
            Vector3 curPos = enemy.getPosition();
            if (curPos.x == pos.x  || curPos.y == pos.y) {
                System.out.println("Dupe");
                r = MathUtils.random(0, 34);
                pos = spawnArray.getPosition(r);
                xPos = (int) pos.x;
                yPos = (int) pos.y;
            }
        }
        Enemy enemy = new Enemy(xPos, yPos, TimeUtils.millis());
        enemies.add(enemy);
    }

    private class HealthBar {
        private Sprite healthBarBG;
        private Sprite healthBarFG;
//        private final short buffer = 30;

        public HealthBar(Texture healthBG, Texture healthFG) {

            healthBarBG = new Sprite(healthBG);
            healthBarFG = new Sprite(healthFG);

            healthBarBG.setX(50);
            healthBarBG.setY(390);

            healthBarFG.setX(50);
            healthBarFG.setY(390);

            healthBarFG.setOrigin(0, 0);
        }

        public void update() {
            healthBarBG.setX(50);
            healthBarBG.setY(390);

            healthBarFG.setX(50);
            healthBarFG.setY(390);

            healthBarFG.setScale(playerHealthPoints / (float) maxPlayerHealth, 1f);
        }

        public void render(Batch batch) {
            batch.setProjectionMatrix(gamecam.combined);
            batch.begin();
            healthBarFG.draw(batch);
            healthBarBG.draw(batch);
            batch.end();
        }

    }

    @Override
    public void dispose() {
        hud.dispose();
        background.dispose();

    }
}
