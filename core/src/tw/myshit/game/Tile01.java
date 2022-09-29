package tw.myshit.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class Tile01 {
	private Texture t;
	public Vector2 tileMapPos;
	public Vector2 worldPos;
	private boolean onScreen;
	private float animeY;
	private float time;
	private float animeSpeed;
	private boolean animeDown;
	private final int VISION;

	public Tile01(Texture t, Vector2 tileMapPos, Vector2 worldPos) {
		this.t = t;
		this.tileMapPos = tileMapPos;
		this.worldPos = worldPos;
		animeY = worldPos.y - 32;
		onScreen = false;
		float[] speed = { 2, 4, 8, 16 };
		Random ran = new Random();
		animeSpeed = speed[ran.nextInt(4)];
		VISION = 10;

	}

	public void render(SpriteBatch batch, float delta, Vector2 pTileMapPos, int size) {
		if (Math.abs(tileMapPos.x - pTileMapPos.x) <= VISION // 設定當物件出現在視線內時上升出現，離開時落下
				&& Math.abs(tileMapPos.y - pTileMapPos.y) <= VISION && !animeDown) {
			if (!onScreen) {
				time += delta;
				if (time > 0.02f) {
//					System.out.println("time=" + time);
					animeY += animeSpeed;
					time = 0;
				}
				if (animeY == worldPos.y) {
					onScreen = true;
				}
			}
			batch.draw(t, worldPos.x, animeY, size, size);
		} else {
			if (onScreen) {
				animeDown = true;
				time += delta;
				if (time > 0.02f) {
					animeY -= animeSpeed;
					time = 0;
				}
				if (animeY == worldPos.y - 32) {
					onScreen = false;
					animeDown = false;
				}
				batch.draw(t, worldPos.x, animeY, size, size);
			}
		}

	}

}
