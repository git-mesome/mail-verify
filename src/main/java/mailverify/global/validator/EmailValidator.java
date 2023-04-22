package mailverify.global.validator;

import java.util.regex.Pattern;

public class EmailValidator {

    public static void validateEmail(String email) {

        String patternEmail = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";

        if (!Pattern.matches(patternEmail, email)) {
            throw new IllegalArgumentException("Invalid email address");
        }

    }

}
