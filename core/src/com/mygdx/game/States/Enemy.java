package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by iguest on 4/28/16.
 */
public class Enemy {

    private Vector3 position;
    private long spawnTime;
    private Texture enemy;

    //Basic Enemy Type
    public Enemy(int x, int y) {
        position = new Vector3(x, y, 0);
        enemy = new Texture("bird.png");
        spawnTime = TimeUtils.millis();
        System.out.println(spawnTime);
    }

    public void update(float dt) {
        spawnTime = TimeUtils.millis();
        System.out.println(spawnTime);
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
