package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.helpers.Pair;
import com.mygdx.game.levels.Level;

public class EvilWizard extends Enemy {

  public EvilWizard(Direction facing, Body body, Level level) {
    super(facing, body, level);
    this.state = State.IDLE;
    this.spriteScaleFactor = 1.25f;
    this.maxHp = 3;
    this.currentHp = this.maxHp;

    animationMap.put(Entity.State.IDLE, new Pair<>("sprites/enemies/evil_wizard/idle.png", 8));
    // Making these two states use the IDLE animation hides weird behavior caused by jumping on an
    // enemy's head
    animationMap.put(Entity.State.JUMP, new Pair<>("sprites/enemies/evil_wizard/idle.png", 8));
    animationMap.put(Entity.State.FALL, new Pair<>("sprites/enemies/evil_wizard/idle.png", 8));
    animationMap.put(Entity.State.RUN, new Pair<>("sprites/enemies/evil_wizard/run.png", 8));
    animationMap.put(
        Entity.State.ATTACK_A, new Pair<>("sprites/enemies/evil_wizard/attack1.png", 8));
    animationMap.put(Entity.State.DAMAGE, new Pair<>("sprites/enemies/evil_wizard/damage.png", 4));
    animationMap.put(Entity.State.DEATH, new Pair<>("sprites/enemies/evil_wizard/death.png", 5));
  }
}
