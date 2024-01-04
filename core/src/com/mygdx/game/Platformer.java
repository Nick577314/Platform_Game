package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.helpers.Renderer;
import com.mygdx.game.levels.Level;

public class Platformer extends Game {

  private Level level;
  private CharacterSelectionScreen characterSelect;

  private Renderer renderer;

  public Platformer() {}

  @Override
  public void create() {
    characterSelect = new CharacterSelectionScreen(this);

    //    this.level = new Level_1();
    //    this.renderer = new Renderer(level);
    //    setScreen(renderer);

    setScreen(characterSelect);
  }

  @Override
  public void render() {
    // Update physics/movement
    // level.update();
    // Draw objects to the screen
    // renderer.render(Gdx.graphics.getDeltaTime());
    super.render();
  }

  @Override
  public void dispose() {
    // level.dispose();
    super.dispose();
  }
}
