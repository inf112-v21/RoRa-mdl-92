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
public class TileMap extends ApplicationAdapter{

    //Player
    Actor player;

    //Cards
    Cards card1;
    Cards card2;
    Cards card3;
    Cards card4;
    Cards card5;
    Cards card6;

    //Card positions descending from top to bottom
    private static final int allCards_XPos = 996;
    private static final int card1_YPos = 856;
    private static final int card2_YPos = 716;
    private static final int card3_YPos = 576;
    private static final int card4_YPos = 436;
    private static final int card5_YPos = 296;
    private static final int card6_YPos = 156;

    //Map
    TiledMap map;
    TiledMapTileLayer terrainLayer;
    int[] decorationLayersIndices;

    // Camera and render
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer renderer;

    @Override
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
        player.draw(renderer.getBatch());
        renderer.getBatch().end();

        // Rendering Cards
        renderer.getBatch().begin();

        //card1.draw(renderer.getBatch());
        //card2.draw(renderer.getBatch());
        //card3.draw(renderer.getBatch());
        //card4.draw(renderer.getBatch());
        //card5.draw(renderer.getBatch());
        //card6.draw(renderer.getBatch());

        renderer.getBatch().end();

        //##########################################################################
        //MouseHandling
        int x = allCards_XPos;
        int CARD_WIDTH = 96;
        int CARD_HEIGHT = 140;
        int BOARD_HEIGHT = 996;
        int BOARD_WIDTH = 1136;

        renderer.getBatch().begin();

        // Clicking the 1st card
        if (Gdx.input.getX() < x + CARD_WIDTH && Gdx.input.getX() > x -CARD_WIDTH &&
            BOARD_HEIGHT - Gdx.input.getY() < card1_YPos + CARD_HEIGHT &&
                BOARD_HEIGHT - Gdx.input.getY() > card1_YPos){
            card1.draw(renderer.getBatch());
            if(Gdx.input.justTouched()){
                System.out.println("Card 1 selected");
            }
        }
        // Clicking card 2
        if (Gdx.input.getX() < x + CARD_WIDTH && Gdx.input.getX() > x -CARD_WIDTH &&
                BOARD_HEIGHT - Gdx.input.getY() < card2_YPos + CARD_HEIGHT &&
                BOARD_HEIGHT - Gdx.input.getY() > card2_YPos){
            card2.draw(renderer.getBatch());
            if(Gdx.input.justTouched()){
                System.out.println("Card 2 selected");
            }
        }
        // Clicking card 3
        if (Gdx.input.getX() < x + CARD_WIDTH && Gdx.input.getX() > x -CARD_WIDTH &&
                BOARD_HEIGHT - Gdx.input.getY() < card3_YPos + CARD_HEIGHT &&
                BOARD_HEIGHT - Gdx.input.getY() > card3_YPos){
            card3.draw(renderer.getBatch());
            if(Gdx.input.justTouched()){
                System.out.println("Card 3 selected");
            }
        }
        // Clicking card 4
        if (Gdx.input.getX() < x + CARD_WIDTH && Gdx.input.getX() > x -CARD_WIDTH &&
                BOARD_HEIGHT - Gdx.input.getY() < card4_YPos + CARD_HEIGHT &&
                BOARD_HEIGHT - Gdx.input.getY() > card4_YPos){
            card4.draw(renderer.getBatch());
            if(Gdx.input.justTouched()){
                System.out.println("Card 4 selected");
            }
        }
        // Clicking card 5
        if (Gdx.input.getX() < x + CARD_WIDTH && Gdx.input.getX() > x -CARD_WIDTH &&
                BOARD_HEIGHT - Gdx.input.getY() < card5_YPos + CARD_HEIGHT &&
                BOARD_HEIGHT - Gdx.input.getY() > card5_YPos){
            card5.draw(renderer.getBatch());
            if(Gdx.input.justTouched()){
                System.out.println("Card 5 selected");
            }
        }

        renderer.getBatch().end();
    }
    //##########################################################################

    public void resize(int height, int width){
        // Set up the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1092, 996);
        camera.update();
    }

    @Override
    public void create() {
        // Map loading
        map = new TmxMapLoader().load(String.valueOf(Gdx.files.internal("src/assets/Tilemap/layeredmap.tmx")));

        // Instantiation of the map renderer
        renderer = new OrthogonalTiledMapRenderer(map);

        // Actor
        player = new Actor(new Sprite(new Texture("src/assets/robot1.png")));

        // Card
        card1 = new Cards(new Sprite(new Texture("src/assets/move1.png")));
        card2 = new Cards(new Sprite(new Texture("src/assets/move2.png")));
        card3 = new Cards(new Sprite(new Texture("src/assets/move3.png")));
        card4 = new Cards(new Sprite(new Texture("src/assets/rotate_left.png")));
        card5 = new Cards(new Sprite(new Texture("src/assets/rotate_right.png")));
        card6 = new Cards(new Sprite(new Texture("src/assets/u_turn.png")));
        //Card positionings
        card1.setPosition(allCards_XPos,card1_YPos);
        card2.setPosition(allCards_XPos,card2_YPos);
        card3.setPosition(allCards_XPos,card3_YPos);
        card4.setPosition(allCards_XPos,card4_YPos);
        card5.setPosition(allCards_XPos,card5_YPos);
        card6.setPosition(allCards_XPos,card6_YPos);

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
    @Override
    public void dispose(){
        map.dispose();
        renderer.dispose();
        player.getTexture().dispose();
    }

    public void handleInput(){
        
    }
}