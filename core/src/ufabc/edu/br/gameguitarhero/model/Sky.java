package ufabc.edu.br.gameguitarhero.model;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;

import ufabc.edu.br.gameguitarhero.AssetsManagement.AssetManagement;

public class Sky extends AbstractModel{
	private int state;
	private GameObject gameObject;

	public Sky(String id) {
		super(id);
		
		//Cria o SkyTexture do jogo.
		gameObject = new GameObject((Model)AssetManagement.assetManager.get("models/sky2.g3db"));
		gameObject.transform.scale(15, 15, 15);
		gameObject.transform.translate(0, 0, 0.0f);
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
