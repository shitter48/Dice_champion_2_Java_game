package tw.myshit.game;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class game01 extends Game {
	private SpriteBatch batch;
	private GameScreen01 screen;

	@Override
	public void create() {
		batch = new SpriteBatch();
		screen = new GameScreen01(batch);
		setScreen(screen);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		screen.dispose();
	}
}
