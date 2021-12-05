package model.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

    private String firstName;
    private String lastName;
    private final String mailAddress;

    public Person(String firstName, String lastName, String mailAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mailAddress = mailAddress;
    }

    public Person(String mailAddress){
        this.mailAddress = mailAddress;
        Pattern pattern = Pattern.compile("(.*)\\.(.*)@");
        Matcher matcher = pattern.matcher(mailAddress);
        boolean found = matcher.find();
        if(found){
            this.firstName = matcher.group(1);
            firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1);
            this.lastName = matcher.group(2);
            lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
        }
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getMailAddress(){
        return mailAddress;
    }

}
