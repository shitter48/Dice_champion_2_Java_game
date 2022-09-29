package tw.myshit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import tw.myshit.isometric.Entity;

public class Player01 implements Entity {

	private Texture img;
	public Vector2 worldPos;
	public Vector2 tileMapPos;
	private float time;
	private float ptime;
	private boolean isArrived;
	private float nowx, nowy;
	private int i;

	public Player01() {
		img = new Texture(Gdx.files.internal("C:\\JavaFramework\\eclipse-workspace\\MyDemo\\assets\\diceman.png"));
		worldPos = new Vector2(1, 164);
		tileMapPos = new Vector2(9, 9);
		time = 0.5f;
		ptime = 0;
		isArrived = false;
		nowx = worldPos.x;
		nowy = worldPos.y;
		i = 0;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(img, worldPos.x, worldPos.y, 32, 32);
	}

	@Override
	public void update(SpriteBatch batch, float delta) {
		time += delta;
		if (time >= 0.5f) {
			move(batch, delta);
//			time = 0;
		}

	}

	private void move(SpriteBatch batch, float delta) {

		if (GameScreen01.encounter == false) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.W)
					&& TileMap01.mapw[(int) (tileMapPos.x + 1)][(int) (tileMapPos.y)] == true) {
//			for(int i =0;i<32;i++) {
//			worldPos.x -= 0.5;
//			worldPos.y += 0.25;
//			}
//			ptime = 0;
				isArrived = false;
//				nowx = worldPos.x;
//				nowy = worldPos.y;
				do {
					ptime += delta;
					if (ptime >= 2f) {
//					System.out.println("ptime=" + ptime);

						worldPos.x -= 2;
						worldPos.y += 1;

						ptime = 0f;
//					batch.draw(img, worldPos.x, worldPos.y, 32, 32);
					}

					if ((nowx - worldPos.x) == 16 && (worldPos.y - nowy) == 8) {
						isArrived = true;
						nowx = worldPos.x;
						nowy = worldPos.y;
					}
				} while (!isArrived);

				tileMapPos.x += 1;
//			ptime = 0;
				System.out.println(tileMapPos.x + ":" + tileMapPos.y);
			} else if (Gdx.input.isKeyJustPressed(Input.Keys.A) && tileMapPos.y - 1 >= 0
					&& TileMap01.mapw[(int) (tileMapPos.x)][(int) (tileMapPos.y - 1)] == true) {
				isArrived = false;
//				nowx = worldPos.x;
//				nowy = worldPos.y;
				do {
					time += delta;
					if (time > 2f) {
						worldPos.x -= 4 / 2;
						worldPos.y -= 2 / 2;
						time = 0;
					}

					if ((nowx - worldPos.x) == 16 && (nowy - worldPos.y) == 8) {
						isArrived = true;
						nowx = worldPos.x;
						nowy = worldPos.y;
					}
				} while (!isArrived);

				tileMapPos.y -= 1;
				time = 0;
				System.out.println(tileMapPos.x + ":" + tileMapPos.y);
			} else if (Gdx.input.isKeyJustPressed(Input.Keys.S) && tileMapPos.x - 1 >= 0
					&& TileMap01.mapw[(int) (tileMapPos.x - 1)][(int) (tileMapPos.y)] == true) {
				isArrived = false;
//				nowx = worldPos.x;
//				nowy = worldPos.y;
				do {
					time += delta;
					if (time > 2f) {
						worldPos.x += 4 / 2;
						worldPos.y -= 2 / 2;
						time = 0;
					}

					if ((worldPos.x - nowx) == 16 && (nowy - worldPos.y) == 8) {
						isArrived = true;
						nowx = worldPos.x;
						nowy = worldPos.y;
					}
				} while (!isArrived);

				tileMapPos.x -= 1;

				time = 0;
				System.out.println(tileMapPos.x + ":" + tileMapPos.y);
			} else if (Gdx.input.isKeyJustPressed(Input.Keys.D)
					&& TileMap01.mapw[(int) (tileMapPos.x)][(int) (tileMapPos.y + 1)] == true) {
				isArrived = false;
//				nowx = worldPos.x;
//				nowy = worldPos.y;
				do {
					time += delta;
					if (time > 2f) {
						worldPos.x += 4 / 2;
						worldPos.y += 2 / 2;
						time = 0;
					}

					if ((worldPos.x - nowx) == 16 && (worldPos.y - nowy) == 8) {
						isArrived = true;
						nowx = worldPos.x;
						nowy = worldPos.y;
					}
				} while (!isArrived);

				tileMapPos.y += 1;
				time = 0;
				System.out.println(tileMapPos.x + ":" + tileMapPos.y);

			}
		}
//		if(GameScreen01.encounter) {
//			if(i < 16) {
//				worldPos.x += 2;
//				worldPos.y -= 1;
//				i++;
//			}
//		}
	}
}
