package inf112.skeleton.app;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private SpriteBatch batch;
    private Texture background;
    private Texture play_Button;
    private Texture exit_Button;
    private Skin skin;
    private TextButton playButton;
    private TextButton exitButton;


    //Screen parameters
    private final int SCREEN_WIDTH = 1200;
    private final int SCREEN_HEIGHT = 1000;

    public MenuScreen(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("src/assets/uiskin.json"));

        playButton = new TextButton("PLAY", skin);
        playButton.setPosition(600,500);
        playButton.setSize(300,60);


        stage.addActor(playButton);




        //
        viewport = new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);

        background = new Texture("src/assets/game_menu.png");
        play_Button = new Texture("src/assets/play_Button.png");
        exit_Button = new Texture("src/assets/exit_Button.png");

        batch = new SpriteBatch();

        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        //clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        stage.draw();

        /*Start rendering
        batch.begin();

        batch.draw(background,0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        batch.draw(play_Button,600,500, 200, 100);


        batch.end();

         */
    }

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
