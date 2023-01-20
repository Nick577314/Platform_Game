package com.mygdx.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.entities.playable.Player;
import java.util.ArrayList;

public class Hud {
  private final Stage hudStage;
  private final Table hudTable;
  private final Texture heartTexture = new Texture(Gdx.files.internal("icons/heart-icon.png"));
  private final ArrayList<Cell<Image>> healthBar = new ArrayList<>();
  private final BitmapFont thaleahFont =
      new BitmapFont(Gdx.files.internal("fonts/thaleahfat/thaleahfat.fnt"), false);
  private final Label.LabelStyle labelStyle = new Label.LabelStyle(thaleahFont, Color.WHITE);
  private final Cell<Label> collectedKeys, allKeys;

  public Hud(SpriteBatch spriteBatch) {
    FitViewport stageViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    hudStage = new Stage(stageViewport, spriteBatch);

    hudTable = new Table();
    hudTable.top().left();
    hudTable.setFillParent(true);
    hudTable.pad(10);
    //        table.setDebug(true);

    // Draw health bar
    HorizontalGroup healthBarGroup = new HorizontalGroup();
    int health = 3;
    for (int i = 0; i < health; i++) {
      Image heart = new Image(heartTexture);
      heart.scaleBy(3);
      healthBarGroup.addActor(heart);
    }

    // Gives the hearts some overlap
    healthBarGroup.space(healthBarGroup.getChild(0).getWidth() + 5);

    hudTable.add(healthBarGroup);

    // Draw key icon
    Texture keyTexture =
        new Texture(Gdx.files.internal("icons/kyrises-free-icons/icons/16x16/key_01d.png"));
    Image key = new Image(keyTexture);
    key.setOrigin(key.getWidth() / 2, key.getHeight() / 2);
    key.rotateBy(-15);

    hudTable.add(key).size(30).pad(2).right().expandX();

    // Draw collected keys label
    Label[] keyLabels = {
      new Label("0", labelStyle), new Label("/", labelStyle), new Label("3", labelStyle)
    };

    for (Label label : keyLabels) {
      label.setFontScale(1.5f);
    }

    collectedKeys = hudTable.add(keyLabels[0]).right().pad(3);
    hudTable.add(keyLabels[1]).right().pad(3);
    allKeys = hudTable.add(keyLabels[2]).right().pad(3);

    hudStage.addActor(hudTable);
  }

  public Stage getStage() {
    return hudStage;
  }

  public void dispose() {
    hudStage.dispose();
    thaleahFont.dispose();
    heartTexture.dispose();
  }

  public void incrementKeyCounter() {
    int val = Integer.parseInt(collectedKeys.getActor().getText().toString()) + 1;
    collectedKeys.setActor(new Label(new StringBuilder(String.valueOf(val)), labelStyle));
  }

  public void update(Player player) {
    HorizontalGroup healthBarGroup = (HorizontalGroup) hudTable.getChild(0);
    healthBarGroup.clear();

    for (int i = 0; i < player.getCurrentHp(); i++) {
      Image heart = new Image(heartTexture);
      heart.scaleBy(3);
      healthBarGroup.addActor(heart);
    }
  }
}
