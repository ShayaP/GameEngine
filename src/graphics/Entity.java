package graphics;

import org.lwjgl.util.vector.Vector3f;

public class Entity {
	
	private TexturedModel model;
	private float rx, ry, rz;
	private float scale;
	private Vector3f position;
	
	public Entity(TexturedModel model, float rx, float ry, float rz, float scale, Vector3f position) {
		this.model = model;
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
		this.scale = scale;
		this.position = position;
	}
	
	public void increasePosition(float dx, float dy, float dz) {
		position.x += dx;
		position.y += dy;
		position.z += dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		rx += dx;
		ry += dy;
		rz += dz;
	}
	
	public TexturedModel getModel() {
		return model;
	}
	
	public void setModel(TexturedModel model) {
		this.model = model;
	}
	
	public float getRx() {
		return rx;
	}
	
	public void setRx(float rx) {
		this.rx = rx;
	}
	
	public float getRy() {
		return ry;
	}
	
	public void setRy(float ry) {
		this.ry = ry;
	}
	
	public float getRz() {
		return rz;
	}
	
	public void setRz(float rz) {
		this.rz = rz;
	}
	
	public float getScale() {
		return scale;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	
	
}
