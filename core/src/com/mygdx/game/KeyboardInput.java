package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
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
            characterClass.setState(Player.State.JUMP);

        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            // Handle A key press event
            characterClass.setState(Player.State.RUN);
            characterClass.setFacing(Entity.Direction.LEFT);

        } else if (characterClass.getVelocity().y < 0) {

            characterClass.setState(Player.State.FALL);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            // Handle D key press event
            characterClass.setState(Player.State.RUN);
            characterClass.setFacing(Entity.Direction.RIGHT);

        } else {
            characterClass.setState(Player.State.IDLE);
        }
        // Update the character's position based on the current state and speed
        if (characterClass.getState() == Player.State.RUN) {

            if (characterClass.getFacing() == Entity.Direction.LEFT) {
                characterClass.setX(characterClass.getX() - characterClass.getVelocity().x * delta);

            } else {
                characterClass.setX(characterClass.getX() + characterClass.getVelocity().x * delta);
            }
        } else if (characterClass.getState() == Player.State.JUMP && characterClass.isOnGround()) {
            characterClass.setOnGround(false);
            characterClass.setPosition(new Vector2(characterClass.getPosition().x, characterClass.getPosition().y + 1));
            characterClass.setVelocity(new Vector2(300, 400 + characterClass.getVelocity().y * delta));
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
