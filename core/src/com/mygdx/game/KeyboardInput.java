package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Items.Item;
import com.mygdx.game.Items.Potions;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.helpers.Pair;
import java.util.ArrayList;

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
      player.setVelX(-player.getSpeed());
    }
    if (Gdx.input.isKeyPressed(Input.Keys.D)) {
      player.setFacing(Entity.Direction.RIGHT);
      player.setVelX(player.getSpeed());
    }
    if (player.getBody().getLinearVelocity().y == 0) {
      player.resetJumpCounter();
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
      player.setVelX(0);
      player.tryAttack();
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
      if (player.getCurrentHp() < player.getMaxHp()) {
        ArrayList<Pair<Item, Integer>> storage = player.getStorage();
        Pair<Item, Integer> item = storage.get(0);
        if (item.second > 0) { // Check if storage is not empty

          if (item.first instanceof Potions) { // Check if it's a Potions object
            // Use the potion object as a Potions object

            player.setCurrentHp(player.getMaxHp());

            item.second = 0;
          }
        }
      }
    }
    player
        .getBody()
        .setLinearVelocity(
            player.getVelX() * player.getSpeed(),
            Math.min(player.getBody().getLinearVelocity().y, 25));
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
