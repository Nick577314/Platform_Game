package com.mygdx.game;

import static com.mygdx.game.helpers.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.mygdx.game.entities.*;
import com.mygdx.game.entities.playable.*;
import com.mygdx.game.helpers.mapHelper;
import com.mygdx.game.hud.Hud;

public class Level_1 extends ScreenAdapter {
  private final OrthographicCamera camera;
  private Texture region1, region2, region3;
  private Sprite layer1, layer2, layer3;

  private Stage stage;
  // Set the position and size of each layer
  SpriteBatch batch;
  private Hud hud;
  private Platformer app;
  private Viewport viewport;
  // Get the window width and height in pixels
  int windowWidth = 320;
  int windowHeight = 180;
  private String playerChoice;
  private TiledMap map;
  private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
  private com.mygdx.game.helpers.mapHelper mapHelper;
  float Xposition, Yposition;
  float speed = 20.0f;
  Player testChar, player;
  Player.State currentState = Player.State.IDLE;
  KeyboardInput INPUT;
  // CharacterAnimation mage1;
  Rectangle boundary;
  Texture ground;
  private World world;
  private Box2DDebugRenderer box2DDebugRenderer;

  public Level_1(OrthographicCamera camera) {
    //    this.app = app;
    this.camera = camera;
    this.batch = new SpriteBatch();

    // part of the YouTube Tutorial
    this.world = new World(new Vector2(0, -25f), false);
    this.box2DDebugRenderer = new Box2DDebugRenderer();
    this.mapHelper = new mapHelper(this);
    this.orthogonalTiledMapRenderer = mapHelper.setupMap();
    // calls the hud
    hud = new Hud(batch, player);
    //    testChar = new Mage(new Vector2(100, 400), Entity.Direction.RIGHT);
    //    ground = new Texture("assets/ground.png");
    // Create a SpriteBatch object
    stage = new Stage();
    Gdx.input.setInputProcessor(stage);
    boundary = new Rectangle(0, 0, Gdx.graphics.getWidth(), 50);
    //    Vector2 position = new Vector2(100, 100);
    //    Vector2 speed = new Vector2(200, 0);
    //    System.out.println(playerChoice);

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
    player.update();
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
    batch.draw(
        player.getCurrentFrame(),
        player.getX() - player.getSpriteWidth() / 2f,
        player.getY() - player.getSpriteHeight() / 2f);
    batch.end();

    // From the tutorial for map collision
    box2DDebugRenderer.render(world, camera.combined.scl(PPM));

    // TODO: change collision system to use map geometry

    //    if (testChar.getBounds().overlaps(boundary) && !testChar.isOnGround()) {
    //
    //      testChar.setVelocity(new Vector2(testChar.getVelocity().x, 0));
    //
    //      testChar.setOnGround(true);
    //    } else if (!testChar.isOnGround()) {
    //      //          testChar.calcVelocity(delta);
    //      testChar.gravity(delta);
    //    }
    //
    //    testChar.setX(testChar.getPosition().x + testChar.getVelocity().x * delta);
    //    testChar.setY(testChar.getPosition().y + testChar.getVelocity().y * delta);

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
