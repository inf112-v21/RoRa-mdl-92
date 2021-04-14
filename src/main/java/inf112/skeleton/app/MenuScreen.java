package inf112.skeleton.app;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen {

    //Screen
    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;
    private Stage stage;

    //Graphics
    private final Object TextureAtlas;
    private SpriteBatch batch;
    private Texture background;
    private Skin skin;
    private TextButton playButton;
    private TextButton exitButton;
    private TextureAtlas dropdownAtlas;


    //Screen parameters
    private final int SCREEN_WIDTH = 1200;
    private final int SCREEN_HEIGHT = 1000;

    public MenuScreen(){
        // initialize stage with given skin asset
        stage = new Stage();
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
        shapeRenderer = new ShapeRenderer();
        viewport = new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);

        TextureAtlas = dropdownAtlas = new
                TextureAtlas(Gdx.files.internal("src/assets/GDX_SKIN/craftacular/skin/craftacular-ui.atlas"));
        skin = new
                Skin(Gdx.files.internal("src/assets/GDX_SKIN/craftacular/skin/craftacular-ui.json"), dropdownAtlas);


        // textures
        background = new Texture("src/assets/game_menu.png");

        // Textbuttons
        playButton = new TextButton("PLAY", skin);
        playButton.setPosition(400,600);
        playButton.setSize(400,100);

        exitButton = new TextButton("EXIT", skin);
        exitButton.setPosition(400,400);
        exitButton.setSize(400,100);

        // Adding actors (buttons) to the stage
        stage.addActor(playButton);
        stage.addActor(exitButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        //clear screen
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        // adding background image before stage is called
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.getBatch().end();

        // calling the stage
        stage.draw();
    }
    // Handles input from user when clicking buttons
    public void handleInput(){

    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
