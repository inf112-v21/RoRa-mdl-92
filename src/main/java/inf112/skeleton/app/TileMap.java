package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


/**
 * This class loads a TiledMap(tmx file) and renders it in a window on screen when initiated
 * In the future this class is maybe only used to handle a TiledMap (not render it etc.)
 */
public class TileMap{

    //Map
    TiledMap map;
    TiledMapTileLayer terrainLayer;
    int[] decorationLayersIndices;

    // Camera and render
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer renderer;

    public TileMap() {
        resize(1200,1000);
        // Map loading
        map = new TmxMapLoader().load(String.valueOf(Gdx.files.internal("src/assets/Tilemap/layeredmap.tmx")));

        // Instantiation of the map renderer
        renderer = new OrthogonalTiledMapRenderer(map);

        // Actor


        // Reading map layers (not used)
        MapLayers mapLayers = map.getLayers();
        terrainLayer = (TiledMapTileLayer) mapLayers.get("Ground Floor");
        decorationLayersIndices = new int[]{
                mapLayers.getIndex("Lasers"),
                mapLayers.getIndex("Battery"),
                mapLayers.getIndex("Left Cogs"),
                mapLayers.getIndex("Right Cogs"),
                mapLayers.getIndex("Walls"),
                mapLayers.getIndex("Express Conveyor Belts"),
                mapLayers.getIndex("Conveyor Belts"),
                mapLayers.getIndex("Holes"),
        };
    }


    public void render() {
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update camera
        camera.update();
        renderer.setView(camera);

        // Rendering map
        renderer.render();

        // Rendering player
        renderer.getBatch().begin();
        //player.draw(renderer.getBatch());
        renderer.getBatch().end();

        // Rendering Cards
        renderer.getBatch().begin();

        renderer.getBatch().end();

    }
    //##########################################################################

    public void resize(int width, int height){
        // Set up the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();
    }



    public void dispose(){
        map.dispose();
        renderer.dispose();
    }

    public void handleInput(){

    }
}