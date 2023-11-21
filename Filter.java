import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Filter {
	private double rating;
	private String roomType;
	private int price;
	private String city;
	public Filter() {
		this.rating = -1;
		this.roomType = "";
		this.price = -1;
	}
	public void setCity(String city){
		this.city = city;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void addfilter(){
		Scanner sc=new Scanner(System.in);
		System.out.println("----------------------------------------");
		System.out.println("Enter Filtering type");
		System.out.println("1.Rating");
		System.out.println("2.Type of the room");
		System.out.println("3.Price");
		System.out.println("4.Clear Rating");
		System.out.println("5.Clear Type of Room");
		System.out.println("6.Clear City");
		System.out.println("----------------------------------------");
		
		int choice =sc.nextInt();
		switch (choice) {
		
		case 1:
			double rating;
			System.out.println("Enter the rating:");
			rating = sc.nextDouble();
			try {
				Validation_1.validateRating(rating);
			} catch (MyCustomException e) {
				System.out.println(e.getMessage());
			}
			this.rating=rating;
			break;
			
		case 2:
			String roomType;
			System.out.println("Enter the type of room:(AC/NonAC)");
			roomType=sc.next();
			try {
				Validation_1.validateRoomType(roomType);
			} catch (MyCustomException e) {
				System.out.println(e.getMessage());
			}
			this.roomType=roomType;
			break;
			
		case 3:
			int price;
			System.out.println("Enter the minimum price :");
			price = sc.nextInt();
			try {
				Validation_1.validatePrice(price);
			} catch (MyCustomException e) {
				System.out.println(e.getMessage());
			}
			this.price=price;
			break;
		case 4:
			setRating(-1);
			break;
		case 5:
			setRoomType("");
			break;
		case 6:
			setPrice(-1);
			break;
		default:
			System.out.println("Wrong choice");
			break;
		}
	}

	public List<Hotel> FilterHotel(List<Hotel> hotels){
		List<Hotel> filteredList = new ArrayList<>();
		
		for(Hotel hotel : hotels){
			
			if(price==-1 && roomType.isEmpty() && rating==-1 && hotel.getCity().equalsIgnoreCase(city)){
				List<Room> room1 = new ArrayList<>();
				room1.add(hotel.getRooms().get(0));
				Hotel FilHotel1 = new Hotel(hotel.getName(),hotel.getCity(),room1,hotel.getRatings(),hotel.getRateCount(),hotel.getId()+"-AC");
				filteredList.add(FilHotel1);
				
				List<Room> room2 = new ArrayList<>();
				room2.add(hotel.getRooms().get(1));
				Hotel FilHotel2 = new Hotel(hotel.getName(),hotel.getCity(),room2,hotel.getRatings(),hotel.getRateCount(),hotel.getId()+"-NON-AC");
				filteredList.add(FilHotel2);
				continue;
			}
			
			if(!hotel.getCity().equalsIgnoreCase(city)) continue;
			int hotelAcPrice = hotel.getRooms().get(0).getPrice();
			int hotelNonAcPrice = hotel.getRooms().get(1).getPrice();
			double hotelRating = hotel.getRatings();
			int acCount = hotel.getRooms().get(0).getCount();
			int nonAcCount = hotel.getRooms().get(1).getCount();

			int acTypeFlag = -1;
			int nonAcTypeFlag=-1;
			int ratingFlag = -1;
			int priceFlag = -1;
			
			if(roomType.isEmpty()) {
				acTypeFlag=1;
				nonAcTypeFlag=1;
				if(price==-1 || (price>hotel.getRooms().get(0).getPrice() || price>hotel.getRooms().get(1).getPrice())){
					priceFlag=1;
				}
			}
			if((roomType.equalsIgnoreCase("AC") && acCount>0 && (price==-1 || price>hotelAcPrice))){
				priceFlag=1;
				acTypeFlag=1;
			}
			if(roomType.equalsIgnoreCase("nonAC") && nonAcCount>0 && (price==-1 || price>hotelNonAcPrice)){
				priceFlag=1;
				nonAcTypeFlag=1;
			}
			
			if(rating==-1 || hotelRating>rating){
				ratingFlag=1;
			}
			//System.out.println(acTypeFlag+" "+nonAcTypeFlag+" "+ratingFlag+" "+priceFlag);
			if(acTypeFlag==1 && ratingFlag==1 && priceFlag==1) {
				List<Room> room = new ArrayList<>();
				room.add(hotel.getRooms().get(0));
				Hotel FilHotel = new Hotel(hotel.getName(),hotel.getCity(),room,hotel.getRatings(),hotel.getRateCount(),hotel.getId()+"-AC");
				filteredList.add(FilHotel);
			}
			if(nonAcTypeFlag==1 && ratingFlag==1 && priceFlag==1) {
				List<Room> room = new ArrayList<>();
				room.add(hotel.getRooms().get(1));
				Hotel FilHotel = new Hotel(hotel.getName(),hotel.getCity(),room,hotel.getRatings(),hotel.getRateCount(),hotel.getId()+"-NON-AC");
				filteredList.add(FilHotel);
			}
		}
		return filteredList;
	}
}
