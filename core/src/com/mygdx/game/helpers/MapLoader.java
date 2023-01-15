package com.mygdx.game.helpers;

import static com.mygdx.game.helpers.Constants.PPM;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.playable.Mage;
import com.mygdx.game.entities.playable.Player;
import com.mygdx.game.levels.Level;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MapLoader {
  private final Level level;

  public MapLoader(Level level) {
    this.level = level;
  }

  public OrthogonalTiledMapRenderer setupMap() {
    TiledMap tiledMap = new TmxMapLoader().load(level.getMapFile());
    parseMapObjects(tiledMap.getLayers().get("platforms").getObjects());
    parseMapObjects(tiledMap.getLayers().get("entities").getObjects());
    return new OrthogonalTiledMapRenderer(tiledMap);
  }

  private void parseMapObjects(MapObjects mapObjects) {
    for (MapObject mapObject : mapObjects) {
      if (mapObject instanceof PolygonMapObject) {
        createStaticBody((PolygonMapObject) mapObject);
        continue;
      }
      if (mapObject instanceof RectangleMapObject) {
        Rectangle rectangle = (((RectangleMapObject) mapObject).getRectangle());
        String rectangleName = mapObject.getName();

        Body body =
            BodyBuilder.createBody(
                rectangle.getX() + rectangle.getWidth() / 2,
                rectangle.getY() + rectangle.getHeight() / 2,
                rectangle.getWidth(),
                rectangle.getHeight(),
                BodyDef.BodyType.KinematicBody,
                level.getWorld());

        if (rectangleName.equals("player")) {
          body.setType(BodyDef.BodyType.DynamicBody);
          Player player = new Mage(Entity.Direction.RIGHT, body);
          level.addEntity(player);
          level.setPlayer(player);
        } else {
          // Locate appropriate class based on polygon name in .tmx file
          Class<?> entityClass;
          try {
            // Full path to class required
            entityClass = Class.forName("com.mygdx.game.entities.enemies." + rectangleName);
          } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                "The current object from the .tmx file doesn't match any valid entity class", e);
          }

          // Get the constructor of the class that matches the given parameter types
          Constructor<?> entityConstructor;
          try {
            entityConstructor = entityClass.getConstructor(Entity.Direction.class, Body.class);
          } catch (NoSuchMethodException e) {
            throw new RuntimeException(
                "An error occurred when trying to find the constructor of a map entity", e);
          }

          // Instantiate the desired object
          Object entity;
          try {
            entity = entityConstructor.newInstance(Entity.Direction.LEFT, body);
          } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(
                "An error occurred when trying to instantiate a map entity", e);
          }

          level.addEntity((Entity) entity);
        }
      }
    }
  }

  private void createStaticBody(PolygonMapObject polygonMapObject) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.StaticBody;
    Body body = level.getWorld().createBody(bodyDef);
    Shape shape = createPolgyonShape(polygonMapObject);
    body.createFixture(shape, 1000);
    shape.dispose();
  }

  private Shape createPolgyonShape(PolygonMapObject polygonMapObject) {

    float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
    Vector2[] worldVertices = new Vector2[vertices.length / 2];

    for (int i = 0; i < vertices.length / 2; i++) {
      Vector2 current = new Vector2(vertices[i * 2] / PPM, vertices[i * 2 + 1] / PPM);
      worldVertices[i] = current;
    }
    PolygonShape shape = new PolygonShape();
    shape.set(worldVertices);
    return shape;
  }
}
