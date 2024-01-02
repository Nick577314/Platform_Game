package com.mygdx.game.Items;

import static com.mygdx.game.helpers.Constants.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.levels.Level;

public abstract class Item {

  protected final Body body;
  protected final Level level;
  private String fileName;
  protected Texture sprite;
  protected float scaleFactor = 1f;
  float x;
  float y;
  protected int width, height;

  public Item(Body body, Level level) {
    this.body = body;
    this.level = level;
    this.x = body.getPosition().x * PPM;
    this.y = body.getPosition().y * PPM;
  }

  public Level getLevel() {
    return level;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public float getHeight() {
    return height;
  }

  public float getWidth() {
    return width;
  }

  public float getScaleFactor() {
    return scaleFactor;
  }

  public abstract Texture getSprite();

  public abstract void doWhenCollected(Player player);

  public void dispose() {
    sprite.dispose();
  }

  public Body getBody() {
    return body;
  }

  public void eraseItem() {

    level.getWorld().destroyBody(body);
  }
}
