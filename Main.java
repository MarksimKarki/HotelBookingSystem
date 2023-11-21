import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String args[]) {
		System.out.println("      Hotel reservation system    ");
		List<Hotel> hotelList = new ArrayList<>();
		List<User> userList = new ArrayList<>();
		
		
		//adding hotel
		List<Room> ChennaiRooms= new ArrayList<>();
		ChennaiRooms.add(new Room(5,2000,"CH-ID-1","AC"));
		ChennaiRooms.add(new Room(5,850,"CH-ID-1","Non-AC"));
		hotelList.add(new Hotel("CH-Hotel A","Chennai",ChennaiRooms,4.2,5,"CH-ID-1"));
		ChennaiRooms.clear();
		ChennaiRooms.add(new Room(4,1200,"CH-ID-2","AC"));
		ChennaiRooms.add(new Room(7,800,"CH-ID-2","Non-AC"));
		hotelList.add(new Hotel("CH-Hotel B","Chennai",ChennaiRooms,4.7,5,"CH-ID-2"));
		ChennaiRooms.clear();
		ChennaiRooms.add(new Room(9,1650,"CH-ID-3","AC"));
		ChennaiRooms.add(new Room(8,700,"CH-ID-3","Non-AC"));
		hotelList.add(new Hotel("CH-Hotel C","Chennai",ChennaiRooms,3.2,5,"CH-ID-3"));
 
		
		List<Room> GoaRooms= new ArrayList<>();
		GoaRooms.add(new Room(5,2000,"GA-ID-1","AC"));
		GoaRooms.add(new Room(5,850,"GA-ID-1","Non-AC"));
		hotelList.add(new Hotel("GA-Hotel A","Goa",GoaRooms,4.2,5,"GA-ID-1"));
		GoaRooms.clear();
		GoaRooms.add(new Room(4,1200,"GA-ID-2","AC"));
		GoaRooms.add(new Room(7,800,"GA-ID-2","Non-AC"));
		hotelList.add(new Hotel("GA-Hotel B","Goa",GoaRooms,4.7,5,"GA-ID-2"));
		GoaRooms.clear();
		GoaRooms.add(new Room(9,1650,"GA-ID-3","AC"));
		GoaRooms.add(new Room(8,700,"GA-ID-3","Non-AC"));
		hotelList.add(new Hotel("GA-Hotel C","Goa",GoaRooms,3.2,5,"GA-ID-3"));
		
		List<Room> VarkalaRooms= new ArrayList<>();
		VarkalaRooms.add(new Room(5,2000,"VK-ID-1","AC"));
		VarkalaRooms.add(new Room(5,850,"VK-ID-1","Non-AC"));
		hotelList.add(new Hotel("VK-Hotel A","Varkala",VarkalaRooms,4.2,5,"VK-ID-1"));
		VarkalaRooms.clear();
		VarkalaRooms.add(new Room(4,1200,"VK-ID-2","AC"));
		VarkalaRooms.add(new Room(7,800,"VK-ID-2","Non-AC"));
		hotelList.add(new Hotel("VK-Hotel B","Varkala",VarkalaRooms,4.7,5,"VK-ID-2"));
		VarkalaRooms.clear();
		VarkalaRooms.add(new Room(9,1650,"VK-ID-3","AC"));
		VarkalaRooms.add(new Room(8,700,"VK-ID-3","Non-AC"));
		hotelList.add(new Hotel("VK-Hotel C","Varkala",VarkalaRooms,3.2,5,"VK-ID-3"));
		
		
		List<Room> MumbaiRooms= new ArrayList<>();
		MumbaiRooms.add(new Room(5,2000,"MU-ID-1","AC"));
		MumbaiRooms.add(new Room(5,850,"MU-ID-1","Non-AC"));
		hotelList.add(new Hotel("MU-Hotel A","Mumbai",MumbaiRooms,4.2,5,"MU-ID-1"));
		MumbaiRooms.clear();
		MumbaiRooms.add(new Room(4,1200,"MU-ID-2","AC"));
		MumbaiRooms.add(new Room(7,800,"MU-ID-2","Non-AC"));
		hotelList.add(new Hotel("MU-Hotel B","Mumbai",MumbaiRooms,4.2,5,"MU-ID-2"));
		MumbaiRooms.clear();
		MumbaiRooms.add(new Room(9,1650,"MU-ID-3","AC"));
		MumbaiRooms.add(new Room(8,700,"MU-ID-3","Non-AC"));
		hotelList.add(new Hotel("MU-Hotel C","Mumbai",MumbaiRooms,4.2,5,"MU-ID-3"));
			
		
		//adding users
		userList.add(new User("kavin","9578681490","kavin"));
		userList.add(new User("karki","7708955729","karki"));
		userList.add(new User("kunal","9750574719","kunal"));
		Scanner scanner = new Scanner(System.in);
		
		while(true){
			Booking book = new Booking();
			Filter filter = new Filter();
			User user = new User();
			
			System.out.println("Enter city name:");
			String city = scanner.next();
			
			try {
				Validation_1.validateCity(city);
			} catch (MyCustomException e) {
				System.out.println(e.getMessage());
			}
			
			if(book.displayCityHotels(hotelList, city)){
				System.out.println("No hotels found at "+city);
				if(book.exit()) return;
				continue;
			}
			else filter.setCity(city);
			
			List<Hotel> filteredHotels;
			while(true){
					System.out.println("1.Add filters\n2.continue");
					
					int choice = scanner.nextInt();
					
					if(choice!=1 && choice!=2) {
						System.out.println("Invalid choice");
						continue;
					}
					if (choice == 2) {
						filteredHotels = filter.FilterHotel(hotelList);
						break;
					}
					
					filter.addfilter();
					filteredHotels = filter.FilterHotel(hotelList);
					if(filteredHotels.size()==0){
						System.out.println("No hotels found");
						if(book.exit()) return;
						continue;
					}
					book.displayFiltered(filteredHotels);
					if(book.exit()) return;
			}
			book.displayFiltered(filteredHotels);
			
			if(filteredHotels.size()==0){
				System.out.println("Sorry! no hotels matches :(");
				if(book.exit()) return;
				continue;
			}
			
			if(book.exit()) return;
			
			System.out.print("Are you a \n1.Admin \n2.User\n3.New User");
			int choiceController=scanner.nextInt();
			if(choiceController==1) {
				Admin admin=new Admin();
				while(!admin.validateAdmin()) {
					System.out.println("Invalid credentials! please try agian");
					if(book.exit()) return;
				}
			}
			else if(choiceController==2) {
			while(!user.ValidateLogin(userList)){
				System.out.println("Invalid credentials! please try agian");
				if(book.exit()) return;
			}
			
			System.out.println("Login successfull");	
			
			while(true) {
				book.ValidateId(filteredHotels);
				System.out.println("Booking successfull");
				System.out.println("do you wish to continue booking");
				if(book.exit()) {
					System.out.print("****Thank for Visiting****");
					return;
				}
			}
			}
			else if(choiceController==3) {
				System.out.println("Adding new User : ");
//				user.addUser(userList);
			}
			else {
				System.out.println("Illegal Entry");
				if(book.exit()) return;
			}
		}
	}
}
