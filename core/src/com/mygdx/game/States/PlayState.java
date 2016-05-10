package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.States.Enemy;

import java.util.Iterator;


/**
 * Created by Hamzah on 4/50/2016.
 */
public class PlayState extends State  {

    private Texture bird;
    private Texture background;
//  private Enemy enemy;
    private Vector3 touchPos;
    private double points;
    private long maxPlayerHealth = 150;
    private long playerHealthPoints = maxPlayerHealth;
    private HealthBar playerHealthBar;
    private Array<Enemy> enemies;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Texture("bird.png");
        background = new Texture("bg.png");
        enemies = new Array<Enemy>();
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        playerHealthPoints = 150;
        points = 0;
        playerHealthBar = new HealthBar(new Texture("playerHealthbg.png"), new Texture("playerHealthfg.png"));
        spawnEnemy();
    }

    @Override
    protected void handleInput() {
        //Handle the input of touching an enemy

    }

    @Override
    public void update(float dt) {
        playerHealthBar.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
//        spriteBatch.draw(enemy.getTexture(), enemy.getPosition().x, enemy.getPosition().y);
        for (Enemy enemy: enemies) {
            spriteBatch.draw(enemy.getTexture(), enemy.getPosition().x, enemy.getPosition().y);
        }
        spriteBatch.end();

        playerHealthBar.render(spriteBatch);
        if (enemies.size < 4) {
            spawnEnemy();
        }

        Iterator<Enemy> iterator = enemies.iterator();
        while(iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.render(spriteBatch);
            enemy.update();
            touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

            if (TimeUtils.millis() - enemy.getSpawnTime() >= 7000) {
                iterator.remove();
            }

            if(Gdx.input.justTouched()) {

                cam.unproject(touchPos);
                float x = touchPos.x;
                float y = touchPos.y;

                float enemyWidth = enemy.getPosition().x;
                float enemyHeight = enemy.getPosition().y;

                float enemyWidthEnd = enemy.getPosition().x + enemy.getTexture().getWidth();
                float enemyHeightEnd = enemy.getPosition().y + enemy.getTexture().getHeight();

                long currentTime = TimeUtils.millis();
                long timePassed = currentTime - enemy.getSpawnTime();
                if ((x >= enemyWidth && x <= enemyWidthEnd) && (y >= enemyHeight && y <= enemyHeightEnd)) {
                    System.out.println("Enemy is Touched!");

                    // Can add damage to enemy class.
                    // Can add health which correlates to time to enemy class
                    if (timePassed >= 7000) {
                        System.out.println(points);
                        System.out.println(currentTime);
                        System.out.println(enemy.getSpawnTime());
                        System.out.println(currentTime - enemy.getSpawnTime());
                        System.out.println("You touched the enemy after 7 seconds");
                        playerHealthPoints -= 20;
                        iterator.remove();

                    } else {
                        points += timePassed / 1000.0;
                        //if the points is within 1 second of the time limit for the enemy, give extra points.
                        if (currentTime - enemy.getSpawnTime() <= 750) {
                            points += 1000;
                        }
                        if (currentTime - enemy.getSpawnTime() >= 3500) {
                            playerHealthPoints -= 10;
                        }
                        System.out.println(points);
                        System.out.println(currentTime);
                        System.out.println(enemy.getSpawnTime());
                        System.out.println(currentTime - enemy.getSpawnTime());
                        System.out.println("You touched the enemy before 7 seconds of spawning");
                        // destroy enemy
                        iterator.remove();
                    }

                }
            }
        }
//        enemy.update();

//        touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//
//        if(Gdx.input.justTouched()) {
//
//
//            cam.unproject(touchPos);
//            float x = touchPos.x;
//            float y = touchPos.y;
//
//            float enemyWidth = enemy.getPosition().x;
//            float enemyHeight = enemy.getPosition().y;
//
//            float enemyWidthEnd = enemy.getPosition().x + enemy.getTexture().getWidth();
//            float enemyHeightEnd = enemy.getPosition().y + enemy.getTexture().getHeight();
//
//            long currentTime = TimeUtils.millis();
//            long timePassed = currentTime - enemy.getSpawnTime();
//            if ((x >= enemyWidth && x <= enemyWidthEnd) && (y >= enemyHeight && y <= enemyHeightEnd)) {
//                System.out.println("Enemy is Touched!");
//
//                // Can add damage to enemy class.
//                // Can add health which correlates to time to enemy class
//                if (timePassed >= 7000) {
//                    System.out.println(currentTime - enemy.getSpawnTime());
//                    System.out.println("You touched the enemy after 7 seconds");
//                    playerHealthPoints -= 20;
//
//                } else {
//                    points = timePassed / 1000.0;
//                    //if the points is within 1 second of the time limit for the enemy, give extra points.
//                    System.out.println(points);
//                    System.out.println(currentTime - enemy.getSpawnTime());
//                    System.out.println("You touched the enemy before 7 seconds of spawning");
//                    // destroy enemy
//                }
//
//            }
//        }
    }

    private void spawnEnemy() {
        int xPos = MathUtils.random(50, 200);
        int yPos = MathUtils.random(50, 200);
        Enemy enemy = new Enemy(xPos, yPos, TimeUtils.millis());
        enemies.add(enemy);
    }

    private class HealthBar {
        private Sprite healthBarBG;
        private Sprite healthBarFG;
        private final short buffer = 20;

        public HealthBar(Texture healthBG, Texture healthFG) {

            healthBarBG = new Sprite(healthBG);
            healthBarFG = new Sprite(healthFG);

            healthBarBG.setX(100);
            healthBarBG.setY(750);

            healthBarFG.setX(100);
            healthBarFG.setY(750);

            healthBarFG.setOrigin(0, 0);
        }

        public void update() {
            healthBarBG.setX(100);
            healthBarBG.setY(750);

            healthBarFG.setX(100);
            healthBarFG.setY(750);

            healthBarFG.setScale(playerHealthPoints / (float) maxPlayerHealth, 1f);
        }

        public void render(Batch batch) {
            batch.begin();
            healthBarFG.draw(batch);
            healthBarBG.draw(batch);
            batch.end();
        }

    }
    @Override
    public void dispose() {
        //Dispose of all of the assets

    }
}
