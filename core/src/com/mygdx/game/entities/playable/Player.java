package com.mygdx.game.entities.playable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.KeyboardInput;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.hud.Hud;

public abstract class Player extends Entity {
    KeyboardInput input;
    Hud hud;
    static float stateTime = 0f;
    public boolean onGround = false;

    public enum State {
        IDLE, RUN, JUMP, FALL, ATTACK_A, ATTACK_B, DAMAGE, DEATH
    }

    State state = State.IDLE;

    public Player(Vector2 position, Direction facing) {
        super(position, facing);
        bounds = new Rectangle(position.x, position.y, 200, 200);
        velocity = new Vector2(0, 0);

        input = new KeyboardInput(this);
        Gdx.input.setInputProcessor(input);
    }

    public abstract Animation<TextureRegion> animationFactory(State characterState);

    public TextureRegion getCurrentFrame() {
        stateTime += Gdx.graphics.getDeltaTime();
        final TextureRegion currentFrame = animationFactory(state).getKeyFrame(stateTime, true);

        if (facing == Direction.LEFT) currentFrame.flip(true, false);

        return currentFrame;
    }

    public void setState(State setState) {
        this.state = setState;
    }

    public State getState() {
        return this.state;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public void setHud(Hud newHud) {
        hud = newHud;
    }

    public void takeDamage(int damage) {
        currentHp -= damage;
        // TODO: throw exception if hud not set
        hud.updateHealth(currentHp);
    }

    public void calcVelocity(float delta) {
        velocity.y = velocity.y + acceleration.y * delta;

        if (velocity.y < 0) {
            state = State.FALL;
        } else if (velocity.y > 0) {
            state = State.JUMP;
        }
    }

    public void gravity(float delta) {
        calcVelocity(delta);
        position.y += velocity.y * delta;
    }
}
