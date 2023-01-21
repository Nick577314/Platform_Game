package com.mygdx.game.Items;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.levels.Level;

public class Potions extends Items {

  public Potions(Body body, Level level) {
    super(body, level);
  }

  @Override
  public void doWhenCollected(Player player) {

    if (player.getCurrentHp() != player.getMaxHp()) {
      player.setCurrentHp(player.getMaxHp());
    }
  }
}
