package graphics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;


public class OBJLoader {
		
	public static Model loadModel(String file, Loader loader) {
		FileReader fr = null;
		try {
			fr = new FileReader(new File("res/"+file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(fr);
		String line;
		ArrayList<Vector3f> vertices = new ArrayList<>();
		ArrayList<Vector2f> textures = new ArrayList<>();
		ArrayList<Vector3f> normals = new ArrayList<>();
		ArrayList<Integer> indices = new ArrayList<>();
		float[] verticesArray = null;
		float[] texturesArray = null;
		float[] normalsArray = null;
		int[] indicesArray = null;
		
		try {
			while(true) {
				line = reader.readLine();
				String[] currLine = line.split(" ");
				if (line.startsWith("v ")) {
					try {
						Vector3f vertex = new Vector3f(Float.parseFloat(currLine[1]), Float.parseFloat(currLine[2]), Float.parseFloat(currLine[3]));
						vertices.add(vertex);
					} catch (Exception e) {
						System.out.println("caught error while reading file");
					}
				} else if (line.startsWith("vt ")) {
					Vector2f texture = new Vector2f(Float.parseFloat(currLine[1]), Float.parseFloat(currLine[2]));
					textures.add(texture);
				} else if (line.startsWith("vn ")) {
					Vector3f normal = new Vector3f(Float.parseFloat(currLine[1]), Float.parseFloat(currLine[2]), Float.parseFloat(currLine[3]));
					normals.add(normal);
				} else if (line.startsWith("f ")) {
					texturesArray = new float[vertices.size() * 2];
					normalsArray = new float[vertices.size() * 3];
					break;
				}
			}
			while(line != null) {
				if (!line.startsWith("f ")) {
					line = reader.readLine();
					continue;
				}
				String[] current = line.split(" ");
				String[] vertex1 = current[1].split("/");
				String[] vertex2 = current[2].split("/");
				String[] vertex3 = current[3].split("/");
				
				proccesVertex(vertex1, indices, textures, normals, texturesArray, normalsArray);
				proccesVertex(vertex2, indices, textures, normals, texturesArray, normalsArray);
				proccesVertex(vertex3, indices, textures, normals, texturesArray, normalsArray);
				line = reader.readLine();
			}
			reader.close();
				
		} catch(Exception e) {
			e.printStackTrace();
		}
		verticesArray = new float[vertices.size() * 3];
		indicesArray = new int[indices.size()];
		
		int vertexPointer = 0;
		for(Vector3f vector : vertices) {
			verticesArray[vertexPointer++] = vector.x;
			verticesArray[vertexPointer++] = vector.y;
			verticesArray[vertexPointer++] = vector.z;
		}
		for(int i = 0; i < indices.size(); i++) {
			indicesArray[i] = indices.get(i);
		}
		return loader.loadToVAO(verticesArray, texturesArray, indicesArray);
	}
	
	private static void proccesVertex(String[] vertexData, ArrayList<Integer> indices, 
			ArrayList<Vector2f> textures, ArrayList<Vector3f> normals, float[] textureArr, float[] normalArr) {
		
		int currentVertexPointer = Integer.parseInt(vertexData[0]) -1;
		indices.add(currentVertexPointer);
		Vector2f currTex = textures.get(Integer.parseInt(vertexData[1]) -1);
		textureArr[currentVertexPointer * 2] = currTex.x;
		textureArr[currentVertexPointer * 2 + 1] = 1 - currTex.y;
		Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2]) -1);
		normalArr[currentVertexPointer * 3] = currentNorm.x;
		normalArr[currentVertexPointer * 3 + 1] = currentNorm.y;
		normalArr[currentVertexPointer * 3 + 2] = currentNorm.z;
		
		
	}
	
}
