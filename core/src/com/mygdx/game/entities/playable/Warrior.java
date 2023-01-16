package com.mygdx.game.entities.playable;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Warrior extends Player {

  public Warrior(Direction facing, Body body) {
    super(facing, body);
    maxHp = 4;
    currentHp = maxHp;
    // Placeholder values
    attackPower = 0;
  }

  public Animation<TextureRegion> animationFactory(State characterState) {
    switch (characterState) {
      case IDLE:
        return CreateAnimation("sprites/players/warrior/idle.png", 10);
      case RUN:
        return CreateAnimation("sprites/players/warrior/run.png", 8);
      case JUMP:
        return CreateAnimation("sprites/players/warrior/jump.png", 3);
      case FALL:
        return CreateAnimation("sprites/players/warrior/fall.png", 3);
      case ATTACK_A:
        return CreateAnimation("sprites/players/warrior/attack1.png", 8);
      case ATTACK_B:
        return CreateAnimation("sprites/players/warrior/attack2.png", 8);
      case DAMAGE:
        return CreateAnimation("sprites/players/warrior/damage.png", 3);
      case DEATH:
        return CreateAnimation("sprites/players/warrior/death.png", 7);
      default:
        return CreateAnimation("sprites/missing_texture.png", 1);
    }
  }
}
