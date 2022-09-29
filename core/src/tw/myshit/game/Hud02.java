package tw.myshit.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud02 {
	public static Stage stage;
	private Viewport viewPort;

	public static Integer enemylevel;

	public static Integer php;
	public static Integer maxphp;
	private String pnowhp;
	private Image hpimg01;
	private Image hpimg02;
	private Image sword;
	private Image skull;
	private Integer ehp, ehp1, ehp2, ehp3, ehp4, ehp5;
	private String ename, ename1, ename2, ename3, ename4, ename5;
	private Integer eatk, eatk1, eatk2, eatk3, eatk4, eatk5;
	private Integer easinit, eas1, eas2, eas3, eas4, eas5;
	public static Integer easnow;
	private Table tablep, tablee, tabled;

	private Label phpLabel;

	private Label enameLabel;
	private Label ehpLabel;
	private Label eatkLabel;
	private Label easLabel;

	private Skin skin01;
	private Skin skin02;
	private backgroundbot bgb;
	private backgroundtop bgt;

	private TextButton roll, thr;
	private Image d1, d2, d3, d4, d5, d6;
	private boolean d1chosen, d2chosen, d3chosen, d4chosen, d5chosen, d6chosen, rolled, thrown, init;
	private Dice1 dice1;
	private Dice2 dice2;
	private Dice3 dice3;
	private Dice4 dice4;
	private Dice5 dice5;
	private Dice6 dice6;
	private Random r;
	private ArrayList<Integer> points;

	public Hud02(SpriteBatch batch, Vector2 pTileMapPos) {
		php = 30;
		maxphp = 30;
		pnowhp = php + "/" + maxphp;

		hpimg01 = new Image(new Texture("Heart.png"));
		hpimg02 = new Image(new Texture("Heart.png"));
		sword = new Image(new Texture("sword.png"));
		skull = new Image(new Texture("skull.png"));

		viewPort = new FitViewport(GameScreen01.WIDTH, GameScreen01.HEIGHT, new OrthographicCamera());
		stage = new Stage(viewPort, batch);
		TextureAtlas atlas01 = new TextureAtlas(Gdx.files.internal(
				"C:\\JavaFramework\\eclipse-workspace\\MyDemo\\assets\\craftacular\\skin\\craftacular-ui.atlas"));
		skin01 = new Skin(
				Gdx.files.internal(
						"C:\\JavaFramework\\eclipse-workspace\\MyDemo\\assets\\craftacular\\skin\\craftacular-ui.json"),
				atlas01);

		TextureAtlas atlas02 = new TextureAtlas(Gdx.files.internal(
				"C:\\JavaFramework\\eclipse-workspace\\MyDemo\\assets\\terra-mother\\skin\\terra-mother-ui.atlas"));
		skin02 = new Skin(Gdx.files.internal(
				"C:\\JavaFramework\\eclipse-workspace\\MyDemo\\assets\\terra-mother\\skin\\terra-mother-ui.json"),
				atlas02);
//		TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture("C:\\JavaFramework\\eclipse-workspace\\MyDemo\\assets\\terra-mother\\raw\\window.png")));
//		textureRegionDrawableBg.setTopHeight(300);
//		textureRegionDrawableBg.setBottomHeight(300);
		bgb = new backgroundbot();
		bgt = new backgroundtop();

		// 連接資料庫

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dicechampion2", prop);
			String sql = "SELECT * FROM enemies";
			PreparedStatement pstm = con.prepareStatement(sql);
//					pstm.setString(1,enemylevel.toString());  //抓取遭遇敵人的等級作為ID搜尋
			ResultSet rs = pstm.executeQuery();
			rs.next();
			ehp1 = rs.getInt("hp");
			ename1 = rs.getString("name");
			eatk1 = rs.getInt("atk");
			eas1 = rs.getInt("atks");
			rs.next();
			ehp2 = rs.getInt("hp");
			ename2 = rs.getString("name");
			eatk2 = rs.getInt("atk");
			eas2 = rs.getInt("atks");
			rs.next();
			ehp3 = rs.getInt("hp");
			ename3 = rs.getString("name");
			eatk3 = rs.getInt("atk");
			eas3 = rs.getInt("atks");
			rs.next();
			ehp4 = rs.getInt("hp");
			ename4 = rs.getString("name");
			eatk4 = rs.getInt("atk");
			eas4 = rs.getInt("atks");
			rs.next();
			ehp5 = rs.getInt("hp");
			ename5 = rs.getString("name");
			eatk5 = rs.getInt("atk");
			eas5 = rs.getInt("atks");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		roll = new TextButton("Roll!", skin01);
		thr = new TextButton("Throw!", skin01);

		rolled = false;
		thrown = false;
		init = false;

		dice1 = new Dice1();
		dice2 = new Dice2();
		dice3 = new Dice3();
		dice4 = new Dice4();
		dice5 = new Dice5();
		dice6 = new Dice6();

		points = new ArrayList();

		roll.setPosition(680, 80);
		roll.setSize(200, 50);
		thr.setPosition(680, 20);
		thr.setSize(200, 50);
		stage.addActor(roll);
		stage.addActor(thr);

//		roll.addListener(new DragListener() {
//			public void drag(InputEvent event, float x, float y, int pointer) 
//		    {
//				roll.moveBy(x - tablep.getWidth() / 2, y - roll.getHeight() / 2);
//		    }
//		});
		Gdx.input.setInputProcessor(stage); // 設這個才能接收輸入
	}

	public void update() {

		Table tablep = new Table();
		tablep.bottom();
		tablep.left();
		tablep.setFillParent(true);
		tablep.setSkin(skin01);
		tablep.setBackground(bgb);

		Table tablee = new Table();
		tablee.top();
		tablee.setFillParent(true);
		tablee.setSkin(skin01);
		tablee.setBackground(bgt);

		Table tabled = new Table();
		tabled.bottom();
		tablee.setFillParent(true);
		if (d1chosen == true) {
			tabled.add(d1).size(80, 80).padBottom(150).padLeft(700);
//			points.add(dice1.point);
		} else if (d1chosen == false) {
			tabled.add(d1).size(80, 80).padBottom(100).padLeft(700);
//			points.remove(dice1.point);
		}
		if (d2chosen == true) {
			tabled.add(d2).size(80, 80).padBottom(150).padLeft(25);
//			points.add(dice2.point);
		} else if (d2chosen == false) {
			tabled.add(d2).size(80, 80).padBottom(100).padLeft(25);
//			points.remove(dice2.point);
		}
		if (d3chosen == true) {
			tabled.add(d3).size(80, 80).padBottom(150).padLeft(25);
//			points.add(dice3.point);
		} else if (d3chosen == false) {
			tabled.add(d3).size(80, 80).padBottom(100).padLeft(25);
//			points.remove(dice3.point);
		}
		if (d4chosen == true) {
			tabled.add(d4).size(80, 80).padBottom(150).padLeft(25);
//			points.add(dice4.point);
		} else if (d4chosen == false) {
			tabled.add(d4).size(80, 80).padBottom(100).padLeft(25);
//			points.remove(dice4.point);
		}
		if (d5chosen == true) {
			tabled.add(d5).size(80, 80).padBottom(150).padLeft(25);
//			points.add(dice5.point);
		} else if (d5chosen == false) {
			tabled.add(d5).size(80, 80).padBottom(100).padLeft(25);
//			points.remove(dice5.point);
		}
		if (d6chosen == true) {
			tabled.add(d6).size(80, 80).padBottom(150).padLeft(25);
//			points.add(dice6.point);
		} else if (d6chosen == false) {
			tabled.add(d6).size(80, 80).padBottom(100).padLeft(25);
//			points.remove(dice6.point);
		}
		r = new Random();

		roll.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (rolled == false) {
					rolled = true;
					thrown = false;
					d1chosen = false;
					d2chosen = false;
					d3chosen = false;
					d4chosen = false;
					d5chosen = false;
					d6chosen = false;
					int ran1 = r.nextInt(6) + 1;
					int ran2 = r.nextInt(6) + 1;
					int ran3 = r.nextInt(6) + 1;
					int ran4 = r.nextInt(6) + 1;
					int ran5 = r.nextInt(6) + 1;
					int ran6 = r.nextInt(6) + 1;

					switch (ran1) {
					case 1:
						d1 = new Image(new Texture("dice1.png"));
						dice1.setPoint(1);
						break;
					case 2:
						d1 = new Image(new Texture("dice2.png"));
						dice1.setPoint(2);
						break;
					case 3:
						d1 = new Image(new Texture("dice3.png"));
						dice1.setPoint(3);
						break;
					case 4:
						d1 = new Image(new Texture("dice4.png"));
						dice1.setPoint(4);
						break;
					case 5:
						d1 = new Image(new Texture("dice5.png"));
						dice1.setPoint(5);
						break;
					case 6:
						d1 = new Image(new Texture("dice6.png"));
						dice1.setPoint(6);
						break;
					}
					switch (ran2) {
					case 1:
						d2 = new Image(new Texture("dice1.png"));
						dice2.setPoint(1);
						break;
					case 2:
						d2 = new Image(new Texture("dice2.png"));
						dice2.setPoint(2);
						break;
					case 3:
						d2 = new Image(new Texture("dice3.png"));
						dice2.setPoint(3);
						break;
					case 4:
						d2 = new Image(new Texture("dice4.png"));
						dice2.setPoint(4);
						break;
					case 5:
						d2 = new Image(new Texture("dice5.png"));
						dice2.setPoint(5);
						break;
					case 6:
						d2 = new Image(new Texture("dice6.png"));
						dice2.setPoint(6);
						break;
					}
					switch (ran3) {
					case 1:
						d3 = new Image(new Texture("dice1.png"));
						dice3.setPoint(1);
						break;
					case 2:
						d3 = new Image(new Texture("dice2.png"));
						dice3.setPoint(2);
						break;
					case 3:
						d3 = new Image(new Texture("dice3.png"));
						dice3.setPoint(3);
						break;
					case 4:
						d3 = new Image(new Texture("dice4.png"));
						dice3.setPoint(4);
						break;
					case 5:
						d3 = new Image(new Texture("dice5.png"));
						dice3.setPoint(5);
						break;
					case 6:
						d3 = new Image(new Texture("dice6.png"));
						dice3.setPoint(6);
						break;
					}
					switch (ran4) {
					case 1:
						d4 = new Image(new Texture("dice1.png"));
						dice4.setPoint(1);
						break;
					case 2:
						d4 = new Image(new Texture("dice2.png"));
						dice4.setPoint(2);
						break;
					case 3:
						d4 = new Image(new Texture("dice3.png"));
						dice4.setPoint(3);
						break;
					case 4:
						d4 = new Image(new Texture("dice4.png"));
						dice4.setPoint(4);
						break;
					case 5:
						d4 = new Image(new Texture("dice5.png"));
						dice4.setPoint(5);
						break;
					case 6:
						d4 = new Image(new Texture("dice6.png"));
						dice4.setPoint(6);
						break;
					}
					switch (ran5) {
					case 1:
						d5 = new Image(new Texture("dice1.png"));
						dice5.setPoint(1);
						break;
					case 2:
						d5 = new Image(new Texture("dice2.png"));
						dice5.setPoint(2);
						break;
					case 3:
						d5 = new Image(new Texture("dice3.png"));
						dice5.setPoint(3);
						break;
					case 4:
						d5 = new Image(new Texture("dice4.png"));
						dice5.setPoint(4);
						break;
					case 5:
						d5 = new Image(new Texture("dice5.png"));
						dice5.setPoint(5);
						break;
					case 6:
						d5 = new Image(new Texture("dice6.png"));
						dice5.setPoint(6);
						break;
					}
					switch (ran6) {
					case 1:
						d6 = new Image(new Texture("dice1.png"));
						dice6.setPoint(1);
						break;
					case 2:
						d6 = new Image(new Texture("dice2.png"));
						dice6.setPoint(2);
						break;
					case 3:
						d6 = new Image(new Texture("dice3.png"));
						dice6.setPoint(3);
						break;
					case 4:
						d6 = new Image(new Texture("dice4.png"));
						dice6.setPoint(4);
						break;
					case 5:
						d6 = new Image(new Texture("dice5.png"));
						dice6.setPoint(5);
						break;
					case 6:
						d6 = new Image(new Texture("dice6.png"));
						dice6.setPoint(6);
						break;
					}
					d1.addListener(new ClickListener() {
						@Override
						public void clicked(InputEvent event, float x, float y) {
							for (Integer i : points) {
								System.out.println(i);
							}
							if (d1chosen == false) {
								d1chosen = true;
								points.add(dice1.point);
								System.out.println("dice1=" + dice1.point);
							} else if (d1chosen == true) {
								d1chosen = false;
								points.remove(dice1.point);
							}
						}
					});
					d2.addListener(new ClickListener() {

						@Override
						public void clicked(InputEvent event, float x, float y) {
							for (Integer i : points) {
								System.out.println(i);
							}
							if (d2chosen == false) {
								d2chosen = true;
								points.add(dice2.point);
								System.out.println("dice2=" + dice2.point);
							} else if (d2chosen == true) {
								d2chosen = false;
								points.remove(dice2.point);
							}
						}
					});
					d3.addListener(new ClickListener() {

						@Override
						public void clicked(InputEvent event, float x, float y) {
							for (Integer i : points) {
								System.out.println(i);
							}
							if (d3chosen == false) {
								d3chosen = true;
								points.add(dice3.point);
								System.out.println("dice3=" + dice3.point);
							} else if (d3chosen == true) {
								d3chosen = false;
								points.remove(dice3.point);
							}
						}
					});
					d4.addListener(new ClickListener() {

						@Override
						public void clicked(InputEvent event, float x, float y) {
							for (Integer i : points) {
								System.out.println(i);
							}
							if (d4chosen == false) {
								d4chosen = true;
								points.add(dice4.point);
								System.out.println("dice4=" + dice4.point);
							} else if (d4chosen == true) {
								d4chosen = false;
								points.remove(dice4.point);
							}
						}
					});
					d5.addListener(new ClickListener() {

						@Override
						public void clicked(InputEvent event, float x, float y) {
							for (Integer i : points) {
								System.out.println(i);
							}
							if (d5chosen == false) {
								d5chosen = true;
								points.add(dice5.point);
								System.out.println("dice5=" + dice5.point);
							} else if (d5chosen == true) {
								d5chosen = false;
								points.remove(dice5.point);
							}
						}
					});
					d6.addListener(new ClickListener() {

						@Override
						public void clicked(InputEvent event, float x, float y) {
							for (Integer i : points) {
								System.out.println(i);
							}
							if (d6chosen == false) {
								d6chosen = true;
								points.add(dice6.point);
								System.out.println("dice6=" + dice6.point);
							} else if (d6chosen == true) {
								d6chosen = false;
								points.remove(dice6.point);
							}
						}
					});
				}
			}
		});

		thr.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Collections.sort(points, Collections.reverseOrder());
				System.out.println(points.get(0));
				System.out.println(points.size());
				if (points.size() == 0 || points.size() == 4 || points.size() == 6) {
					if (points.get(0) % 2 == 0) {
						ehp -= points.get(0);
					} else if (points.get(0) % 2 == 1) {
						if (php + points.get(0) < maxphp) {
							php += points.get(0);
						} else if (php + points.get(0) >= maxphp) {
							php = maxphp;
						}
					}
				} else if (points.size() == 2) {
					if (points.get(0) == points.get(1)) {
						ehp -= points.get(0) * 2;
					} else {
						if (points.get(0) % 2 == 0) {
							ehp -= points.get(0);
						} else if (points.get(0) % 2 == 1) {
							if (php + points.get(0) < maxphp) {
								php += points.get(0);
							} else if (php + points.get(0) >= maxphp) {
								php = maxphp;
							}
						}
					}
				} else if (points.size() == 3) {
					if (points.get(0) == points.get(1) && points.get(1) == points.get(2)) {
						ehp -= points.get(0) * 4;
					} else if (points.get(0) == (points.get(1) + 1) && points.get(1) == (points.get(2) + 1)) {
						if ((php + points.get(1) * 2) < maxphp) {
							php += points.get(1) * 2;
						} else if ((php + points.get(1) * 2) >= maxphp) {
							php = maxphp;
						}
					} else {
						if (points.get(0) % 2 == 0) {
							ehp -= points.get(0);
						} else if (points.get(0) % 2 == 1) {
							if (php + points.get(0) < maxphp) {
								php += points.get(0);
							} else if (php + points.get(0) >= maxphp) {
								php = maxphp;
							}
						}
					}
				} else if (points.size() == 5) {

				}
//				if (d1chosen == true && thrown == false) {
//					if (dice1.point % 2 == 0) {
//						ehp -= dice1.point;
//					} else if (dice1.point % 2 == 1) {
//						if(php + dice1.point < maxphp) {
//							php += dice1.point;
//						}else if(php + dice1.point >= maxphp) {
//							php = maxphp;
//						}						
//					}
//					thrown = true;
//					rolled = false;
//					easnow -= 1;
//					if(easnow == 0) {
//						php -= eatk;
//						easnow = easinit;
//						Tile02.attack = true;
//					}
//				}
			}
		});
		if (thrown == true) {
			tabled.clear();
		}
		stage.addActor(tabled);

		phpLabel = new Label(php + "/" + maxphp, new Label.LabelStyle(new BitmapFont(), Color.BLACK));

		if (init == false) {
			init = true;
			switch (enemylevel) {
			case 1:
				ename = ename1;
				ehp = ehp1;
				eatk = eatk1;
				easnow = eas1;
				easinit = eas1;
				break;
			case 2:
				ename = ename2;
				ehp = ehp2;
				eatk = eatk2;
				easnow = eas2;
				easinit = eas2;
				break;
			case 3:
				ename = ename3;
				ehp = ehp3;
				eatk = eatk3;
				easnow = eas3;
				easinit = eas3;
				break;
			case 4:
				ename = ename4;
				ehp = ehp4;
				eatk = eatk4;
				easnow = eas4;
				easinit = eas4;
				break;
			case 5:
				ename = ename5;
				ehp = ehp5;
				eatk = eatk5;
				easnow = eas5;
				easinit = eas5;
				break;
			}
		}
		enameLabel = new Label(ename, new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		ehpLabel = new Label(ehp.toString(), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		eatkLabel = new Label(eatk.toString(), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		easLabel = new Label(easnow.toString(), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		phpLabel.setFontScale(3);
		enameLabel.setFontScale(3);
		ehpLabel.setFontScale(3);
		eatkLabel.setFontScale(3);
		easLabel.setFontScale(3);

		tablep.add(hpimg01).size(60, 48).padBottom(15).padLeft(75); // 玩家數據
		tablep.add(phpLabel).padBottom(15).padLeft(0);

		tablee.add(enameLabel).padTop(10).padRight(50); // 敵人數據
		tablee.add(hpimg02).size(60, 48).padTop(10).padLeft(30);
		tablee.add(ehpLabel).padTop(10).padLeft(15);
		tablee.add(sword).size(80, 60).padTop(10).padLeft(50);
		tablee.add(eatkLabel).padTop(10).padLeft(0);
		tablee.add(skull).size(48, 48).padTop(10).padLeft(50);
		tablee.add(easLabel).padTop(10).padLeft(15);

		stage.addActor(tablep);
		stage.addActor(tablee);
	}

	public static class Dice1 {
		private Integer point;

		public Dice1() {

		}

		public Integer getPoint() {
			return point;
		}

		public void setPoint(Integer point) {
			this.point = point;
		}
	}

	public static class Dice2 {
		public Integer point;

		public Dice2() {
		}

		public Integer getPoint() {
			return point;
		}

		public void setPoint(Integer point) {
			this.point = point;
		}
	}

	public static class Dice3 {
		public Integer point;

		public Dice3() {

		}

		public Integer getPoint() {
			return point;
		}

		public void setPoint(Integer point) {
			this.point = point;
		}
	}

	public static class Dice4 {
		public Integer point;

		public Dice4() {

		}

		public Integer getPoint() {
			return point;
		}

		public void setPoint(Integer point) {
			this.point = point;
		}
	}

	public static class Dice5 {
		public Integer point;

		public Dice5() {

		}

		public Integer getPoint() {
			return point;
		}

		public void setPoint(Integer point) {
			this.point = point;
		}
	}

	public static class Dice6 {
		public Integer point;

		public Dice6() {

		}

		public Integer getPoint() {
			return point;
		}

		public void setPoint(Integer point) {
			this.point = point;
		}
	}

	public static class backgroundbot extends BaseDrawable {

		public backgroundbot() {

		}

		@Override
		public void draw(Batch batch, float x, float y, float width, float height) {

			batch.draw(new Texture(
					"C:\\JavaFramework\\eclipse-workspace\\MyDemo\\assets\\comic\\raw\\select-box-big-list.9.png"), 30,
					y, 300, 80);

		}
	}

	public static class backgroundtop extends BaseDrawable {

		public backgroundtop() {

		}

		@Override
		public void draw(Batch batch, float x, float y, float width, float height) {

			batch.draw(new Texture(
					"C:\\JavaFramework\\eclipse-workspace\\MyDemo\\assets\\comic\\raw\\select-box-big-list.9.png"), 30,
					635, 900, 80);

		}
	}

}
