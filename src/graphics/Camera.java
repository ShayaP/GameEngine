package graphics;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f position = new Vector3f(0,0,0);
	private float yaw, pitch;
	
	public void move(Entity entity) {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z -= 0.02f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x -= 0.02f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x += 0.02f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			entity.increaseRotation(0, 1, 0);
		} 
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			entity.increaseRotation(0, -1, 0);
		} 
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z += 0.02f;
		}
	}
	
	public void move(Cloud entity) {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z -= 0.02f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x -= 0.02f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x += 0.02f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			entity.increaseRotation(0, 1, 0);
		} 
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			entity.increaseRotation(0, -1, 0);
		} 
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z += 0.02f;
		}
	}
	
	public Camera() {}

	public Vector3f getPosition() {
		return position;
	}

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}
	
	
	
}
