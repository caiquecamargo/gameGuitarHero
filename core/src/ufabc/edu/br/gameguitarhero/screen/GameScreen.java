package ufabc.edu.br.gameguitarhero.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import ufabc.edu.br.gameguitarhero.model.AbstractModel;
import ufabc.edu.br.gameguitarhero.model.Disco;
import ufabc.edu.br.gameguitarhero.model.Fundo;
import ufabc.edu.br.gameguitarhero.model.Pista;
import ufabc.edu.br.gameguitarhero.util.Discos;
import ufabc.edu.br.gameguitarhero.util.Parameters;

public class GameScreen extends AbstractScreen{
	
	private PerspectiveCamera camera;
	private Environment 	  environment;
	private ModelBatch 		  modelBatch;
	private ArrayList<Pista>  pistas;
	private Disco			  disco[];
	private Disco			  blackDisco[];
	private Disco			  test;
	private AbstractModel	  model;
	private SpriteBatch 	  spriteBatch;
	private Matrix4 		  viewMatrix;
	private Matrix4 		  tranMatrix;
	private BitmapFont 		  font;
	private Vector3			  velocidade;
	private float			  lookPosition = 5.0f;
	private int				  timeToBegin = 3;
	private float             time = 0.0f;
	private Texture			  menu;
	private Fundo			  fundo;

	public GameScreen(String id, AbstractModel model) {
		super(id);
		this.model = model;
		model.getCurrent().transform.scale(0.5f, 0.5f, 0.5f);
		model.getCurrent().transform.rotate(Vector3.Y, 0);
		model.getCurrent().transform.translate(4.0f, -1.0f, -15.0f);
		
		pistas = new ArrayList<Pista>();
		
		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		camera = new PerspectiveCamera(67.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0, 5.0f, 5.0f);
		camera.lookAt(0f, 0f, lookPosition);// a camera "olha" para a origem
		camera.near = 0.1f;
		camera.far  = 100f;
		camera.update();
		velocidade = new Vector3(0, 0, 0);
		font = new BitmapFont(Gdx.files.internal("fonts/bronx.fnt"));
		
		spriteBatch = new SpriteBatch();
		viewMatrix = new Matrix4();
		tranMatrix = new Matrix4();
		
		disco = new Disco[5];
		disco[Discos.BTN_1] = new Disco("DISCO_1", Color.GREEN);
		disco[Discos.BTN_1].getCurrent().transform.translate(-0.2f, 0.0f, 4.6f);
		disco[Discos.BTN_2] = new Disco("DISCO_2", Color.YELLOW);
		disco[Discos.BTN_2].getCurrent().transform.translate(-0.1f, 0.0f, 4.6f);
		disco[Discos.BTN_3] = new Disco("DISCO_3", Color.RED);
		disco[Discos.BTN_3].getCurrent().transform.translate(0.0f, 0.0f, 4.6f);
		disco[Discos.BTN_4] = new Disco("DISCO_4", Color.BLUE);
		disco[Discos.BTN_4].getCurrent().transform.translate(0.1f, 0.0f, 4.6f);
		disco[Discos.BTN_5] = new Disco("DISCO_5", Color.ORANGE);
		disco[Discos.BTN_5].getCurrent().transform.translate(0.2f, 0.0f, 4.6f);
		
		blackDisco = new Disco[5];
		blackDisco[Discos.BTN_1] = new Disco("DISCO_1", Color.BLACK);
		blackDisco[Discos.BTN_1].getCurrent().transform.translate(-0.2f, -0.001f, 4.6f);
		blackDisco[Discos.BTN_1].getCurrent().transform.scale(1.1f, 1.1f, 1.1f);
		blackDisco[Discos.BTN_2] = new Disco("DISCO_2", Color.BLACK);
		blackDisco[Discos.BTN_2].getCurrent().transform.translate(-0.1f, -0.001f, 4.6f);
		blackDisco[Discos.BTN_2].getCurrent().transform.scale(1.1f, 1.1f, 1.1f);
		blackDisco[Discos.BTN_3] = new Disco("DISCO_3", Color.BLACK);
		blackDisco[Discos.BTN_3].getCurrent().transform.translate(0.0f, -0.001f, 4.6f);
		blackDisco[Discos.BTN_3].getCurrent().transform.scale(1.1f, 1.1f, 1.1f);
		blackDisco[Discos.BTN_4] = new Disco("DISCO_4", Color.BLACK);
		blackDisco[Discos.BTN_4].getCurrent().transform.translate(0.1f, -0.001f, 4.6f);
		blackDisco[Discos.BTN_4].getCurrent().transform.scale(1.1f, 1.1f, 1.1f);
		blackDisco[Discos.BTN_5] = new Disco("DISCO_5", Color.BLACK);
		blackDisco[Discos.BTN_5].getCurrent().transform.translate(0.2f, -0.001f, 4.6f);
		blackDisco[Discos.BTN_5].getCurrent().transform.scale(1.1f, 1.1f, 1.1f);
		
		test = new Disco("DISCOTEST", Color.RED);
		test.getCurrent().transform.translate(-0.2f, 0.001f, -4.6f);
		
		for (int i = 0; i < 50; i++){
			Pista pista = new Pista("Pista");
			pista.getCurrent().transform.translate(0, 0, -1.0f - (2.0f * i));
			pistas.add(pista);
		}
		
		fundo = new Fundo("Fundo");
		fundo.getCurrent().transform.translate(0, 0, -2.0f);
		
		menu = new Texture(Gdx.files.internal("luz.png"));
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
	}

	@Override
	public void update(float delta) {
		velocidade.z = 1.0f * delta;
		if (camera.position.y > 0.2f) camera.position.set(camera.position.x, camera.position.y - 1.2f * delta, camera.position.z);
		if (lookPosition > 0.0f) {
			lookPosition -= 1.2f * delta;
			camera.lookAt(0.0f, 0.0f, lookPosition);
			if (lookPosition > 1.5f && lookPosition < 3.0f) timeToBegin = 2;
			else if(lookPosition < 1.5f) timeToBegin = 1;
		}
		
		if(pistas != null)
			for (Pista p: pistas) p.translate(velocidade);
		
		test.getCurrent().transform.translate(velocidade);
		
		Vector3 position = new Vector3();
		if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			disco[Discos.BTN_1].press();
			if(disco[Discos.BTN_1].getCurrent().collidesWith(test.getCurrent())) {
				System.out.println("Colidiu");
			}
		}
		else{
			disco[Discos.BTN_1].getCurrent().transform.getTranslation(position);
			if(position.y > 0.0f) disco[Discos.BTN_1].release();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			disco[Discos.BTN_2].press();
		}
		else{
			disco[Discos.BTN_2].getCurrent().transform.getTranslation(position);
			if(position.y > 0.0f) disco[Discos.BTN_2].release();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			disco[Discos.BTN_3].press();
		}
		else{
			disco[Discos.BTN_3].getCurrent().transform.getTranslation(position);
			if(position.y > 0.0f) disco[Discos.BTN_3].release();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.F)) {
			disco[Discos.BTN_4].press();
		}
		else{
			disco[Discos.BTN_4].getCurrent().transform.getTranslation(position);
			if(position.y > 0.0f) disco[Discos.BTN_4].release();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.G)) {
			disco[Discos.BTN_5].press();
		}
		else{
			disco[Discos.BTN_5].getCurrent().transform.getTranslation(position);
			if(position.y > 0.0f) disco[Discos.BTN_5].release();
		}
		for (Disco d: disco) d.update(delta);
		test.update(delta);
		time += delta;
	}

	@Override
	public void draw(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);

		modelBatch.begin(camera);
		if(pistas != null)
			for(Pista p: pistas) modelBatch.render(p.getCurrent(), environment);
		for (Disco d: disco)
			modelBatch.render(d.getCurrent(), environment);
		for (Disco d: blackDisco)
			modelBatch.render(d.getCurrent(), environment);
		modelBatch.render(test.getCurrent(), environment);
		modelBatch.render(model.getCurrent(), environment);
		modelBatch.render(fundo.getCurrent(), environment);
		modelBatch.end();

		camera.update();
		
		viewMatrix.setToOrtho2D(0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(tranMatrix);
		spriteBatch.begin();
		if (time < 5.5f)
			font.draw(spriteBatch, ((lookPosition > 0.0f)?"  "+timeToBegin:"BEGIN"), 350, 350);
		else
			spriteBatch.draw(menu, 500, 100, 300, 400, 0, 0, menu.getWidth(), menu.getHeight(), false, false);

		spriteBatch.end();
	}

}
