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
        float heightStart = 0;
        float widthStart = 0;

        for (int i = 0; i < 4; i++) {
            heightStart += enemyHeight;
            spawnArray.add(new Vector3(heightStart, widthStart, 0));
            for (int j = 0; j < 4; j++) {
                widthStart += enemyWidth;
                spawnArray.add(new Vector3(heightStart, widthStart, 0));
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
