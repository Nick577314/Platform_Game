package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    // Combat stats
    protected int currentHp, maxHp, attackPower;

    // Position & movement stats
    public enum Direction {
        LEFT, RIGHT
    }

    protected Vector2 acceleration = new Vector2(0, -981);
    protected Rectangle bounds = new Rectangle();
    protected Vector2 position;
    protected Vector2 velocity = new Vector2();
    protected Direction facing;

    public Entity(Vector2 position, Direction facing) {
        this.position = position;
        this.facing = facing;
    }

    public static Animation<TextureRegion> CreateAnimation(String fileName, int numFrames, float frameDuration) {
        Texture spriteSheet = new Texture(Gdx.files.internal(fileName));
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / numFrames, spriteSheet.getHeight());

        TextureRegion[] spriteTextureRegion = new TextureRegion[numFrames];
        int index = 0;
        for (int i = 0; i < numFrames; i++) {
            spriteTextureRegion[index++] = tmp[0][i];
        }
        return new Animation<>(frameDuration, spriteTextureRegion);
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

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setX(float x) {
        this.position.x = x;
    }

    public void setY(float y) {
        this.position.y = y;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setXVelocity(float velocity) {
        this.velocity.x = velocity;
    }

    public void setYVelocity(float velocity) {
        this.velocity.y = velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public Direction getFacing() {
        return facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }
}
