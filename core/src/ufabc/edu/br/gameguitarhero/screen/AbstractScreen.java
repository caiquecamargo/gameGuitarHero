package ufabc.edu.br.gameguitarhero.screen;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen{
	public abstract void update(float delta);
	public abstract void draw(float delta);
	private boolean done;
	private String  id;
	
	public AbstractScreen(String id) {
		this.id = id;
	}
	
	public String getId() {  
		return this.id;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}
	public boolean isDone() {
		return this.done;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}	
}
