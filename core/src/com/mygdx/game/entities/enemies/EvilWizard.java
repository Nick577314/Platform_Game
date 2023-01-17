package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.Entity;
import com.sun.tools.javac.util.Pair;

public class EvilWizard extends Enemy {

  public EvilWizard(Direction facing, Body body) {
    super(facing, body);
    this.state = State.IDLE;
    this.spriteScaleFactor = 1.25f;
    this.maxHp = 3;
    this.currentHp = this.maxHp;

    animationMap.put(Entity.State.IDLE, new Pair<>("sprites/enemies/evil_wizard/idle.png", 8));
    animationMap.put(Entity.State.RUN, new Pair<>("sprites/enemies/evil_wizard/run.png", 8));
    animationMap.put(
        Entity.State.ATTACK_A, new Pair<>("sprites/enemies/evil_wizard/attack1.png", 8));
    animationMap.put(Entity.State.DAMAGE, new Pair<>("sprites/enemies/evil_wizard/damage.png", 4));
    animationMap.put(Entity.State.DEATH, new Pair<>("sprites/enemies/evil_wizard/death.png", 5));
  }
}
