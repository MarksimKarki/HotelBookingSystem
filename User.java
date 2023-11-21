import java.util.*;
abstract class UserDetail {
    private String name;
    private String number;
    private String password;
    
    public UserDetail(String name, String number, String password) {
        this.name = name;
        this.number = number;
        this.password = password;
    }
    public UserDetail() {
        name=null;
        number=null;
        password=null;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public abstract boolean isValidLogin(String number,String password,List<User> userList);
    public abstract boolean ValidateLogin(List<User> userList);
}

public class User extends UserDetail{
    Scanner scan = new Scanner(System.in);
    public User(){
        super();
    }
    public User(String name, String number, String password) {
        super(name, number, password);
    }

    @Override
    public boolean isValidLogin(String number,String password,List<User> userList){
        for(User user : userList){
            if(user.getNumber().equals(number) && user.getPassword().equals(password)) return true;
        }
        return false;
    }

    @Override
    public boolean ValidateLogin(List<User> userList){
        System.out.println("Enter registered mobile number:");
        String number = scan.next();
        System.out.println("Enter password:");
        String password = scan.next();
        return isValidLogin(number, password,userList);
    }
}