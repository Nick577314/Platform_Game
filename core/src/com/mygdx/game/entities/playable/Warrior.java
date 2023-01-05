package com.mygdx.game.entities.playable;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.KeyboardInput;
import com.mygdx.game.animations.*;

public class Warrior extends Player {

  KeyboardInput input;
  TextureRegion currentFrame;

  Animation<TextureRegion> animation1;

  static float stateTime = 0f;

  public Warrior(
          Vector2 position,
          Direction facing) {
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
        return CreateAnimation("assets/sprites/warrior/idle.png", 10);
      case RUN:
        return CreateAnimation("assets/sprites/warrior/run.png", 8);
      case JUMP:
        return CreateAnimation("assets/sprites/warrior/jump.png", 3);
      case FALL:
        return CreateAnimation("assets/sprites/warrior/fall.png", 3);
      case ATTACK_A:
        return CreateAnimation("assets/sprites/warrior/attack1.png", 8);
      case ATTACK_B:
        return CreateAnimation("assets/sprites/warrior/attack2.png", 8);
      case DAMAGE:
        return CreateAnimation("assets/sprites/warrior/damage.png", 3);
      case DEATH:
        return CreateAnimation("assets/sprites/warrior/death.png", 7);
      default:
        throw new CharacterAnimationTypeException("Animation not yet implemented");
    }
  }
}
