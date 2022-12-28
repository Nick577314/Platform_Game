package com.mygdx.game.entities.playable;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.animations.*;

public class Archer extends Player {
  public Archer(
      int maxHp,
      int attackPower,
      float width,
      float height,
      Vector2 position,
      Vector2 speed,
      Direction facing) {
    super(maxHp, attackPower, width, height, position, speed, facing);
  }

  public static CharacterAnimation factory(States characterState) {
    switch (characterState) {
      case IDLE:
        return new CharacterAnimation("assets/sprites/archer/idle.png", 10);
      case RUN:
        return new CharacterAnimation("TODO", 6);
      case JUMP:
        return new CharacterAnimation("TODO", 6);
      case ATTACK_A:
        return new CharacterAnimation("assets/sprites/archer/attack1.png", 8);
      case ATTACK_B:
        return new CharacterAnimation("TODO", 8);
      case DEATH:
        return new CharacterAnimation("assets/sprites/archer/death.png", 7);
      default:
        throw new CharacterAnimationTypeException("Animation not yet implemented");
    }
  }
}
