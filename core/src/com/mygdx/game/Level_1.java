package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.animations.CharacterSelectionScreen;
import com.mygdx.game.entities.*;
import com.mygdx.game.entities.playable.*;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.hud.Hud;

public class Level_1 implements Screen {
    private Texture region1, region2, region3;
    private Sprite layer1, layer2, layer3;

    private Stage stage;
    // Set the position and size of each layer
    SpriteBatch batch;
    private Hud hud;
    private Platformer app;
    private Viewport viewport;
    // Get the window width and height in pixels
    int windowWidth = 320;
    int windowHeight = 180;
    private String playerChoice;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    float Xposition, Yposition;
    float speed = 20.0f;
    Player testChar;
    Player.States currentState = Player.States.IDLE;
    KeyboardInput INPUT;
    // CharacterAnimation mage1;

    public Level_1(final Platformer app) {
        // super(app);
        this.app = app;

        testChar = new Mage(new Vector2(100, 100), Entity.Direction.RIGHT);

        // Create a SpriteBatch object
        batch = new SpriteBatch();
        hud = new Hud(batch, testChar);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        map = new TmxMapLoader().load("assets/maps/test_map.tmx");
        this.camera = new OrthographicCamera();
        this.renderer = new OrthogonalTiledMapRenderer(map);
        // viewport.setScreenBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        CharacterSelectionScreen choice = new CharacterSelectionScreen(app);
        Vector2 position = new Vector2(100, 100);
        Vector2 speed = new Vector2(200, 0);
        System.out.println(playerChoice);

        INPUT = new KeyboardInput(testChar);
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        batch.dispose();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    public void update(float delta) {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // calls movement for character
        INPUT.keyboardMovement(delta);
        batch.begin();
        batch.draw(testChar.getCurrentFrame(), testChar.getX(), testChar.getY());
        batch.end();
        stage.act(delta);

        // Set the viewport size
        //        batch.setProjectionMatrix(stage.getCamera().combined);
        //        batch.begin();
        //        layer1.draw(batch);
        //        layer2.draw(batch);
        //        layer3.draw(batch);

        batch.setProjectionMatrix(
                hud.getStage()
                        .getCamera()
                        .combined); // set the spriteBatch to draw what our stageViewport sees
        hud.getStage().act(delta); // act the Hud
        hud.getStage().draw(); // draw the Hud

        stage.act();
        stage.draw();
        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void resume() {
    }

    public void setScreen(Level_1 level1Screen) {
    }
}

// TODO: create parent Level class that initializes HUD and Player objects