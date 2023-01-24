package com.mygdx.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Items.Items;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.enemies.Enemy;
import com.mygdx.game.entities.enemies.Skeleton;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.helpers.CollisionHandler;
import com.mygdx.game.helpers.MapLoader;
import java.util.ArrayList;

public abstract class Level {
  protected Player player;
  protected Rectangle boundary;
  protected World world;
  protected CollisionHandler sensorListener;
  protected String mapFile;
  protected ArrayList<Entity> entities;
  protected ArrayList<Items> itemsArrayList;
  protected ArrayList<Items> itemsTobeRemove;

  public Level(String mapFile) {
    this.mapFile = mapFile;
    this.world = new World(new Vector2(0, -50f), false);
    this.sensorListener = new CollisionHandler();
    this.world.setContactListener(sensorListener);
    this.boundary = new Rectangle(0, 0, Gdx.graphics.getWidth(), 50);
    this.entities = new ArrayList<>();
    this.itemsArrayList = new ArrayList<>();
    this.itemsTobeRemove = new ArrayList<>();
  }

  public void dispose() {
    world.dispose();
    for (Entity entity : entities) {
      entity.dispose();
    }
  }

  public String getMapFile() {
    return mapFile;
  }

  public World getWorld() {
    return world;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void update() {
    world.step(1 / 60f, 6, 2);
    player.updateMovement();
    for (Entity entity : entities) {
      entity.updatePosition();
      if (entity instanceof Enemy) ((Enemy) entity).facePlayer(player);
      if (entity instanceof Skeleton) {
        ((Skeleton) entity).followPlayer(player);
      }
    }
    for (Items items : itemsTobeRemove) {

      items.eraseItem();
    }
    itemsTobeRemove.clear();
  }

  public ArrayList<Entity> getEntities() {
    return entities;
  }

  public void addEntity(Entity entity) {
    entities.add(entity);
  }

  public ArrayList<Items> getItemsArrayList() {
    return itemsArrayList;
  }

  public ArrayList<Items> getItemsToRemove() {
    return itemsTobeRemove;
  }

  public void addItem(Items items) {
    itemsArrayList.add(items);
  }

  public OrthogonalTiledMapRenderer loadMap() {
    MapLoader mapLoader = new MapLoader(this);
    return mapLoader.setupMap();
  }
}
