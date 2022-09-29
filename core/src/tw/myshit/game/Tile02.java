package tw.myshit.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tile02 {
	private Texture t;
	public Vector2 tileMapPos;
	public Vector2 worldPos;
	private boolean onScreen;
	private float nowX;
	private float nowY;
	private float animeX;
	private float animeY;
	private float time;
	private float animeSpeed;
	private boolean animeDown;
	private final int VISION;
	private float etime;
	private float mtime;
	private boolean isArrived;
	public boolean isbattling;
	public static boolean attack;
	private Move move;
	Timer timer;
	private int i;
	private Integer level;
	

	public Tile02(Texture t, Vector2 tileMapPos, Vector2 worldPos,int level) {
		this.t = t;
		this.tileMapPos = tileMapPos;
		this.worldPos = worldPos;
		this.level = level;
		animeY = worldPos.y - 32;
		onScreen = false;
//		float[] speed = { 2, 4, 8, 16 };
		Random ran = new Random();
//		animeSpeed = speed[ran.nextInt(4)];
		VISION = 10;
		etime = 0f;
		mtime = 0.2f;
//		move = new Move();
//		timer = new Timer();
//		timer.schedule(move, 0, 500);
		isbattling = false;
		attack = false;
	}

	private class Move extends TimerTask {
		Random r = new Random();

		public void run() {
			int ran = r.nextInt(100);
			if (GameScreen01.encounter == false) {
				if (ran <= 50 && ran > 40 && TileMap01.mapw[(int) (tileMapPos.x + 1)][(int) (tileMapPos.y)] == true) { // W
					if (!isArrived) {
//					if (time > 0.02f) {
						worldPos.x -= 16;
						worldPos.y += 8;
//					time = 0;
//					}
//					if (Math.abs(worldPos.x - nowX) == 16 && Math.abs(worldPos.y - nowY) == 8) {
//						isArrived = true;
//					}
						tileMapPos.x += 1;
//					batch.draw(t, animeX, animeY, size, size);
					}
					etime = 0;
				} else if (ran <= 40 && ran > 30 && tileMapPos.y - 1 >= 0
						&& TileMap01.mapw[(int) (tileMapPos.x)][(int) (tileMapPos.y - 1)] == true) { // A
					if (!isArrived) {
//					time += delta;
//					if (time > 0.02f) {
						worldPos.x -= 16;
						worldPos.y -= 8;
//					time = 0;
//					}
//					if (Math.abs(worldPos.x - nowX) == 16 && Math.abs(worldPos.y - nowY) == 8) {
//						isArrived = true;
//					}
						tileMapPos.y -= 1;
//					batch.draw(t, animeX, animeY, size, size);
					}
					etime = 0;
				} else if (ran <= 30 && ran > 20 && tileMapPos.x - 1 >= 0
						&& TileMap01.mapw[(int) (tileMapPos.x - 1)][(int) (tileMapPos.y)] == true) { // S
					if (!isArrived) {
//					time += delta;
//					if (time > 0.02f) {
						worldPos.x += 16;
						worldPos.y -= 8;
//					time = 0;
//					}
//					if (Math.abs(worldPos.x - nowX) == 16 && Math.abs(worldPos.y - nowY) == 8) {
//						isArrived = true;
//					}
						tileMapPos.x -= 1;
//					batch.draw(t, animeX, animeY, size, size);
					}
					etime = 0;
				} else if (ran <= 20 && ran > 10
						&& TileMap01.mapw[(int) (tileMapPos.x)][(int) (tileMapPos.y + 1)] == true) { // D
					if (!isArrived) {
//				time += delta;
//				if (time > 0.02f) {
						worldPos.x += 16;
						worldPos.y += 8;
//				time = 0;
//				}
//					if (Math.abs(worldPos.x - nowX) == 16 && Math.abs(worldPos.y - nowY) == 8) {
//						isArrived = true;
//					}
						tileMapPos.y += 1;
//				batch.draw(t, animeX, animeY, size, size);
					}
				} else {

				}

				try {
					Thread.sleep(20 + (int) (Math.random() * 121));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void render(SpriteBatch batch, float delta, Vector2 pTileMapPos, int size) {
		etime += delta;
		if (pTileMapPos.x == tileMapPos.x && pTileMapPos.y == tileMapPos.y) {
			GameScreen01.encounter = true;
			Hud02.enemylevel = this.level;
			this.isbattling = true;
			if(attack == false) {
				worldPos.x = GameScreen01.player.worldPos.x - 42;
				worldPos.y = GameScreen01.player.worldPos.y + 24;
			}
			
			if(attack == true) {
				worldPos.x += 14;
				worldPos.y -= 8;
			}
			if(worldPos.x == GameScreen01.player.worldPos.x 
					&& worldPos.y == GameScreen01.player.worldPos.y) {
				attack = false;
			}
		}
		if (GameScreen01.encounter) {
//			if(i < 16) {
			
//				i++;
//			}
		}
		if (Math.abs(tileMapPos.x - pTileMapPos.x) <= VISION // 設定當物件出現在視線內時上升出現，離開時落下
				&& Math.abs(tileMapPos.y - pTileMapPos.y) <= VISION
//				&& !animeDown
		) {
//			if (!onScreen) {
//				time += delta;
//				if (time > 0.02f) {
//					animeY += animeSpeed;
//					time = 0;
//				}
//				if (animeY == worldPos.y) {
//					onScreen = true;
//				}
//			}
			batch.draw(t, worldPos.x, worldPos.y, size, size);

//		} else {
//			if (onScreen) {
//				animeDown = true;
//				time += delta;
//				if (time > 0.02f) {
//					animeY -= animeSpeed;
//					time = 0;
//				}
//				if (animeY == worldPos.y - 32) {
//					onScreen = false;
//					animeDown = false;
//				}
//				batch.draw(t, worldPos.x, animeY, size, size);
//			}

		}
//		move.run();

	}

//	public Task scheduleTask(Task task,float s) {
//		return scheduleTask(task, 0,1);
//	}

}