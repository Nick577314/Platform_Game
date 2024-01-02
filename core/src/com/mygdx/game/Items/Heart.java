package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.levels.Level;

public class Heart extends Item {

  float width, height;

  public Heart(Body body, Level level) {
    super(body, level);
    width = 32f;
    height = 32f;
    sprite = new Texture(Gdx.files.internal("icons/heart-icon.png"));
  }

  public float getWidth() {
    return width;
  }

  @Override
  public float getHeight() {
    return height;
  }

  @Override
  public Texture getSprite() {
    return sprite;
  }

  @Override
  public void doWhenCollected(Player player) {
    if (player.getMaxHp() == 8) {
      return;
    }

    player.setMaxHp(player.getMaxHp() + 1);
    player.setCurrentHp(player.getCurrentHp() + 1);
  }
}
