package com.mygdx.game.entities.playable;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.KeyboardInput;
import com.mygdx.game.animations.*;

public class Archer extends Player {
  KeyboardInput input;
  TextureRegion currentFrame;

  Animation<TextureRegion> animation1;

  static float stateTime = 0f;

  public Archer(
          Vector2 position,
          Direction facing) {
    super(position, facing);
    maxHp = 2;
    currentHp = maxHp;
    // Placeholder values
    attackPower = 0;
    bounds.width = 0;
    bounds.height = 0;
  }

  public Animation<TextureRegion> animationFactory(State characterState) {
    switch (characterState) {
      case IDLE:
        return CreateAnimation("assets/sprites/archer/idle.png", 10);
      case RUN:
        return CreateAnimation("assets/sprites/archer/run.png", 8);
      case JUMP:
        return CreateAnimation("assets/sprites/archer/jump.png", 2);
      case FALL:
        return CreateAnimation("assets/sprites/archer/fall.png", 2);
      case ATTACK_A:
        return CreateAnimation("assets/sprites/archer/attack1.png", 8);
      case ATTACK_B:
        // May be removed in the future if it is decided that Archer will have only one attack
        return CreateAnimation("TODO", 8);
      case DAMAGE:
        return CreateAnimation("assets/sprites/archer/damage.png", 3);
      case DEATH:
        return CreateAnimation("assets/sprites/archer/death.png", 7);
      default:
        throw new CharacterAnimationTypeException("Animation not yet implemented");
    }
  }
}
