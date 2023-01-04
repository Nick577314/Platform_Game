package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.playable.Player;

public class KeyboardInput implements InputProcessor {

  private final Player characterClass;

  public KeyboardInput(Player characterClass) {
    this.characterClass = characterClass;
  }

  public void keyboardMovement(float delta) {

    if (Gdx.input.isKeyPressed(Input.Keys.W)) {
      // Handle W key press event
      characterClass.setState(Player.States.JUMP);
    } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
      // Handle A key press event
      characterClass.setState(Player.States.RUN);
      characterClass.setFacing(Entity.Direction.LEFT);

    } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
      // Handle S key press event
    } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
      // Handle D key press event
      characterClass.setState(Player.States.RUN);
      characterClass.setFacing(Entity.Direction.RIGHT);

    } else {
      characterClass.setState(Player.States.IDLE);
    }
    // Update the character's position based on the current state and speed
    if (characterClass.getState() == Player.States.RUN) {

      if (characterClass.getFacing() == Entity.Direction.LEFT) {

        characterClass.setX(characterClass.getX() - characterClass.getSpeed().x * delta);
      } else {
        characterClass.setX(characterClass.getX() + characterClass.getSpeed().x * delta);
      }
    } else if (characterClass.getState() == Player.States.JUMP) {
      //      characterClass.setY(characterClass.getY() - characterClass.getSpeed().y * delta);
    }
  }

  @Override
  public boolean keyDown(int keycode) {
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
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
