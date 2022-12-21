package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.screen.AbstractScreen;



public class CharacterSelectionScreen extends AbstractScreen {
    public SpriteBatch batch,batch2,batch3;
    private Stage stage;
    private ParallaxBackground parallaxBackground;
    private ParallaxBackground layer1,layer2,layer3,layer4,layer5,layer6,layer7,layer8;

    private Table characterselect;
    float stateTime;
    Skin skin;
    ScrollPane scroll;
    Texture sprite1;
    String playerChoice;
    TextButton MageB,ArcherB,WarriorB;


    private platformer app;
    private  Level_1 level1Screen;

   final CharacterAnimation mage = CharacterAnimation.factory(CharacterAnimationType.WIZARDIDLE);
    final CharacterAnimation archer = CharacterAnimation.factory(CharacterAnimationType.ARCHERIDLE);
    final CharacterAnimation warrior = CharacterAnimation.factory(CharacterAnimationType.WARRIORIDLE);
    CharacterAnimation selectedCharacter;
    CharacterAnimation[] characterArray= {mage,warrior,archer };
    boolean ConfirmCharacter1,ConfirmCharacter2,ConfirmCharacter3;

    public CharacterSelectionScreen(final platformer app)
    {
        //super(app);
        this.app = app;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("assets/shadeui/uiskin.json"));
        // establishing the buttons
        MageB = new TextButton("Mage", skin, "default");
        ArcherB = new TextButton("Archer", skin, "default");
        WarriorB = new TextButton("Warrior", skin, "default");
        TextButton ConfirmB = new TextButton("Confirm", skin, "default");

      //  Texture background = new Texture(Gdx.files.internal("assets/sprites/Reference-Image.png"));

// this code is related to the ParallaxBackground class
        Array<Texture> textures = new Array<Texture>();
        for(int i = 1; i <=8;i++){
            textures.add(new Texture(Gdx.files.internal("layers/"+i+".png")));
            textures.get(textures.size-1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        }
        ParallaxBackground parallaxBackground = new ParallaxBackground(textures);
        parallaxBackground.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        parallaxBackground.setSpeed(1);
        stage.addActor(parallaxBackground);
// ends around here

        stage.addActor(ConfirmB);
        stage.addActor(MageB);
        stage.addActor(ArcherB);
        stage.addActor(WarriorB);
        //setting up the character sprites

        MageB.addListener(new ClickListener()
        {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
//                selectedCharacter = mage;
               selectedCharacter = mage;
               drawCharacter();
                ConfirmCharacter1 = true;

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        ArcherB.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                selectedCharacter = archer;
                drawCharacter();
                return super.touchDown(event, x, y, pointer, button);
            }

        });

        WarriorB.addListener(new ClickListener() {
            // cycles right
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                selectedCharacter = warrior;
                drawCharacter();

                ConfirmCharacter3 = true;
                return super.touchDown(event, x, y, pointer, button);
            }

        });

        ConfirmB.addListener(new ClickListener()
        {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                Level_1 level1Screen = new Level_1(app);
                app.setScreen(level1Screen);
                return super.touchDown(event, x, y, pointer, button);
            }

        });

        Table tableLevel1 = new Table();
        tableLevel1.add(MageB);

        Table tableLevel2 = new Table();
        tableLevel2.add(ArcherB);

        Table tableLevel3 = new Table();
        tableLevel3.add(WarriorB);

        characterselect = new Table(skin);
        characterselect.add(tableLevel1);
        characterselect.row();
        characterselect.add(tableLevel2);
        characterselect.row();
        characterselect.add(tableLevel3);
        characterselect.row();

        scroll = new ScrollPane(characterselect, skin);

        scroll.layout();
        scroll.setPosition(Gdx.graphics.getWidth() / 2f - 225f,Gdx.graphics.getHeight()/2f - 200f);
        scroll.setSize(250f,70f);
        stage.addActor(scroll);
        scroll.setScrollingDisabled(false,false);
        ConfirmB.setPosition(Gdx.graphics.getWidth() / 2f + 150f,Gdx.graphics.getHeight() / 2f - 205f);
        ConfirmB.setSize(115f,50f);

    }

    @Override
    public void create() {


    }

    @Override
    public void render() {

    }

//    public String sendString()
//    {
//        String mage1,archer1,warrior1;
//
//        if (MageButtonPressed && ConfirmFalse == true) {
//            mage1 = "Mage";
//            return mage1;
//        } else if (ArcherButtonPressed && ConfirmFalse == true) {
//            archer1 = "archer";
//            return archer1;
//        } else if (WarriorButtonPressed && ConfirmFalse == true){
//            warrior1 = "Warrior";
//            return warrior1;
//        }
//        return "No choice";
//    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
    }

    @Override
    public void update(float delta) {



    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();


    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {

    }

    private void setScreen(Game game_level1)
    {
    }

public void drawCharacter(){

    for (CharacterAnimation character : characterArray) {
        if (character.equals(selectedCharacter)) {
            character.CreateAnimation(character.Filename, character.numCols);
            stage.addActor(character);
            continue;
        }
        character.remove();
    }






}


}