package com.mygdx.game.entities;

import static com.mygdx.game.helpers.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.sun.tools.javac.util.Pair;
import java.util.HashMap;

public abstract class Entity {
  // Combat stats
  protected int currentHp, maxHp, attackPower;

  // Position & movement stats
  public enum Direction {
    LEFT,
    RIGHT
  }

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

  protected HashMap<State, Pair<String, Integer>> animationMap;

  protected float x;
  protected float y;

  protected float velX;
  protected float velY;
  protected float speed;
  protected int spriteWidth, spriteHeight;
  protected float scaleFactor = 1f;
  protected float stateTime = 0f;
  protected float animationFrameDuration = 1 / 10f;
  protected State state;
  protected Direction facing;
  protected Body body;
  protected boolean movementDisabled = false;

  public Entity(Direction facing, Body body) {
    this.x = body.getPosition().x;
    this.y = body.getPosition().y;
    this.facing = facing;
    this.body = body;
    this.velX = 0f;
    this.velY = 0f;
    this.speed = 0;
    this.animationMap = new HashMap<>();
  }

  public Animation<TextureRegion> CreateAnimation(String fileName, int numFrames) {
    Texture spriteSheet = new Texture(Gdx.files.internal(fileName));
    // split() returns a 2D array even if the sprite sheet is 1D
    TextureRegion[][] tmp =
        TextureRegion.split(
            spriteSheet, spriteSheet.getWidth() / numFrames, spriteSheet.getHeight());

    TextureRegion[] spriteTextureRegion = new TextureRegion[numFrames];
    System.arraycopy(tmp[0], 0, spriteTextureRegion, 0, numFrames);

    spriteWidth = spriteTextureRegion[0].getRegionWidth();
    spriteHeight = spriteTextureRegion[0].getRegionHeight();

    return new Animation<>(animationFrameDuration, spriteTextureRegion);
  }

  public Animation<TextureRegion> getCurrentAnimation(State state) {
    Pair<String, Integer> animationData = animationMap.get(state);
    if (animationData == null) return CreateAnimation("sprites/missing_texture.png", 1);
    return CreateAnimation(animationData.fst, animationData.snd);
  }

  public TextureRegion getCurrentFrame() {
    stateTime += Gdx.graphics.getDeltaTime();
    boolean looping =
        !(state == State.ATTACK_A
            || state == State.ATTACK_B
            || state == State.DAMAGE
            || state == State.DEATH);
    final TextureRegion currentFrame = getCurrentAnimation(state).getKeyFrame(stateTime, looping);

    if (facing == Direction.LEFT) currentFrame.flip(true, false);

    return currentFrame;
  }

  public void updateCharacterState() {
    if (movementDisabled) return;

    if (body.getLinearVelocity().y == 0) {
      if (velX > 0) {
        setFacing(Direction.RIGHT);
        setState(State.RUN);
        return;
      }
      if (velX < 0) {
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

  public void attack(Entity target) {
    target.takeDamage(this.attackPower);
  }

  public void takeDamage(int damage) {
    currentHp -= damage;
  }

  public void updatePosition() {
    x = body.getPosition().x * PPM;
    y = body.getPosition().y * PPM;
    updateCharacterState();
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public Body getBody() {
    return body;
  }

  public float getSpeed() {
    return speed;
  }

  public int getCurrentHp() {
    return currentHp;
  }

  public void setCurrentHp(int currentHp) {
    this.currentHp = currentHp;
  }

  public int getMaxHp() {
    return maxHp;
  }

  public void setMaxHp(int maxHp) {
    this.maxHp = maxHp;
  }

  public int getAttackPower() {
    return attackPower;
  }

  public void setAttackPower(int attackPower) {
    this.attackPower = attackPower;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }

  public float getVelX() {
    return velX;
  }

  public void setVelX(float velX) {
    this.velX = velX;
  }

  public float getVelY() {
    return velY;
  }

  public void setVelY(float velY) {
    this.velY = velY;
  }

  public Direction getFacing() {
    return facing;
  }

  public void setFacing(Direction facing) {
    this.facing = facing;
  }

  public int getSpriteWidth() {
    return spriteWidth;
  }

  public int getSpriteHeight() {
    return spriteHeight;
  }

  public float getScaleFactor() {
    return scaleFactor;
  }

  public float getAnimationFrameDuration() {
    return animationFrameDuration;
  }

  public void setState(State state) {
    // Guard clause prevents stateTime from constantly being reset to 0
    if (this.state == state) return;
    this.state = state;
    stateTime = 0f;
  }

  public State getState() {
    return this.state;
  }

  public boolean isMovementDisabled() {
    return movementDisabled;
  }

  public void setMovementDisabled(boolean movementDisabled) {
    this.movementDisabled = movementDisabled;
  }
}
