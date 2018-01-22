package utils;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Created by Patryk on 2017-08-12.
 */
public class Validators {


    public static String validateEmail(String str) {
        boolean valid = EmailValidator.getInstance().isValid(str);
        if (valid) {
            return str;
        }
        return "";
    }

    public static String validateName(String str) {
        boolean valid = false;
        String regex = "[A-Z][a-zA-Z]*";
        if (str.matches(regex)) {
            return str;
        }
        return "";
    }

    //TO DO
    // another validators for fields in form... skipped right now


}
