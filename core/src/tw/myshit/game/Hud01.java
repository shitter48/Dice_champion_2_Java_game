package tw.myshit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud01 {
	public Stage stage;
	private Viewport viewPort;

	private Integer php;
	private Integer maxphp;
	private String pnowhp;
	private Integer score;
	private Image hpimg;

	Label healthLabel01;
	Label healthLabel02;
	Label scoreLabel01;
	Label scoreLabel02;
	Label levelLabel01;
	Label levelLabel02;

	public Hud01(SpriteBatch batch) {
		php = 30;
		maxphp = 30;
		pnowhp = php + "/" + maxphp;
		score = 0;
		hpimg = new Image(new Texture("Heart.png"));

		viewPort = new FitViewport(GameScreen01.WIDTH, GameScreen01.HEIGHT,new OrthographicCamera());
		stage = new Stage(viewPort,batch);
		
		Table table = new Table();
		table.bottom();
		table.setFillParent(true);
		
		healthLabel01 = new Label(pnowhp, new Label.LabelStyle(new BitmapFont(),Color.WHITE));
//		healthLabel02 = new Label("HP", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		scoreLabel01 = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		scoreLabel02 = new Label("Score", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		levelLabel01 = new Label(GameScreen01.maplevel.toString(), new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		levelLabel02 = new Label("MapLevel", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		
		healthLabel01.setFontScale(2);scoreLabel01.setFontScale(2);scoreLabel02.setFontScale(2);
		levelLabel01.setFontScale(2);levelLabel02.setFontScale(2);
		
		table.add(scoreLabel02).padLeft(50).padRight(300);
		table.add(hpimg).size(60,48).padLeft(80);	
		table.add(levelLabel02).padLeft(50);
		table.row();
		table.add(scoreLabel01).padBottom(10).padLeft(50).padRight(300);
		table.add(healthLabel01).padBottom(10).padLeft(80);
		table.add(levelLabel01).padLeft(50);
		
		stage.addActor(table);
		
		Skin skin = new Skin(Gdx.files.internal
				("C:\\JavaFramework\\eclipse-workspace\\MyDemo\\assets\\craftacular\\skin\\craftacular-ui.json"));
		
		TextButton button = new TextButton("HeHe", skin);
		
		button.setPosition(200, 200);
		button.setSize(100, 50);
//		stage.addActor(button);
	}
}
