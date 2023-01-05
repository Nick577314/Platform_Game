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

        if (Gdx.input.isKeyPressed(Input.Keys.W) && player.isOnGround()) {
            player.setState(Player.State.JUMP);
            player.setOnGround(false);
            player.setPosition(new Vector2(player.getPosition().x, player.getPosition().y + 10));
            // Without the following line, the player gets stuck when jumping
            // (May have to do with ground collisions)
            player.setY(player.getPosition().y + 10);
            player.setYVelocity(400);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (player.isOnGround()) player.setState(Player.State.RUN);
            player.setXVelocity(-300);
            player.setFacing(Player.Direction.LEFT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (player.isOnGround()) player.setState(Player.State.RUN);
            player.setXVelocity(300);
            player.setFacing(Player.Direction.RIGHT);
        } else if (player.isOnGround()) {
            player.setState(Player.State.IDLE);
            player.setVelocity(new Vector2(0, player.getVelocity().y));
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
