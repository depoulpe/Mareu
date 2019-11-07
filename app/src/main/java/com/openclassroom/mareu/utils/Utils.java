package com.openclassroom.mareu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * check if  email address has valid format
     * @param email
     * @return
     */
    static public boolean emailValidator(String email)
    {
        boolean isValidEmail =false;
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        isValidEmail = matcher.matches();

        return isValidEmail;
    }
}
