package tw.myshit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import tw.myshit.demo.DropGame;


// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(GameScreen01.WIDTH,GameScreen01.HEIGHT);
		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setTitle("MyShit");
		config.setResizable(true);
		new Lwjgl3Application(new game01(),config);
	}
}
