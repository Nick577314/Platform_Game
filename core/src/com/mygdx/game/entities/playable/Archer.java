package com.mygdx.game.entities.playable;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Archer extends Player {

  public Archer(Direction facing, Body body) {
    super(facing, body);
    maxHp = 2;
    currentHp = maxHp;
    // Placeholder values
    attackPower = 0;
  }

  public Animation<TextureRegion> animationFactory(State characterState) {
    switch (characterState) {
      case IDLE:
        return CreateAnimation("sprites/players/archer/idle.png", 10);
      case RUN:
        return CreateAnimation("sprites/players/archer/run.png", 8);
      case JUMP:
        return CreateAnimation("sprites/players/archer/jump.png", 2);
      case FALL:
        return CreateAnimation("sprites/players/archer/fall.png", 2);
      case ATTACK_A:
        return CreateAnimation("sprites/players/archer/attack1.png", 8);
      case ATTACK_B:
        // May be removed in the future if it is decided that Archer will have only one attack
        return CreateAnimation("TODO", 8);
      case DAMAGE:
        return CreateAnimation("sprites/players/archer/damage.png", 3);
      case DEATH:
        return CreateAnimation("sprites/players/archer/death.png", 7);
      default:
        return CreateAnimation("sprites/missing_texture.png", 1);
    }
  }
}
