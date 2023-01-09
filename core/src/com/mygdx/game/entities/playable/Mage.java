package com.mygdx.game.entities.playable;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Mage extends Player {
  public Mage(float width, float height, Direction facing, Body body) {
    super(width, height, facing, body);
    maxHp = 3;
    currentHp = maxHp;
    // Placeholder values
    attackPower = 0;
  }

  @Override
  public void render(SpriteBatch batch) {}

  public Animation<TextureRegion> animationFactory(State characterState) {
    switch (characterState) {
      case IDLE:
        return CreateAnimation("assets/sprites/mage/idle.png", 6, 0.075f);
      case RUN:
        return CreateAnimation("assets/sprites/mage/run.png", 8, 0.075f);
      case JUMP:
        return CreateAnimation("assets/sprites/mage/jump.png", 2, 0.075f);
      case FALL:
        return CreateAnimation("assets/sprites/mage/fall.png", 2, 0.075f);
      case ATTACK_A:
        return CreateAnimation("assets/sprite/mage/attack1.png", 8, 0.075f);
      case ATTACK_B:
        return CreateAnimation("assets/sprite/mage/attack2.png", 8, 0.075f);
      case DAMAGE:
        return CreateAnimation("assets/sprite/mage/damage.png", 4, 0.075f);
      case DEATH:
        return CreateAnimation("assets/sprite/mage/death.png", 7, 0.075f);
      default:
        throw new RuntimeException(
            String.format(
                "Animation %s does not exist on class %s",
                characterState, this.getClass().toString()));
    }
  }
}
