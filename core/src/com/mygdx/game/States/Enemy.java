package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import java.sql.Time;

/**
 * Created by iguest on 4/28/16.
 */
public class Enemy {

    private Vector3 position;
    private long spawnTime;
    private int maxHealth = 7000;
    private long health = maxHealth;
    private Texture enemy;
    private HealthBar healthBar;

    //Basic Enemy Type
    public Enemy(int x, int y, long spawn) {
        position = new Vector3(x, y, 0);
        enemy = new Texture("bird.png");
        spawnTime = spawn;
        healthBar = new HealthBar(this, new Texture("enemyhealthbg.png"), new Texture("enemyhealthfg.png"));
    }

    public void update() {
        healthBar.update();
    }

    public void render(Batch batch) {
        long time = TimeUtils.millis();
        health = maxHealth - (time - spawnTime);
        healthBar.render(batch);

    }

    private class HealthBar {
        private Sprite healthBarBG;
        private Sprite healthBarFG;
        private Enemy owner;
        private final short buffer = 20;

        public HealthBar(Enemy owner, Texture healthBG, Texture healthFG) {
            this.owner = owner;

            healthBarBG = new Sprite(healthBG);
            healthBarFG = new Sprite(healthFG);

            healthBarBG.setX(getPosition().x);
            healthBarBG.setY(getPosition().y);

            healthBarFG.setX(getPosition().x);
            healthBarFG.setY(getPosition().y);

            healthBarFG.setOrigin(0, 0);
        }

        public void update() {
            healthBarBG.setX(getPosition().x);
            healthBarBG.setY(getPosition().y);

            healthBarFG.setX(getPosition().x);
            healthBarFG.setY(getPosition().y);

            healthBarFG.setScale(owner.health / (float) owner.maxHealth, 1f);
        }

        public void render(Batch batch) {
            batch.begin();
            healthBarFG.draw(batch);
            healthBarBG.draw(batch);
            batch.end();
        }

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return enemy;
    }

    public long getSpawnTime() {
        return spawnTime;
    }

}
