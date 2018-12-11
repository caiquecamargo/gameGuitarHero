package ufabc.edu.br.gameguitarhero.screen;

import com.badlogic.gdx.Gdx;
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
import ufabc.edu.br.gameguitarhero.util.Commands;
import ufabc.edu.br.gameguitarhero.util.GameDifficult;
import ufabc.edu.br.gameguitarhero.util.Menus;
import ufabc.edu.br.gameguitarhero.util.Parameters;
import ufabc.edu.br.gameguitarhero.util.SoundFXMenus;

public class DifficultScreen extends AbstractScreen{
	
	private Texture     menu[];
	private Texture[]   texture;
	private SpriteBatch spriteBatch;
	private Environment environment;
	private ModelBatch 	modelBatch;
	private Matrix4     viewMatrix;
	private Matrix4     tranMatrix;
	private int 	    menuSelect = Menus.NOT_SELECTED;
	private AbstractModel model;
	private PerspectiveCamera camera;
	
	public void setSelectedScreen(int menuSelect) {
		this.menuSelect = menuSelect;
	}
	
	public DifficultScreen(String id, AbstractModel model) {
		super(id);
		spriteBatch = new SpriteBatch();
		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		camera = new PerspectiveCamera(67.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0, 1.7f, 2.2f);
		camera.lookAt(0f, 0f, -10f);  // a camera "olha" para a origem
		camera.near = 0.1f;
		camera.far  = 100f;
		camera.update();
		this.model = model;
		
		menu = new Texture[4];
		menu[GameDifficult.BEGGINER] = new Texture(Gdx.files.internal("menu/Beginner.png"));
		menu[GameDifficult.NORMAL]	 = new Texture(Gdx.files.internal("menu/Normal.png"));
		menu[GameDifficult.HARD]     = new Texture(Gdx.files.internal("menu/Hard.png"));
		menu[GameDifficult.EXPERT]   = new Texture(Gdx.files.internal("menu/Expert.png"));
		
		viewMatrix  = new Matrix4();
		tranMatrix  = new Matrix4();
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		for (int i = 0; i < 4; i++)
			menu[i].dispose();
	}

	@Override
	public void update(float delta) {
		if (Commands.commands[Commands.UP] && !Commands.pressed[Commands.UP]) {
			if (menuSelect > 0) {
				menuSelect--;
				SoundFXMenus.getSoundbyName("DIFF_SELECT_SOUND1").play();
			} else SoundFXMenus.getSoundbyName("DIFF_SELECT_SOUND3").play();
			Commands.pressed[Commands.UP] = true;
		}
		if (Commands.commands[Commands.DOWN] && !Commands.pressed[Commands.DOWN]) {
			if (menuSelect < 3) {
				menuSelect++;
				SoundFXMenus.getSoundbyName("DIFF_SELECT_SOUND2").play();
			} else SoundFXMenus.getSoundbyName("DIFF_SELECT_SOUND3").play();
			Commands.pressed[Commands.DOWN] = true;
		}
		if(true) {
			if (Commands.commands[Commands.ENTER] && !Commands.pressed[Commands.ENTER]) {
				setDone(true);
				Commands.pressed[Commands.ENTER] = true;
			}
		}
	}

	@Override
	public void draw(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		modelBatch.begin(camera);

		modelBatch.render(model.getCurrent(), environment);
		modelBatch.end();
		
		viewMatrix.setToOrtho2D(0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(tranMatrix);
		spriteBatch.begin();
		spriteBatch.draw(menu[menuSelect], 300, 100, 200, 400, 0, 0, menu[menuSelect].getWidth(), menu[menuSelect].getHeight(), false, false);
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
	
	public Texture[] getTexture() {
		return texture;
	}
}
