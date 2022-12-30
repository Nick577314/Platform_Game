package com.mygdx.game.entities.playable;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.animations.*;
import com.mygdx.game.entities.Entity;

public abstract class Player extends Entity {

  public enum States {
    IDLE,
    RUN,
    JUMP,
    ATTACK_A,
    ATTACK_B,
    DEATH
  }

  // public abstract CharacterAnimation factory(States characterState);

  public Player(
      Vector2 position,
      Direction facing) {
    super(position, facing);
  }

//  TODO: set HP, speed, attackPower, etc. manually in subclasses instead of passing them to the constructor
}
