import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
abstract class HotelBooking {
	Scanner scanner = new Scanner(System.in);
	
	public void displayHotels(Hotel hotel){
		System.out.println("--------------------------------------");
		System.out.println("Hotel id : "+hotel.getId());
		System.out.println("Hotel name : "+hotel.getName());
		System.out.println("Hotel city : "+hotel.getCity());
		System.out.println("Hotel Rating: "+hotel.getRatings());
		System.out.println("Hotel reviews: "+hotel.getReview());
		System.out.println("Hotel Rooms : " );
		for(Room room : hotel.getRooms()) {
			System.out.println(room);
		}
		double rating = hotel.getRatings();
		if(hotel.getRateCount()==0) System.out.println("No Reviews found");
		else if(rating<2) System.out.println("Overall status:bad");
		else if(rating>=2 && rating<4) System.out.println("Overall status:good");
		else if(rating>=4 && rating<=5) System.out.println("Overall status :Very good");
		System.out.println("--------------------------------------");
	}
	
	public boolean displayCityHotels(List<Hotel> hotels,String city){
		int count=0;
		for(Hotel hotel:hotels){
			if(hotel.getCity().equalsIgnoreCase(city)){
				count++;
				displayHotels(hotel);
			}
		}
		if(count==0) return true;
		else{ 
			System.out.println(count+" hotels found");
			return false;
		}
	}

	public void displayFiltered(List<Hotel> hotels){
		for(Hotel hotel : hotels){
			displayHotels(hotel);
		}
	}
	
	public boolean exit(){
		String exit="";
		System.out.println("To exit press 1 ,press any key to continue");
		exit = scanner.next();
		return exit.equals("1");
	}

	public abstract boolean ValidateId(List<Hotel> hotels);
}
public class Booking extends HotelBooking{
	Scanner scan = new Scanner(System.in);
	public boolean ValidateId(List<Hotel> hotels){
		displayFiltered(hotels);
		System.out.println("Enter hotel Id in from the list: ");
		String hotelId = scan.next();
		for(Hotel hotel : hotels){
			if(hotel.getId().equals(hotelId)){
				return bookRoom(hotel);
			}
		}
		System.out.println("Id not exits");
		return false;
	}


	public boolean bookRoom(Hotel hotel){
		while(true){
			
			System.out.print("Enter required Room count: ");
			int reqCount = scan.nextInt();
			int availCount = hotel.getRooms().get(0).getCount();
			if(reqCount>availCount){
				System.out.print("Sorry we don't have "+reqCount+" number of rooms");
				continue;
			}
			else{
				System.out.println("Enter check-in date(mm-dd-yyyy)");
				System.out.println("Enter month:");
				int month = scan.nextInt();
				System.out.println("Enter day:");
				int day=scan.nextInt();
				System.out.println("Enter year");
				int year=scan.nextInt();
				
				Date userCheckInDate=new Date(day,month,year);
				try {
					Validation_1.validateDate(userCheckInDate);
				} catch (MyCustomException e) {
					System.out.println(e.getMessage());
					continue;
				}
				
				System.out.println("Enter check-OUT date(mm-dd-yyyy)");
				System.out.println("Enter month:");
				int monthCOut = scan.nextInt();
				System.out.println("Enter day:");
				int dayCOut=scan.nextInt();
				System.out.println("Enter year");
				int yearCOut=scan.nextInt();
				
				Date userCheckOutDate=new Date(dayCOut,monthCOut,yearCOut);
				try {
					Validation_1.validateDate(userCheckOutDate);
				} catch (MyCustomException e) {
					System.out.println(e.getMessage());
					continue;
				}
				
				String day1=stringConversion(day,month,year);
				String day2=stringConversion(dayCOut,monthCOut,yearCOut);

				 try {
					LocalDate userCheckInDateFormatted=LocalDate.parse(day1);
					LocalDate userCheckOutDateFormatted=LocalDate.parse(day2);
					long noOfDays=ChronoUnit.DAYS.between(userCheckInDateFormatted, userCheckOutDateFormatted);
					System.out.println("No of days :"+noOfDays);
					System.out.println("Total price : RS:"+hotel.getRooms().get(0).getPrice()*reqCount * noOfDays);
					hotel.getRooms().get(0).setCount(availCount-reqCount);
				 }
				 catch(Exception e) {
					 System.out.println(e.getMessage());
				 }
				System.out.println("Please add your rating : ");
				double rating = scan.nextInt();
				double previousRating = hotel.getRatings();
				int ratingCount = hotel.getRateCount();
				double newRating = (previousRating*ratingCount+rating)/(ratingCount+1);
				hotel.setRatings(newRating);
				hotel.setRateCount(ratingCount+1);
				
				System.out.print("Add your review : ");
				scan.next();
				String review = scan.nextLine();
				hotel.addReview(review);
				return true;		
			}
		}
	}
	public static String stringConversion(int day,int month,int year) {
		return (String.format("%04d-%02d-%02d", year, month, day));
	}
}
	


