package tw.myshit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen01 extends ScreenAdapter {
	public static final int WIDTH = 240 * 4;
	public static final int HEIGHT = 180 * 4;

	private OrthographicCamera camera;
	private Viewport gamePort;
	private SpriteBatch batch;
	private TileMap01 map01;
	public static Player01 player;
	private Hud01 hudact;
	private Hud02 hudbat;
	private Stage stage;
	public static boolean encounter;
	public static Integer maplevel = 1;

	public GameScreen01(SpriteBatch batch) {
		this.batch = batch;
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		gamePort = new FitViewport(WIDTH, HEIGHT, camera);
		stage = new Stage(gamePort, batch);
		map01 = new TileMap01();
		player = new Player01();
		hudact = new Hud01(batch);
		hudbat = new Hud02(batch,player.tileMapPos);
		camera.position.set(gamePort.getScreenWidth() / 2, gamePort.getScreenHeight() / 2, 10);
		camera.zoom -= 0.55;
		encounter = false;

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		

		cameraMove();
		if(!encounter) {
		camera.position.x = player.worldPos.x + 15;
		camera.position.y = player.worldPos.y;
		map01.updateMap();
		}
		camera.update();

		batch.begin();
		
		map01.render(batch, delta);
		
		player.update(batch, delta);
		player.render(batch);
		batch.end();
		
		batch.setProjectionMatrix(hudact.stage.getCamera().combined);
		hudact.stage.draw();
		if (encounter) {
			hudact.stage.dispose();

			batch.setProjectionMatrix(hudbat.stage.getCamera().combined);	
			hudbat.update();
			hudbat.stage.draw();
			
			camera.position.x = player.worldPos.x + 2;
			camera.position.y = player.worldPos.y + 13;
			while (camera.zoom >= 0.15) {
				camera.zoom -= 0.3;
			}
		}
		stage.draw();
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
	}

	private void cameraMove() {

		if (Gdx.input.isKeyJustPressed(Input.Keys.PAGE_UP)) {
//			camera.zoom -= 0.01f;
			maplevel += 1;
			map01.isupdated = false;
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.PAGE_DOWN)) {
//			camera.zoom += 0.01f;
			maplevel -= 1;
			map01.isupdated = false;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			camera.position.x -= 5;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			camera.position.x += 5;
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			camera.position.y += 5;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			camera.position.y -= 5;
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

}
