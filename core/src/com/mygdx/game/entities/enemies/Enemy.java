package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.playable.Player;

public abstract class Enemy extends Entity {

  public Enemy(Direction facing, Body body) {
    super(facing, body);
  }

  public void facePlayer(Player player) {
    float distance = x - player.getX();
    facing = distance < 0 ? Direction.RIGHT : Direction.LEFT;
  }
}
