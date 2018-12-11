package ufabc.edu.br.gameguitarhero.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Matrix4;

import ufabc.edu.br.gameguitarhero.model.AbstractModel;
import ufabc.edu.br.gameguitarhero.model.Dahaka;
import ufabc.edu.br.gameguitarhero.util.Commands;
import ufabc.edu.br.gameguitarhero.util.Menus;
import ufabc.edu.br.gameguitarhero.util.Parameters;
import ufabc.edu.br.gameguitarhero.util.SoundFXMenus;

public class MenuScreen extends AbstractScreen{
	
	private Texture     menu[];
	private SpriteBatch spriteBatch;
	private Environment environment;
	private ModelBatch 	modelBatch;
	private Matrix4     viewMatrix;
	private Matrix4     tranMatrix;
	private Music   	music;
	private int 	    menuSelect = Menus.NOT_SELECTED;
	private AbstractModel model;
	private PerspectiveCamera camera;
	
	public void setSelectedScreen(int menuSelect) {
		this.menuSelect = menuSelect;
	}
	
	public MenuScreen(String id) {
		super(id);
		spriteBatch = new SpriteBatch();
		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.6f, 0.6f, 0.6f, 1));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		camera = new PerspectiveCamera(67.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0, 1.7f, 2.2f);
		camera.lookAt(0f, 0f, -10f); 
		camera.near = 0.1f;
		camera.far  = 100f;
		camera.update();
		music = Gdx.audio.newMusic(Gdx.files.internal("music/music3.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		
		model = new Dahaka("DAHAKA");
		
		menu = new Texture[4];
		menu[Menus.NOT_SELECTED]  = new Texture(Gdx.files.internal("menu/BotoesGame.png"));
		menu[Menus.PLAY]		  = new Texture(Gdx.files.internal("menu/BotoesGamePlay.png"));
		menu[Menus.MODEL_SELECT]  = new Texture(Gdx.files.internal("menu/BotoesGameModel.png"));
		menu[Menus.EXIT]  		  = new Texture(Gdx.files.internal("menu/BotoesGameExit.png"));
		
		viewMatrix  = new Matrix4();
		tranMatrix  = new Matrix4();
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		for (int i = 0; i < 4; i++)
			menu[i].dispose();
		music.dispose();
	}

	@Override
	public void update(float delta) {
		if (Commands.commands[Commands.UP] && !Commands.pressed[Commands.UP]) {
			if (menuSelect > 0) {
				menuSelect--;
				SoundFXMenus.getSoundbyName("MENU_SELECT").play();
			}
			Commands.pressed[Commands.UP] = true;
		}
		if (Commands.commands[Commands.DOWN] && !Commands.pressed[Commands.DOWN]) {
			if (menuSelect < 3) {
				menuSelect++;
				SoundFXMenus.getSoundbyName("MENU_SELECT").play();
			}
			Commands.pressed[Commands.DOWN] = true;
		}
		if (Commands.commands[Commands.ENTER] && !Commands.pressed[Commands.ENTER]) {
			setDone(true);
			if (menuSelect == Menus.PLAY)
				music.stop();
			Commands.pressed[Commands.ENTER] = true;
		}
		model.update(delta);
	}

	@Override
	public void draw(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		
		modelBatch.begin(camera);

		modelBatch.render(model.getCurrent(), environment);
		modelBatch.end();
		
		viewMatrix.setToOrtho2D(0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(tranMatrix);
		spriteBatch.begin();
		spriteBatch.draw(menu[menuSelect], 500, 100, 300, 400, 0, 0, menu[menuSelect].getWidth(), menu[menuSelect].getHeight(), false, false);
		spriteBatch.end();
	}
	
	public int getSelectedScreen() {
		return menuSelect;
	}
	
	public void setModel(AbstractModel model) {
		this.model = model;
	}
	
	public AbstractModel getModel() {
		return model;
	}
}
