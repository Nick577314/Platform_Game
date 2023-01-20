package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.helpers.Pair;
import com.mygdx.game.levels.Level;

public class FlyingEye extends Enemy {
  public FlyingEye(Direction facing, Body body, Level level) {
    super(facing, body, level);
    this.state = Entity.State.IDLE;
    this.spriteScaleFactor = 1.15f;
    this.maxHp = 1;
    this.currentHp = this.maxHp;

    animationMap.put(Entity.State.IDLE, new Pair<>("sprites/enemies/Flying_eye/Flight.png", 8));
    // Making these two states use the IDLE animation hides weird behavior caused by jumping on an
    // enemy's head
    animationMap.put(Entity.State.JUMP, new Pair<>("sprites/enemies/Flying_eye/Flight.png", 8));
    animationMap.put(Entity.State.FALL, new Pair<>("sprites/enemies/Flying_eye/Flight.png", 8));
    animationMap.put(Entity.State.RUN, new Pair<>("sprites/enemies/Flying_eye/Flight.png", 8));
    animationMap.put(Entity.State.ATTACK_A, new Pair<>("sprites/enemies/Flying_eye/Attack.png", 8));
    animationMap.put(Entity.State.DAMAGE, new Pair<>("sprites/enemies/Flying_eye/damage.png", 4));
    animationMap.put(Entity.State.DEATH, new Pair<>("sprites/enemies/Flying_eye/Death.png", 4));
  }
}
