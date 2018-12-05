package ufabc.edu.br.gameguitarhero.model;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;

import ufabc.edu.br.gameguitarhero.MainClass;

public class FellMonster extends AbstractModel{
	private int state;
	private GameObject gameObject;

	public FellMonster(String id) {
		super(id);
		gameObject = new GameObject((Model)MainClass.assetManager.get("models/fellguard.g3db"));
		gameObject.transform.scale(0.4f, 0.4f, 0.4f);
		gameObject.transform.rotate(Vector3.Y, -165);
		gameObject.transform.translate(2, 0, 0);
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

	@Override
	public void setGamePosition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setState(int state) {
		// TODO Auto-generated method stub
		
	}
}
