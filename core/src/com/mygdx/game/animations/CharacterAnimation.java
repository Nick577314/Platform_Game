package com.mygdx.game.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CharacterAnimation extends Actor {
  static int frameCols, frameRows = 1;
  static Texture sprite1;

  // path to sprite PNG file
  String fileName;
  // number of sprites contained in the PNG file
  int numSprites;

  static Animation<TextureRegion> spriteAnimation;
  static float stateTime;
  TextureRegion currentFrame;
  // ... creating animation etc...
  public CharacterAnimation(String fileName, int numSprites) {
    this.fileName = fileName;
    this.numSprites = numSprites;
  }

  // public static CharacterAnimation factory(CharacterAnimationType which) {
  //   switch (which) {
  //     case ARCHERIDLE:
  //       return new CharacterAnimation("assets/sprites/Archer.png", 10, 250f, 250f, 40, 35);
  //     case WARRIORIDLE:
  //       return new CharacterAnimation("assets/sprites/Hero.png", 10, 250f, 250f, 40, 20);
  //     case WIZARDIDLE:
  //       return new CharacterAnimation("assets/sprites/wizard.png", 6, 250f, 250f, 40, 45);
  //     case WIZARDRUNNING:
  //       return new CharacterAnimation("assets/sprites/wizardrun.png", 6, 250f, 250f, 40, 45);
  //     case WIZARDJUMP:
  //       return new CharacterAnimation("assets/sprites/wizardjump.png", 6, 250f, 250f, 40, 45);
  //     case ARCHERATTACK:
  //       return new CharacterAnimation(
  //           "assets/spriteAttackAnimations/archerAttack.png", 6, 250f, 250f, 40, 20);
  //     case WIZARDATTACK1:
  //       return new CharacterAnimation(
  //           "assets/spriteAttackAnimations/wizardAttack1.png", 8, 250f, 250f, 40, 20);
  //     case WIZARDATTACK2:
  //       return new CharacterAnimation(
  //           "assets/spriteAttackAnimations/wizardAttack2.png", 8, 250, 250, 50, 20);
  //     case WARRIORATTACK1:
  //       return new CharacterAnimation(
  //           "assets/spriteAttackAnimations/warriorAttack1.png", 7, 350, 250, 50, 20);
  //     case WARRIORATTACK2:
  //       return new CharacterAnimation(
  //           "assets/spriteAttackAnimations/warriorAttack2.png", 7, 350, 250, 50, 20);
  //     case WARRIORATTACK3:
  //       return new CharacterAnimation(
  //           "assets/spriteAttackAnimations/warriorAttack3.png", 8, 350, 250, 50, 20);
  //     default:
  //       throw new CharacterAnimationTypeException("Animation not yet implemented");
  //   }
  // }

  public static void CreateAnimation(String fileName, int numCols) {
    sprite1 = new Texture(Gdx.files.internal(fileName));
    frameCols = numCols;
    TextureRegion[][] tmp =
        TextureRegion.split(
            sprite1, sprite1.getWidth() / frameCols, sprite1.getHeight() / frameRows);

    TextureRegion[] spriteIdle = new TextureRegion[frameCols * frameRows];
    int index = 0;
    for (int i = 0; i < frameRows; i++) {
      for (int j = 0; j < frameCols; j++) {
        spriteIdle[index++] = tmp[i][j];
      }
      spriteAnimation = new Animation<TextureRegion>(0.045f, spriteIdle);
    }
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    stateTime += Gdx.graphics.getDeltaTime();
    currentFrame = spriteAnimation.getKeyFrame(stateTime, true);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    batch.draw(currentFrame, 100, 200, 150, 150);
  }
}
