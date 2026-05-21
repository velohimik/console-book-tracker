package com.velohimik.validator;

import java.util.regex.Pattern;

public class UserInputValidator {

    private static final int EXPECTED_YEAR_LENGTH = 4;
    private static final String UUID_REGEX = "[0-9a-zA-Z]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}";
    public static final int MIN_COMMAND_KEY = 0;

    public boolean validateUserInputIsCommandKey(int commandKey, int maxCommandKey) {
        return commandKey >= MIN_COMMAND_KEY && commandKey <= maxCommandKey;
    }

    public boolean validatePublishedYear(String year) {
        for (Character c : year.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        if (isZeroInBeginningOf(year)) {
            return false;
        }

        return areFourDigitsIn(year);
    }

    private static boolean areFourDigitsIn(String year) {
        return year.length() == EXPECTED_YEAR_LENGTH;
    }

    private static boolean isZeroInBeginningOf(String year) {
        return year.isEmpty() || year.charAt(0) == '0';
    }

    public boolean validateIdHasUUIDFormat(String bookId) {
        Pattern uuid = Pattern.compile(UUID_REGEX);
        return uuid.matcher(bookId).find();
    }
}
