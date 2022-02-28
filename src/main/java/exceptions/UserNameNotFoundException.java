package exceptions;

public class UserNameNotFoundException extends RuntimeException{
    String message = "--------------------------------\nUser name already exist!!!\n--------------------------------";

    @Override
    public String getMessage() {
        return message;
    }

    public UserNameNotFoundException() {
    }
}
