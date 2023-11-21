import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Admin {
	private final String adminId="admin";
	private String adminPassword="admin@123";
	Scanner scan = new Scanner(System.in);
	
	public void addNewHotel(List<Hotel> hotelList) {
		String name;
		String city;
		 int count;
		 int price;
		 String idRoom;
		 String type;
		List<Room> rooms=new ArrayList<>();
		List<String> review=new ArrayList<>();
		
		String id;
		
		System.out.println("Enter the name of the hotel: ");
		name = scan.next();
		System.out.println("Enter city name: ");
		city=scan.next();
		
		while(true) {
		System.out.println("Enter Room details: \n Enter the type of the room");
		type=scan.next();
		System.out.println("Enter the price of the room: ");
		price=scan.nextInt();
		System.out.println("Enter the count of rooms: ");
		count=scan.nextInt();
		System.out.println("Enter the ID for the room: ");
		idRoom=scan.next();
		Room room=new Room(count,price,idRoom,type);
		rooms.add(room);
		System.out.println("Do you want to add rooms further");
		System.out.println("1.yes \n 2.no");
		int choice=scan.nextInt();
		if(choice==2) {
			break;
		}
		if(choice!=1 && choice!=2) {
			System.out.println("Invalid choice");
			break;
		}
		}
		
		System.out.print("Enter ID for the hotel:");
		id=scan.next();
		Hotel hotel=new Hotel(name,city,rooms,0,0,id);
		hotelList.add(hotel);
	}
	
	public boolean validateAdmin() {
		System.out.println("Enter Id:");
		String adminId = scan.next();
		System.out.print("Enter password");
		String password = scan.next();
		
		return (adminId.equals(adminId) && password.equals(adminPassword));
	}
}
