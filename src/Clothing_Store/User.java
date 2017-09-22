package Clothing_Store;

public class User {
	private String name;
	private int waistSize;
	private int footSize;
	private int sleeveSize;
	private int chestSize;
	private int neckSize;
	
	public User(String name, int waistSize, int footSize, int sleeveSize, int chestSize, int neckSize) {
		super();
		this.name = name;
		this.waistSize = waistSize;
		this.footSize = footSize;
		this.sleeveSize = sleeveSize;
		this.chestSize = chestSize;
		this.neckSize = neckSize;
	}

	public String getName() {
		return name;
	}

	public int getWaistSize() {
		return waistSize;
	}

	public int getFootSize() {
		return footSize;
	}

	public int getSleeveSize() {
		return sleeveSize;
	}

	public int getChestSize() {
		return chestSize;
	}

	public int getNeckSize() {
		return neckSize;
	}
	
	
	
}
