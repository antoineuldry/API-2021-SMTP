package model.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

    private String mailAddress;

    public Person(){}

    public Person(String mailAddress){
        this.mailAddress = mailAddress;
    }

    public void setMailAddress(String address){
        this.mailAddress = address;
    }

    public String getMailAddress(){
        return mailAddress;
    }

}
