package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.levels.Level;

public abstract class Items {

  protected final Body body;
  protected final Level level;
  private String fileName;
  Texture sprite;

  public Items(Body body, Level level) {
    this.body = body;
    this.level = level;
  }

  public abstract Texture createItem();

  public abstract void doWhenCollected(Player player);

  public void dispose() {
    createItem().dispose();
  }

  public Body getBody() {
    return body;
  }

  public void eraseItem() {

    level.getWorld().destroyBody(body);
  }
}
