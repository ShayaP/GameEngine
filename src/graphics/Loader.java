package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.lwjgl.opengl.GL11;

public class Loader {
	
	private ArrayList<Integer> vao = new ArrayList<>();
	private ArrayList<Integer> vbo = new ArrayList<>();
	private ArrayList<Integer> textures = new ArrayList<>();
	
	public Model loadToVAO(float[] positions, float[] textureCoords,  int[] indices) {
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInList(0, 3, positions);
		storeDataInList(1, 2, textureCoords);
		unbindVAO();
		return new Model(vaoID, indices.length);
	}
	public Model loadToVAO(float[] positions) {
		int voaID = createVAO();
		storeDataInList(0, 2, positions);
		unbindVAO();
		return new Model(voaID, positions.length / 2);
	}
	
	public int loadTexture(String file) {
		
		Texture tex = null;
		try {
			tex = TextureLoader.getTexture("PNG", new FileInputStream("res/"+file));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		int textureID = tex.getTextureID();
		textures.add(textureID);
		return textureID;
	}
	
	public void cleanUp() {
		for(int x : vao) {
			GL30.glDeleteVertexArrays(x);
		}
		
		for(int i : vbo) {
			GL15.glDeleteBuffers(i);
		}
		for(int t : textures) {
			GL11.glDeleteTextures(t);
		}
	}
	
	private int createVAO() {
		int vaoID = GL30.glGenVertexArrays();
		vao.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
		
	}
	
	private void storeDataInList(int attNum, int size, float[] data) {
		int vboId = GL15.glGenBuffers();
		vbo.add(vboId);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		FloatBuffer buffer = StoreDataInBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attNum, size, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
	}
	
	private void bindIndicesBuffer(int [] indices) {
		int vboID = GL15.glGenBuffers();
		vbo.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
	}
	
	private IntBuffer storeIntBuffer(int [] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	private FloatBuffer StoreDataInBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
