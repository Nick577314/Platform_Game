package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.animations.CharacterSelectionScreen;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.playable.Mage;

public class Level_1 implements Screen {
  private Texture region1, region2, region3;
  private Sprite layer1, layer2, layer3;

  private Stage stage;
  // Set the position and size of each layer
  SpriteBatch batch;
  private Platformer app;
  //  private CharacterAnimation playerAnimation;
  private ScreenViewport viewport;
  // Get the window width and height in pixels
  int windowWidth = 320;
  int windowHeight = 180;
  private String playerChoice;
  private TiledMap map;
  private OrthogonalTiledMapRenderer renderer;
  private OrthographicCamera camera;

  Mage testcharacter;
  // CharacterAnimation mage1;
  public Level_1(final Platformer app) {

    // super(app);
    this.app = app;

    // Create a SpriteBatch object
    batch = new SpriteBatch();
    stage = new Stage();
    Gdx.input.setInputProcessor(stage);
    map = new TmxMapLoader().load("assets/maps/test_map.tmx");
    this.camera = new OrthographicCamera();
    this.renderer = new OrthogonalTiledMapRenderer(map);
    // viewport.setScreenBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    CharacterSelectionScreen choice = new CharacterSelectionScreen(app);
    Vector2 position = new Vector2(100, 100);
    Vector2 speed = new Vector2(50, 0);
    System.out.println(playerChoice);
    testcharacter = new Mage(100, 100, 100, 100, position, speed, Entity.Direction.RIGHT);
  }

  @Override
  public void dispose() {
    map.dispose();
    renderer.dispose();
    batch.dispose();
  }

  @Override
  public void hide() {}

  @Override
  public void pause() {}

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    ScreenUtils.clear(1, 1, 1, 0);
    batch.begin();
    batch.draw(testcharacter.getCurrentFrame(), 100, 100);

    stage.act(delta);
    stage.draw();

    renderer.setView(camera);
    renderer.render();
    batch.end();
  }

  @Override
  public void show() {}

  @Override
  public void resize(int width, int height) {
    camera.viewportWidth = width;
    camera.viewportHeight = height;
    camera.update();
    stage.getViewport().update(width, height, true);
  }

  @Override
  public void resume() {}

  public void setScreen(Level_1 level1Screen) {}
}
