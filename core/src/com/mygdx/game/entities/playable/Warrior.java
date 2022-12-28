package com.mygdx.game.entities.playable;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.animations.*;

public class Warrior extends Player {
  public Warrior(
      int maxHp,
      int attackPower,
      float width,
      float height,
      Vector2 position,
      Vector2 speed,
      Direction facing) {
    super(maxHp, attackPower, width, height, position, speed, facing);
  }

  public CharacterAnimation factory(States characterState) {
    switch (characterState) {
      case IDLE:
        return new CharacterAnimation("assets/sprites/warrior/idle.png", 7);
      case RUN:
        return new CharacterAnimation("TODO", 6);
      case JUMP:
        return new CharacterAnimation("TODO", 6);
      case ATTACK_A:
        return new CharacterAnimation("assets/sprites/warrior/attack1.png", 8);
      case ATTACK_B:
        return new CharacterAnimation("assets/sprites/warrior/attack2.png", 8);
      case DEATH:
        return new CharacterAnimation("TODO", 7);
      default:
        throw new CharacterAnimationTypeException("Animation not yet implemented");
    }
  }
}
