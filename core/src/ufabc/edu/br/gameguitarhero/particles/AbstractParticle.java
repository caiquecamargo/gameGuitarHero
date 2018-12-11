package ufabc.edu.br.gameguitarhero.particles;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSystem;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;

public abstract class AbstractParticle {
	protected ParticleSystem  system;
	protected ParticleEffect  effect;
	private boolean			  renderize    = false;
	private float			  timeParticle = 2.0f;
	
	public AbstractParticle(PerspectiveCamera camera) {
		system = new ParticleSystem(); // criei meu sistema
		BillboardParticleBatch particle = new BillboardParticleBatch();
		
		particle.setCamera(camera); // apontei a camera para esta particula
		system.add(particle); // adicionei a particula no sistema
	}
	
	public ParticleEffect getEffect() {
		return effect;
	}
	
	public boolean getRenderize() {
		return renderize;
	}
	
	public void setRenderize(boolean renderize) {
		this.renderize = renderize;
	}
	
	public float getTimeParticle() {
		return timeParticle;
	}
	
	public void setTimeParticle(float timeParticle) {
		this.timeParticle = timeParticle;
	}
	
	public void update() {
		system.update();
		system.begin();
		system.draw();
		system.end();
	}
	
	public ParticleSystem getSystem() {
		return system;
	}
}
