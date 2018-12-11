package ufabc.edu.br.gameguitarhero.particles;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader;
import com.badlogic.gdx.math.Vector3;

import ufabc.edu.br.gameguitarhero.AssetsManagement.AssetManagement;

public class GreenParticle extends AbstractParticle{

	public GreenParticle(PerspectiveCamera camera) {
		super(camera);
		
		//Cria as partículas verdes do jogo
		ParticleEffectLoader.ParticleEffectLoadParameter loaderParam;
		loaderParam = new ParticleEffectLoader.ParticleEffectLoadParameter(system.getBatches());
		ParticleEffectLoader loader = new ParticleEffectLoader(new InternalFileHandleResolver());

		AssetManagement.assetManager.setLoader(ParticleEffect.class, loader);
		AssetManagement.assetManager.load("particles/cylinder_green2.p", ParticleEffect.class, loaderParam);
		AssetManagement.assetManager.finishLoading();
		
		effect = AssetManagement.assetManager.get("particles/cylinder_green2.p", ParticleEffect.class).copy();
		effect.init();
		Vector3 translation = new Vector3(-0.2f, 0.45f, 4.6f);
		effect.translate(translation);
		effect.scale(0.1f, 0.1f, 0.1f);
		system.add(effect);
	}

}