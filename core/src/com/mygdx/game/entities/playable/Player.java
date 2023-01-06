package com.mygdx.game.entities.playable;

import static com.mygdx.game.helpers.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.KeyboardInput;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.hud.Hud;

public abstract class Player extends Entity {
  KeyboardInput input;
  Hud hud;
  static float stateTime = 0f;
  float x;
  float y;
  public boolean onGround = false;

  public enum State {
    IDLE,
    RUN,
    JUMP,
    FALL,
    ATTACK_A,
    ATTACK_B,
    DAMAGE,
    DEATH
  }

  State state = State.IDLE;
  public int jumpCounter;

  public Player(float width, float height, Direction facing, Body body) {
    super(width, height, facing, body);
    // bounds = new Rectangle(position.x, position.y, 200, 200);
    this.speed = 5f;
    input = new KeyboardInput(this);
    Gdx.input.setInputProcessor(input);
    this.jumpCounter = 0;
  }

  public abstract Animation<TextureRegion> animationFactory(State characterState);

  public TextureRegion getCurrentFrame() {
    stateTime += Gdx.graphics.getDeltaTime();
    final TextureRegion currentFrame = animationFactory(state).getKeyFrame(stateTime, true);

    if (facing == Direction.LEFT) currentFrame.flip(true, false);

    return currentFrame;
  }

  @Override
  public void update() {
    x = body.getPosition().x * PPM;
    y = body.getPosition().y * PPM;
    updateCharacterAnimation();
  }

  public void updateCharacterAnimation() {

    if (body.getLinearVelocity().y == 0) {
      if (VelX > 0) {
        setFacing(Direction.RIGHT);
        setState(State.RUN);
        return;
      }
      if (VelX < 0) {
        setFacing(Direction.LEFT);
        setState(State.RUN);
        return;
      }
      setState(State.IDLE);
      return;
    }
    if (body.getLinearVelocity().y > 0) {

      setState(State.JUMP);
    }
    if (body.getLinearVelocity().y < 0) {
      setState(State.FALL);
    }
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public void setState(State setState) {
    this.state = setState;
  }

  public State getState() {
    return this.state;
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

  public void takeDamage(int damage) {
    currentHp -= damage;
    // TODO: throw exception if hud not set
    hud.updateHealth(currentHp);
  }

  //    public void calcVelocity(float delta) {
  //        velocity.y = velocity.y + acceleration.y * delta;
  //
  //        if (velocity.y < 0) {
  //            state = State.FALL;
  //        } else if (velocity.y > 0) {
  //            state = State.JUMP;
  //        }
  //    }

  //    public void gravity(float delta) {
  //        calcVelocity(delta);
  //        position.y += velocity.y * delta;
  //    }
}
