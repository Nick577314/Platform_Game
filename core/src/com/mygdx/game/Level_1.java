package com.mygdx.game;

import static com.mygdx.game.helpers.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.playable.*;
import com.mygdx.game.helpers.MapHelper;
import com.mygdx.game.hud.Hud;
import java.util.ArrayList;

public class Level_1 extends ScreenAdapter {
  private final OrthographicCamera camera;
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
    this.camera = camera;
    this.batch = new SpriteBatch();

    // part of the YouTube Tutorial
    this.world = new World(new Vector2(0, -50f), false);
    this.box2DDebugRenderer = new Box2DDebugRenderer();
    this.mapHelper = new MapHelper(this);
    this.orthogonalTiledMapRenderer = mapHelper.setupMap();
    // calls the hud
    hud = new Hud(batch, player);

    // Create a SpriteBatch object
    stage = new Stage();
    Gdx.input.setInputProcessor(stage);
    boundary = new Rectangle(0, 0, Gdx.graphics.getWidth(), 50);

    INPUT = new KeyboardInput(player);
  }

  @Override
  public void dispose() {
    map.dispose();

    batch.dispose();
  }

  @Override
  public void hide() {}

  @Override
  public void pause() {}

  private void cameraUpdate() {
    Vector3 position = camera.position;
    position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
    position.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f;
    camera.position.set(position);
    camera.update();
  }

  private void update() {
    world.step(1 / 60f, 6, 2);
    cameraUpdate();
    batch.setProjectionMatrix(camera.combined);
    orthogonalTiledMapRenderer.setView(camera);
    // player.updatePosition();
    for (Entity entity : entitiesToDraw) {
      entity.updatePosition();
    }
  }

  @Override
  public void render(float delta) {

    this.update();
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    orthogonalTiledMapRenderer.render();
    // calls movement for character
    INPUT.keyboardMovement();
    batch.begin();
    for (Entity entity : entitiesToDraw) {
      batch.draw(
          entity.getCurrentFrame(),
          entity.getX() - entity.getSpriteWidth() / 2f,
          entity.getY() - entity.getSpriteHeight() / 2f + 10);
    }
    batch.end();

    // From the tutorial for map collision
    box2DDebugRenderer.render(world, camera.combined.scl(PPM));

    // TODO: change collision system to use map geometry

    batch.setProjectionMatrix(
        hud.getStage()
            .getCamera()
            .combined); // set the spriteBatch to draw what our stageViewport sees
    hud.getStage().act(delta); // act the Hud
    hud.getStage().draw(); // draw the Hud

    stage.act();
    stage.draw();
  }

  public World getWorld() {
    return world;
  }

  public void setPlayer(Player player) {

    this.player = player;
    entitiesToDraw.add(player);
  }

  @Override
  public void resize(int width, int height) {
    //    camera.viewportWidth = width;
    //    camera.viewportHeight = height;
    //    camera.update();
    stage.getViewport().update(width, height, true);
  }

  @Override
  public void show() {}

  @Override
  public void resume() {}

  public void setScreen(Level_1 level1Screen) {}
}

// TODO: create parent Level class that initializes HUD and Player objects
