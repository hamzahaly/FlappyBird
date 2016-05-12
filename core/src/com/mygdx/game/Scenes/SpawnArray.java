package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

/**
 * Created by iguest on 5/11/16.
 */
public class SpawnArray {

    ArrayList<Vector3> spawnArray;
    private Texture enemy;
    private int enemyWidth;
    private int enemyHeight;

    public SpawnArray() {
        spawnArray = new ArrayList<Vector3>();
        enemy = new Texture("bird.png");
        enemyHeight = enemy.getHeight();
        enemyWidth = enemy.getWidth();
        createArray();
    }

    public void createArray() {
        float heightStart = 25f;
        float widthStart = 25f;

        for (int i = 0; i < 5; i++) {
            spawnArray.add(new Vector3(heightStart, widthStart, 0));
            heightStart += enemyHeight;
            for (int j = 0; j < 5; j++) {
                spawnArray.add(new Vector3(heightStart, widthStart, 0));
                widthStart += enemyWidth;
            }
        }
    }

    public ArrayList<Vector3> getSpawnArray() {
        return spawnArray;

    }

    public int getSize() {
        return spawnArray.size();
    }

    public boolean isEmpty() {
        return spawnArray.isEmpty();
    }

    public Vector3 getPosition(int index) {
        return spawnArray.get(index);
    }
}
