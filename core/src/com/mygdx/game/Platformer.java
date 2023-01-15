package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.entities.playable.Mage;
import com.mygdx.game.hud.Hud;
import com.mygdx.game.levels.Level_1;

public class Platformer extends Game {

  private static Screen screen;
  private static Level_1 level1Screen;
  private static Hud hud;
  public OrthographicCamera orthographicCamera;
  public static Platformer INSTANCE;
  private int widthScreen, heightScreen;

  KeyboardInput INPUT;
  Mage testcharacter;

  public Platformer() {
    INSTANCE = this;
  }

  @Override
  public void create() {
    // From tutorial by Small Pixel Games on YouTube
    this.widthScreen = Gdx.graphics.getWidth();
    this.heightScreen = Gdx.graphics.getHeight();
    this.orthographicCamera = new OrthographicCamera();
    this.orthographicCamera.setToOrtho(false, widthScreen, heightScreen);
    setScreen(new Level_1(orthographicCamera));
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    super.render();
  }

  @Override
  public void dispose() {}
}
