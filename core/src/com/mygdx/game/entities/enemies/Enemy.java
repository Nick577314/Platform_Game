package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.Entity;

public abstract class Enemy extends Entity {

  public Enemy(float x, float y, Direction facing, Body body) {
    super(facing, body);
  }
}
