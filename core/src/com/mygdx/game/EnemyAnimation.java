package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EnemyAnimation extends Actor {
  static int frameCols, frameRows = 1;
  static Texture sprite1;
  String fileName;
  int numCols;
  float width, height, xPos, yPos;
  static Animation<TextureRegion> spriteAnimation;
  static float stateTime;
  TextureRegion currentFrame;
  // ... creating animation etc...
  EnemyAnimation(String fileName, int numCols, float width, float height, float xPos, float yPos) {
    this.fileName = fileName;
    this.numCols = numCols;
    this.width = width;
    this.height = height;
    this.xPos = xPos;
    this.yPos = yPos;
  }

  public static EnemyAnimation factory(CharacterAnimationType which) {
    switch (which) {
      case MAGEIDLE:
        return new EnemyAnimation("sprites/Mage.png", 8, 400f, 400f, 300, 10);
      case MAGEATTACK1:
        return new EnemyAnimation("spriteAttackAnimations/mageAttack1.png", 8, 400f, 400f, 300, 10);
      case MAGEATTACK2:
        return new EnemyAnimation("spriteAttackAnimations/mageAttack2.png", 8, 250f, 250f, 300, 20);
      case FIREWORMIDLE:
        return new EnemyAnimation("assets/sprites/firewormidle.png", 9, 250f, 250f, 300, 20);
      case EVILWIZARD:
        return new EnemyAnimation("sprites/EvilWizard.png", 8, 250f, 250f, 300, 20);
      case EVILWIZARDATTACK:
        return new EnemyAnimation(
            "spriteAttackAnimations/EvilWizardAttack.png", 8, 250f, 250f, 300, 20);
      case FIREWORMATTACK:
        return new EnemyAnimation(
            "assets/spriteAttackAnimations/firewormattack.png", 16, 250f, 250f, 300, 20);
      default:
        throw new CharacterAnimationTypeException("Animation not yet implemented");
    }
  }

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

    if (!currentFrame.isFlipX()) {
      currentFrame.flip(true, false);
    }

    batch.draw(currentFrame, xPos, yPos, width, height);
  }
}
