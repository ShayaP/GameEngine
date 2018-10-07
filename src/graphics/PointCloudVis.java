package graphics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;


public class PointCloudVis {
	
	public static void main(String [] args) {
		
		Loader loader = new Loader();
		DisplayManager.createDisplay();
		Model model = loadCloud(loader);
		Cloud cloud = new Cloud(model,0,0,0,1,new Vector3f(0,-1,-3));
		Camera camera = new Camera();
		
		MasterRenderer renderer = new MasterRenderer();
		while(!Display.isCloseRequested()) {
			camera.move(cloud);
			renderer.render(camera, cloud);
			DisplayManager.updateDisplay();
		}
		renderer.cleaUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
	
	public static Model loadCloud(Loader loader) {
		ArrayList<Vector3f> pc = new ArrayList<>();
		float[] verticesArray = null;
		FileReader fr = null;
		try {
			fr = new FileReader(new File("res/points.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(fr);
		String line;
		try {
			while((line = reader.readLine()) != null) {
				String [] tokens = line.split(" ");
				Vector3f p = new Vector3f(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
				pc.add(p);
			}
			reader.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		verticesArray = new float[pc.size() * 3];
		
		int vertexPointer = 0;
		for(Vector3f vector : pc) {
			verticesArray[vertexPointer++] = vector.x;
			verticesArray[vertexPointer++] = vector.y;
			verticesArray[vertexPointer++] = vector.z;
		}
		return loader.loadToVAO(verticesArray);
	}
}
