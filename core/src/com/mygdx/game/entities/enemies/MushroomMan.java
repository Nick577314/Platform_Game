package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.helpers.Pair;
import com.mygdx.game.levels.Level;

public class MushroomMan extends Enemy {

  public MushroomMan(Direction facing, Body body, Level level) {
    super(facing, body, level);
    this.state = Entity.State.IDLE;
    this.spriteScaleFactor = 1.15f;
    this.maxHp = 1;
    this.currentHp = this.maxHp;

    animationMap.put(Entity.State.IDLE, new Pair<>("sprites/enemies/Mushroom/Idle.png", 4));
    // Making these two states use the IDLE animation hides weird behavior caused by jumping on an
    // enemy's head
    animationMap.put(Entity.State.JUMP, new Pair<>("sprites/enemies/Mushroom/Idle.png", 4));
    animationMap.put(Entity.State.FALL, new Pair<>("sprites/enemies/Mushroom/Idle.png", 4));
    animationMap.put(Entity.State.RUN, new Pair<>("sprites/enemies/MushroomRun.png", 8));
    animationMap.put(Entity.State.ATTACK_A, new Pair<>("sprites/enemies/Mushroom/Attack.png", 8));
    animationMap.put(Entity.State.DAMAGE, new Pair<>("sprites/enemies/Mushroom/damage.png", 4));
    animationMap.put(Entity.State.DEATH, new Pair<>("sprites/enemies/Mushroom/Death.png", 4));
  }
}
