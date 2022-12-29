package com.mygdx.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Hud {
  private final Stage stage;
  private final FitViewport stageViewport;
  private final Table table;

  public Hud(SpriteBatch spriteBatch) {
    stageViewport = new FitViewport(500, 500);
    stage =
        new Stage(
            stageViewport,
            spriteBatch); // create stage with the stageViewport and the SpriteBatch given in
    // Constructor

    table = new Table();
    table.top().left();
    table.setFillParent(true);
    table.pad(10);
    //        table.setDebug(true);

    // TODO: figure out why Level 1 is a black screen

    Texture heartTexture = new Texture(Gdx.files.internal("icons/heart-icon.png"));
    Image[] hearts = new Image[3];
    for (int i = 0; i < hearts.length; i++) {
      hearts[i] = new Image(heartTexture);
    }

    Texture keyTexture =
        new Texture(Gdx.files.internal("icons/kyrises-free-icons/icons/16x16/key_01d.png"));
    Image key = new Image(keyTexture);
    key.setOrigin(key.getWidth() / 2, key.getHeight() / 2);
    key.rotateBy(-15);

    BitmapFont whiteFont =
        new BitmapFont(Gdx.files.internal("fonts/thaleahfat/thaleahfat.fnt"), false);
    Label.LabelStyle labelStyle = new Label.LabelStyle(whiteFont, Color.WHITE);
    Label[] keyLabels = {
      new Label("0", labelStyle), new Label("/", labelStyle), new Label("3", labelStyle)
    };

    for (Image heart : hearts) {
      table.add(heart).width(25).height(30).pad(2);
    }

    table.add(key).width(25).height(30).pad(2).right().expandX();

    for (Label label : keyLabels) {
      label.setFontScaleY(1.2f);
      label.setFontScaleX(0.9f);
      table.add(label).right().pad(2);
    }

    stage.addActor(table);
  }

  public Stage getStage() {
    return stage;
  }

  public void dispose() {
    stage.dispose();
  }
}
