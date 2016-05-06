package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.FlappyDemo;
import com.mygdx.game.States.Enemy;

import java.sql.Time;

/**
 * Created by Hamzah on 4/25/2016.
 */
public class PlayState extends State  {

    private Texture bird;
    private Texture background;
    private Enemy enemy;
    private Vector3 touchPos;


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        //bird = new Texture("bird.png");
        background = new Texture("bg.png");
        enemy = new Enemy(100, 100);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {
        //Handle the input of touching an enemy
        //How to handle touching a specific area i.e. specifically the enemy texture
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

        enemy.update();

        if (TimeUtils.millis() - enemy.getSpawnTime() >= 5000) {
            //Destroy the enemy because the countdown has reached 5 seconds.
            //Create a UI element that simulates time

        }

        touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

        if(Gdx.input.justTouched()) {


            cam.unproject(touchPos);
            float x = touchPos.x;
            float y = touchPos.y;

//            System.out.println(x);
//            System.out.println(y);
            float enemyWidth = enemy.getPosition().x;
            float enemyHeight = enemy.getPosition().y;

            float enemyWidthEnd = (float) enemy.getPosition().x + enemy.getTexture().getWidth(); //Get position and add the width to get coordinate at the right most of the image
            float enemyHeightEnd = (float) enemy.getPosition().y + enemy.getTexture().getHeight(); //Get position and add the height to get coordinate at the top of the image
//            System.out.println("Enemy Width is at: " + enemyWidthEnd);
//            System.out.println("Enemy Height is at: " + enemyHeightEnd);
//            System.out.println("just touched");
//            System.out.println(enemyHeight);

            if ((x >= enemyWidth && x <= enemyWidthEnd) && (y >= enemyHeight && y <= enemyHeightEnd)) {
                System.out.println("Enemy is Touched!");
                
                //if the enemy is touched between 0 and 5 seconds of spawning then their score is low
                //if the enemy is touched between 5 and 7 seconds, their score is higher
                //if the enemy is touched after 9 seconds or as close to 10 seconds to spawning then they get the highest score
                //As time passes, subtract from the health pool.
                //Convert the difference to a measurable score

            }
        }
    }

    @Override
    public void dispose() {
        //Dispose of all of the assets

    }
}
