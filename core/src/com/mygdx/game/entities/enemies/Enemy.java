package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;

public class Enemy extends Entity {

  public Enemy(
      int maxHp,
      int attackPower,
      float width,
      float height,
      Vector2 position,
      Vector2 speed,
      Direction facing) {
    super(maxHp, attackPower, width, height, position, speed, facing);
  }
}
