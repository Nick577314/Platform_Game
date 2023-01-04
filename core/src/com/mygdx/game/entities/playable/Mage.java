package com.mygdx.game.entities.playable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.KeyboardInput;
import com.mygdx.game.animations.*;

public class Mage extends Player {
  KeyboardInput input;
  TextureRegion currentFrame;

  Animation<TextureRegion> animation1;

  static float stateTime = 0f;

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
    speed = new Vector2(200, 0);
  }

  public Animation<TextureRegion> animationFactory(States characterState) {
    switch (characterState) {
      case IDLE:
        return CreateAnimation("assets/sprites/mage/idle.png", 6);
      case RUN:
        return CreateAnimation("assets/sprites/mage/run.png", 8);
      case JUMP:
        return CreateAnimation("assets/sprites/mage/jump.png", 2);
      case ATTACK_A:
        return CreateAnimation("assets/sprite/mage/attack1.png", 8);
      case ATTACK_B:
        return CreateAnimation("assets/sprite/mage/attack2.png", 8);
      case DEATH:
        return CreateAnimation("assets/sprite/mage/death.png", 7);
      default:
        throw new CharacterAnimationTypeException("Animation not yet implemented");
    }
  }

  public TextureRegion getCurrentFrame() {
    stateTime += Gdx.graphics.getDeltaTime();
    animation1 = animationFactory(state);

    currentFrame = animation1.getKeyFrame(stateTime, true);
    if (facing == Direction.LEFT) {
      currentFrame.flip(true, false);
    }
    return currentFrame;
  }
}
