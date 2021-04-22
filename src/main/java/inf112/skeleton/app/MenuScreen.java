package inf112.skeleton.app;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    private TextureAtlas dropdownAtlas;

    private RoboRallyGame game;



    //Screen parameters
    private final int SCREEN_WIDTH = 1532;
    private final int SCREEN_HEIGHT = 1000;

    public MenuScreen(RoboRallyGame g){
        this.game = g;
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
        TitleScreen();
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

    public void TitleScreen(){
        stage.getActors().clear();
        // Textbuttons
        TextButton playButton = new TextButton("OFFLINE PLAY", skin);
        playButton.setPosition(400,600);
        playButton.setSize(400,100);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.game = new Game(false,2,0,null);
            }
        });

        TextButton play2Button = new TextButton("ONLINE PLAY", skin);
        play2Button.setPosition(400,400);
        play2Button.setSize(400,100);
        play2Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                OnlineSelect();
            }
        });

        TextButton exitButton = new TextButton("EXIT", skin);
        exitButton.setPosition(400,200);
        exitButton.setSize(400,100);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        // Adding actors (buttons) to the stage
        stage.addActor(playButton);
        stage.addActor(play2Button);
        stage.addActor(exitButton);
    }

    public void OnlineSelect(){
        stage.getActors().clear();
        TextButton hostButton = new TextButton("HOST GAME", skin);
        hostButton.setPosition(200,600);
        hostButton.setSize(300,100);
        hostButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hostScreen();
            }
        });

        TextButton joinButton = new TextButton("JOIN GAME", skin);
        joinButton.setPosition(700,600);
        joinButton.setSize(300,100);
        joinButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                joinScreen();
            }
        });


        TextButton exitButton = new TextButton("BACK", skin);
        exitButton.setPosition(400,400);
        exitButton.setSize(400,100);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TitleScreen();
            }
        });
        // Adding actors (buttons) to the stage
        stage.addActor(hostButton);
        stage.addActor(joinButton);
        stage.addActor(exitButton);
    }


    public void hostScreen(){
        stage.getActors().clear();
        TextButton hostButton = new TextButton("START HOSTING", skin);
        hostButton.setPosition(350,400);
        hostButton.setSize(500,100);


        TextButton exitButton = new TextButton("BACK", skin);
        exitButton.setPosition(400,200);
        exitButton.setSize(400,100);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                OnlineSelect();
            }
        });

        Label label = new Label("Nr. of players:  2",skin);
        label.setPosition(200,700);
        Slider nrField = new Slider(2,4,1,false,skin);
        nrField.setPosition(200,600);
        nrField.setSize(300,70);
        nrField.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                label.setText("Nr. of players:  "+ Integer.toString((int)nrField.getValue()));
            }
        });

        TextField portField = new TextField("3000",skin);
        portField.setPosition(600,600);
        portField.setSize(300,70);


        Label label2 = new Label("Port",skin);
        label2.setPosition(600,700);

        hostButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.game = new Game(true,(int)nrField.getValue()-1,Integer.valueOf(portField.getText()),null);
            }
        });

        // Adding actors (buttons) to the stage
        stage.addActor(hostButton);
        stage.addActor(exitButton);
        stage.addActor(nrField);
        stage.addActor(portField);
        stage.addActor(label);
        stage.addActor(label2);
    }


    public void joinScreen(){
        stage.getActors().clear();
        TextButton hostButton = new TextButton("JOIN HOST", skin);
        hostButton.setPosition(350,400);
        hostButton.setSize(500,100);


        TextButton exitButton = new TextButton("BACK", skin);
        exitButton.setPosition(400,200);
        exitButton.setSize(400,100);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                OnlineSelect();
            }
        });

        Label label = new Label("Host IP",skin);
        label.setPosition(200,700);
        TextField nrField = new TextField("",skin);
        nrField.setPosition(200,600);
        nrField.setSize(300,70);

        TextField portField = new TextField("3000",skin);
        portField.setPosition(600,600);
        portField.setSize(300,70);


        Label label2 = new Label("Host Port",skin);
        label2.setPosition(600,700);

        hostButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.game = new Game(true,2,Integer.valueOf(portField.getText()),nrField.getText());
            }
        });

        // Adding actors (buttons) to the stage
        stage.addActor(hostButton);
        stage.addActor(exitButton);
        stage.addActor(nrField);
        stage.addActor(portField);
        stage.addActor(label);
        stage.addActor(label2);
    }
}
