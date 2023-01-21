package com.mygdx.game.Items;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.levels.Level;

public class Heart extends Items {

  public Heart(Body body, Level level) {
    super(body, level);
  }

  @Override
  public void doWhenCollected(Player player) {
    player.setMaxHp(player.getMaxHp() + 1);
  }
}
