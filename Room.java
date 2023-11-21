

public class Room{
	
	private int count;
	private int price;
	private String id;
	private String type;
	
	
	public Room(int count, int price, String id, String type) {
		this.count = count;
		this.price = price;
		this.id = id;
		this.type = type;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Room [count=" + count + ", price=" + price + ", id=" + id + ", type=" + type + "]";
	}
	
}
