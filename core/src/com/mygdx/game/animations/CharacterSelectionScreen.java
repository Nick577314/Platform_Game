package com.mygdx.game.animations;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Level_1;
import com.mygdx.game.ParallaxBackground;
import com.mygdx.game.Platformer;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.playable.*;

public class CharacterSelectionScreen implements Screen {
  public SpriteBatch batch;
  Stage stage;
  private ParallaxBackground parallaxBackground;
  private ParallaxBackground layer1, layer2, layer3, layer4, layer5, layer6, layer7, layer8;
  private Table characterSelect;
  Skin skin;
  ScrollPane scroll;
  TextButton MageB, ArcherB, WarriorB, ConfirmB;
  private Platformer app;
  private Level_1 level1Screen;
  Mage mage;
  Archer archer;
  Warrior warrior;

  //  boolean visible1,visible2,visible3;

  public CharacterSelectionScreen(final Platformer app) {
    // super(app);
    this.app = app;
    FitViewport viewport = new FitViewport(800, 600);
    batch = new SpriteBatch();
    stage = new Stage();
    Gdx.input.setInputProcessor(stage);
    //     this is for the mage,archer,warrior
    Vector2 position = new Vector2(100, 100);
    Vector2 speed = new Vector2(50, 0);

    //    these three lines of code are causing the buttons to break and not be clickable
    mage = new Mage(100, 100, 100, 100, position, speed, Entity.Direction.RIGHT);
    archer = new Archer(100, 100, 100, 100, position, speed, Entity.Direction.RIGHT);
    warrior = new Warrior(100, 100, 100, 100, position, speed, Entity.Direction.RIGHT);

    prepaireUI();
    //    ConfirmB.setPosition(Gdx.graphics.getWidth() / 2f + 150f, Gdx.graphics.getHeight() / 2f -
    // 205f);
    //    ConfirmB.setSize(115f, 50f);
  }

  void prepaireUI() {

    // this prepares the background
    Array<Texture> textures = new Array<Texture>();
    for (int i = 1; i <= 8; i++) {
      textures.add(new Texture(Gdx.files.internal("layers/" + i + ".png")));
      textures
          .get(textures.size - 1)
          .setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
    }
    ParallaxBackground parallaxBackground = new ParallaxBackground(textures);
    parallaxBackground.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    parallaxBackground.setSpeed(1);

    // this establishes the buttons
    skin = new Skin(Gdx.files.internal("assets/shadeui/uiskin.json"));
    MageB = new TextButton("Mage", skin, "default");
    ArcherB = new TextButton("Archer", skin, "default");
    WarriorB = new TextButton("Warrior", skin, "default");
    ConfirmB = new TextButton("Confirm", skin, "default");

    MageB.debug();
    MageB.debugActor();
    ArcherB.debug();
    WarriorB.debug();

    ConfirmB.setPosition((stage.getWidth() - ConfirmB.getWidth()) / 2, stage.getHeight() / 9);
    MageB.setPosition((stage.getWidth() - MageB.getWidth()) / 2, stage.getHeight() / 6);
    ArcherB.setPosition((stage.getWidth() - MageB.getWidth()) / 2, stage.getHeight() / 4);
    WarriorB.setPosition((stage.getWidth() - MageB.getWidth()) / 2, stage.getHeight() / 3);

    // adding actors to the stage
    stage.addActor(parallaxBackground);
    stage.addActor(ConfirmB);
    stage.addActor(MageB);
    stage.addActor(ArcherB);
    stage.addActor(WarriorB);
    MageB.addListener(
        new ClickListener() {

          @Override
          public void clicked(InputEvent event, float x, float y) {

            System.out.println("CLICKING THE MAGE BUTTON");
          }
        });

    ArcherB.addListener(
        new ClickListener() {
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            //        batch.begin();
            //        batch.draw(archer.getCurrentFrame(),100,100);
            //        batch.end();

            System.out.println("CLICKING THE MAGE BUTTON");

            return super.touchDown(event, x, y, pointer, button);
          }
        });

    WarriorB.addListener(
        new ClickListener() {
          // cycles right
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            // selectedCharacter = warrior;

            return super.touchDown(event, x, y, pointer, button);
          }
        });

    ConfirmB.addListener(
        new ClickListener() {

          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            dispose();
            Level_1 level1Screen = new Level_1(app);
            app.setScreen(level1Screen);
            return super.touchDown(event, x, y, pointer, button);
          }
        });
    // this sets up 3 different tables and adds one button to each table
    //    Table tableLevel1 = new Table();
    //    tableLevel1.add(MageB);
    //    Table tableLevel2 = new Table();
    //    tableLevel2.add(ArcherB);
    //    Table tableLevel3 = new Table();
    //    tableLevel3.add(WarriorB);
    // this combines all three tables into one table separated by rows
    //    characterSelect = new Table(skin);
    //    characterSelect.add(tableLevel1);
    //    characterSelect.row();
    //    characterSelect.add(tableLevel2);
    //    characterSelect.row();
    //    characterSelect.add(tableLevel3);
    //    characterSelect.row();

    //    scroll = new ScrollPane(characterSelect, skin);
    //    scroll.layout();
    //    scroll.setPosition(Gdx.graphics.getWidth() / 2f - 225f, Gdx.graphics.getHeight() / 2f -
    // 100f);
    //    scroll.setSize(250f, 70f);
    //    stage.addActor(scroll);
    //    scroll.setScrollingDisabled(false, false);

  }

  @Override
  public void dispose() {
    //    Gdx.input.setInputProcessor(null);
    stage.dispose();
  }

  @Override
  public void hide() {}

  @Override
  public void resize(int width, int height) {}

  @Override
  public void pause() {}

  public void update(float delta) {}

  @Override
  public void render(float delta) {

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.act(delta);
    stage.draw();

    batch.begin();
    batch.draw(mage.getCurrentFrame(), 150, 250);
    batch.draw(archer.getCurrentFrame(), 350, 250);
    batch.draw(warrior.getCurrentFrame(), 650, 250);
    batch.end();
  }

  @Override
  public void resume() {}

  @Override
  public void show() {}

  private void setScreen(Game game_level1) {}

  //  public void drawCharacter() {
  //
  //    for ( Animation<TextureRegion> character : characterArray) {
  //      if (character.equals(characterArray)) {
  //        character.animationFactory(character.fileName, character.numSprites);
  //        stage.addActor(character);
  //        continue;
  //      }
  //      character.remove();
  //    }
  //  }
}
