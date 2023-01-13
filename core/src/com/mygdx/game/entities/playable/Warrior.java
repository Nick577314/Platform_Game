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
        return CreateAnimation("sprites/players/warrior/idle.png", 10, 0.075f);
      case RUN:
        return CreateAnimation("sprites/players/warrior/run.png", 8, 0.075f);
      case JUMP:
        return CreateAnimation("sprites/players/warrior/jump.png", 3, 0.075f);
      case FALL:
        return CreateAnimation("sprites/players/warrior/fall.png", 3, 0.075f);
      case ATTACK_A:
        return CreateAnimation("sprites/players/warrior/attack1.png", 8, 0.075f);
      case ATTACK_B:
        return CreateAnimation("sprites/players/warrior/attack2.png", 8, 0.075f);
      case DAMAGE:
        return CreateAnimation("sprites/players/warrior/damage.png", 3, 0.075f);
      case DEATH:
        return CreateAnimation("sprites/players/warrior/death.png", 7, 0.075f);
      default:
        return CreateAnimation("sprites/missing_texture.png", 1, 1f);
    }
  }
}
