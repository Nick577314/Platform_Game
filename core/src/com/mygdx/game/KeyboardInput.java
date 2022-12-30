package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.entities.playable.Player;

public class KeyboardInput implements InputProcessor {

  private final Player characterClass;

  public KeyboardInput(Player characterClass) {
    this.characterClass = characterClass;
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Input.Keys.W) {
      // Handle W key press event

    } else if (keycode == Input.Keys.A) {
      // Handle A key press event

    } else if (keycode == Input.Keys.S) {
      // Handle S key press event

    } else if (keycode == Input.Keys.D) {
      // Handle D key press event

    }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keycode == Input.Keys.W) {
      // Handle W key release event

    } else if (keycode == Input.Keys.A) {
      // Handle A key release event

    } else if (keycode == Input.Keys.S) {
      // Handle S key release event

    } else if (keycode == Input.Keys.D) {
      // Handle D key release event

    }
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(float amountX, float amountY) {
    return false;
  }
}
