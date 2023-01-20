package com.mygdx.game.entities.enemies;

import static com.mygdx.game.helpers.Constants.PPM;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.MassData;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.helpers.Pair;
import com.mygdx.game.levels.Level;

public class Skeleton extends Enemy {

  public Skeleton(Direction facing, Body body, Level level) {
    super(facing, body, level);
    this.state = State.IDLE;
    this.spriteScaleFactor = 1.15f;
    this.maxHp = 2;
    this.currentHp = this.maxHp;
    this.speed = 4;

    animationMap.put(Entity.State.IDLE, new Pair<>("sprites/enemies/Skeleton/Idle.png", 4));
    // Making these two states use the IDLE animation hides weird behavior caused by jumping on an
    // enemy's head
    animationMap.put(Entity.State.JUMP, new Pair<>("sprites/enemies/Skeleton/Idle.png", 4));
    animationMap.put(Entity.State.FALL, new Pair<>("sprites/enemies/Skeleton/Idle.png", 4));
    animationMap.put(Entity.State.RUN, new Pair<>("sprites/enemies/Skeleton/Walk.png", 4));
    animationMap.put(Entity.State.ATTACK_A, new Pair<>("sprites/enemies/Skeleton/attack.png", 8));
    animationMap.put(Entity.State.DAMAGE, new Pair<>("sprites/enemies/Skeleton/damage.png", 4));
    animationMap.put(Entity.State.DEATH, new Pair<>("sprites/enemies/Skeleton/Death.png", 5));
  }

  public void followPlayer(Player player) {

    MassData massData = new MassData();
    massData.mass = 10;
    this.getBody().setMassData(massData);
    this.getBody().getFixtureList().get(0).setFriction(8);

    if (this.getBody().getPosition().x - player.getBody().getPosition().x <= -5 * PPM
        || this.getBody().getPosition().x - player.getBody().getPosition().x >= 5 * PPM) {
      System.out.println("TRYING TO GET SKELETON TO STOP MOVING");
      this.stopMoving();
      return;
    }
    if (this.getBody().getPosition().x >= player.getBody().getPosition().x) {
      System.out.println("TRYING TO GET SKELETON TO MOVE LEFT");
      this.setState(State.RUN);
      this.moveLeft();

    } else if (this.getBody().getPosition().x <= player.getBody().getPosition().x) {
      System.out.println("TRYING TO GET SKELETON TO MOVE RIGHT");
      this.setState(State.RUN);
      this.moveRight();
    }
  }
}
