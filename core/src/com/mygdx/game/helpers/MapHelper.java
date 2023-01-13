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
import com.mygdx.game.entities.enemies.EvilWizard;
import com.mygdx.game.entities.playable.Mage;

public class MapHelper {
  private TiledMap tiledMap;
  private Level level;

  public MapHelper(Level level1) {
    this.level = level1;
  }

  public OrthogonalTiledMapRenderer setupMap() {
    // import the test map
    tiledMap = new TmxMapLoader().load(level.getMapFile());
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
        if (rectangleName.equals("player")) {

          Body body =
              BodyBuilder.createBody(
                  rectangle.getX() + rectangle.getWidth() / 2,
                  rectangle.getY() + rectangle.getHeight() / 2,
                  rectangle.getWidth(),
                  rectangle.getHeight(),
                  false,
                  level.getWorld());

          level.setPlayer(new Mage(Entity.Direction.RIGHT, body));
        }
        if (rectangleName.equals("EvilWizard")) {

          Body body =
              BodyBuilder.createBody(
                  rectangle.getX() + rectangle.getWidth() / 2,
                  rectangle.getY() + rectangle.getHeight() / 2,
                  rectangle.getWidth(),
                  rectangle.getHeight(),
                  false,
                  level.getWorld());
          level.entitiesToDraw.add(new EvilWizard(Entity.Direction.LEFT, body));
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
