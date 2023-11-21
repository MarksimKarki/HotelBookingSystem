import java.util.*;
public class Hotel {
	private String name;
	private String city;
	private List<Room> rooms;
	private List<String> review=new ArrayList<>();
	private double ratings;
	private int rateCount;
	private String id;
	
	public Hotel(String name, String city, List<Room> rooms, double ratings, int rateCount, String id) {
		super();
		this.name = name;
		this.city = city;
		this.rooms = rooms;
		this.ratings = ratings;
		this.rateCount = rateCount;
		this.id = id;
	}

	public void addReview(String s) {
		review.add(s);
	}
	public List<String> getReview(){
		return review;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public double getRatings() {
		return ratings;
	}

	public void setRatings(double ratings) {
		this.ratings = ratings;
	}

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	

	
	
}
	
