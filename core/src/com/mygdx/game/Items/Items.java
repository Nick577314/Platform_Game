package com.mygdx.game.Items;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.levels.Level;

public abstract class Items {

  protected final Body body;
  protected final Level level;

  public Items(Body body, Level level) {
    this.body = body;
    this.level = level;
  }

  public abstract void doWhenCollected(Player player);

  public Body getBody() {
    return body;
  }
}
