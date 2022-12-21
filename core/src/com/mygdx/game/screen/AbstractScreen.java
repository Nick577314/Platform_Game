package com.mygdx.game.screen;

import com.badlogic.gdx.Screen;
import com.mygdx.game.platformer;

/**
 * @author hydrozoa
 */
public abstract class AbstractScreen implements Screen {

    private platformer app;

    public AbstractScreen(platformer app) {
        this.app = app;
    }

    protected AbstractScreen() {
    }

    public abstract void create();

    public abstract void render();

    @Override
    public abstract void dispose();

    @Override
    public abstract void hide();

    @Override
    public abstract void pause();


    public abstract void update(float delta);

    @Override
    public abstract void render(float delta);

    @Override
    public abstract void resize(int width, int height);

    @Override
    public abstract void resume();

    @Override
    public abstract void show();

}