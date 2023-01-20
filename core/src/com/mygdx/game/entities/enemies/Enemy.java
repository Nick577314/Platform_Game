package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.levels.Level;

public abstract class Enemy extends Entity {

  public Enemy(Direction facing, Body body, Level level) {
    super(facing, body, level);
  }

  public void facePlayer(Player player) {
    float distance = x - player.getX();
    facing = distance < 0 ? Direction.RIGHT : Direction.LEFT;
  }

  public void moveLeft() {
    // if (movementDisabled) {
    //   body.setLinearVelocity(0, 0);
    //   return;
    // }

    body.setLinearVelocity(-speed, body.getLinearVelocity().y);
  }

  public void moveRight() {
    // if (movementDisabled) {
    //   body.setLinearVelocity(0, 0);
    //   return;
    // }

    body.setLinearVelocity(speed, body.getLinearVelocity().y);
  }
}
