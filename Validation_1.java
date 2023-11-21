import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;;
public class Validation_1 {
	public static void validateCity(String city) throws MyCustomException{
		if(city.matches(".*\\d.*")){
			throw new MyCustomException("Invalid city name: city name contains digit in it.");
		}
	}
	public static void validateMobileNumber(String number) throws MyCustomException{
		if(number.matches(".*\\D.*")){
			throw new MyCustomException("Invalid Mobile Number: Mobile number contains Alphabets in it.");
		}
	}
	public static void validateRoomType(String roomType) throws MyCustomException{
		if(roomType.matches(".*\\d.*")){
			throw new MyCustomException("Invalid RoomType: It contains Digit in it.");
		}
		else if((!roomType.equalsIgnoreCase("ac")) && (!roomType.equalsIgnoreCase("non-ac"))){
			throw new MyCustomException("Invalid RoomType: Only (AC OR NON-AC).");
		}
	}
	public static void validateRating(double rating)throws MyCustomException{
		if(rating<=0 || rating >=5){
			throw new MyCustomException("Invalid Rating: Rating should be in the range (0 to 5).");
		}
	}
	public static void validatePrice(int price)throws MyCustomException{
		if(price<0 ){
			throw new MyCustomException("Invalid price:Price should not be negative.");
		}
		else if(price>=Integer.MAX_VALUE){
			throw new MyCustomException("You have reached the maximum amount.");
		}
		else if(Integer.toString(price).matches(".*\\D.*")){
			throw new MyCustomException("Invalid price: Price value contains digit in it.");
		}
	}
    public static void validateDate(Date userDate) throws MyCustomException {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String userDateString = String.format("%04d-%02d-%02d", userDate.getYear(), userDate.getMonth(), userDate.getDay());

        try {
            LocalDate localDate = LocalDate.parse(userDateString, formatter);
            
            if (userDate.getDay() < 0 || userDate.getMonth() < 0 || userDate.getYear() < 0) {
                throw new MyCustomException("Invalid Date: You have entered a negative value in the date");
            }

            if (localDate.isBefore(currentDate)) {
                throw new MyCustomException("Invalid Date: You have entered a past date.");
            }

            if (!isWithinNextFourMonths(localDate, currentDate)) {
                throw new MyCustomException("The date exceeds the next 4 months");
            }
        } catch (DateTimeParseException e) {
            throw new MyCustomException("Invalid date: The date is not present in the calendar");
        }
    }
    public static boolean isWithinNextFourMonths(LocalDate localDate, LocalDate currentDate) {
        LocalDate fourMonthsLater = currentDate.plusMonths(4);
        return !localDate.isBefore(currentDate) && localDate.isBefore(fourMonthsLater);
    }
}



