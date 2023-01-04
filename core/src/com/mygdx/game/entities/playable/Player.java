package com.mygdx.game.entities.playable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
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
  float prevX;

  float prevY;

  public boolean onGround;

  public enum States {
    IDLE,
    RUN,
    JUMP,
    FALL,
    ATTACK_A,
    ATTACK_B,
    DAMAGE,
    DEATH
  }

  public void setState(Player.States setState) {
    this.state = setState;
  }

  public States getState() {
    return this.state;
  }
  // public abstract CharacterAnimation factory(States characterState);

  public Player(Vector2 position, Direction facing) {
    super(position, facing);
    bounds = new Rectangle(position.x, position.y, 200, 200);
    velocity = new Vector2(350, 0);
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

  public boolean isOnGround() {
    return onGround;
  }

  public void setOnGround(boolean onGround) {
    this.onGround = onGround;
  }

  public void setHud(Hud newHud) {
    hud = newHud;
  }

  public void setPrevX(float prevX) {
    this.prevX = prevX;
  }

  public void setPrevY(float prevY) {
    this.prevY = prevY;
  }

  public void takeDamage(int damage) {
    currentHp -= damage;
    // TODO: throw exception if hud not set
    hud.updateHealth(currentHp);
  }

  public void calcVelocity(float delta) {

    velocity.y = velocity.y + acceleration.y * delta;
  }

  public void gravity(float delta) {

    calcVelocity(delta);
    setY(getY() + getVelocity().y * delta);
  }
}
