package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.levels.Level;

public class Keys extends Item {

  float scaleFactor = 1f;

  float width, height;

  public Keys(Body body, Level level) {
    super(body, level);

    width = 32f;
    height = 32f;

    sprite =
        new Texture(Gdx.files.internal("assets/icons/kyrises-free-icons/icons/32x32/key_02d.png"));
  }

  @Override
  public Texture getSprite() {
    return sprite;
  }

  public float getWidth() {
    return width;
  }

  @Override
  public float getHeight() {
    return height;
  }

  @Override
  public void doWhenCollected(Player player) {

    player.addKey();

    player.setKeyCount(player.getKeyCount());

    System.out.println("Key collected!");
  }
}
