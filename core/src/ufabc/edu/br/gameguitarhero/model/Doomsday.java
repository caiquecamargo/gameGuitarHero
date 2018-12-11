package ufabc.edu.br.gameguitarhero.model;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.math.Vector3;

import ufabc.edu.br.gameguitarhero.AssetsManagement.AssetManagement;
import ufabc.edu.br.gameguitarhero.util.GamingPhases;

public class Doomsday extends AbstractModel{
	private int 		   state     = 0;
	private int			   animation = 0;
	private GameObject[][] gameObject;
	private String[]	   description;

	public Doomsday(String id) {
		super(id);
		
		//Array de animações do modelo.
		gameObject = new GameObject[10][3];
		gameObject[GamingPhases.PRESENTATION][0] = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Warrior Idle.g3db"));
		gameObject[GamingPhases.PRESENTATION][1] = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Idle.g3db"));
		gameObject[GamingPhases.PRESENTATION][2] = null;
		gameObject[GamingPhases.INIT][0] 		 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Shoulder Rubbing.g3db"));
		gameObject[GamingPhases.INIT][1] 		 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Clapping.g3db"));
		gameObject[GamingPhases.INIT][2] 		 = null;
		gameObject[GamingPhases.EXCITED][0] 	 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Cheering (1).g3db"));
		gameObject[GamingPhases.EXCITED][1] 	 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Cheering.g3db"));
		gameObject[GamingPhases.EXCITED][2] 	 = null;
		gameObject[GamingPhases.OUSTANDING][0] 	 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Silly Dancing (1).g3db"));
		gameObject[GamingPhases.OUSTANDING][1] 	 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Guitar Playing.g3db"));
		gameObject[GamingPhases.OUSTANDING][2] 	 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Silly Dancing.g3db"));
		gameObject[GamingPhases.BREAK][0] 		 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Angry Point.g3db"));
		gameObject[GamingPhases.BREAK][1] 		 = null;
		gameObject[GamingPhases.BREAK][2] 		 = null;
		gameObject[GamingPhases.ERRORS][0] 		 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Defeat.g3db"));
		gameObject[GamingPhases.ERRORS][1] 		 = null;
		gameObject[GamingPhases.ERRORS][2] 		 = null;	
		gameObject[GamingPhases.DECREASING][0]	 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Threatening.g3db"));
		gameObject[GamingPhases.DECREASING][1] 	 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Standing Taunt Battlecry.g3db"));
		gameObject[GamingPhases.DECREASING][2]	 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Shaking Head No.g3db"));
		gameObject[GamingPhases.LOOSING][0]		 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Agony.g3db"));
		gameObject[GamingPhases.LOOSING][1]		 = null;
		gameObject[GamingPhases.LOOSING][2]		 = null;
		gameObject[GamingPhases.GAMEOVER][0]	 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Defeated.g3db"));
		gameObject[GamingPhases.GAMEOVER][1]	 = null;
		gameObject[GamingPhases.GAMEOVER][2]	 = null;
		gameObject[GamingPhases.WINNER][0]		 = new GameObject((Model)AssetManagement.assetManager.get("models/Doomsday/Victory.g3db"));
		gameObject[GamingPhases.WINNER][1]		 = null;
		gameObject[GamingPhases.WINNER][2]		 = null;
		
		//Array com a descrição do modelo.
		description = new String[4];
		description[0] = "The agony of their repeated deaths";
		description[1] = "was recorded in his genes,";
		description[2] = "driving the creature";
		description[3] = "to hate all life.";
	
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 3; j++) {
				if(gameObject[i][j] != null) {
					gameObject[i][j].transform.translate(-1.0f, 0, 0);
					gameObject[i][j].transform.rotate(Vector3.Y, 15);
				}
			}
		}
	}

	@Override
	public void update(float delta) {
		//Lógica de transição das animações de acordo com o estado do jogo.
		gameObject[state][animation].update(delta);
		if(state == GamingPhases.PRESENTATION) {
			if (animation == 0 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.PRESENTATION;
				animation = 1;
			}else if(animation == 1 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.PRESENTATION;
				animation = 0;
			}
		}
		else if(state == GamingPhases.INIT) {
			if (animation == 0 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.INIT;
				animation = 1;
			}else if(animation == 1 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.INIT;
				animation = 0;
			}
		}
		else if(state == GamingPhases.EXCITED) {
			if (animation == 0 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.EXCITED;
				animation = 1;
			}else if(animation == 1 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.EXCITED;
				animation = 0;
			}
		}
		else if(state == GamingPhases.OUSTANDING) {
			if (animation == 0 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.OUSTANDING;
				animation = 1;
			}else if(animation == 1 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.OUSTANDING;
				animation = 2;
			}else if(animation == 3 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.OUSTANDING;
				animation = 0;
			}
		}
		else if(state == GamingPhases.BREAK) {
			if (animation == 0 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.BREAK;
				animation = 0;
			}
		}
		else if(state == GamingPhases.ERRORS) {
			if (animation == 0 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.ERRORS;
				animation = 0;
			}
		}
		else if(state == GamingPhases.DECREASING) {
			if (animation == 0 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.DECREASING;
				animation = 1;
			}else if(animation == 1 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.DECREASING;
				animation = 2;
			}else if(animation == 2 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.DECREASING;
				animation = 0;
			}
		}
		else if(state == GamingPhases.LOOSING) {
			if (animation == 0 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.LOOSING;
				animation = 0;
			}
		}
		else if(state == GamingPhases.GAMEOVER) {
			if (animation == 0 && gameObject[state][animation].isDone()) {
				state = GamingPhases.GAMEOVER;
				animation = 0;
			}
		}
		else if(state == GamingPhases.WINNER) {
			if (animation == 0 && gameObject[state][animation].isDone()) {
				gameObject[state][animation].reset();
				state = GamingPhases.WINNER;
				animation = 0;
			}
		}
	}

	@Override
	public GameObject getCurrent() {
		return gameObject[state][animation];
	}

	@Override
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
		animation = 0;
	}

	@Override
	public void setGamePosition() {
		//Leva o modelo para a sua posição no mundo do jogo.
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 3; j++) {
				if(gameObject[i][j] != null) {
					gameObject[i][j].transform.scale(0.1f, 0.1f, 0.1f);
					gameObject[i][j].transform.translate(-4.5f, 0.0f, 47.0f);
				}
			}
		}	
	}
	
	public void setTexture(TextureAttribute tex) {
		//Muda a textura do modelo.
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 3; j++) {
				if(gameObject[i][j] != null) {
					for(Material mat:gameObject[i][j].materials)
						mat.set(tex);
				}
			}
		}
	}
	
	public String[] getDescription() {
		return description;
	}
}
