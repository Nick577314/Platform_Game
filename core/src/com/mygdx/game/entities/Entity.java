package com.mygdx.game.entities;

import static com.mygdx.game.helpers.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {
  // Combat stats
  protected int currentHp, maxHp, attackPower;

  // Position & movement stats
  public enum Direction {
    LEFT,
    RIGHT
  }

  // Must be overridden by child classes
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

  protected float x;
  protected float y;

  protected float VelX;
  protected float VelY;
  protected float speed;
  protected float width;
  protected float height;
  protected int spriteWidth, spriteHeight;
  protected float stateTime = 0f;
  protected State state;
  protected Direction facing;
  protected Body body;

  public Entity(float width, float height, Direction facing, Body body) {

    this.x = body.getPosition().x;
    this.y = body.getPosition().y;
    this.facing = facing;
    this.width = width;
    this.height = height;
    this.body = body;
    this.VelX = 0f;
    this.VelY = 0f;
    this.speed = 0;
  }

  public Animation<TextureRegion> CreateAnimation(
      String fileName, int numFrames, float frameDuration) {
    Texture spriteSheet = new Texture(Gdx.files.internal(fileName));
    // split() returns a 2D array even if the sprite sheet is 1D
    TextureRegion[][] tmp =
        TextureRegion.split(
            spriteSheet, spriteSheet.getWidth() / numFrames, spriteSheet.getHeight());

    TextureRegion[] spriteTextureRegion = new TextureRegion[numFrames];
    int index = 0;
    for (int i = 0; i < numFrames; i++) {
      spriteTextureRegion[index++] = tmp[0][i];
    }

    spriteWidth = spriteTextureRegion[0].getRegionWidth();
    spriteHeight = spriteTextureRegion[0].getRegionHeight();

    return new Animation<>(frameDuration, spriteTextureRegion);
  }

  public abstract Animation<TextureRegion> animationFactory(State characterState);

  public TextureRegion getCurrentFrame() {
    stateTime += Gdx.graphics.getDeltaTime();
    final TextureRegion currentFrame = animationFactory(state).getKeyFrame(stateTime, true);

    if (facing == Direction.LEFT) currentFrame.flip(true, false);

    return currentFrame;
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

  public void attack(Entity target) {
    target.takeDamage(this.attackPower);
  }

  public void takeDamage(int damage) {
    currentHp -= damage;
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

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }

  public void updatePosition() {
    x = body.getPosition().x * PPM;
    y = body.getPosition().y * PPM;
    updateCharacterAnimation();
  }

  public abstract void render(SpriteBatch batch);

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
    return VelX;
  }

  public void setVelX(float velX) {
    VelX = velX;
  }

  public float getVelY() {
    return VelY;
  }

  public void setVelY(float velY) {
    VelY = velY;
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

  public void setState(State setState) {
    this.state = setState;
  }

  public State getState() {
    return this.state;
  }
}
