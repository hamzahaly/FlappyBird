package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by iguest on 4/28/16.
 */
public class Enemy {

    private Vector3 position;
    private long spawnTime;
    private int maxHealth = 500;
    private int health = maxHealth;
    private Texture enemy;
    private HealthBar healthBar;

    //Basic Enemy Type
    public Enemy(int x, int y) {
        position = new Vector3(x, y, 0);
        enemy = new Texture("bird.png");
        spawnTime = TimeUtils.millis();
        System.out.println(spawnTime);
        healthBar = new HealthBar(this, new Texture("enemyhealthbg.png"), new Texture("enemyhealthfg.png"));
    }

    public void update() {
        spawnTime = TimeUtils.millis();
        //System.out.println(spawnTime);
        healthBar.update();
    }

    public void render(Batch batch) {
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

            healthBarBG.setX(100);
            healthBarBG.setY(100);

            healthBarFG.setX(100);
            healthBarFG.setY(100);

            healthBarFG.setOrigin(0, 0);
        }

        public void update() {
            healthBarBG.setX(100);
            healthBarBG.setY(100);

            healthBarFG.setX(100);
            healthBarFG.setY(100);

            healthBarFG.setScale(owner.health / (float) owner.maxHealth, 1f);
        }

        public void render(Batch batch) {
            healthBarFG.draw(batch);
            healthBarBG.draw(batch);
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
