package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.playable.Player;

public class KeyboardInput implements InputProcessor {

  private final Player player;

  public KeyboardInput(Player characterClass) {
    this.player = characterClass;
  }

  public void keyboardMovement() {

    player.VelX = 0;
    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && player.jumpCounter < 2) {
      // player.setState(Player.State.JUMP);
      float force = player.getBody().getMass() * 18;
      player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, 0);
      player
          .getBody()
          .applyLinearImpulse(new Vector2(0, force), player.getBody().getPosition(), true);
      player.jumpCounter++;
      player.setOnGround(false);

      // player.setPosition(new Vector2(player.getPosition().x, player.getPosition().y + 10));
      // Without the following line, the player gets stuck when jumping
      // (May have to do with ground collisions)
      // player.setY(player.getPosition().y + 10);
      // player.setYVelocity(400);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.A)) {

      player.VelX = -1;
    }
    if (Gdx.input.isKeyPressed(Input.Keys.D)) {

      player.VelX = 1;
    }
    // resets the jump counter
    if (player.getBody().getLinearVelocity().y == 0) {
      player.jumpCounter = 0;
    }
    player
        .getBody()
        .setLinearVelocity(
            player.VelX * player.getSpeed(),
            player.getBody().getLinearVelocity().y < 25
                ? player.getBody().getLinearVelocity().y
                : 25);
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
