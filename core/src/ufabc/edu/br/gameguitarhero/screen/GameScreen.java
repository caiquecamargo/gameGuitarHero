package ufabc.edu.br.gameguitarhero.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
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
import ufabc.edu.br.gameguitarhero.model.End;
import ufabc.edu.br.gameguitarhero.model.Fundo;
import ufabc.edu.br.gameguitarhero.model.Notes;
import ufabc.edu.br.gameguitarhero.model.Pista;
import ufabc.edu.br.gameguitarhero.model.Sky;
import ufabc.edu.br.gameguitarhero.particles.AbstractParticle;
import ufabc.edu.br.gameguitarhero.util.Commands;
import ufabc.edu.br.gameguitarhero.util.Discos;
import ufabc.edu.br.gameguitarhero.util.GamingPhases;
import ufabc.edu.br.gameguitarhero.util.Menus;
import ufabc.edu.br.gameguitarhero.util.Musics;
import ufabc.edu.br.gameguitarhero.util.Parameters;
import ufabc.edu.br.gameguitarhero.util.Particles;

public class GameScreen extends AbstractScreen{
	
	private PerspectiveCamera  camera;
	private Environment 	   environment;
	private ModelBatch 		   modelBatch;
	private ArrayList<Pista>   pistas;
	private Disco			   disco[];
	private Disco			   blackDisco[];
	private ArrayList<Notes>   notes;
	private AbstractModel	   model;
	private SpriteBatch 	   spriteBatch;
	private Matrix4 		   viewMatrix;
	private Matrix4 		   tranMatrix;
	private BitmapFont 		   font;
	private Vector3			   velocidade;
	private float			   lookPosition = 5.0f;
	private int				   timeToBegin = 3;
	private float              time = 0.0f;
	private Texture			   pointBatch;
	private Sky				   sky;
	private Fundo			   fundo;
	private End				   end;
	private float			   timeError = 0.0f;
	private AbstractParticle[] particles;
	private int				   points = 0;
	private int				   chainHits = 0;
	private int				   chainMultiplicator = 1;
	private int				   multiplicator = 1;
	private int				   errors;
	private int				   errorMax = 200;
	private int				   difficultMode = 1;
	private int				   gamePhase = GamingPhases.INIT;
	private Texture    		   menu[][];
	private int 	    	   menuSelect = Menus.RESUME;

	public GameScreen(String id, AbstractModel model, ArrayList<Notes> notes, Disco[] disco, Disco[] blackDisco, ArrayList<Pista> pistas, PerspectiveCamera camera, AbstractParticle[] particles, int difficultMode) {
		super(id);
		this.model = model;
		this.difficultMode = difficultMode + 1;
		
		errors = errorMax;
		
		modelBatch = new ModelBatch();
		spriteBatch = new SpriteBatch();
		viewMatrix = new Matrix4();
		tranMatrix = new Matrix4();
		font = new BitmapFont(Gdx.files.internal("fonts/bronx.fnt"));
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		this.camera = camera;
		
		velocidade = new Vector3(0, 0, 0);
		
		Musics.getMusicbyName("MUSIC").setLooping(false);
		
		this.particles = particles;
		
		this.pistas = pistas;
		this.disco = disco;
		this.blackDisco = blackDisco;
		this.notes = notes;	
		
		sky = new Sky("SKY");	
		fundo = new Fundo("FUNDO");		
		end = new End("END");
		
		pointBatch = new Texture(Gdx.files.internal("textures/luz.png"));
		
		menu = new Texture[3][2];
		menu[Menus.RESUME][0]  = new Texture(Gdx.files.internal("menu/MenuResume.png"));
		menu[Menus.RESUME][1]  = null;
		menu[Menus.RESTART][0]	= new Texture(Gdx.files.internal("menu/MenuRestart.png"));
		menu[Menus.RESTART][1]	= new Texture(Gdx.files.internal("menu/MenuRestartGameOver.png"));
		menu[Menus.EXIT-1][0]  = new Texture(Gdx.files.internal("menu/MenuExit.png"));
		menu[Menus.EXIT-1][1]  = new Texture(Gdx.files.internal("menu/MenuExitGameOver.png"));
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
	}

	@Override
	public void update(float delta) {
		if (errors < 0) {
			gamePhase = GamingPhases.GAMEOVER;
			model.setState(GamingPhases.GAMEOVER);
			Musics.getMusicbyName("GAMEOVER").play();
			Musics.getMusicbyName("LAUGHING").play();
			Musics.getMusicbyName("MUSIC").stop();
			
			if (menuSelect == Menus.RESUME)
				menuSelect = Menus.RESTART;
			
			if (Commands.commands[Commands.UP] && !Commands.pressed[Commands.UP]) {
				if (menuSelect > 1) menuSelect--;
				Commands.pressed[Commands.UP] = true;
			}
			if (Commands.commands[Commands.DOWN] && !Commands.pressed[Commands.DOWN]) {
				if (menuSelect < 2) menuSelect++;
				Commands.pressed[Commands.DOWN] = true;
			}
			if (Commands.commands[Commands.ENTER] && !Commands.pressed[Commands.ENTER]) {
				if(menuSelect == Menus.RESUME) {
					System.out.println("Não vai acontecer nada, não esquece de implementar isso depois");
				}
				else {
					model.setState(GamingPhases.INIT);
					setDone(true);
					stopMusic();
				}
				Commands.pressed[Commands.ENTER] = true;
			}
		}
		else if (gamePhase == GamingPhases.BREAK) {
			pauseMusic();
			if (Commands.commands[Commands.UP] && !Commands.pressed[Commands.UP]) {
				if (menuSelect > 0) menuSelect--;
				Commands.pressed[Commands.UP] = true;
			}
			if (Commands.commands[Commands.DOWN] && !Commands.pressed[Commands.DOWN]) {
				if (menuSelect < 2) menuSelect++;
				Commands.pressed[Commands.DOWN] = true;
			}
			if (Commands.commands[Commands.ENTER] && !Commands.pressed[Commands.ENTER]) {
				if(menuSelect == Menus.RESUME) {
					gamePhase = GamingPhases.INIT;
					model.setState(gamePhase);
					Musics.getMusicbyName("MUSIC").play();
				}
				else {
					model.setState(GamingPhases.INIT);
					setDone(true);
					stopMusic();
				}
				
				Commands.pressed[Commands.ENTER] = true;
			}
		}
		else if(!Musics.getMusicbyName("MUSIC").isPlaying() && time > 5.5f) {
			gamePhase = GamingPhases.WINNER;
			model.setState(gamePhase);
			
			stopMusic();
			
			Musics.getMusicbyName("AUDIENCE_CHEERING").setLooping(true);
			Musics.getMusicbyName("AUDIENCE_CHEERING").play();
			Musics.getMusicbyName("APPLAUSE").setLooping(true);
			Musics.getMusicbyName("APPLAUSE").play();
			Musics.getMusicbyName("FINAL").play();
			
			if (Commands.commands[Commands.ENTER] && !Commands.pressed[Commands.ENTER]) {
				menuSelect = Menus.CREDITS;
				setDone(true);
				stopMusic();
				Commands.pressed[Commands.ENTER] = true;
			}
		}
		else {
			if((Commands.commands[Commands.ESCAPE] && !Commands.pressed[Commands.ESCAPE]) || (Commands.commands[Commands.ENTER] && !Commands.pressed[Commands.ENTER])) {
				if(Commands.commands[Commands.ESCAPE])
					Commands.pressed[Commands.ESCAPE] = true;
				if(Commands.commands[Commands.ENTER])
					Commands.pressed[Commands.ENTER] = true;
				gamePhase = GamingPhases.BREAK;
				model.setState(gamePhase);
			}
			else {
				chainMultiplicator = (chainMultiplicator < 8)?chainHits / 20 + 1:chainMultiplicator;
				if(multiplicator != chainMultiplicator && chainMultiplicator != 1) {
					multiplicator = chainMultiplicator;
					errors += 1;
				}
				verifyGamePhase();
				velocidade.z = Parameters.VELOCIDADE * delta;
				
				if(time > 5.5f) {
					if(errors > errorMax * 0.8f) font.setColor(0, 1, 0, 1);
					else if(errors > errorMax * 0.6f) font.setColor(0.2f, 0.8f, 0, 1);
					else if(errors > errorMax * 0.4f) font.setColor(0.4f, 0.6f, 0, 1);
					else if(errors > errorMax * 0.2f) font.setColor(0.6f, 0.4f, 0, 1);
					else font.setColor(1.0f, 0.0f, 0, 1);
				}
				
				if (camera.position.y > 0.2f) camera.position.set(camera.position.x, camera.position.y - Parameters.VELOCIDADE * 1.2f * delta, camera.position.z);
				if (lookPosition > 0.0f) {
					lookPosition -= Parameters.VELOCIDADE * 1.2f * delta;
					camera.lookAt(0.0f, 0.0f, lookPosition);
					if (lookPosition > 1.5f && lookPosition < 3.0f) timeToBegin = 2;
					else if(lookPosition < 1.5f) timeToBegin = 1;
				}
				
				if(pistas != null)
					for (Pista p: pistas) {
						Vector3 pos = new Vector3();
						p.getCurrent().transform.getTranslation(pos);
						if(pos.z > 7.0f) p.setRenderize(false);
						else {
							p.translate(velocidade);
							p.update(delta);
							if(p.getCurrent().collidesWith(fundo.getCurrent())) {
								p.setRenderize(true);
							}
						}
					}
				
				if (time > 5.5f - 0.2f) {
					Musics.getMusicbyName("MUSIC").play();
				}
				
				Musics.getMusicbyName("MUSIC_INIT").play();
				
				fundo.update(delta);
				end.update(delta);
				timeError += delta;
				if(timeError > 0.2f) {
					Musics.getMusicbyName("ERROR").stop();
				}
				
				for(Notes n: notes) {
					Vector3 pos = new Vector3();
					n.getCurrent().transform.getTranslation(pos);
					if(pos.z > 5.0f) {
						if(n.getRenderize()) errors -= 1;
						n.setRenderize(false);
					}
					else {
						n.getCurrent().transform.translate(velocidade.x, 2.5f * -velocidade.z, velocidade.y);
						n.update(delta);
						if(n.getCurrent().collidesWith(fundo.getCurrent())) n.setRenderize(true);
					}
				}
				
				Vector3 position = new Vector3();
				if(Commands.commands[Commands.BTN_1] && !Commands.pressed[Commands.BTN_1]) {
					disco[Discos.BTN_1].press();
					Commands.pressed[Commands.BTN_1] = true;
					for(Notes n: notes) {
						if(n.getColor() == Discos.BTN_1) {
							if(disco[Discos.BTN_1].getCurrent().collidesWith(n.getCurrent())) {
								n.setRenderize(false);
								particles[Particles.PARTICLE_GREEN].setRenderize(true);
								disco[Discos.BTN_1].setCollided(true);
								points += 100 * difficultMode * chainMultiplicator;
								chainHits++;
							}
						}
					}
					if(disco[Discos.BTN_1].getCollided()) {
						disco[Discos.BTN_1].setCollided(false);
					}else {
						Musics.getMusicbyName("ERROR").play();
						gamePhase = GamingPhases.INIT;
						model.setState(gamePhase);
						Musics.getMusicbyName("ERROR_AUDIENCE").play();
						timeError = 0.0f;
						chainHits = 0;
						chainMultiplicator = 1;
						errors -= 1;
					}
				}
				else{
					disco[Discos.BTN_1].getCurrent().transform.getTranslation(position);
					if(position.y > 0.0f) disco[Discos.BTN_1].release();
				}
				if(Commands.commands[Commands.BTN_2] && !Commands.pressed[Commands.BTN_2]) {
					disco[Discos.BTN_2].press();
					Commands.pressed[Commands.BTN_2] = true;
					for(Notes n: notes) {
						if(n.getColor() == Discos.BTN_2) {
							if(disco[Discos.BTN_2].getCurrent().collidesWith(n.getCurrent())) {
								n.setRenderize(false);
								particles[Particles.PARTICLE_YELLOW].setRenderize(true);
								disco[Discos.BTN_2].setCollided(true);
								points += 100 * difficultMode * chainMultiplicator;
								chainHits++;
							}
						}
					}
					if(disco[Discos.BTN_2].getCollided()) {
						disco[Discos.BTN_2].setCollided(false);
					}else {
						Musics.getMusicbyName("ERROR").play();
						gamePhase = GamingPhases.INIT;
						model.setState(gamePhase);
						Musics.getMusicbyName("ERROR_AUDIENCE").play();
						timeError = 0.0f;
						chainHits = 0;
						chainMultiplicator = 1;
						errors -= 1;
					}
				}
				else{
					disco[Discos.BTN_2].getCurrent().transform.getTranslation(position);
					if(position.y > 0.0f) disco[Discos.BTN_2].release();
				}
				if(Commands.commands[Commands.BTN_3] && !Commands.pressed[Commands.BTN_3]) {
					disco[Discos.BTN_3].press();
					Commands.pressed[Commands.BTN_3] = true;
					for(Notes n: notes) {
						if(n.getColor() == Discos.BTN_3) {
							if(disco[Discos.BTN_3].getCurrent().collidesWith(n.getCurrent())) {
								n.setRenderize(false);
								particles[Particles.PARTICLE_RED].setRenderize(true);
								disco[Discos.BTN_3].setCollided(true);
								points += 100 * difficultMode * chainMultiplicator;
								chainHits++;
							}
						}
					}
					if(disco[Discos.BTN_3].getCollided()) {
						disco[Discos.BTN_3].setCollided(false);
					}else {
						Musics.getMusicbyName("ERROR").play();
						gamePhase = GamingPhases.INIT;
						model.setState(gamePhase);
						Musics.getMusicbyName("ERROR_AUDIENCE").play();
						timeError = 0.0f;
						chainHits = 0;
						chainMultiplicator = 1;
						errors -= 1;
					}
				}
				else{
					disco[Discos.BTN_3].getCurrent().transform.getTranslation(position);
					if(position.y > 0.0f) disco[Discos.BTN_3].release();
				}
				if(Commands.commands[Commands.BTN_4] && !Commands.pressed[Commands.BTN_4]) {
					disco[Discos.BTN_4].press();
					Commands.pressed[Commands.BTN_4] = true;
					for(Notes n: notes) {
						if(n.getColor() == Discos.BTN_4) {
							if(disco[Discos.BTN_4].getCurrent().collidesWith(n.getCurrent())) {
								n.setRenderize(false);
								particles[Particles.PARTICLE_BLUE].setRenderize(true);
								disco[Discos.BTN_4].setCollided(true);
								points += 100 * difficultMode * chainMultiplicator;
								chainHits++;
							}
						}
					}
					if(disco[Discos.BTN_4].getCollided()) {
						disco[Discos.BTN_4].setCollided(false);
					}else {
						Musics.getMusicbyName("ERROR").play();
						gamePhase = GamingPhases.INIT;
						model.setState(gamePhase);
						Musics.getMusicbyName("ERROR_AUDIENCE").play();
						timeError = 0.0f;
						chainHits = 0;
						chainMultiplicator = 1;
						errors -= 1;
					}
				}
				else{
					disco[Discos.BTN_4].getCurrent().transform.getTranslation(position);
					if(position.y > 0.0f) disco[Discos.BTN_4].release();
				}
				if(Commands.commands[Commands.BTN_5] && !Commands.pressed[Commands.BTN_5]) {
					disco[Discos.BTN_5].press();
					Commands.pressed[Commands.BTN_5] = true;
					for(Notes n: notes) {
						if(n.getColor() == Discos.BTN_5) {
							if(disco[Discos.BTN_5].getCurrent().collidesWith(n.getCurrent())) {
								n.setRenderize(false);
								particles[Particles.PARTICLE_ORANGE].setRenderize(true);
								disco[Discos.BTN_5].setCollided(true);
								points += 100 * difficultMode * chainMultiplicator;
								chainHits++;
							}
						}
					}
					if(disco[Discos.BTN_5].getCollided()) {
						disco[Discos.BTN_5].setCollided(false);
					}else {
						Musics.getMusicbyName("ERROR").play();
						gamePhase = GamingPhases.INIT;
						model.setState(gamePhase);
						Musics.getMusicbyName("ERROR_AUDIENCE").play();
						timeError = 0.0f;
						chainHits = 0;
						chainMultiplicator = 1;
						errors -= 1;
					}
				}
				else{
					disco[Discos.BTN_5].getCurrent().transform.getTranslation(position);
					if(position.y > 0.0f) disco[Discos.BTN_5].release();
				}
				for (Disco d: disco) d.update(delta);
				time += delta;
				particles[Particles.PARTICLE_GREEN].setTimeParticle(particles[Particles.PARTICLE_GREEN].getTimeParticle() + delta);
				particles[Particles.PARTICLE_YELLOW].setTimeParticle(particles[Particles.PARTICLE_YELLOW].getTimeParticle() + delta);
				particles[Particles.PARTICLE_RED].setTimeParticle(particles[Particles.PARTICLE_RED].getTimeParticle() + delta);
				particles[Particles.PARTICLE_BLUE].setTimeParticle(particles[Particles.PARTICLE_BLUE].getTimeParticle() + delta);
				particles[Particles.PARTICLE_ORANGE].setTimeParticle(particles[Particles.PARTICLE_ORANGE].getTimeParticle() + delta);
			}
		}
		model.update(delta);
	}

	@Override
	public void draw(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 0, 1);

		modelBatch.begin(camera);
		if(pistas != null)
			for(Pista p: pistas) if(p.getRenderize()) modelBatch.render(p.getCurrent(), environment);
		for (Disco d: disco)
			modelBatch.render(d.getCurrent(), environment);
		for (Disco d: blackDisco)
			modelBatch.render(d.getCurrent(), environment);
		for(Notes n: notes) {
			if(n.getRenderize())
				modelBatch.render(n.getCurrent(), environment);
		}
		modelBatch.render(model.getCurrent(), environment);
		modelBatch.render(sky.getCurrent(), environment);
		if(particles[Particles.PARTICLE_GREEN].getRenderize() || particles[Particles.PARTICLE_GREEN].getTimeParticle() < 0.1f) {
			if(particles[Particles.PARTICLE_GREEN].getRenderize()) particles[Particles.PARTICLE_GREEN].setTimeParticle(0.0f);
			particles[Particles.PARTICLE_GREEN].update();
			modelBatch.render(particles[Particles.PARTICLE_GREEN].getSystem());
			particles[Particles.PARTICLE_GREEN].setRenderize(false);
		}else {
			particles[Particles.PARTICLE_GREEN].setTimeParticle(2.0f);
		}
		if(particles[Particles.PARTICLE_YELLOW].getRenderize() || particles[Particles.PARTICLE_YELLOW].getTimeParticle() < 0.1f) {
			if(particles[Particles.PARTICLE_YELLOW].getRenderize()) particles[Particles.PARTICLE_YELLOW].setTimeParticle(0.0f);
			particles[Particles.PARTICLE_YELLOW].update();
			modelBatch.render(particles[Particles.PARTICLE_YELLOW].getSystem());
			particles[Particles.PARTICLE_YELLOW].setRenderize(false);
		}else {
			particles[Particles.PARTICLE_YELLOW].setTimeParticle(2.0f);
		}
		if(particles[Particles.PARTICLE_RED].getRenderize() || particles[Particles.PARTICLE_RED].getTimeParticle() < 0.1f) {
			if(particles[Particles.PARTICLE_RED].getRenderize()) particles[Particles.PARTICLE_RED].setTimeParticle(0.0f);
			particles[Particles.PARTICLE_RED].update();
			modelBatch.render(particles[Particles.PARTICLE_RED].getSystem());
			particles[Particles.PARTICLE_RED].setRenderize(false);
		}else {
			particles[Particles.PARTICLE_RED].setTimeParticle(2.0f);
		}
		if(particles[Particles.PARTICLE_BLUE].getRenderize() || particles[Particles.PARTICLE_BLUE].getTimeParticle() < 0.1f) {
			if(particles[Particles.PARTICLE_BLUE].getRenderize()) particles[Particles.PARTICLE_BLUE].setTimeParticle(0.0f);
			particles[Particles.PARTICLE_BLUE].update();
			modelBatch.render(particles[Particles.PARTICLE_BLUE].getSystem());
			particles[Particles.PARTICLE_BLUE].setRenderize(false);
		}else {
			particles[Particles.PARTICLE_BLUE].setTimeParticle(2.0f);
		}
		if(particles[Particles.PARTICLE_ORANGE].getRenderize() || particles[Particles.PARTICLE_ORANGE].getTimeParticle() < 0.1f) {
			if(particles[Particles.PARTICLE_ORANGE].getRenderize()) particles[Particles.PARTICLE_ORANGE].setTimeParticle(0.0f);
			particles[Particles.PARTICLE_ORANGE].update();
			modelBatch.render(particles[Particles.PARTICLE_ORANGE].getSystem());
			particles[Particles.PARTICLE_ORANGE].setRenderize(false);
		}else {
			particles[Particles.PARTICLE_ORANGE].setTimeParticle(2.0f);
		}
		modelBatch.end();

		camera.update();
		
		viewMatrix.setToOrtho2D(0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(tranMatrix);
		spriteBatch.begin();
		if (time < 5.5f)
			font.draw(spriteBatch, ((lookPosition > 0.0f)?"  "+timeToBegin:"BEGIN"), 350, 350);
		else {
			if(gamePhase == GamingPhases.BREAK) {
				spriteBatch.draw(menu[menuSelect][0], 300, 100, 300, 400, 0, 0, menu[menuSelect][0].getWidth(), menu[menuSelect][0].getHeight(), false, false);
			}
			else if(gamePhase == GamingPhases.WINNER) {
				font.draw(spriteBatch, "YOU WIN", 300, 400);
				font.draw(spriteBatch,"Press Enter!",100,70);
			}
			else if(gamePhase != GamingPhases.GAMEOVER) {
				spriteBatch.draw(pointBatch, 430, 100, 300, 400, 0, 0, pointBatch.getWidth(), pointBatch.getHeight(), false, false);
				font.draw(spriteBatch, "POINTS " + points, 500, 450);
				font.draw(spriteBatch, "CHAIN  " + chainHits, 500, 400);
				font.draw(spriteBatch, "    " + chainMultiplicator + "x", 500, 350);
			}else {
				font.draw(spriteBatch, "GAME OVER", 290, 450);
				spriteBatch.draw(menu[menuSelect][1], 300, 100, 200, 250, 0, 0, menu[menuSelect][1].getWidth(), menu[menuSelect][1].getHeight(), false, false);
			}
		}

		spriteBatch.end();
	}
	
	private void verifyGamePhase() {
		if (!Musics.getMusicbyName("MUSIC").isPlaying() && time > 5.5) {
			gamePhase = GamingPhases.WINNER;
			model.setState(gamePhase);
			Musics.getMusicbyName("FINAL").play();
		}
		else if(gamePhase == GamingPhases.ERRORS) {
			gamePhase = GamingPhases.INIT;
			model.setState(gamePhase);
			Musics.getMusicbyName("ERROR_AUDIENCE").play();
		}
		else {
			if (chainMultiplicator < 2 && (gamePhase == GamingPhases.EXCITED || gamePhase == GamingPhases.OUSTANDING)) {
				gamePhase = GamingPhases.ERRORS;
				model.setState(gamePhase);
			}
			else if (gamePhase == GamingPhases.INIT && chainMultiplicator > 2) {
				gamePhase = GamingPhases.EXCITED;
				model.setState(gamePhase);
				Musics.getMusicbyName("APPLAUSE").play();
				errors += 1;
			}
			else if (gamePhase == GamingPhases.EXCITED && chainMultiplicator > 4) {
				gamePhase = GamingPhases.OUSTANDING;
				model.setState(gamePhase);
				Musics.getMusicbyName("AUDIENCE_CHEERING").play();
				errors += 1;
			}
			else if(errors > errorMax * 0.2f && errors < errorMax * 0.4f) {
				gamePhase = GamingPhases.DECREASING;
				Musics.getMusicbyName("DECREASING").play();
				model.setState(gamePhase);
			}
			else if (errors < errorMax * 0.2f) {
				gamePhase = GamingPhases.LOOSING;
				Musics.getMusicbyName("LOSING").play();
				model.setState(gamePhase);
			}
		}
	}
	
	private void stopMusic() {
		Musics.getMusicbyName("MUSIC").stop();
		Musics.getMusicbyName("MUSIC_INIT").stop();
		Musics.getMusicbyName("ERROR").stop();
		Musics.getMusicbyName("ERROR_AUDIENCE").stop();
		Musics.getMusicbyName("GAMEOVER").stop();
		Musics.getMusicbyName("DECREASING").stop();
		Musics.getMusicbyName("AUDIENCE_CHEERING").stop();
		Musics.getMusicbyName("AUDIENCE_CHEERING").stop();
		Musics.getMusicbyName("LOSING").stop();
		Musics.getMusicbyName("APPLAUSE").stop();
		Musics.getMusicbyName("LAUGHING").stop();
	}
	
	private void pauseMusic() {
		Musics.getMusicbyName("MUSIC").pause();
		Musics.getMusicbyName("MUSIC_INIT").pause();
		Musics.getMusicbyName("ERROR").pause();
		Musics.getMusicbyName("ERROR_AUDIENCE").pause();
		Musics.getMusicbyName("GAMEOVER").pause();
		Musics.getMusicbyName("DECREASING").pause();
		Musics.getMusicbyName("AUDIENCE_CHEERING").pause();
		Musics.getMusicbyName("AUDIENCE_CHEERING").pause();
		Musics.getMusicbyName("LOSING").pause();
		Musics.getMusicbyName("APPLAUSE").pause();
		Musics.getMusicbyName("LAUGHING").pause();
	}
	
	public int getMenuSelect() {
		return menuSelect;
	}
	
	public AbstractModel getModel() {
		return model;
	}
	
	public int getDifficult() {
		return difficultMode - 1;
	}

	public AbstractParticle[] getParticles() {
		return particles;
	}

	public Disco[] getDiscos(){
		return disco;
	}
	
	public Disco[] getBlackDiscos(){
		return blackDisco;
	}
}
