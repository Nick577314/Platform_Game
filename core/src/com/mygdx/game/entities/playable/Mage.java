package com.mygdx.game.entities.playable;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Mage extends Player {
  public Mage(Direction facing, Body body) {
    super(facing, body);
    maxHp = 3;
    currentHp = maxHp;
    // Placeholder values
    attackPower = 0;
  }

  public Animation<TextureRegion> animationFactory(State characterState) {
    switch (characterState) {
      case IDLE:
        return CreateAnimation("sprites/players/mage/idle.png", 6, 0.075f);
      case RUN:
        return CreateAnimation("sprites/players/mage/run.png", 8, 0.075f);
      case JUMP:
        return CreateAnimation("sprites/players/mage/jump.png", 2, 0.075f);
      case FALL:
        return CreateAnimation("sprites/players/mage/fall.png", 2, 0.075f);
      case ATTACK_A:
        return CreateAnimation("sprites/players/mage/attack1.png", 8, 0.075f);
      case ATTACK_B:
        return CreateAnimation("sprites/players/mage/attack2.png", 8, 0.075f);
      case DAMAGE:
        return CreateAnimation("sprites/players/mage/damage.png", 4, 0.075f);
      case DEATH:
        return CreateAnimation("sprites/players/mage/death.png", 7, 0.075f);
      default:
        return CreateAnimation("sprites/missing_texture.png", 1, 1f);
    }
  }
}
