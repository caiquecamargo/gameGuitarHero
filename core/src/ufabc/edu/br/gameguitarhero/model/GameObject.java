package ufabc.edu.br.gameguitarhero.model;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationDesc;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class GameObject extends ModelInstance{
	
	private AnimationController animationController;
	private boolean 			done;
	private float 				angle = 0.0f;
	private BoundingBox 		boundingBox;
	private Vector3     		originalMax;
	private Vector3     		originalMin;

	public GameObject(Model model) {
		super(model);
		
		done = false;
		
		boundingBox = new BoundingBox();
		calculateBoundingBox(boundingBox);
		originalMax = new Vector3();
		originalMin = new Vector3();
		boundingBox.getMax(originalMax);
		boundingBox.getMin(originalMin);

		animationController = new AnimationController(this);
		if (animations.size > 0) {
			animationController.setAnimation(this.animations.get(0).id, -1,
					new AnimationController.AnimationListener() {

						@Override
						public void onLoop(AnimationDesc animation) {
							done = true;
						}

						@Override
						public void onEnd(AnimationDesc animation) {
							done = true;
						}
					});
		}
	}
	
	public void setAngle(float angle) {
		this.angle += angle;
	}
	public float getAngle() {
		return this.angle;
	}

	public void update(float delta) {
		animationController.update(delta);
		Vector3 position = new Vector3();
		transform.getTranslation(position);
		boundingBox.set(originalMin.cpy().add(position), originalMax.cpy().add(position));
		
	}

	public boolean isDone() {
		return done;
	}

	public void reset() {
		done = false;
	}
	
	public boolean collidesWith(GameObject other) {
		return this.boundingBox.intersects(other.boundingBox);
	}
	
}
