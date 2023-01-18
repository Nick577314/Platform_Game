package com.mygdx.game.entities.playable;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.helpers.Pair;
import com.mygdx.game.levels.Level;

public class Archer extends Player {

  public Archer(Direction facing, Body body, Level level) {
    super(facing, body, level);
    maxHp = 2;
    currentHp = maxHp;
    // Placeholder values
    attackPower = 0;

    animationMap.put(Entity.State.IDLE, new Pair<>("sprites/players/archer/idle.png", 10));
    animationMap.put(Entity.State.RUN, new Pair<>("sprites/players/archer/run.png", 8));
    animationMap.put(Entity.State.JUMP, new Pair<>("sprites/players/archer/jump.png", 2));
    animationMap.put(Entity.State.FALL, new Pair<>("sprites/players/archer/fall.png", 2));
    animationMap.put(Entity.State.ATTACK_A, new Pair<>("sprites/players/archer/attack1.png", 8));
    animationMap.put(Entity.State.DAMAGE, new Pair<>("sprites/players/archer/damage.png", 3));
    animationMap.put(Entity.State.DEATH, new Pair<>("sprites/players/archer/death.png", 7));
  }
}
