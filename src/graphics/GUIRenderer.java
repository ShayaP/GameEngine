package graphics;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import toolBox.Maths;

public class GUIRenderer {
	private final Model gui;
	private GUIShader shader; 
	
	public GUIRenderer(Loader loader) {
		float[] positions  = {-1,1,-1,-1,1,1,1,-1};
		gui = loader.loadToVAO(positions);
		shader = new GUIShader();
	}
	public void render(List<GUITexture> guis, GUIShader shader) {
		GL30.glBindVertexArray(gui.getVaoId());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		for (GUITexture tex : guis) {
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex.getTexture());
			Matrix4f matrix = Maths.createTransformationMatrix(tex.getPosition(), tex.getScale());
			shader.loadTransformation(matrix);
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, gui.getVertecCount());
		}
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}
	public void cleanUp() {
		shader.cleanUp();
	}
}
