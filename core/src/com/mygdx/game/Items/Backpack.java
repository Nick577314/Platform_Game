package com.mygdx.game.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.levels.Level;

public class Backpack extends Item {

  public Backpack(Body body, Level level) {
    super(body, level);
  }

  @Override
  public Texture getSprite() {
    return null;
  }

  @Override
  public void doWhenCollected(Player player) {

    // possibly increase the capacity of how many potions you can hold
    // this would update for any type
    player.setQuantity(player.getQuantity() + 2);
  }

  // Maybe have a designated quest item slots

}
