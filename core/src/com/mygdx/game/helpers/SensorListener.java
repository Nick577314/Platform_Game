package com.mygdx.game.helpers;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.entities.Entity;

public class SensorListener implements ContactListener {

  @Override
  public void beginContact(Contact contact) {
    // It appears that the sensor in a sensor-entity collision is always Fixture B
    if (!contact.getFixtureB().isSensor()) return;
    Entity target = (Entity) contact.getFixtureA().getBody().getUserData();
    Entity attacker = (Entity) contact.getFixtureB().getBody().getUserData();

    if (target == attacker) return;

    attacker.attack(target);
  }

  @Override
  public void endContact(Contact contact) {}

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {}

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {}
}
