package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.animations.CharacterSelectionScreen;
import com.mygdx.game.hud.Hud;

public class Platformer extends Game {

  public static final int V_WIDTH = 1280;
  public static final int V_HEIGHT = 720;
  private static CharacterSelectionScreen screen;
  private static Level_1 level1Screen;

  private static Hud hud;

  @Override
  public void create() {
    screen = new CharacterSelectionScreen(this);
    this.setScreen(screen);
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
