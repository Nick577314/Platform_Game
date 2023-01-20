package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class ParallaxBackground extends Actor {

  private float scroll;
  private Array<Texture> layers;
  private final float LAYER_SPEED_DIFFERENCE = 2f;

  float x, y, width, height, scaleX, scaleY;
  float originX, originY, rotation;
  int srcX, srcY;
  boolean flipX, flipY;
  ParallaxBackground parallaxBackground;

  private float speed;

  public ParallaxBackground() {
    layers = new Array<>();
    for (int i = 0; i <= 2; i++) {
      layers.add(new Texture(Gdx.files.internal("layers2/background_" + i + ".png")));
      layers
          .get(layers.size - 1)
          .setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
    }
    scroll = 0;
    speed = 0;

    x = y = originX = originY = rotation = srcY = 0;
    width = Gdx.graphics.getWidth();
    height = Gdx.graphics.getHeight();
    scaleX = scaleY = 1;
    flipX = flipY = false;
  }

  public void setSpeed(float newSpeed) {
    this.speed = newSpeed;
  }

  public void setPosition(float positionX, float positionY) {

    this.x = positionX;
    this.y = positionY;
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);

    scroll += speed;
    for (int i = 0; i < layers.size; i++) {
      srcX = (int) (scroll + i * this.LAYER_SPEED_DIFFERENCE * scroll);
      batch.draw(
          layers.get(i),
          x,
          y,
          originX,
          originY,
          width,
          height,
          scaleX,
          scaleY,
          rotation,
          srcX,
          srcY,
          layers.get(i).getWidth(),
          layers.get(i).getHeight(),
          flipX,
          flipY);
    }
  }

  public void dispose() {
    for (Texture layer : layers) {
      layer.dispose();
    }
  }
}
