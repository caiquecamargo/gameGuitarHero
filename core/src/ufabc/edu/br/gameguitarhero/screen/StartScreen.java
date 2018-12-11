package ufabc.edu.br.gameguitarhero.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import ufabc.edu.br.gameguitarhero.AssetsManagement.AssetManagement;
import ufabc.edu.br.gameguitarhero.util.Commands;
import ufabc.edu.br.gameguitarhero.util.Parameters;

public class StartScreen extends AbstractScreen{
	private Texture     texture;
	private SpriteBatch spriteBatch;
	private Matrix4     viewMatrix;
	private Matrix4     tranMatrix;
	private BitmapFont 	font;
	private boolean 	loaded = false;
	private int 		progress;
	private float 		time=0;
	private boolean 	visible=false;
	private Music   	music;

	public StartScreen(String id) {
		super(id);
		spriteBatch = new SpriteBatch();
		texture     = new Texture(Gdx.files.internal("textures/GuitarFuckingMonster.jpg"));
		viewMatrix  = new Matrix4();
		tranMatrix  = new Matrix4();
		font = new BitmapFont(Gdx.files.internal("fonts/Magneto.fnt"));
		progress = 0;
		music = Gdx.audio.newMusic(Gdx.files.internal("music/mainIntro.mp3"));
		music.setLooping(true);
		music.play();
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		texture.dispose();
		music.dispose();
	}

	@Override
	public void update(float delta) {
		if (loaded) {
			if ((Gdx.input.justTouched() || Commands.commands[Commands.ENTER]) && !Commands.pressed[Commands.ENTER]) {
				setDone(true);
				music.stop();
				Commands.pressed[Commands.ENTER] = true;
			}
		}
		progress = (int)(AssetManagement.assetManager.getProgress()*100);
		AssetManagement.assetManager.update();
		if (progress == 100) {
			loaded = true;
		}
		time += delta;
		if (time >= 0.5f) {
			visible = !visible;
			time=0;
		}
		
	}

	@Override
	public void draw(float delta) {
		viewMatrix.setToOrtho2D(0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(tranMatrix);
		spriteBatch.begin();
		spriteBatch.draw(texture, 0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
		
		if (!loaded)
		    font.draw(spriteBatch, "Loading... "+progress+"%", 100, 70);
		else {
			if (visible) {
				font.draw(spriteBatch,"Press Enter to Start!",100,70); 
			}
		}

		spriteBatch.end();
	}
		
}

