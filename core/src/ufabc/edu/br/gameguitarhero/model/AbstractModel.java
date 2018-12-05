package ufabc.edu.br.gameguitarhero.model;

public abstract class AbstractModel {
	private String id;
	public abstract void update(float delta);
	public abstract GameObject getCurrent();
	public abstract int getState();
	public abstract void setState(int state);
	public abstract void setGamePosition();
	
	public AbstractModel (String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
