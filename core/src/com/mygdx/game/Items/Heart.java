package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.levels.Level;

public class Heart extends Items {

  public Heart(Body body, Level level) {
    super(body, level);
  }

  @Override
  public Texture createItem() {
    // change asset to a heart
    return new Texture(
        Gdx.files.internal("assets/icons/kyrises-free-icons/icons/32x32/gift_01c.png"));
  }

  @Override
  public void doWhenCollected(Player player) {
    if (player.getMaxHp() == 8) {
      return;
    }
    // this is not updating the hud with the extra hearts unless you, use a potion
    player.setMaxHp(player.getMaxHp() + 1);
  }
}
