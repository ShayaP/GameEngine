package graphics;

class Model {
	
	private int vaoId;
	private int vertecCount;
	
	public Model(int vaoId, int vertecCount) {
		this.vaoId = vaoId;
		this.vertecCount = vertecCount;
	}
	
	public int getVaoId() {
		return vaoId;
	}
	
	public int getVertecCount() {
		return vertecCount;
	}
}