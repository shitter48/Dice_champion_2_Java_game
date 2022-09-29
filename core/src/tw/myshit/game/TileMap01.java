package tw.myshit.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TileMap01 {
	private final int MAP_SIZE = 20;
	private final int TILE_WIDTH = 32;
	private final int TILE_HEIGHT = 32;

	private LinkedList<Tile01> base;
	private LinkedList<Tile01> objects01;
	private LinkedList<Tile01> objects02;
	private LinkedList<Tile01> hearts;
	private LinkedList<Tile02> enemieslv1;
	private LinkedList<Tile02> enemieslv2;
	private LinkedList<Tile02> enemieslv3;
	private LinkedList<Tile02> enemieslv4;
	private LinkedList<Tile02> enemieslv5;

	public Texture grass;
	public Texture water;
	public Texture dirt;
	public Texture snow;
	public Texture tree01;
	public Texture cactus02;
	public Texture heart;
	public Texture slime01, slime02, slime03, slime04, slime05;

	public static boolean isupdated;

	private String[][] map;
	public static boolean[][] mapw, mapable;

	private int e11 = 0, e12 = 0, e21 = 0, e22 = 0, e23 = 0, e31 = 0, e32 = 0, e33 = 0, e34 = 0, e41 = 0, e42 = 0,
			e43 = 0, e44 = 0, e45 = 0, h = 0;

	public TileMap01() {
		grass = new Texture("Grass_Block.png");
		water = new Texture("Water_Block.png");
		dirt = new Texture("Dirt_Block.png");
		snow = new Texture("Snow_Block.png");
		tree01 = new Texture("tree01.png");
		cactus02 = new Texture("cactus02.png");
		heart = new Texture("Heart.png");
		slime01 = new Texture("slime.png");
		slime02 = new Texture("ugly_slime.png");
		slime03 = new Texture("Chocoslime.png");
		slime04 = new Texture("Box_slime.png");
		slime05 = new Texture("slime_king.png");

		base = new LinkedList<Tile01>(); // 地圖base tiles
		objects01 = new LinkedList<Tile01>(); // 32size的物件
		objects02 = new LinkedList<Tile01>(); // 16size的物件
		enemieslv1 = new LinkedList<Tile02>(); // 敵人物件
		enemieslv2 = new LinkedList<Tile02>();
		enemieslv3 = new LinkedList<Tile02>();
		enemieslv4 = new LinkedList<Tile02>();
		enemieslv5 = new LinkedList<Tile02>();
		hearts = new LinkedList<Tile01>(); // 事件物件
		map = new String[MAP_SIZE][MAP_SIZE];
		mapw = new boolean[MAP_SIZE][MAP_SIZE];
		mapable = new boolean[MAP_SIZE][MAP_SIZE];

		isupdated = false;

		try {
			fillMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void render(SpriteBatch batch, float delta) {
		for (Tile01 t : base) {
			t.render(batch, delta, GameScreen01.player.tileMapPos, 32);
		}
		if (!GameScreen01.encounter) {
			for (Tile01 t : objects01) {
				t.render(batch, delta, GameScreen01.player.tileMapPos, 32);
			}
			for (Tile01 t : objects02) {
				t.render(batch, delta, GameScreen01.player.tileMapPos, 16);
			}
			for (Tile01 t : hearts) {
				t.render(batch, delta, GameScreen01.player.tileMapPos, 16);
			}
		}
		for (Tile02 t : enemieslv1) {
			if (GameScreen01.encounter == false) {
				t.render(batch, delta, GameScreen01.player.tileMapPos, 20);
			} else if (GameScreen01.encounter == true && t.isbattling == true) {
				t.render(batch, delta, GameScreen01.player.tileMapPos, 20);
			}
		}
		for (Tile02 t : enemieslv2) {
			if (GameScreen01.encounter == false) {
				t.render(batch, delta, GameScreen01.player.tileMapPos, 20);
			} else if (GameScreen01.encounter == true && t.isbattling == true) {
				t.render(batch, delta, GameScreen01.player.tileMapPos, 20);
			}
		}
		for (Tile02 t : enemieslv3) {
			if (GameScreen01.encounter == false) {
				t.render(batch, delta, GameScreen01.player.tileMapPos, 20);
			} else if (GameScreen01.encounter == true && t.isbattling == true) {
				t.render(batch, delta, GameScreen01.player.tileMapPos, 20);
			}
		}
		for (Tile02 t : enemieslv4) {
			if (GameScreen01.encounter == false) {
				t.render(batch, delta, GameScreen01.player.tileMapPos, 20);
			} else if (GameScreen01.encounter == true && t.isbattling == true) {
				t.render(batch, delta, GameScreen01.player.tileMapPos, 20);
			}
		}
		for (Tile02 t : enemieslv5) {
			if (GameScreen01.encounter == false) {
				t.render(batch, delta, GameScreen01.player.tileMapPos, 32);
			} else if (GameScreen01.encounter == true && t.isbattling == true) {
				t.render(batch, delta, GameScreen01.player.tileMapPos, 32);
			}
		}
	}

	public void updateMap() {
		if (GameScreen01.maplevel == 2 && isupdated == false) {
			enemieslv1.clear();
			Random r = new Random();

			for (int row = MAP_SIZE - 2; row >= 0; row--) {
				for (int col = MAP_SIZE - 2; col >= 0; col--) {
					float x = (col - row) * (TILE_WIDTH / 2f);
					float y = (col + row) * (TILE_HEIGHT / 4f);

					int ran = r.nextInt(100);
					if (map[row][col].equals("g") && mapable[row][col] == true) {
						if (row != 9 || col != 9) {
							if (ran <= 5 && e11 <= 1) {
								enemieslv1
										.add(new Tile02(slime01, new Vector2(row, col), new Vector2(x + 7, y + 21), 1));
								e11++;
							} else if (ran > 5 && ran <= 10 && e12 <= 1) {
								enemieslv2
										.add(new Tile02(slime02, new Vector2(row, col), new Vector2(x + 7, y + 21), 2));
								e12++;
							}
						}
					} else if (map[row][col].equals("s") && mapable[row][col] == true) {
						if (row != 9 || col != 9) {
							if (ran <= 5 && e11 > 1 && e11 <= 2) {
								enemieslv1
										.add(new Tile02(slime01, new Vector2(row, col), new Vector2(x + 7, y + 21), 1));
								e11++;
							} else if (ran > 5 && ran <= 10 && e12 > 1 && e12 <= 3) {
								enemieslv2
										.add(new Tile02(slime02, new Vector2(row, col), new Vector2(x + 7, y + 21), 2));
								e12++;
							} else if (ran > 95 && h <= 1) {
								hearts.add(new Tile01(heart, new Vector2(row, col), new Vector2(x + 8, y + 18)));
								h++;
								mapable[row][col] = false;
							}
						}
					}
				}
			}
			isupdated = true;
		} else if (GameScreen01.maplevel == 3 && isupdated == false) {
			enemieslv1.clear();
			enemieslv2.clear();
			Random r = new Random();
			for (int row = MAP_SIZE - 2; row >= 0; row--) {
				for (int col = MAP_SIZE - 2; col >= 0; col--) {
					float x = (col - row) * (TILE_WIDTH / 2f);
					float y = (col + row) * (TILE_HEIGHT / 4f);

					int ran = r.nextInt(100);
					if (map[row][col].equals("g") && mapable[row][col] == true) {
						if (row != 9 || col != 9) {
							if (ran <= 5 && e21 == 0) {
								enemieslv1
										.add(new Tile02(slime01, new Vector2(row, col), new Vector2(x + 7, y + 21), 1));
								e21++;
							} else if (ran > 5 && ran <= 10 && e22 == 0) {
								enemieslv2
										.add(new Tile02(slime02, new Vector2(row, col), new Vector2(x + 7, y + 21), 2));
								e22++;
							} else if (ran > 10 && ran <= 15 && e23 <= 2) {
								enemieslv3
										.add(new Tile02(slime03, new Vector2(row, col), new Vector2(x + 7, y + 21), 3));
								e23++;
							} else if (ran > 95 && h <= 2) {
								hearts.add(new Tile01(heart, new Vector2(row, col), new Vector2(x + 8, y + 18)));
								h++;
								mapable[row][col] = false;
							}
						}
					} else if (map[row][col].equals("s") && mapable[row][col] == true) {
						if (row != 9 || col != 9) {
							if (ran <= 5 && e21 == 1) {
								enemieslv1
										.add(new Tile02(slime01, new Vector2(row, col), new Vector2(x + 7, y + 21), 1));
								e21++;
							} else if (ran > 5 && ran <= 10 && e22 == 1) {
								enemieslv2
										.add(new Tile02(slime02, new Vector2(row, col), new Vector2(x + 7, y + 21), 2));
								e22++;
							} else if (ran > 10 && ran <= 15 && e23 > 2 && e23 <= 4) {
								enemieslv3
										.add(new Tile02(slime03, new Vector2(row, col), new Vector2(x + 7, y + 21), 3));
								e23++;
							}
						}
					}
				}
			}
			isupdated = true;
		} else if (GameScreen01.maplevel == 4 && isupdated == false) {
			enemieslv1.clear();
			enemieslv2.clear();
			enemieslv3.clear();
			Random r = new Random();
			for (int row = MAP_SIZE - 2; row >= 0; row--) {
				for (int col = MAP_SIZE - 2; col >= 0; col--) {
					float x = (col - row) * (TILE_WIDTH / 2f);
					float y = (col + row) * (TILE_HEIGHT / 4f);
					int ran = r.nextInt(100);
					if (map[row][col].equals("g") && mapable[row][col] == true) {
						if (row != 9 || col != 9) {
							if (ran <= 5 && e31 == 0) {
								enemieslv1
										.add(new Tile02(slime01, new Vector2(row, col), new Vector2(x + 7, y + 21), 1));
								e31++;
							} else if (ran > 5 && ran <= 10 && e32 == 0) {
								enemieslv2
										.add(new Tile02(slime02, new Vector2(row, col), new Vector2(x + 7, y + 21), 2));
								e32++;
							} else if (ran > 10 && ran <= 15 && e33 == 0) {
								enemieslv3
										.add(new Tile02(slime03, new Vector2(row, col), new Vector2(x + 7, y + 21), 3));
								e33++;
							} else if (ran > 15 && ran <= 20 && e34 <= 2) {
								enemieslv4
										.add(new Tile02(slime04, new Vector2(row, col), new Vector2(x + 7, y + 21), 4));
								e34++;
							}
						}
					} else if (map[row][col].equals("s") && mapable[row][col] == true) {
						if (row != 9 || col != 9) {
//							if (ran <= 5 && e41 == 1) {
//								enemieslv1.add(new Tile02(slime01, new Vector2(row, col), new Vector2(x + 7, y + 21),1));
//								e41++;
//							} 
							if (ran > 5 && ran <= 10 && e32 == 1) {
								enemieslv2
										.add(new Tile02(slime02, new Vector2(row, col), new Vector2(x + 7, y + 21), 2));
								e32++;
							} else if (ran > 10 && ran <= 15 && e33 == 1) {
								enemieslv3
										.add(new Tile02(slime03, new Vector2(row, col), new Vector2(x + 7, y + 21), 3));
								e33++;
							} else if (ran > 15 && ran <= 20 && e34 > 2 && e34 <= 5) {
								enemieslv4
										.add(new Tile02(slime04, new Vector2(row, col), new Vector2(x + 7, y + 21), 4));
								e34++;
							} else if (ran > 95 && h <= 3) {
								hearts.add(new Tile01(heart, new Vector2(row, col), new Vector2(x + 8, y + 18)));
								h++;
								mapable[row][col] = false;
							}
						}
					}
				}
			}
			isupdated = true;
		} else if (GameScreen01.maplevel == 5 && isupdated == false) {
			enemieslv1.clear();
			enemieslv2.clear();
			enemieslv3.clear();
			enemieslv4.clear();
			Random r = new Random();
			for (int row = MAP_SIZE - 2; row >= 0; row--) {
				for (int col = MAP_SIZE - 2; col >= 0; col--) {
					float x = (col - row) * (TILE_WIDTH / 2f);
					float y = (col + row) * (TILE_HEIGHT / 4f);
					int ran = r.nextInt(100);
					if (map[row][col].equals("g") && mapable[row][col] == true) {
						if (row != 9 || col != 9) {
							if (ran <= 5 && e41 == 0) {
								enemieslv1
										.add(new Tile02(slime01, new Vector2(row, col), new Vector2(x + 7, y + 21), 1));
								e41++;
							} else if (ran > 5 && ran <= 10 && e42 <= 1) {
								enemieslv2
										.add(new Tile02(slime02, new Vector2(row, col), new Vector2(x + 7, y + 21), 2));
								e42++;
							} else if (ran > 10 && ran <= 15 && e43 <= 1) {
								enemieslv3
										.add(new Tile02(slime03, new Vector2(row, col), new Vector2(x + 7, y + 21), 3));
								e43++;
							} else if (ran > 15 && ran <= 20 && e44 <= 1) {
								enemieslv4
										.add(new Tile02(slime04, new Vector2(row, col), new Vector2(x + 7, y + 21), 4));
								e44++;
							} else if (ran > 15 && ran <= 20 && e45 == 0) {
								enemieslv5
										.add(new Tile02(slime05, new Vector2(row, col), new Vector2(x - 5, y + 21), 5));
								e45++;
							} else if (ran > 95 && h <= 4) {
								hearts.add(new Tile01(heart, new Vector2(row, col), new Vector2(x + 8, y + 18)));
								h++;
								mapable[row][col] = false;
							}
						}
					} else if (map[row][col].equals("s") && mapable[row][col] == true) {
						if (row != 9 || col != 9) {
							if (ran <= 5 && e41 == 1) {
								enemieslv1
										.add(new Tile02(slime01, new Vector2(row, col), new Vector2(x + 7, y + 21), 1));
								e41++;
							} else if (ran > 5 && ran <= 10 && e42 == 2) {
								enemieslv2
										.add(new Tile02(slime02, new Vector2(row, col), new Vector2(x + 7, y + 21), 2));
								e42++;
							} else if (ran > 10 && ran <= 15 && e43 == 2) {
								enemieslv3
										.add(new Tile02(slime03, new Vector2(row, col), new Vector2(x + 7, y + 21), 3));
								e43++;
							} else if (ran > 15 && ran <= 20 && e44 == 2) {
								enemieslv4
										.add(new Tile02(slime04, new Vector2(row, col), new Vector2(x + 7, y + 21), 4));
								e44++;
							}
						}
					}
				}
			}
			isupdated = true;
		}
	}

	public void fillMap() throws IOException {
//		
//		for (int row = MAP_SIZE - 2; row >= 0; row--) {
//			for (int col = MAP_SIZE - 2; col >= 0; col--) {
//				float x = (col - row) * (TILE_WIDTH / 2f);
//				float y = (col + row) * (TILE_HEIGHT/ 4f);
//				base.add(new Tile01(grass, new Vector2(row, col), new Vector2(x, y)));
//			}
//		}
		FileHandle fh1 = Gdx.files.internal("C:\\JavaFramework\\eclipse-workspace\\MyDemo\\assets\\Base_River.txt");
		FileHandle fh2 = Gdx.files.internal("C:\\JavaFramework\\eclipse-workspace\\MyDemo\\assets\\Base_Snow.txt");
		BufferedReader br = new BufferedReader(new FileReader(fh2.path()));
		String s = "";
		int count = 0;
		while ((s = br.readLine()) != null) {
			map[count] = s.split(" ");
			count++;
		}
		br.close();
		Random r = new Random();
		int E1 = 0;
		for (int row = MAP_SIZE - 2; row >= 0; row--) {
			for (int col = MAP_SIZE - 2; col >= 0; col--) {
				float x = (col - row) * (TILE_WIDTH / 2f);
				float y = (col + row) * (TILE_HEIGHT / 4f);

				if (map[row][col].equals("g")) {
					base.add(new Tile01(grass, new Vector2(row, col), new Vector2(x, y)));
					mapw[row][col] = true;
					mapable[row][col] = true;
					int ran = r.nextInt(100);
					if (row != 9 || col != 9) {
						if (ran > 90) {
							objects01.add(new Tile01(tree01, new Vector2(row, col), new Vector2(x - 1, y + 20)));
							mapw[row][col] = false;
							mapable[row][col] = false;
						} else if (ran < 5) {
							objects02.add(new Tile01(cactus02, new Vector2(row, col), new Vector2(x + 7, y + 21)));
							mapable[row][col] = false;
						} else if (5 <= ran && ran < 10 && h == 0) {
							hearts.add(new Tile01(heart, new Vector2(row, col), new Vector2(x + 8, y + 18)));
							h++;
							mapable[row][col] = false;
						} else if (ran >= 10 && ran < 15 && E1 <= 2) {
							enemieslv1.add(new Tile02(slime01, new Vector2(row, col), new Vector2(x + 7, y + 21), 1));
							E1++;
						}
					}
				} else if (map[row][col].equals("s")) {
					base.add(new Tile01(snow, new Vector2(row, col), new Vector2(x, y)));
					mapw[row][col] = true;
					mapable[row][col] = true;
					int ran = r.nextInt(100);
					if (ran >= 10 && ran < 15 && E1 <= 4 && E1 > 2) {
						enemieslv1.add(new Tile02(slime01, new Vector2(row, col), new Vector2(x + 7, y + 21), 1));
						E1++;
//					} else if (5 <= ran && ran < 10 && h <= 4 && h > 2) {
//						hearts.add(new Tile01(heart, new Vector2(row, col), new Vector2(x + 8, y + 18)));
//						h++;
//						mapable[row][col] = false;
					}
				}
			}
		}
	}
}
