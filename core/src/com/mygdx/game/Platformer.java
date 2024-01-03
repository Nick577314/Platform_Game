package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.helpers.Renderer;
import com.mygdx.game.levels.Level;
import com.mygdx.game.levels.Level_1;

public class Platformer extends Game {

  private Level level;
  private Renderer renderer;
  private Level characterSelectionScreen;

  public Platformer() {}

  @Override
  public void create() {

    this.level = new Level_1();
    this.renderer = new Renderer(level);
    setScreen(renderer);
  }

  @Override
  public void render() {
    // Update physics/movement
    level.update();
    // Draw objects to the screen
    renderer.render(Gdx.graphics.getDeltaTime());
  }

  @Override
  public void dispose() {
    level.dispose();
    super.dispose();
  }
}
