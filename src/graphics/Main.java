package graphics;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Main {
	public static void main(String [] args) {
		

		Loader loader = new Loader();
		DisplayManager.createDisplay();
		
//		//GUIs
//		ArrayList<GUITexture> guis = new ArrayList<>();
//		GUITexture button = new GUITexture(loader.loadTexture("cat.png"), new Vector2f(0.65f, 0.65f), new Vector2f(0.08f, 0.08f));
//		GUITexture button2 = new GUITexture(loader.loadTexture("cat.png"), new Vector2f(0.65f, 0.35f), new Vector2f(0.08f, 0.08f));
//		guis.add(button);
//		guis.add(button2);
//		GUIRenderer guiRenderer = new GUIRenderer(loader);
//		GUIShader shaderGUI = new GUIShader();
//		
		
		Model model = OBJLoader.loadModel("cat.obj", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("catTex.png"));
		TexturedModel texModel = new TexturedModel(model, texture);
		Entity entity = new Entity(texModel,0,0,0,1,new Vector3f(0,-1,-3));
		Camera camera = new Camera();
		

		MasterRenderer renderer = new MasterRenderer();
		while(!Display.isCloseRequested()) {
			camera.move(entity);
			renderer.processEntity(entity);
			renderer.render(camera);
//			shaderGUI.start();
//			guiRenderer.render(guis, shaderGUI);
//			shaderGUI.stop();
			DisplayManager.updateDisplay();
		}
//		guiRenderer.cleanUp();
		renderer.cleaUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}	
}
