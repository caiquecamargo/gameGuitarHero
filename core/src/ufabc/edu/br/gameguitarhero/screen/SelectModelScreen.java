package ufabc.edu.br.gameguitarhero.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Matrix4;

import ufabc.edu.br.gameguitarhero.model.AbstractModel;
import ufabc.edu.br.gameguitarhero.model.Dahaka;
import ufabc.edu.br.gameguitarhero.model.Doomsday;
import ufabc.edu.br.gameguitarhero.util.Commands;
import ufabc.edu.br.gameguitarhero.util.Parameters;
import ufabc.edu.br.gameguitarhero.util.SoundFXMenus;

public class SelectModelScreen extends AbstractScreen{
	
	private PerspectiveCamera 		 camera;
	private Environment 			 environment;
	private ModelBatch 				 modelBatch;
	private AbstractModel			 model;
	private SpriteBatch 			 spriteBatch;
	private Matrix4 				 viewMatrix;
	private Matrix4 				 tranMatrix;
	private BitmapFont 				 font;
	private int						 modelSelector;
	private TextureAttribute 		 tex;
	private MenuScreen				 menu;

	public SelectModelScreen(String id, MenuScreen menu) {
		super(id);
		this.menu = menu;
		setModelSelector(menu.getModel());
		
		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		camera = new PerspectiveCamera(67.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0, 1.7f, 2.2f);
		camera.lookAt(0f, 0f, -10f);  // a camera "olha" para a origem
		camera.near = 0.1f;
		camera.far  = 100f;
		camera.update();
		
		spriteBatch = new SpriteBatch();
		viewMatrix = new Matrix4();
		tranMatrix = new Matrix4();
		font = new BitmapFont(Gdx.files.internal("fonts/horroroid.fnt"));
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
	}

	@Override
	public void update(float delta) {
		if (Commands.commands[Commands.LEFT] && !Commands.pressed[Commands.LEFT]) {
			if (modelSelector > 0) { 
				modelSelector--;
				SoundFXMenus.getSoundbyName("MODEL_SELECT_SOUND2").play();
				selectModel();
			}
			Commands.pressed[Commands.LEFT] = true;
		}
		if (Commands.commands[Commands.RIGHT] && !Commands.pressed[Commands.RIGHT]) {
			if (modelSelector < 3) {
				modelSelector++;
				SoundFXMenus.getSoundbyName("MODEL_SELECT_SOUND1").play();
				selectModel();
			}
			Commands.pressed[Commands.RIGHT] = true;
		}
		if (Commands.commands[Commands.ENTER] && !Commands.pressed[Commands.ENTER]) {
			this.setDone(true);
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

		camera.update();

		viewMatrix.setToOrtho2D(0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(tranMatrix);
		spriteBatch.begin();
		font.setColor(Color.RED);
		font.draw(spriteBatch, "SELECT A MONSTER TO PLAY ", 400, 450);
		font.setColor(Color.YELLOW);
		font.draw(spriteBatch, model.getId(), 400, 400);
		font.setColor(Color.WHITE);
		font.draw(spriteBatch, model.getDescription()[0], 400, 350);
		font.draw(spriteBatch, model.getDescription()[1], 400, 300);
		font.draw(spriteBatch, model.getDescription()[2], 400, 250);
		font.draw(spriteBatch, model.getDescription()[3], 400, 200);

		spriteBatch.end();
		
	}
	
	public MenuScreen getMenu() {
		return menu;
	}
	
	private void selectModel() {
		switch(modelSelector) {
		case 0:
			model = new Doomsday("DOOMSDAY");
			break;
			
		case 1:
			model = new Doomsday("DOOMSDAY: PREPARE TO DIE");
			tex = new TextureAttribute(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("models/Doomsday/INJ_iOS_VILLAIN_Doomsday_Injustice_Body_S.png"))));
			Doomsday dom = (Doomsday) model;
			dom.setTexture(tex);
			model = dom;
			break;
			
		case 2:
			model = new Dahaka("DAHAKA");
			
			break;
		
		case 3:
			model = new Dahaka("DAHAKA: WHITE???");
			tex = new TextureAttribute(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("models/Dahaka/Dahaka_Body_s.png"))));
			Dahaka d = (Dahaka) model;
			d.setTexture(tex);
			model = d;
			break;
		}
		
		menu.setModel(model);
	}
	
	private void setModelSelector(AbstractModel model) {
		if(model.getId().equals("DOOMSDAY"))
			modelSelector = 0;
		else if(model.getId().equals("DOOMSDAY2"))
			modelSelector = 1;
		else if(model.getId().equals("DAHAKA"))
			modelSelector = 2;
		else if(model.getId().equals("DAHAKA2"))
			modelSelector = 3;
		
		selectModel();
	}

}
