package com.mygdx.game.entities;

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

  protected float x;
  protected float y;
  public float VelX;
  protected float VelY;
  protected float speed;
  protected float width, height;
  protected int spriteWidth;

  protected int spriteHeight;
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

  public Body getBody() {
    return body;
  }

  public float getSpeed() {
    return speed;
  }

  public void attack(Entity target) {
    target.setCurrentHp(target.getCurrentHp() - this.attackPower);
  }

  public void turnAround() {
    switch (this.facing) {
      case LEFT:
        this.facing = Direction.RIGHT;
      case RIGHT:
        this.facing = Direction.LEFT;
    }
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }

  public abstract void update();

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
}
