package inf112.skeleton.app;

import com.badlogic.gdx.Game;

public class RoboRallyGame extends Game {

    MenuScreen menuScreen;
    public inf112.skeleton.app.Game game;

    @Override
    public void create() {
        menuScreen = new MenuScreen(this);
        setScreen(menuScreen);

    }

    @Override
    public void dispose() {
        menuScreen.dispose();
    }

    @Override
    public void render() {
        super.render();
        if(game != null){
            game.render();
            if(game.gameOver){
                menuScreen = new MenuScreen(this);
                setScreen(menuScreen);
                game = null;
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        menuScreen.resize(width, height);
    }
}
