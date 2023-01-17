package com.mygdx.game.entities.playable;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.levels.Level;
import com.sun.tools.javac.util.Pair;

public class Warrior extends Player {

  public Warrior(Direction facing, Body body, Level level) {
    super(facing, body, level);
    maxHp = 4;
    currentHp = maxHp;
    // Placeholder values
    attackPower = 0;

    animationMap.put(Entity.State.IDLE, new Pair<>("sprites/players/warrior/idle.png", 10));
    animationMap.put(Entity.State.RUN, new Pair<>("sprites/players/warrior/run.png", 8));
    animationMap.put(Entity.State.JUMP, new Pair<>("sprites/players/warrior/jump.png", 3));
    animationMap.put(Entity.State.FALL, new Pair<>("sprites/players/warrior/fall.png", 3));
    animationMap.put(Entity.State.ATTACK_A, new Pair<>("sprites/players/warrior/attack1.png", 8));
    animationMap.put(Entity.State.ATTACK_B, new Pair<>("sprites/players/warrior/attack2.png", 8));
    animationMap.put(Entity.State.DAMAGE, new Pair<>("sprites/players/warrior/damage.png", 3));
    animationMap.put(Entity.State.DEATH, new Pair<>("sprites/players/warrior/death.png", 7));
  }
}
