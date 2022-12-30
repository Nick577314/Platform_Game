package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;

public abstract class Enemy extends Entity {

    public Enemy(Vector2 position, Direction facing) {
        super(position, facing);
    }
}
