package ufabc.edu.br.gameguitarhero.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class Disco extends AbstractModel{
	private int 	   state;
	private GameObject gameObject;
	private Attribute  attrib[];
	private boolean    collided = false;

	public Disco(String id, Color color) {
		super(id);
		
		//Atributos de iluminação do modelo.
		attrib = new Attribute[5];
		attrib[0] = ColorAttribute.createAmbient(Color.BLACK);
		attrib[1] = ColorAttribute.createDiffuse(color);
		attrib[2] = ColorAttribute.createReflection(Color.WHITE);
		attrib[3] = ColorAttribute.createSpecular(color);
		attrib[4] = ColorAttribute.createSpecular(Color.WHITE);
		
		//Criação do modelo.
		ModelBuilder builder = new ModelBuilder();
		gameObject = new GameObject(builder.createCylinder(0.08f, 0.005f, 0.05f, 100, new Material(attrib), Usage.Position | Usage.Normal));
	}
	
	

	@Override
	public void update(float delta) {
		gameObject.update(delta);
	}
	
	public void press() {
		gameObject.transform.translate(0.0f, 0.01f, 0.0f);
	}
	
	public void release() {
		gameObject.transform.translate(0.0f, -0.01f, 0.0f);
	}

	@Override
	public GameObject getCurrent() {
		return gameObject;
	}

	@Override
	public int getState() {
		return state;
	}
	
	public boolean getCollided() {
		return collided;
	}
	
	public void setCollided(boolean collided) {
		this.collided = collided;
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
