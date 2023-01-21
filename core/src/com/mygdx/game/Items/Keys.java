package com.mygdx.game.Items;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.hud.Hud;
import com.mygdx.game.levels.Level;

public class Keys extends Items {
  Hud hud;

  public Keys(Body body, Level level) {
    super(body, level);
  }

  @Override
  public void doWhenCollected(Player player) {
    hud.incrementKeyCounter();
  }
}
