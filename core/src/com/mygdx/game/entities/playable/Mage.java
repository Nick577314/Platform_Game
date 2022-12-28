package com.mygdx.game.entities.playable;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.animations.*;

public class Mage extends Player {

  public Mage(
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
        return new CharacterAnimation("assets/sprites/mage/idle.png", 6);
      case RUN:
        return new CharacterAnimation("assets/sprites/mage/run.png", 6);
      case JUMP:
        return new CharacterAnimation("assets/sprites/mage/jump.png", 6);
      case ATTACK_A:
        return new CharacterAnimation("assets/sprite/mage/attack1.png", 8);
      case ATTACK_B:
        return new CharacterAnimation("assets/sprite/mage/attack2.png", 8);
      case DEATH:
        return new CharacterAnimation("assets/sprite/mage/death.png", 7);
      default:
        throw new CharacterAnimationTypeException("Animation not yet implemented");
    }
  }
}
