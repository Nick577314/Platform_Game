package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.entities.playable.*;
import com.mygdx.game.helpers.Renderer;
import com.mygdx.game.levels.Level;
import com.mygdx.game.levels.Level_1;

public class CharacterSelectionScreen implements Screen {
  public SpriteBatch batch;
  Stage stage;

  Skin skin;
  ScrollPane scroll;
  TextButton MageB, ArcherB, WarriorB, ConfirmB;
  private Platformer app;
  private CharacterSelectionScreen characterSelect;
  Mage mage;
  Archer archer;
  Warrior warrior;
  private Array<Texture> textures;
  private ParallaxBackground parallaxBackground;

  //  boolean visible1,visible2,visible3;

  public CharacterSelectionScreen(Platformer app) {
    this.app = app;
    batch = new SpriteBatch(); // Initialize SpriteBatch

    // Initialize Stage with ScreenViewport
    stage = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(stage);

    prepareUI();
  }

  void prepareUI() {

    skin = new Skin(Gdx.files.internal("assets/shadeui/uiskin.json"));
    MageB = new TextButton("Mage", skin, "default");
    ArcherB = new TextButton("Archer", skin, "default");
    WarriorB = new TextButton("Warrior", skin, "default");
    ConfirmB = new TextButton("Confirm", skin, "default");

    MageB.debug();
    //    MageB.debugActor();
    ArcherB.debug();
    //    ArcherB.debugActor();
    WarriorB.debug();
    //    WarriorB.debugActor();
    //    parallaxBackground.debug();

    ConfirmB.setPosition(100, 300);
    MageB.setPosition((stage.getWidth() - MageB.getWidth()) / 2, stage.getHeight() / 6);
    ArcherB.setPosition((stage.getWidth() - MageB.getWidth()) / 2, stage.getHeight() / 4);
    WarriorB.setPosition((stage.getWidth() - MageB.getWidth()) / 2, stage.getHeight() / 3);

    MageB.setSize(125, 50);
    ArcherB.setSize(125, 50);
    WarriorB.setSize(125, 50);

    // stage.addActor(parallaxBackground);
    stage.addActor(ConfirmB);
    stage.addActor(MageB);
    stage.addActor(ArcherB);
    stage.addActor(WarriorB);

    MageB.addListener(
        new ClickListener() {

          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            batch.begin();
            batch.draw(mage.getCurrentFrame(), 150, 250);
            batch.end();

            return super.touchDown(event, x, y, pointer, button);
          }
        });

    ArcherB.addListener(
        new ClickListener() {
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            batch.begin();
            batch.draw(archer.getCurrentFrame(), 350, 250);
            batch.end();
            return super.touchDown(event, x, y, pointer, button);
          }
        });

    WarriorB.addListener(
        new ClickListener() {
          // cycles right
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            return super.touchDown(event, x, y, pointer, button);
          }
        });

    ConfirmB.addListener(
        new ClickListener() {

          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            // TODO: fix this code that is broken after the restructuring of Level
            Level level = new Level_1();
            Renderer renderer = new Renderer(level);
            app.setScreen(renderer);
            return super.touchDown(event, x, y, pointer, button);
          }
        });

    //     this sets up 3 different tables and adds one button to each table
    //        Table tableLevel1 = new Table();
    //        tableLevel1.add(MageB);
    //        Table tableLevel2 = new Table();
    //        tableLevel2.add(ArcherB);
    //        Table tableLevel3 = new Table();
    //        tableLevel3.add(WarriorB);
    ////     this combines all three tables into one table separated by rows
    //        characterSelect = new Table(skin);
    //        characterSelect.add(tableLevel1);
    //        characterSelect.row();
    //        characterSelect.add(tableLevel2);
    //        characterSelect.row();
    //        characterSelect.add(tableLevel3);
    //        characterSelect.row();
    //
    //        scroll = new ScrollPane(characterSelect, skin);
    //        scroll.layout();
    //        scroll.setPosition(Gdx.graphics.getWidth() / 2f - 225f, Gdx.graphics.getHeight() / 2f
    // -
    //     100f);
    //        scroll.setSize(250f, 70f);
    //        stage.addActor(scroll);
    //        scroll.setScrollingDisabled(false, false);

  }

  @Override
  public void dispose() {
    // Gdx.input.setInputProcessor(null);
    stage.dispose();
    for (Texture texture : textures) {
      texture.dispose();
    }
    // parallaxBackground.dispose();
  }

  @Override
  public void resize(int width, int height) {}

  @Override
  public void pause() {}

  public void update(float delta) {}

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    stage.act(delta);
    stage.draw();

    //  batch.begin();
    //    batch.draw(mage.getCurrentFrame(), 150, 250);
    //    batch.draw(archer.getCurrentFrame(), 350, 250);
    //    batch.draw(warrior.getCurrentFrame(), 650, 250);
    // batch.end();
  }

  @Override
  public void resume() {}

  @Override
  public void hide() {}

  public void show() {

    Gdx.input.setInputProcessor(stage);
  }

  private void setScreen(Game game_level1) {}
}
