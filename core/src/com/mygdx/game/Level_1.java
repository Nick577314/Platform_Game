package com.mygdx.game;

import static com.mygdx.game.animations.CharacterAnimationType.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.animations.CharacterAnimation;
import com.mygdx.game.animations.CharacterAnimationType;
import com.mygdx.game.screen.AbstractScreen;

public class Level_1 extends AbstractScreen {
  private Texture region1;
  private Texture region2;
  private Texture region3;

  private Sprite layer1;
  private Sprite layer2;
  private Sprite layer3;

  private Stage stage;
  // Set the position and size of each layer

  SpriteBatch batch;
  private Platformer app;
  private CharacterAnimation playerAnimation;
  private ScreenViewport viewport;
  private Camera camera;

  // Get the window width and height in pixels
  int windowWidth = 320;
  int windowHeight = 180;
  private String playerChoice;

  public Level_1(final Platformer app) {

    // super(app);
    this.app = app;
    // Create a SpriteBatch object
    batch = new SpriteBatch();
    stage = new Stage();
    Gdx.input.setInputProcessor(stage);

    Texture region1 = new Texture(Gdx.files.internal("background_layer_1.png"));
    Texture region2 = new Texture(Gdx.files.internal("background_layer_2.png"));
    Texture region3 = new Texture(Gdx.files.internal("background_layer_3.png"));

    layer1 = new Sprite(region1);
    layer2 = new Sprite(region2);
    layer3 = new Sprite(region3);

    layer1.setScale(windowWidth / layer1.getWidth(), windowHeight / layer1.getHeight());
    layer2.setScale(windowWidth / layer2.getWidth(), windowHeight / layer2.getHeight());
    layer3.setScale(windowWidth / layer3.getWidth(), windowHeight / layer3.getHeight());

    viewport = new ScreenViewport();
    camera = new OrthographicCamera();
    viewport.setCamera(camera);
    viewport.setWorldSize(viewport.getWorldWidth(), viewport.getWorldHeight());
    viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    layer1.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
    layer2.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
    layer3.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
    // viewport.setScreenBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    //
    CharacterSelectionScreen choice = new CharacterSelectionScreen(app);

    System.out.println(playerChoice);
    final CharacterAnimation mage = CharacterAnimation.factory(CharacterAnimationType.WIZARDIDLE);
    final CharacterAnimation archer = CharacterAnimation.factory(CharacterAnimationType.ARCHERIDLE);
    final CharacterAnimation warrior =
        CharacterAnimation.factory(CharacterAnimationType.WARRIORIDLE);

    //        mage.CreateAnimation(mage.Filename, mage.numCols);
    //           stage.addActor(mage);

  }

  @Override
  public void create() {}

  @Override
  public void render() {
    // Begin the batch
    // In the render method, begin the batch

  }

  @Override
  public void dispose() {}

  @Override
  public void hide() {}

  @Override
  public void pause() {}

  @Override
  public void update(float delta) {}

  @Override
  public void render(float delta) {

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // Set the viewport size
    //        batch.setProjectionMatrix(stage.getCamera().combined);
    //        batch.begin();
    //        layer1.draw(batch);
    //        layer2.draw(batch);
    //        layer3.draw(batch);

    stage.act();
    stage.draw();
    //        batch.end();

  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  @Override
  public void resume() {}

  @Override
  public void show() {}

  public void setScreen(Level_1 level1Screen) {}
}
