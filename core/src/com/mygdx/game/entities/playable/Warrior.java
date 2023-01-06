package com.mygdx.game.entities.playable;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Warrior extends Player {
  public Warrior(Vector2 position, Direction facing) {
    super(position, facing);
    maxHp = 4;
    currentHp = maxHp;
    // Placeholder values
    attackPower = 0;
    bounds.width = 0;
    bounds.height = 0;
  }

  public Animation<TextureRegion> animationFactory(State characterState) {
    switch (characterState) {
      case IDLE:
        return CreateAnimation("assets/sprites/warrior/idle.png", 10, 0.075f);
      case RUN:
        return CreateAnimation("assets/sprites/warrior/run.png", 8, 0.075f);
      case JUMP:
        return CreateAnimation("assets/sprites/warrior/jump.png", 3, 0.075f);
      case FALL:
        return CreateAnimation("assets/sprites/warrior/fall.png", 3, 0.075f);
      case ATTACK_A:
        return CreateAnimation("assets/sprites/warrior/attack1.png", 8, 0.075f);
      case ATTACK_B:
        return CreateAnimation("assets/sprites/warrior/attack2.png", 8, 0.075f);
      case DAMAGE:
        return CreateAnimation("assets/sprites/warrior/damage.png", 3, 0.075f);
      case DEATH:
        return CreateAnimation("assets/sprites/warrior/death.png", 7, 0.075f);
      default:
        throw new RuntimeException(
            String.format(
                "Animation %s does not exist on class %s",
                characterState, this.getClass().toString()));
    }
  }
}
