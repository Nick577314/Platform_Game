package com.mygdx.game.entities.playable;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.animations.*;

public class Mage extends Player {
    public Mage(
            Vector2 position,
            Direction facing) {
        super(position, facing);
        maxHp = 3;
        currentHp = maxHp;
        // Placeholder values
        attackPower = 0;
        bounds.width = 0;
        bounds.height = 0;
    }

    public Animation<TextureRegion> animationFactory(State characterState) {
        switch (characterState) {
            case IDLE:
                return CreateAnimation("assets/sprites/mage/idle.png", 6, 0.075f);
            case RUN:
                return CreateAnimation("assets/sprites/mage/run.png", 8, 0.075f);
            case JUMP:
                return CreateAnimation("assets/sprites/mage/jump.png", 2, 0.075f);
            case FALL:
                return CreateAnimation("assets/sprites/mage/fall.png", 2, 0.075f);
            case ATTACK_A:
                return CreateAnimation("assets/sprite/mage/attack1.png", 8, 0.075f);
            case ATTACK_B:
                return CreateAnimation("assets/sprite/mage/attack2.png", 8, 0.075f);
            case DAMAGE:
                return CreateAnimation("assets/sprite/mage/damage.png", 4, 0.075f);
            case DEATH:
                return CreateAnimation("assets/sprite/mage/death.png", 7, 0.075f);
            default:
                throw new CharacterAnimationTypeException("Animation not yet implemented");
        }
    }
}
