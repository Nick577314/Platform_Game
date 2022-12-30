package com.mygdx.game.entities.playable;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.animations.*;

public class Mage extends Player {
  public Mage(
      Vector2 position,
      Direction facing) {
    super(position, facing);
    maxHp = 3;
    currentHp = maxHp;
    // Placeholder values
    attackPower = 0;
    bounds.width = 0;
    bounds.height = 0;
    speed = new Vector2(0, 0);
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
