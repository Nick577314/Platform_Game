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
    //    Vector2 force = new Vector2(-speed,0);
    //    body.applyForce(force,body.getWorldCenter(),true);
    body.setLinearVelocity(-speed, body.getLinearVelocity().y);
    //    body.setAngularVelocity(0);

  }

  public void moveRight() {
    //    Vector2 force = new Vector2(speed,0);
    //    body.applyForce(force,body.getWorldCenter(),true);
    body.setLinearVelocity(speed, body.getLinearVelocity().y);
    //    body.setAngularVelocity(0);

  }

  public void stopMoving() {
    body.setLinearVelocity(0, 0);
  }
}
