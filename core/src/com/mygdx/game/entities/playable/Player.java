package com.mygdx.game.entities.playable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.KeyboardInput;
import com.mygdx.game.animations.*;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.hud.Hud;

public abstract class Player extends Entity {
  KeyboardInput input;
  Player player;
  States state;
  Hud hud;
  TextureRegion currentFrame;
  Animation<TextureRegion> animation1;

  static float stateTime = 0f;

  public enum States {
    IDLE,
    RUN,
    JUMP,
    ATTACK_A,
    ATTACK_B,
    DEATH
  }

  public void setState(Player.States setState) {
    this.state = setState;
  }

  public States getState() {
    return this.state;
  }
  // public abstract CharacterAnimation factory(States characterState);

  public Player(
          Vector2 position,
          Direction facing) {
    super(position, facing);
    input = new KeyboardInput(player);
    Gdx.input.setInputProcessor(input);
    state = States.IDLE;
  }

  public abstract Animation<TextureRegion> animationFactory(States characterState);

  public TextureRegion getCurrentFrame() {
    stateTime += Gdx.graphics.getDeltaTime();
    animation1 = animationFactory(state);

    currentFrame = animation1.getKeyFrame(stateTime, true);
    if (facing == Direction.LEFT) {
      currentFrame.flip(true, false);
    }
    return currentFrame;
  }

//  TODO: set HP, speed, attackPower, etc. manually in subclasses instead of passing them to the constructor

  public void setHud(Hud newHud) {
    hud = newHud;
  }

  public void takeDamage(int damage) {
    currentHp -= damage;
    // TODO: throw exception if hud not set
    hud.updateHealth(currentHp);
  }
}