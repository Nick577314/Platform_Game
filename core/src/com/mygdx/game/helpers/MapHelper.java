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
import com.mygdx.game.Level_1;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.playable.Mage;

public class MapHelper {
  private TiledMap tiledMap;
  private Level_1 level1;

  public MapHelper(Level_1 level1) {
    this.level1 = level1;
  }

  public OrthogonalTiledMapRenderer setupMap() {
    // import the test map
    tiledMap = new TmxMapLoader().load("assets/maps/map1.tmx");
    parseMapObjects(tiledMap.getLayers().get("platforms").getObjects());
    return new OrthogonalTiledMapRenderer(tiledMap);
  }

  private void parseMapObjects(MapObjects mapObjects) {
    for (MapObject mapObject : mapObjects) {
      if (mapObject instanceof PolygonMapObject) {
        createStaticBody((PolygonMapObject) mapObject);
      }
      if (mapObject instanceof RectangleMapObject) {
        Rectangle rectangle = (((RectangleMapObject) mapObject).getRectangle());
        String rectangleName = mapObject.getName();
        if (rectangleName.equals("player")) {

          Body body =
              BodyBuilder.createBody(
                  rectangle.getX() + rectangle.getWidth() / 2,
                  rectangle.getY() + rectangle.getHeight() / 2,
                  rectangle.getWidth(),
                  rectangle.getHeight(),
                  false,
                  level1.getWorld());

          level1.setPlayer(
              new Mage(rectangle.getWidth(), rectangle.getHeight(), Entity.Direction.RIGHT, body));
        }
      }
    }
  }

  private void createStaticBody(PolygonMapObject polygonMapObject) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.StaticBody;
    Body body = level1.getWorld().createBody(bodyDef);
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