package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.playable.Player;

public class KeyboardInput implements InputProcessor {

  private final Player player;

  public KeyboardInput(Player player) {
    this.player = player;
  }

  public void update() {
    player.setVelX(0);
    if (player.isMovementDisabled()) return;

    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && player.getJumpsRemaining() > 0) {
      float force = player.getBody().getMass() * 20;
      player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, 0);
      player
          .getBody()
          .applyLinearImpulse(new Vector2(0, force), player.getBody().getPosition(), true);
      player.decrementJumpCounter();
    }
    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
      player.setFacing(Entity.Direction.LEFT);
      player.setVelX(-1.5f);
    }
    if (Gdx.input.isKeyPressed(Input.Keys.D)) {
      player.setFacing(Entity.Direction.RIGHT);
      player.setVelX(1.5f);
    }
    if (player.getBody().getLinearVelocity().y == 0) {
      player.resetJumpCounter();
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
      player.setVelX(0);
      player.setState(Entity.State.ATTACK_A);
      player.setMovementDisabled(true);
      // Disable player movement until the animation finishes
      Timer.schedule(
          new Timer.Task() {
            @Override
            public void run() {
              player.setMovementDisabled(false);
            }
          },
          8 * player.getAnimationFrameDuration()); // formula: numFrames * (1 / frameDuration) / 60
    }
    player
        .getBody()
        .setLinearVelocity(
            player.getVelX() * player.getSpeed(),
            player.getBody().getLinearVelocity().y < 25
                ? player.getBody().getLinearVelocity().y
                : 25); // Cap the player's y-velocity at 25 units
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
