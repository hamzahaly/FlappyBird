package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.States.Enemy;

import java.util.Random;


/**
 * Created by Hamzah on 4/25/2016.
 */
public class PlayState extends State  {

    private Texture bird;
    private Texture background;
//  private Enemy enemy;
    private Vector3 touchPos;
    private double points;
    private int playerHealthPoints;
    private Array<Enemy> enemies;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Texture("bird.png");
        background = new Texture("bg.png");
        enemies = new Array<Enemy>();
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        playerHealthPoints = 150;
        points = 0;
        spawnEnemy();
    }

    @Override
    protected void handleInput() {
        //Handle the input of touching an enemy
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        int xPos;
        int yPos;
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
//        spriteBatch.draw(enemy.getTexture(), enemy.getPosition().x, enemy.getPosition().y);
        for (Enemy enemy: enemies) {
            Random random = new Random();
            int num = random.nextInt(3);
            if (num == 0) {
                xPos = 100;
                yPos = 100;
            } else if (num == 1) {
                xPos = 200;
                yPos = 200;
            } else if (num == 2) {
                xPos = 100;
                yPos = 200;
            } else {
                xPos = 200;
                yPos = 100;
            }
            spriteBatch.draw(enemy.getTexture(), xPos, yPos);
        }
        spriteBatch.end();

        enemy.update();

        touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

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
                    System.out.println(currentTime - enemy.getSpawnTime());
                    System.out.println("You touched the enemy after 7 seconds");
                    playerHealthPoints -= 20;

                } else {
                    points = timePassed / 1000.0;
                    //if the points is within 1 second of the time limit for the enemy, give extra points.
                    System.out.println(points);
                    System.out.println(currentTime - enemy.getSpawnTime());
                    System.out.println("You touched the enemy before 7 seconds of spawning");
                    // destroy enemy
                }

            }
        }
    }

    private void spawnEnemy() {
        Enemy enemy = new Enemy(100, 100, TimeUtils.millis());
        enemies.add(enemy);
    }

    @Override
    public void dispose() {
        //Dispose of all of the assets

    }
}
