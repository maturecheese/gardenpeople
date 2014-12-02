package gardenpeople.validator;

import gardenpeople.model.User;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mark-i5 on 28/11/2014.
 */
public class UserValidator extends Validator{

    protected static String model = "user details";

    private static final int MAX_EMAIL_LENGTH = 30;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final int MAX_PASSWORD_LENGTH = 20;
    private static final int MIN_PASSWORD_LENGTH = 4;
    private static final int MAX_USERNAME_LENGTH = 20;
    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MAX_FIRSTNAME_LENGTH = 20;
    private static final int MAX_LASTNAME_LENGTH = 20;


    private static final int MAX_HOUSE_LENGTH = 30;

    private static final int MAX_STREET_LENGTH = 30;

    private static final int MAX_POSTCODE_LENGTH = 10;


    public UserValidator(){
        super(model);
    }



    public void checkUserWithPasswords(User user){
        checkEmail(user.getEmail());
        checkPasswords(user.getPassword(), user.getRepeatedPassword());
        checkUsername(user.getUsername());
        checkFirstName(user.getFirstName());
        checkLastName(user.getLastName());
        checkHouse(user.getHouseNumberName());
        checkStreet(user.getStreet());
        checkPostcode(user.getPostcode());

    }
    public void checkUserWithoutPasswords(User user){
        checkEmail(user.getEmail());
        checkUsername(user.getUsername());
        checkFirstName(user.getFirstName());
        checkLastName(user.getLastName());
        checkHouse(user.getHouseNumberName());
        checkStreet(user.getStreet());
        checkPostcode(user.getPostcode());

    }

    public void checkEmail(String email){
        if (email == null){
            errors.add("email required");
            return;
        }
        if (email.length() > MAX_EMAIL_LENGTH){
            errors.add("email max size is " + MAX_EMAIL_LENGTH  +" characters");
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            errors.add("not a valid email address");
        }
     }

    public void checkPasswords( String password1, String password2){
        if(password1 == null){
            errors.add("password must be set");
            return;
        }
        if(password1.length() < MIN_PASSWORD_LENGTH  || password1.length() > MAX_PASSWORD_LENGTH) {
            errors.add("password must be between " + MIN_PASSWORD_LENGTH + " and "+ MAX_PASSWORD_LENGTH + " characters");

        }
        if( password2 == null ||!password1.equals(password2)){
            errors.add( "passwords do not match");
        }
    }

    public void checkUsername( String username){
        if (username == null){
            errors.add("username required");
            return;
        }
        if(username.length() < MIN_USERNAME_LENGTH  || username.length() > MAX_USERNAME_LENGTH) {
            errors.add("username must be between " + MIN_USERNAME_LENGTH + " and "+ MAX_USERNAME_LENGTH + " characters");

        }
    }

    public void checkFirstName( String name){
        if (name == null){

            return;
        }
        if( name.length() > MAX_FIRSTNAME_LENGTH) {
            errors.add("first name too long (max "+ MAX_FIRSTNAME_LENGTH + " characters)");

        }
    }
    public void checkLastName( String name){
        if (name == null){

            return;
        }
        if( name.length() > MAX_LASTNAME_LENGTH) {
            errors.add("last name too long (max "+ MAX_LASTNAME_LENGTH + " characters)");

        }
    }

    public void checkHouse( String house){
        if (house == null){

            return;
        }
        if( house.length() > MAX_HOUSE_LENGTH) {
            errors.add("house name too long (max "+ MAX_HOUSE_LENGTH + " characters)");

        }
    }

    public void checkStreet( String street){
        if (street == null){

            return;
        }
        if( street.length() > MAX_STREET_LENGTH) {
            errors.add("street name too long (max "+ MAX_STREET_LENGTH + " characters)");

        }
    }
    public void checkPostcode( String postcode){
        if(postcode == null){
            return;
        }
        if( postcode.length() > MAX_POSTCODE_LENGTH) {
            errors.add("postcode too long (max "+ MAX_POSTCODE_LENGTH + " characters)");

        }
    }



}
