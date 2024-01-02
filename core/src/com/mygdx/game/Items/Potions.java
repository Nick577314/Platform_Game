package com.mygdx.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.helpers.Pair;
import com.mygdx.game.levels.Level;
import java.util.ArrayList;

public class Potions extends Item {

  float width, height;

  public Potions(Body body, Level level) {

    super(body, level);
    this.width = 32f;
    this.height = 32f;
    sprite =
        new Texture(
            Gdx.files.internal("assets/icons/kyrises-free-icons/icons/32x32/potion_03a.png"));
  }

  @Override
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

    ArrayList<Pair<Item, Integer>> storage = player.getStorage();
    int maxLimit =
        player.getQuantity(); // max number of that item and this value can be updated when player
    // finds a new better backpack
    int itemCount = 0; // Counter for how many items of this type are already in the storage
    for (Pair<Item, Integer> pair : storage) {
      if (pair.first instanceof Potions) { // Check if the item in the pair is a Potions object
        itemCount += pair.second; // Increment the counter by the quantity of this item in the pair
      }
    }
    if (itemCount < maxLimit
        && storage.size()
            < player.getQuantity()) { // Check if the item limit and storage capacity have not been
      // exceeded
      Pair<Item, Integer> potionPair = new Pair<Item, Integer>(this, 1);
      storage.add(potionPair); // Add this potion object to the storage
    }
  }
}
