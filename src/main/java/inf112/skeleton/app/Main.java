package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Robo Rally");
        cfg.setWindowedMode(1200, 1000);

        new Lwjgl3Application(new Game(), cfg);


        //For TiledMap testing
        //new Lwjgl3Application(new TileMap(), cfg);
    }
}