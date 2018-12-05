package ufabc.edu.br.gameguitarhero.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import ufabc.edu.br.gameguitarhero.model.AbstractModel;
import ufabc.edu.br.gameguitarhero.model.Dahaka;
import ufabc.edu.br.gameguitarhero.model.FellMonster;
import ufabc.edu.br.gameguitarhero.util.Commands;
import ufabc.edu.br.gameguitarhero.util.Parameters;

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
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1));
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

		font.setColor(Color.YELLOW);
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
				selectModel();
			}
			Commands.pressed[Commands.LEFT] = true;
		}
		if (Commands.commands[Commands.RIGHT] && !Commands.pressed[Commands.RIGHT]) {
			if (modelSelector < 2) {
				modelSelector++;
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
		Gdx.gl.glClearColor(0, 0, 0, 1);

		modelBatch.begin(camera);
		modelBatch.render(model.getCurrent(), environment);
		modelBatch.end();

		camera.update();

		viewMatrix.setToOrtho2D(0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(tranMatrix);
		spriteBatch.begin();
		//font.draw(spriteBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 450);

		spriteBatch.end();
		
	}
	
	public MenuScreen getMenu() {
		return menu;
	}
	
	private void selectModel() {
		switch(modelSelector) {
		case 0:
			model = new FellMonster("FELLMONSTER");
			tex = new TextureAttribute(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("models/fguard.jpg"))));
			for (Material mat: model.getCurrent().materials)
				  mat.set(tex);
			break;
			
		case 1:
			model = new FellMonster("FELLMONSTER2");
			tex = new TextureAttribute(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("models/fguard2.jpg"))));
			for (Material mat: model.getCurrent().materials)
				  mat.set(tex);
			break;
			
		case 2:
			model = new Dahaka("DAHAKA");
			break;
		}
		
		menu.setModel(model);
//		model.getCurrent().transform.scale(0.4f, 0.4f, 0.4f);
//		model.getCurrent().transform.rotate(Vector3.Y, -165);
//		model.getCurrent().transform.translate(2, 0, 0);
	}
	
	private void setModelSelector(AbstractModel model) {
		if(model.getId().equals("FELLMONSTER"))
			modelSelector = 0;
		else if(model.getId().equals("FELLMONSTER2"))
			modelSelector = 1;
		else if(model.getId().equals("DAHAKA"))
			modelSelector = 2;
		
		selectModel();
	}

}
