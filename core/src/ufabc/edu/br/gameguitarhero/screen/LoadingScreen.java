package ufabc.edu.br.gameguitarhero.screen;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import ufabc.edu.br.gameguitarhero.AssetsManagement.AssetManagement;
import ufabc.edu.br.gameguitarhero.model.AbstractModel;
import ufabc.edu.br.gameguitarhero.model.Disco;
import ufabc.edu.br.gameguitarhero.model.Notes;
import ufabc.edu.br.gameguitarhero.model.Pista;
import ufabc.edu.br.gameguitarhero.particles.AbstractParticle;
import ufabc.edu.br.gameguitarhero.particles.BlueParticle;
import ufabc.edu.br.gameguitarhero.particles.GreenParticle;
import ufabc.edu.br.gameguitarhero.particles.OrangeParticle;
import ufabc.edu.br.gameguitarhero.particles.RedParticle;
import ufabc.edu.br.gameguitarhero.particles.YellowParticle;
import ufabc.edu.br.gameguitarhero.util.Discos;
import ufabc.edu.br.gameguitarhero.util.LoadNotes;
import ufabc.edu.br.gameguitarhero.util.Parameters;
import ufabc.edu.br.gameguitarhero.util.Particles;

public class LoadingScreen extends AbstractScreen{
	private Texture[]     	   texture;
	private int           	   selectTexture;
	private SpriteBatch 	   spriteBatch;
	private Matrix4     	   viewMatrix;
	private Matrix4     	   tranMatrix;
	private BitmapFont 		   font;
	
	private ArrayList<Pista>   pistas;
	private Disco			   disco[];
	private Disco			   blackDisco[];
	private ArrayList<Notes>   notes;
	private AbstractModel	   model;
	private LoadNotes          loadNotes;
	private AbstractParticle[] particles;
	
	private PerspectiveCamera  camera;
	private float			   lookPosition = 5.0f;
	
	private boolean 	       settingsDone = false;
	private int				   difficult;


	public LoadingScreen(String id, AbstractModel model, int difficult, Texture[] tex) {
		super(id);
		
		this.model = model;
		this.model.setGamePosition();
		this.difficult = difficult;
		
		spriteBatch = new SpriteBatch();
		
		Random random = new Random();
		selectTexture = Math.abs(random.nextInt()) % 5;
		texture = new Texture[5];
		texture[0]  = AssetManagement.assetManager.get("textures/guitar_1.jpg");
		texture[1]  = AssetManagement.assetManager.get("textures/guitar_2.jpg");
		texture[2]  = AssetManagement.assetManager.get("textures/guitar_3.jpg");
		texture[3]  = AssetManagement.assetManager.get("textures/guitar_4.jpg");
		texture[4]  = AssetManagement.assetManager.get("textures/guitar_5.jpg");
		viewMatrix  = new Matrix4();
		tranMatrix  = new Matrix4();
		font = new BitmapFont(Gdx.files.internal("fonts/Magneto.fnt"));
		
		loadNotes = new LoadNotes(difficult);
		loadNotes.start();
		
		settings();
	}
	
	public LoadingScreen(String id, AbstractModel model, int difficult, AbstractParticle[] particles, Disco[] disco, Disco[] blackDisco) {
		super(id);
		this.model = model;
		this.difficult = difficult;
		this.particles = particles;
		this.disco = disco;
		this.blackDisco = blackDisco;
		
		camera = new PerspectiveCamera(67.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0, 5.0f, 5.0f);
		camera.lookAt(0f, 0f, lookPosition);
		camera.near = 0.1f;
		camera.far  = 100f;
		camera.update();
		
		Random random = new Random();
		selectTexture = Math.abs(random.nextInt()) % 5;
		spriteBatch = new SpriteBatch();
		texture     = new Texture[5];
		texture[0]  = AssetManagement.assetManager.get("textures/guitar_1.jpg");
		texture[1]  = AssetManagement.assetManager.get("textures/guitar_2.jpg");
		texture[2]  = AssetManagement.assetManager.get("textures/guitar_3.jpg");
		texture[3]  = AssetManagement.assetManager.get("textures/guitar_4.jpg");
		texture[4]  = AssetManagement.assetManager.get("textures/guitar_5.jpg");
		viewMatrix  = new Matrix4();
		tranMatrix  = new Matrix4();
		font = new BitmapFont(Gdx.files.internal("fonts/bronx.fnt"));
		
		pistas = new ArrayList<Pista>();
		for (int i = 0; i < 200; i++){
			Pista pista = new Pista("Pista");
			if (i < 2) pista.setRenderize(true);
			pista.getCurrent().transform.translate(0, 0, -1.0f - (2.0f * i));
			pistas.add(pista);
		}
		
		loadNotes = new LoadNotes(difficult);
		loadNotes.start();
		
		settingsDone = true;
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		for (Texture t:texture)
			t.dispose();
	}

	private void settings() {
		camera = new PerspectiveCamera(67.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0, 5.0f, 5.0f);
		camera.lookAt(0f, 0f, lookPosition);
		camera.near = 0.1f;
		camera.far  = 100f;
		camera.update();
		
		particles = new AbstractParticle[5];
		
		particles[Particles.PARTICLE_GREEN]  = new GreenParticle(camera);
		particles[Particles.PARTICLE_YELLOW] = new YellowParticle(camera);
		particles[Particles.PARTICLE_RED]    = new RedParticle(camera);
		particles[Particles.PARTICLE_BLUE]   = new BlueParticle(camera);
		particles[Particles.PARTICLE_ORANGE] = new OrangeParticle(camera);
		
		pistas = new ArrayList<Pista>();
		for (int i = 0; i < 200; i++){
			Pista pista = new Pista("Pista");
			if (i < 2) pista.setRenderize(true);
			pista.getCurrent().transform.translate(0, 0, -1.0f - (2.0f * i));
			pistas.add(pista);
		}
		
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
		
		settingsDone = true;
	}
	
	@Override
	public void update(float delta) {
		if (loadNotes.getDone() && settingsDone) {
			notes = loadNotes.getNotes();
			setDone(true);
		}		
	}

	@Override
	public void draw(float delta) {
		viewMatrix.setToOrtho2D(0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(tranMatrix);
		spriteBatch.begin();
		spriteBatch.draw(texture[selectTexture], 0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT, 0, 0, texture[selectTexture].getWidth(), texture[selectTexture].getHeight(), false, false);
		
		if (!loadNotes.getDone() && !settingsDone)
		    font.draw(spriteBatch, "Loading...", 100, 70);
		else {
			font.draw(spriteBatch,"Start!",100,70); 
		}

		spriteBatch.end();
	}
	
	public ArrayList<Notes> getNotes(){
		return notes;
	}
	
	public AbstractModel getModel() {
		return model;
	}
	
	public Disco[] getDiscos(){
		return disco;
	}
	
	public Disco[] getBlackDiscos(){
		return blackDisco;
	}
	
	public ArrayList<Pista> getPista(){
		return pistas;
	}
	
	public PerspectiveCamera getCamera(){
		return camera;
	}
	
	public AbstractParticle[] getParticles() {
		return particles;
	}
	
	public int getDifficult() {
		return difficult;
	}
}
