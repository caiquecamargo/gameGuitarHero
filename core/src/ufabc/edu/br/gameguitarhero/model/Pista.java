package ufabc.edu.br.gameguitarhero.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

import ufabc.edu.br.gameguitarhero.MainClass;

public class Pista extends AbstractModel{
	private int state;
	private GameObject gameObject;
	private TextureAttribute tex;
	private boolean renderize = false;

	public Pista(String id) {
		super(id);
		ModelBuilder builder = new ModelBuilder();
		tex = new TextureAttribute(TextureAttribute.createDiffuse((Texture)MainClass.assetManager.get("braco.jpg")));
		gameObject = new GameObject(builder.createBox(0.5f, 0.001f, 2.0f, new Material(tex),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates));
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
	
	public void setRenderize(boolean renderize) {
		this.renderize = renderize;
	}
	
	public boolean getRenderize() {
		return renderize;
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
