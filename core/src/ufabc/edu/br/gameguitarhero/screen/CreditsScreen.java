package ufabc.edu.br.gameguitarhero.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import ufabc.edu.br.gameguitarhero.util.Commands;
import ufabc.edu.br.gameguitarhero.util.Musics;
import ufabc.edu.br.gameguitarhero.util.Parameters;

public class CreditsScreen extends AbstractScreen{
	
	private Texture     texture;
	private SpriteBatch spriteBatch;
	private Matrix4     viewMatrix;
	private Matrix4     tranMatrix;
	private BitmapFont 	font;
	private boolean 	loaded = false;

	public CreditsScreen(String id) {
		super(id);
		
		//Tela de créditos do jogo
		spriteBatch = new SpriteBatch();
		texture     = new Texture(Gdx.files.internal("ufabc.jpg"));
		viewMatrix  = new Matrix4();
		tranMatrix  = new Matrix4();
		font = new BitmapFont(Gdx.files.internal("fonts/Times.fnt"));
		
		//Inicia a música dos créditos
		Musics.getMusicbyName("CREDITS").setLooping(true);
		Musics.getMusicbyName("CREDITS").play();
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		texture.dispose();
	}

	@Override
	public void update(float delta) {
		if (loaded) {
			if ((Gdx.input.justTouched() || Commands.commands[Commands.ENTER]) && !Commands.pressed[Commands.ENTER]) {
				setDone(true);
				Musics.getMusicbyName("CREDITS").stop();
				Commands.pressed[Commands.ENTER] = true;
			}
		}		
	}

	@Override
	public void draw(float delta) {
		viewMatrix.setToOrtho2D(0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(tranMatrix);
		spriteBatch.begin();
		spriteBatch.draw(texture, 0, 0, Parameters.GAME_WIDTH, Parameters.GAME_HEIGHT, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
		
		font.setColor(Color.BLACK);
		font.draw(spriteBatch, "Game ao estilo Guitar Hero criado pelos alunos", 	 	30, 550);
		font.draw(spriteBatch, "Caique de Camargo             RA: 11091312", 	 	 	30, 480);
		font.draw(spriteBatch, "Cintia Lumi Tho                  RA: 11035014", 	 	30, 430);
		font.draw(spriteBatch, "Luiz Felipe Madero Garcia RA: 11028613", 			 	30, 380);
		font.draw(spriteBatch, "Da disciplina de Introducao a Programacao de Jogos", 	30, 300);
		font.draw(spriteBatch, " da Universidade Federal do ABC.", 					 	30, 250);
		font.draw(spriteBatch, "Ministrada por Prof. Dr. Francisco Isidro de Masseto", 	30, 150);

		spriteBatch.end();
	}

}
