package com.mygdx.game;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.playable.*;
import com.mygdx.game.helpers.Level;
import com.mygdx.game.helpers.MapHelper;
import com.mygdx.game.hud.Hud;
import java.util.ArrayList;

public class Level_1 extends Level {
  //  private final OrthographicCamera camera;
  private Stage stage;
  // Set the position and size of each layer
  SpriteBatch batch;
  private Hud hud;
  private Platformer app;
  private Viewport viewport;

  private TiledMap map;
  private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
  private MapHelper mapHelper;
  Player player;
  Player.State currentState = Player.State.IDLE;
  KeyboardInput INPUT;
  // CharacterAnimation mage1;
  Rectangle boundary;
  private World world;
  private Box2DDebugRenderer box2DDebugRenderer;

  public ArrayList<Entity> entitiesToDraw = new ArrayList<>();

  public Level_1(OrthographicCamera camera) {
    //    this.app = app;
    super(camera, "assets/maps/map1.tmx");
  }

  @Override
  public void show() {}

  @Override
  public void resume() {}

  public void setScreen(Level_1 level1Screen) {}
}

// TODO: create parent Level class that initializes HUD and Player objects
