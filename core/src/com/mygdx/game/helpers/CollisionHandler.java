package com.mygdx.game.helpers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Items.Items;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.enemies.Enemy;
import com.mygdx.game.entities.playable.Player;

public class CollisionHandler implements ContactListener {

  @Override
  public void beginContact(Contact contact) {
    final Fixture fixtureA = contact.getFixtureA();
    final Fixture fixtureB = contact.getFixtureB();
    final Object entityA = fixtureA.getBody().getUserData();
    final Object entityB = fixtureB.getBody().getUserData();

    if (fixtureA.isSensor() && entityB instanceof Enemy) {
      playerAttacksEnemy((Player) entityA, (Enemy) entityB);
      return;
    }

    if (entityA instanceof Enemy && fixtureB.isSensor()) {
      playerAttacksEnemy((Player) entityB, (Enemy) entityA);
      return;
    }

    if (entityA instanceof Player && entityB instanceof Enemy) {
      playerTouchesEnemy((Player) entityA, (Enemy) entityB);
      return;
    }

    if (entityA instanceof Enemy && entityB instanceof Player) {
      playerTouchesEnemy((Player) entityB, (Enemy) entityA);
      return;
    }
    // creating the logic for if a player interacts with an item
    if (entityA instanceof Items && entityB instanceof Player) {
      playerTouchesItem((Player) entityB, (Items) entityA);
      return;
    }
    if (entityB instanceof Items && entityA instanceof Player) {
      playerTouchesItem((Player) entityA, (Items) entityB);
      return;
    }
  }

  @Override
  public void endContact(Contact contact) {}

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {}

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {}

  private void playerAttacksEnemy(final Player player, final Enemy enemy) {
    player.attack(enemy);
  }

  private void playerTouchesEnemy(final Player player, final Enemy enemy) {
    player.setMovementDisabled(true);
    enemy.setMovementDisabled(true);
    player.setState(Entity.State.DAMAGE);

    if (player.getDistance(enemy).x <= 0)
      player.getBody().applyLinearImpulse(new Vector2(-5, 0), enemy.getBody().getPosition(), true);
    else
      player.getBody().applyLinearImpulse(new Vector2(5, 0), enemy.getBody().getPosition(), true);

    enemy.attack(player);

    Timer.schedule(
        new Timer.Task() {
          @Override
          public void run() {
            player.setMovementDisabled(false);
          }
        },
        player.getAnimationDuration());

    Timer.schedule(
        new Timer.Task() {
          @Override
          public void run() {
            enemy.setMovementDisabled(false);
          }
        },
        1f);
  }

  private void playerTouchesItem(final Player player, Items items) {

    items.doWhenCollected(player);
  }
}
