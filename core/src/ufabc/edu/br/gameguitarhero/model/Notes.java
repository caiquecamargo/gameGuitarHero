package ufabc.edu.br.gameguitarhero.model;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;

import ufabc.edu.br.gameguitarhero.AssetsManagement.AssetManagement;
import ufabc.edu.br.gameguitarhero.util.Colors;

public class Notes extends AbstractModel{
	private int 		state;
	private GameObject 	gameObject;
	private int			color;
	private boolean 	renderize = false;

	public Notes(String id, int color) {
		super(id);
		
		//Cria os modelos das notas do jogo
		this.color = color;
		
		switch(color) {
		case Colors.GREEN:
			gameObject = new GameObject((Model)AssetManagement.assetManager.get("models/green.g3db"));
			break;
		case Colors.YELLOW:
			gameObject = new GameObject((Model)AssetManagement.assetManager.get("models/yellow.g3db"));
			break;
		case Colors.RED:
			gameObject = new GameObject((Model)AssetManagement.assetManager.get("models/red.g3db"));
			break;
		case Colors.BLUE:
			gameObject = new GameObject((Model)AssetManagement.assetManager.get("models/blue.g3db"));
			break;
		case Colors.ORANGE:
			gameObject = new GameObject((Model)AssetManagement.assetManager.get("models/orange.g3db"));
			break;
		}
		
	}

	@Override
	public void update(float delta) {
		gameObject.update(delta);		
	}
	
	public void translate(Vector3 velocidade) {
		gameObject.transform.translate(velocidade);
	}

	@Override
	public GameObject getCurrent() {
		return gameObject;
	}

	@Override
	public int getState() {
		return state;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setRenderize(boolean renderize) {
		this.renderize = renderize;
	}
	
	public boolean getRenderize() {
		return renderize;
	}

	@Override
	public void setGamePosition() {
		
	}

	@Override
	public void setState(int state) {
		
	}

	@Override
	public String[] getDescription() {
		return null;
	}
}
