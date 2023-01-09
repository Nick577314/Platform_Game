package com.mygdx.game.entities.playable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.KeyboardInput;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.hud.Hud;

public abstract class Player extends Entity {
  KeyboardInput input;
  Hud hud;
  int maxJumps, jumpsRemaining;

  public Player(Direction facing, Body body) {
    super(facing, body);
    this.speed = 5f;
    this.state = State.IDLE;
    this.maxJumps = 2;
    this.jumpsRemaining = maxJumps;
    input = new KeyboardInput(this);
    Gdx.input.setInputProcessor(input);
  }

  @Override
  public void takeDamage(int damage) {
    currentHp -= damage;
    // TODO: throw exception if hud not set
    hud.updateHealth(currentHp);
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
