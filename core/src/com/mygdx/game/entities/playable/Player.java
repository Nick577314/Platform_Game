package com.mygdx.game.entities.playable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.KeyboardInput;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.helpers.BodyBuilder;
import com.mygdx.game.hud.Hud;
import com.mygdx.game.levels.Level;

public abstract class Player extends Entity {
  KeyboardInput input;
  Hud hud;
  int maxJumps, jumpsRemaining;

  public Player(Direction facing, Body body, Level level) {
    super(facing, body, level);
    this.state = State.IDLE;
    this.maxJumps = 2;
    this.jumpsRemaining = maxJumps;
    this.input = new KeyboardInput(this);
    Gdx.input.setInputProcessor(this.input);
  }

  public void updateMovement() {
    input.update();
  }

  public void tryAttack() {
    if (movementDisabled) return;

    this.setState(Entity.State.ATTACK_A);
    // Disable player movement until the animation finishes
    this.setMovementDisabled(true);
    final Player player = this;
    final Body[] sensor = new Body[1];
    Timer.schedule(
        new Timer.Task() {
          @Override
          public void run() {
            sensor[0] =
                BodyBuilder.createSensor(
                    facing == Direction.RIGHT ? x + 35 : x - 35, y + 25, 50, 50, level.getWorld());
            sensor[0].setUserData(player);
          }
        },
        25 / 60f);

    Timer.schedule(
        new Timer.Task() {
          @Override
          public void run() {
            player.setMovementDisabled(false);
          }
        },
        this.getNumAnimationFrames() * this.getAnimationFrameDuration());

    Timer.schedule(
        new Timer.Task() {
          @Override
          public void run() {
            level.getWorld().destroyBody(sensor[0]);
          }
        },
        45 / 60f);
  }

  @Override
  public void takeDamage(int damage) {
    currentHp -= damage;
  }

  public void setHud(Hud newHud) {
    hud = newHud;
  }

  public int getJumpsRemaining() {
    return jumpsRemaining;
  }

  public void decrementJumpCounter() {
    jumpsRemaining--;
  }

  public void resetJumpCounter() {
    jumpsRemaining = maxJumps;
  }
}
