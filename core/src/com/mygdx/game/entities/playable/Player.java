package com.mygdx.game.entities.playable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.KeyboardInput;
import com.mygdx.game.animations.*;
import com.mygdx.game.entities.Entity;

public abstract class Player extends Entity {
  KeyboardInput input;
  Player player;
  States state;

  public enum States {
    IDLE,
    RUN,
    JUMP,
    ATTACK_A,
    ATTACK_B,
    DEATH
  }

  public void setState(Player.States setState) {
    this.state = setState;
  }

  public States getState() {
    return this.state;
  }
  // public abstract CharacterAnimation factory(States characterState);

  public Player(
      int maxHp,
      int attackPower,
      float width,
      float height,
      Vector2 position,
      Vector2 speed,
      Direction facing) {
    super(maxHp, attackPower, width, height, position, speed, facing);
    input = new KeyboardInput(player);
    Gdx.input.setInputProcessor(input);
    state = States.IDLE;
  }
}
