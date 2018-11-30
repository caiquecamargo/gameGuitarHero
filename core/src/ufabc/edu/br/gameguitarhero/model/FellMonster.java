package ufabc.edu.br.gameguitarhero.model;

import com.badlogic.gdx.graphics.g3d.Model;

import ufabc.edu.br.gameguitarhero.MainClass;

public class FellMonster extends AbstractModel{
	private int state;
	private GameObject gameObject;

	public FellMonster(String id) {
		super(id);
		gameObject = new GameObject((Model)MainClass.assetManager.get("models/fellguard.g3db"));
	}

	@Override
	public void update(float delta) {
				
	}

	@Override
	public GameObject getCurrent() {
		return gameObject;
	}

	@Override
	public int getState() {
		return state;
	}
}
