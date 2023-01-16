package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class EvilWizard extends Enemy {

  public EvilWizard(Direction facing, Body body) {
    super(facing, body);
    state = State.IDLE;
    scaleFactor = 1.25f;
  }

  @Override
  public Animation<TextureRegion> animationFactory(State characterState) {
    switch (characterState) {
      case IDLE:
        return CreateAnimation("sprites/enemies/evil_wizard/idle.png", 8);
      case RUN:
        return CreateAnimation("sprites/enemies/evil_wizard/run.png", 8);
      case ATTACK_A:
        return CreateAnimation("sprites/enemies/evil_wizard/mage/attack1.png", 8);
      case DAMAGE:
        return CreateAnimation("sprites/enemies/evil_wizard/mage/damage.png", 4);
      case DEATH:
        return CreateAnimation("sprites/enemies/evil_wizard/mage/death.png", 5);
      default:
        return CreateAnimation("sprites/missing_texture.png", 1);
    }
  }
}
