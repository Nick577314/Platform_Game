package com.mygdx.game.entities.playable;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.helpers.Pair;
import com.mygdx.game.levels.Level;

public class Mage extends Player {
  public Mage(Direction facing, Body body, Level level) {
    super(facing, body, level);
    maxHp = 3;
    currentHp = maxHp;
    // Placeholder values
    attackPower = 1;
    spriteScaleFactor = 0.85f;

    animationMap.put(Entity.State.IDLE, new Pair<>("sprites/players/mage/idle.png", 6));
    animationMap.put(Entity.State.RUN, new Pair<>("sprites/players/mage/run.png", 8));
    animationMap.put(Entity.State.JUMP, new Pair<>("sprites/players/mage/jump.png", 2));
    animationMap.put(Entity.State.FALL, new Pair<>("sprites/players/mage/fall.png", 2));
    animationMap.put(Entity.State.ATTACK_A, new Pair<>("sprites/players/mage/attack1.png", 8));
    animationMap.put(Entity.State.ATTACK_B, new Pair<>("sprites/players/mage/attack2.png", 8));
    animationMap.put(Entity.State.DAMAGE, new Pair<>("sprites/players/mage/damage.png", 4));
    animationMap.put(Entity.State.DEATH, new Pair<>("sprites/players/mage/death.png", 7));
  }
}
