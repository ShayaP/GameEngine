package graphics;

import org.lwjgl.util.vector.Matrix4f;

import toolBox.Maths;

public class StaticShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = "src/graphics/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/graphics/fragmentShader.txt";
	private int locationTrans, locationProj, locationView;

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}
	
	@Override
	protected void getAllUniformLocations() {
		locationTrans = super.getUniformLocation("transformationMatrix");
		locationProj = super.getUniformLocation("projectionMatrix");
		locationView = super.getUniformLocation("viewMatrix");
	}

	public void loadMatrix(Matrix4f matrix) {
		super.loadMatrix(locationTrans, matrix);
	}
	
	public void loadProjection(Matrix4f projection) {
		super.loadMatrix(locationProj, projection);
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(locationView, viewMatrix);
	}
}
