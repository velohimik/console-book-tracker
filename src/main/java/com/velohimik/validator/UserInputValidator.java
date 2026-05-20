package com.velohimik.validator;

import java.util.regex.Pattern;

public class UserInputValidator {

    private static final int EXPECTED_YEAR_LENGTH = 4;
    private static final String UUID_REGEX = "[0-9a-zA-Z]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}";
    public static final int MIN_COMMAND_KEY = 0;

    public boolean validateStringIsBlank(String text) {
        return text.isBlank();
    }

    public boolean validateUserInputIsCommandKey(int commandKey, int maxCommandKey) {
        return commandKey >= MIN_COMMAND_KEY && commandKey <= maxCommandKey;
    }

    public boolean validateYearIsFourDigits(String year) {
        for (Character letter : year.toCharArray()) {
            if (!Character.isDigit(letter)) {
                return false;
            }
        }

        if (year.charAt(0) == '0') return false;

        return year.length() == EXPECTED_YEAR_LENGTH;
    }

    public boolean validateIdIsUUID(String bookId) {
        Pattern uuid = Pattern.compile(UUID_REGEX);
        return uuid.matcher(bookId).find();
    }
}
