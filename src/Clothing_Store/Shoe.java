package Clothing_Store;

public class Shoe implements Clothing{
	
	private int price;
	private String size;
	private String color;
	private String name;
	
	public Shoe(String name, String color, String size, int price) {
		this.price = price;
		this.name = name;
		this.color = color;
		this.size = size;
	}
	
	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public String getSize() {
		return size;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getColor() {
		return color;
	}
	
}
