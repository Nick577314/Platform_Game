package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class platformer extends Game {

  private static CharacterSelectionScreen screen;
  private static Level_1 level1Screen;

  @Override
  public void create() {
    screen = new CharacterSelectionScreen(this);
    this.setScreen(screen);

    //		level1Screen = new Level_1(this);
    //		this.setScreen(level1Screen);

    // Set the initial screen
    // setScreen(screen);
  }

  //	public static void goToLevel1Screen(Level_1 level1Screen) {
  //		setScreen(platformer.level1Screen);
  //	}

  @Override
  public void render() {
    Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    super.render();
  }

  @Override
  public void dispose() {}
}
