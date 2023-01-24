package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.levels.Level;

public class Potions extends Items {
  Potions potionsBody;

  public Potions(Body body, Level level) {
    super(body, level);
  }

  @Override
  public Texture createItem() {
    potionsBody = new Potions(this.getBody(), level);
    return new Texture(
        Gdx.files.internal("assets/icons/kyrises-free-icons/icons/32x32/potion_03a.png"));
  }

  @Override
  public void doWhenCollected(Player player) {

    if (player.getCurrentHp() != player.getMaxHp()) {
      player.setCurrentHp(player.getMaxHp());
    }
    if (level.getItemsToRemove().contains(this)) {
      return;
    }
    level.getItemsToRemove().add(this);
  }
}
