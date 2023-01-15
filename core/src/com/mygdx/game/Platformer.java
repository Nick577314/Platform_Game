package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.helpers.Renderer;
import com.mygdx.game.levels.Level;
import com.mygdx.game.levels.Level_1;

public class Platformer extends Game {

  public OrthographicCamera orthographicCamera;
  public static Platformer INSTANCE;
  private Level level;
  private Renderer renderer;

  public Platformer() {
    INSTANCE = this;
  }

  @Override
  public void create() {
    // From tutorial by Small Pixel Games on YouTube
    int widthScreen = Gdx.graphics.getWidth();
    int heightScreen = Gdx.graphics.getHeight();
    this.orthographicCamera = new OrthographicCamera();
    this.orthographicCamera.setToOrtho(false, widthScreen, heightScreen);
    this.level = new Level_1();
    this.renderer = new Renderer(orthographicCamera, level);
    setScreen(renderer);
  }

  @Override
  public void render() {
    // Update physics/movement
    level.update();
    // Draw objects to the screen
    renderer.render(Gdx.graphics.getDeltaTime());
  }
}
